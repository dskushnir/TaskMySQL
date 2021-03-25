package com.kushnir.taskMySQL.product.repository;

import com.kushnir.taskMySQL.product.dto.ProductQuantityOutputDto;
import com.kushnir.taskMySQL.product.entity.Product;


import java.util.List;
import java.util.Optional;

public interface ProductBaseRepository {
    Optional<Product> findById(Integer id);

    List<Product> findAll();

    List<ProductQuantityOutputDto> quantityOrderedProduct();

    Product save(Product product);

    void deleteById(Integer id);

    void deleteAll();
}
