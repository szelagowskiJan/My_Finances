package com.resources.service;

import com.resources.dto.PlannerDto;
import com.resources.entity.Planner;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Praca
 */
@Service
public interface PlannerService {

    void savePlanner(Planner planner);

    List<PlannerDto> findAll();
}
