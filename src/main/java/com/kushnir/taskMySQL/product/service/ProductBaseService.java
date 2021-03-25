package com.kushnir.taskMySQL.product.service;

import com.kushnir.taskMySQL.product.dto.ProductQuantityOutputDto;
import com.kushnir.taskMySQL.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductBaseService {
    Optional<Product> findById(Integer id);

    List<Product> findAll();

    List<ProductQuantityOutputDto> quantityOrderedProduct();

    Product createProduct(Product product);

    void delete(Integer id);

    void deleteAll();

}
