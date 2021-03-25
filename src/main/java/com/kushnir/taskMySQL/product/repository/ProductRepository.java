package com.kushnir.taskMySQL.product.repository;

import com.kushnir.taskMySQL.product.entity.Product;
import com.kushnir.taskMySQL.product.dto.ProductQuantityOutputDto;


import java.util.List;
import java.util.Optional;


public class ProductRepository implements ProductBaseRepository {
    ProductConnectRepository productConnectRepository;

    @Override
    public Optional<Product> findById(Integer id) {
        return productConnectRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productConnectRepository.findAll();
    }

    @Override
    public List<ProductQuantityOutputDto> quantityOrderedProduct() {
        return productConnectRepository.quantityOrderedProduct();
    }

    @Override
    public Product save(Product product) {
        return productConnectRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productConnectRepository.deleteById(id);

    }

    @Override
    public void deleteAll() {
        productConnectRepository.deleteAll();

    }
}

