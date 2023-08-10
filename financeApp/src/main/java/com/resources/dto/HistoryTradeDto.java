package com.resources.dto;

import com.resources.entity.Product;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Praca
 */
@Getter
@Setter
@RequiredArgsConstructor
public class HistoryTradeDto {
    private int Id;

    private int userId;
    
    private double amount;
    
    private int expense;

    private int productId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date tradeDt;
    
    private String title;
    
    private Product product;
}
