package com.kushnir.taskMySQL;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class Starter implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {

        log.info("Application has started. " +
                "There are all possible ways how to interact with application: " +
                "1. Create Product :curl --location --request POST 'http://localhost:8080/productsCreate' " +
                "--header 'Content-Type: application/json'" +
                "--data-raw '{name: Apple," +
                "  price : 20," +
                "  status: IN_STOCK }.  " +
                "2. Create Order: curl --location --request POST 'http://localhost:8080/ordersCreate' " +
                "--header 'Content-Type: application/json'" +
                "--data-raw '{ id: 2," +
                "  status: NEW ," +
                "  orderEntryList: [{productId:1,quantity:1},{productId:2,quantity:3}]}.  '" +
                "3. Update Product: curl --location --request PUT 'http://localhost:8080/ordersEntityUpdate'" +
                "--header 'Content-Type: application/json'" +
                "--data-raw '{orderId :2," +
                "productId :1," +
                "quantity: 20}.  " +
                "4. List all products: curl --location --request GET 'http://localhost:8080/productsAll'  " +
                "5. List all products, which have been  ordered at least once: curl --location --request GET 'http://localhost:8080/products/quantity'  " +
                "6. Order by id: curl --location --request GET 'http://localhost:8080//ordersById/2'  " +
                "7. List of all orders using previous view: curl --location --request GET 'http://localhost:8080/orders'  " +
                "8. Remove Product by id: curl --location --request DELETE 'http://localhost:8080/products/2' " +
                "--header 'Authorization: Basic YWRtaW46MTIz' , Postman : admin, password 123. " +
                "9. Remove all Products: curl --location --request DELETE 'http://localhost:8080/products'" +
                "--header 'Authorization: Basic YWRtaW46MTIz', Postman : admin, password 123. " +
                "You could use Postman too");
    }
}
