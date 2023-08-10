package com.resources.service.impl;

import com.resources.dto.HistoryTradeDto;
import com.resources.entity.HistoryTrade;
import com.resources.repository.HistoryTradeRepository;
import com.resources.service.TransactionService;
import com.resources.service.config.ConfigService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public class TransactionServiceImpl extends ConfigService implements TransactionService {

    @Autowired
    HistoryTradeRepository historyTradeRepository;

    @Override
    public List<HistoryTradeDto> findAllTransactionForUser(String email) {

        return allTransactionForUser(email);
    }

    @Override
    public void saveTransaction(HistoryTrade historyTrade) {
        HistoryTrade transaction = new HistoryTrade();
        transaction.setId(historyTrade.getId());
        transaction.setUserId(historyTrade.getUserId());
        transaction.setTradeDt(historyTrade.getTradeDt());
        transaction.setAmount(historyTrade.getAmount());
        transaction.setProductId(historyTrade.getProductId());
        transaction.setExpense(getExpense(historyTrade));
        transaction.setTitle(historyTrade.getTitle());

        historyTradeRepository.save(transaction);
    }

    @Override
    public void removeTransaction(int historyTransactionId) {
        historyTradeRepository.deleteById(historyTransactionId);
    }

    @Override
    public HistoryTradeDto findById(int idTransaction) {
        HistoryTrade historyTradeToUpdate = historyTradeRepository.findById(idTransaction);
        return mapToHistoryTradeDto(historyTradeToUpdate, getProductsList());
    }
}
