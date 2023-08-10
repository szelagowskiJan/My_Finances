package com.resources.repository;

import com.resources.entity.Planner;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Praca
 */
@Repository
public interface PlannerRepository extends JpaRepository<Planner, Long> {

    Planner save(Planner planner);

    List<Planner> findAllByUserId(int userId);
}
