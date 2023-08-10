package com.resources.repository;

import com.resources.entity.HistoryTrade;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Praca
 */
@Repository
public interface HistoryTradeRepository extends JpaRepository<HistoryTrade, Long> {

    @Transactional
    void deleteById(int historyTradeId);

    List<HistoryTrade> findByUserId(int userId);

    HistoryTrade findById(int idTransaction);

    HistoryTrade save(HistoryTrade historyTrade);
}
