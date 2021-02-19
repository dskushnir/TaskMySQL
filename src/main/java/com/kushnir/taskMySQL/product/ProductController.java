package com.kushnir.taskMySQL.product;

import com.kushnir.taskMySQL.product.dto.*;
import com.kushnir.taskMySQL.sequrity.TaskMysqlUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final ProductDtoConverter productDtoConverter;
    private final ProductModelConverter productModelConverter;
    private final UriComponentsBuilder uriBuilder;
    private Clock clock;

    public ProductController(ProductService productService, ProductDtoConverter productDtoConverter, ProductModelConverter productModelConverter, Clock clock) {
        this.productService = productService;
        this.productDtoConverter = productDtoConverter;
        this.productModelConverter = productModelConverter;
        uriBuilder = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost:8080")
                .path("/products/{id}");
        this.clock = clock;

    }

    @GetMapping("/productsAll")
    public List<ProductOutputDto> findAllProducts() {
        var products = productService.findAll();
        if (products.size() == 0) {
            throw new ProductNotFoundException();
        }
        return products.stream()
                .map(productModelConverter::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/products/quantity")
    public List<ProductQuantityOutputDto> quantityOrderedProduct() {
        var orderedProducts = productService.quantityOrderedProduct();
        if (orderedProducts.size() == 0) {
            throw new ProductNotFoundException();
        }
        return orderedProducts;
    }


    @PostMapping("/productsCreate")
    public ResponseEntity<Object> createProduct(@RequestBody ProductInputDto productInputDto) {
        var created = productService.createProduct(productDtoConverter.toModel(productInputDto, LocalDateTime.now(clock)));
        return ResponseEntity.created(uriBuilder.build(created.getId())).build();
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasAuthority('delete:product')")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id, @AuthenticationPrincipal TaskMysqlUser userDetails) {
        var product = productService.findById(id);
        if (product.isPresent()) {
            productService.delete(id);
            log.info("Current user details: " + userDetails);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            throw new NoSuchProductException();

        }
    }

    @DeleteMapping("/products")
    @PreAuthorize("hasAuthority('deleteAll:products')")
    public ResponseEntity<Object> deleteAllProducts() {
        var products = productService.findAll();
        if (products.size() == 0) {
            throw new ProductNotFoundException();
        }
        productService.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
