package com.resources.dto;

import com.resources.entity.Distributors;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Praca
 */
@Getter
@Setter
public class HistoryFeesDto {
    
    private int id;
    
    private int userId;
    
    private double amountFees;
    
    private int distributorsId;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date feesDt;
    
    private String status;
    
    private String title;
    
    private Distributors distributors;
}
