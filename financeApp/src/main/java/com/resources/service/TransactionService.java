package com.resources.service;

import com.resources.dto.HistoryTradeDto;
import com.resources.entity.HistoryTrade;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public interface TransactionService {

    void removeTransaction(int id);

    void saveTransaction(HistoryTrade historyTrade);

    List<HistoryTradeDto> findAllTransactionForUser(String email);

    HistoryTradeDto findById(int idTransaction);
}
