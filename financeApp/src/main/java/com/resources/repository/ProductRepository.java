package com.resources.repository;

import com.resources.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Praca
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductId(int productId);

    List<Product> findAll();
}
