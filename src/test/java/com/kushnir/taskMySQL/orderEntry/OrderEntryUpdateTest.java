package com.kushnir.taskMySQL.orderEntry;

import com.kushnir.taskMySQL.TestRunner;
import com.kushnir.taskMySQL.product.Product;
import com.kushnir.taskMySQL.product.ProductRepository;
import com.kushnir.taskMySQL.product.ProductStatusEnum;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.time.Clock;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@TestRunner
public class OrderEntryUpdateTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderEntryService orderEntryService;
    @Autowired
    Clock clock;

    @Test
    public void shouldUpdateOrderEntry() throws Exception {
        var product1 = productRepository.save(new Product("Apple", 10, ProductStatusEnum.IN_STOCK, LocalDateTime.now(clock)));
        productRepository.save(new Product("Milk", 20, ProductStatusEnum.IN_STOCK, LocalDateTime.now(clock)));
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/ordersCreate")
                .contentType("application/json")
                .content(fromResource("order/create-order.json")))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", Matchers.containsString("http://localhost%3A8080/orders/")))
                .andReturn().getResponse();
        Integer id = Integer.parseInt(response.getHeader("location").replace("http://localhost%3A8080/orders/", ""));
        orderEntryService.orderEntryUpdate(id, product1.getId(), 40);
        Assertions.assertThat(orderEntryService.findById(id, product1.getId()).get().getQuantity()).isEqualTo(40);
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

