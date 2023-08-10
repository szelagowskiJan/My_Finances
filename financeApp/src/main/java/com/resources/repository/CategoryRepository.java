package com.resources.repository;

import com.resources.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Praca
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAll();
}
