package com.resources.repository;

import com.resources.entity.HistoryFees;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Praca
 */
@Repository
public interface HistoryFeesRepository extends JpaRepository<HistoryFees, Long> {

    HistoryFees save(HistoryFees historyFees);

    void deleteById(int id);

    List<HistoryFees> findAllByUserId(int userId);
}
