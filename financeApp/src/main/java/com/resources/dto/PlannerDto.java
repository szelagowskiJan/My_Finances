package com.resources.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Praca
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PlannerDto {
    
    private int id;
    
    private int userId;
    
    private int categoryId;
    
    private double scheduledAmount;
    
    private String title;
    
    private CategoryDto category;
    
    private double realExpenses;
    
    private double difference;
    
    private String status;
}
