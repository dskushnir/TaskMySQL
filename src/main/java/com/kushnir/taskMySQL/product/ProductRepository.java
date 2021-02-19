package com.kushnir.taskMySQL.product;

import com.kushnir.taskMySQL.product.dto.ProductQuantityOutputDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT new com.kushnir.taskMySQL.product.dto.ProductQuantityOutputDto(p.name,SUM(oE.quantity)) FROM Product AS p INNER JOIN OrderEntry AS oE ON p.id = oE.id.productId GROUP BY p.name ORDER BY SUM(oE.quantity) DESC")
    List<ProductQuantityOutputDto> quantityOrderedProduct();
}

