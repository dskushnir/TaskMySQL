package com.kushnir.taskMySQL.product;

import com.kushnir.taskMySQL.TestRunner;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@TestRunner
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepository productRepository;

  /*  @After
    public void cleanup() {
        productRepository.deleteAll();
    }*/

    @Test

    public void shouldFindAllProducts() throws Exception {
        productRepository.save(new Product("Apple", 10, ProductStatusEnum.IN_STOCK, LocalDateTime.now()));
        productRepository.save(new Product("Milk", 20, ProductStatusEnum.IN_STOCK, LocalDateTime.now()));
        mockMvc.perform(MockMvcRequestBuilders.get("/productsAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Apple")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Milk")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price", Matchers.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("IN_STOCK")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].status", Matchers.is("IN_STOCK")));
    }


    @Test
    public void shouldCreateProductNullPrice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/productsCreate")
                .contentType("application/json")
                .content(fromResource("product/create-product-null-price.json")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldCreateProductNullName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/productsCreate")
                .contentType("application/json")
                .content(fromResource("product/create-product-null-name.json")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldCreateProductNullStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/productsCreate")
                .contentType("application/json")
                .content(fromResource("product/create-product-null-status.json")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/productsCreate")
                .contentType("application/json")
                .content(fromResource("product/create-product.json")))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", Matchers.containsString("http://localhost%3A8080/products/")))
                .andReturn().getResponse();

        Integer id = Integer.parseInt(response.getHeader("location").replace("http://localhost%3A8080/products/", ""));

        Assertions.assertThat(productRepository.findById(id)).isPresent();
    }

    @Test
    public void shouldDeleteProduct() throws Exception {
        Integer id = productRepository.save(new Product("Apple", 10, ProductStatusEnum.IN_STOCK, LocalDateTime.now())).getId();
        productRepository.save(new Product("Milk", 20, ProductStatusEnum.IN_STOCK, LocalDateTime.now()));
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW4gMTIz"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void shouldDeleteAllProducts() throws Exception {
        productRepository.save(new Product("Apple", 10, ProductStatusEnum.IN_STOCK, LocalDateTime.now()));
        productRepository.save(new Product("Milk", 20, ProductStatusEnum.IN_STOCK, LocalDateTime.now()));
        mockMvc.perform(MockMvcRequestBuilders.delete("/products"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    public String fromResource(String path) {
        try {
            File file = ResourceUtils.getFile("classpath:" + path);
            return Files.readString(file.toPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
