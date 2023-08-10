package com.resources.service;

import com.resources.dto.HistoryFeesDto;
import com.resources.entity.HistoryFees;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public interface HistoryFeesService {

    List<HistoryFeesDto> findAllHistoryFees();

    HistoryFees addNewHistoryFees(HistoryFees historyFees);

    void removeHistoryFees(int id);
}
