package com.resources.service.impl;

import com.resources.dto.HistoryFeesDto;
import com.resources.entity.Distributors;
import com.resources.entity.HistoryFees;
import com.resources.repository.DistributorsRepository;
import com.resources.repository.HistoryFeesRepository;
import com.resources.service.HistoryFeesService;
import com.resources.service.config.ConfigService;
import static com.resources.service.impl.FeesStatusEnum.*;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public class HistoryFeesServiceImpl extends ConfigService implements HistoryFeesService {

    @Autowired
    private HistoryFeesRepository historyFeesRepository;

    @Autowired
    private DistributorsRepository distributorsRepository;

    private HistoryFees historyFees;

    private List<Distributors> distributorsList;

    @Override
    public List<HistoryFeesDto> findAllHistoryFees() {
        distributorsList = distributorsRepository.findAll();
        List<HistoryFees> historyFeesList = historyFeesRepository.findAllByUserId(getUserId(getUserEmail()));
        List<HistoryFeesDto> historyFeesDto = historyFeesList.stream().map(mapper -> mapperToHistoryFeesDto(mapper)).toList();
        return historyFeesDto;
    }

    @Override
    public HistoryFees addNewHistoryFees(HistoryFees historyFees) {
        return historyFeesRepository.save(historyFees);
    }

    @Override
    public void removeHistoryFees(int id) {
        historyFeesRepository.deleteById(id);
    }

    private HistoryFeesDto mapperToHistoryFeesDto(HistoryFees historyFees) {
        this.historyFees = historyFees;
        HistoryFeesDto HistoryFeesDto = new HistoryFeesDto();
        HistoryFeesDto.setId(historyFees.getId());
        HistoryFeesDto.setUserId(historyFees.getUserId());
        HistoryFeesDto.setDistributorsId(historyFees.getDistributorsId());
        HistoryFeesDto.setFeesDt(historyFees.getFeesDt());
        HistoryFeesDto.setAmountFees(historyFees.getAmountFees());
        HistoryFeesDto.setStatus(getStatus());
        HistoryFeesDto.setTitle(historyFees.getTitle());
        HistoryFeesDto.setDistributors(getDistributors(historyFees));
        return HistoryFeesDto;
    }

    private String getStatus() {
        if (isPaidInDate(Calendar.YEAR) && isPaidInDate(Calendar.MONTH) && isPaidInDate(Calendar.DAY_OF_MONTH)) {
            return UNPAID.name();
        }
        return PAID.name();
    }

    private boolean isPaidInDate(int typeDateType) {
        return compareDate(typeDateType) || feesDateIsAfterRightNow(typeDateType);
    }

    private boolean compareDate(int typeDateType) {
        return getFeesDate(typeDateType) == getRightNowDate(typeDateType);
    }

    private boolean feesDateIsAfterRightNow(int typeDateType) {
        return getFeesDate(typeDateType) >= getRightNowDate(typeDateType);
    }

    private int getFeesDate(int typeDateType) {
        return dateToCalendar(historyFees.getFeesDt()).get(typeDateType);
    }

    private int getRightNowDate(int typeDateType) {
        return rightNow.get(typeDateType);
    }

    private Distributors getDistributors(HistoryFees historyFees) {
        for (Distributors distributors : distributorsList) {
            if (distributors.getDistributorsId() == historyFees.getDistributorsId()) {
                return distributors;
            }
        }
        return distributorsList.get(historyFees.getDistributorsId());
    }
}