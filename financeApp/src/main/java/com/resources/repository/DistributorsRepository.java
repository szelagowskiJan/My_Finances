package com.resources.repository;

import com.resources.entity.Distributors;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Praca
 */
@Repository
public interface DistributorsRepository extends JpaRepository<Distributors, Long> {

    Distributors save(Distributors distributors);

    List<Distributors> findAll();
}
