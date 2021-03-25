package com.kushnir.taskMySQL.product.service;

import com.kushnir.taskMySQL.product.entity.Product;
import com.kushnir.taskMySQL.product.dto.ProductQuantityOutputDto;
import com.kushnir.taskMySQL.product.repository.ProductBaseRepository;
import lombok.AllArgsConstructor;


import java.util.List;
import java.util.Optional;


@AllArgsConstructor

public class ProductService implements ProductBaseService {
    private final ProductBaseRepository productBaseRepository;

    @Override
    public Optional<Product> findById(Integer id) {
        return productBaseRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productBaseRepository.findAll();
    }

    @Override
    public List<ProductQuantityOutputDto> quantityOrderedProduct() {
        return productBaseRepository.quantityOrderedProduct();
    }

    @Override
    public Product createProduct(Product product) {
        return productBaseRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productBaseRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        productBaseRepository.deleteAll();
    }
}
