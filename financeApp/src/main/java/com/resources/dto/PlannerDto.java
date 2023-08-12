package com.resources.dto;

import lombok.*;

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
