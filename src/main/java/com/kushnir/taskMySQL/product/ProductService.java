package com.kushnir.taskMySQL.product;

import com.kushnir.taskMySQL.product.dto.ProductQuantityOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<ProductQuantityOutputDto> quantityOrderedProduct() {
        return productRepository.quantityOrderedProduct();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }
}
