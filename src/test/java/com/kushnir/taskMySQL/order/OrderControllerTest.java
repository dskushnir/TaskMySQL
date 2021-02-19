package com.kushnir.taskMySQL.order;

import com.kushnir.taskMySQL.TestRunner;
import com.kushnir.taskMySQL.orderEntry.OrderEntryService;

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
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    Clock clock;

    @Test
    public void shouldFindAllOrders() throws Exception {
        productRepository.save(new Product("Apple", 10, ProductStatusEnum.IN_STOCK, LocalDateTime.now(clock)));
        productRepository.save(new Product("Milk", 20, ProductStatusEnum.IN_STOCK, LocalDateTime.now(clock)));
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/ordersCreate")
                .contentType("application/json")
                .content(fromResource("order/create-order.json")))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", Matchers.containsString("http://localhost%3A8080/orders/")))
                .andReturn().getResponse();

        MockHttpServletResponse response1 = mockMvc.perform(MockMvcRequestBuilders.post("/ordersCreate")
                .contentType("application/json")
                .content(fromResource("order/create-order1.json")))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", Matchers.containsString("http://localhost%3A8080/orders/")))
                .andReturn().getResponse();
        var allOrders = orderService.findAllOrders();
        Assertions.assertThat(allOrders.get(0).getProductsTotalPrice()).isEqualTo(100);
        Assertions.assertThat(allOrders.get(0).getProductName()).isEqualTo("Apple");
        Assertions.assertThat(allOrders.get(0).getProductsQuantity()).isEqualTo(10);
        Assertions.assertThat(allOrders.get(0).getOrderCreatedDate()).isEqualTo("2021-01-01 14:00");

        Assertions.assertThat(allOrders.get(1).getProductsTotalPrice()).isEqualTo(400);
        Assertions.assertThat(allOrders.get(1).getProductName()).isEqualTo("Milk");
        Assertions.assertThat(allOrders.get(1).getProductsQuantity()).isEqualTo(20);
        Assertions.assertThat(allOrders.get(1).getOrderCreatedDate()).isEqualTo("2021-01-01 14:00");

        Assertions.assertThat(allOrders.get(2).getProductsTotalPrice()).isEqualTo(10);
        Assertions.assertThat(allOrders.get(2).getProductName()).isEqualTo("Apple");
        Assertions.assertThat(allOrders.get(2).getProductsQuantity()).isEqualTo(1);
        Assertions.assertThat(allOrders.get(2).getOrderCreatedDate()).isEqualTo("2021-01-01 14:00");

        Assertions.assertThat(allOrders.get(3).getProductsTotalPrice()).isEqualTo(40);
        Assertions.assertThat(allOrders.get(3).getProductName()).isEqualTo("Milk");
        Assertions.assertThat(allOrders.get(3).getProductsQuantity()).isEqualTo(2);
        Assertions.assertThat(allOrders.get(3).getOrderCreatedDate()).isEqualTo("2021-01-01 14:00");

    }

    @Test
    public void shouldFindOrderedProductByOrderId() throws Exception {
        productRepository.save(new Product("Apple", 10, ProductStatusEnum.IN_STOCK, LocalDateTime.now(clock)));
        productRepository.save(new Product("Milk", 20, ProductStatusEnum.IN_STOCK, LocalDateTime.now(clock)));
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/ordersCreate")
                .contentType("application/json")
                .content(fromResource("order/create-order.json")))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", Matchers.containsString("http://localhost%3A8080/orders/")))
                .andReturn().getResponse();
        Integer id = Integer.parseInt(response.getHeader("location").replace("http://localhost%3A8080/orders/", ""));
        var orderById = orderService.findOrderedProductByOrderId(id);
        Assertions.assertThat(orderById.get(0).getProductsTotalPrice()).isEqualTo(100);
        Assertions.assertThat(orderById.get(0).getProductName()).isEqualTo("Apple");
        Assertions.assertThat(orderById.get(0).getProductsQuantity()).isEqualTo(10);
        Assertions.assertThat(orderById.get(0).getOrderCreatedDate()).isEqualTo("2021-01-01 14:00");

        Assertions.assertThat(orderById.get(1).getProductsTotalPrice()).isEqualTo(400);
        Assertions.assertThat(orderById.get(1).getProductName()).isEqualTo("Milk");
        Assertions.assertThat(orderById.get(1).getProductsQuantity()).isEqualTo(20);
        Assertions.assertThat(orderById.get(1).getOrderCreatedDate()).isEqualTo("2021-01-01 14:00");
    }

    @Test
    public void shouldCreateOrder() throws Exception {
        productRepository.save(new Product("Apple", 10, ProductStatusEnum.IN_STOCK, LocalDateTime.now(clock)));
        productRepository.save(new Product("Milk", 20, ProductStatusEnum.IN_STOCK, LocalDateTime.now(clock)));
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/ordersCreate")
                .contentType("application/json")
                .content(fromResource("order/create-order.json")))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", Matchers.containsString("http://localhost%3A8080/orders/")))
                .andReturn().getResponse();

        Integer id = Integer.parseInt(response.getHeader("location").replace("http://localhost%3A8080/orders/", ""));
        Assertions.assertThat(orderRepository.findById(id)).isPresent();
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
