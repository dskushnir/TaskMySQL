package com.kushnir.taskMySQL.product.repository;

import com.kushnir.taskMySQL.product.dto.ProductQuantityOutputDto;
import com.kushnir.taskMySQL.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductConnectRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT new com.kushnir.taskMySQL.product.dto.ProductQuantityOutputDto(p.name,SUM(oE.quantity)) FROM Product AS p INNER JOIN OrderEntry AS oE ON p.id = oE.id.productId GROUP BY p.name ORDER BY SUM(oE.quantity) DESC")
    List<ProductQuantityOutputDto> quantityOrderedProduct();
}
