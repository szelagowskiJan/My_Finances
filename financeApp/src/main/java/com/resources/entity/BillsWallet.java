package com.resources.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Praca
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="PLANNED_EXPENSES")
public class BillsWallet {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name="USER_ID")
    private int userId;
   
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_FROM")
    private Date dtFrom;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="DT_TO")
    private Date dtTo;
    
    @Column(name="AMOUNT", nullable=false)
    private double amount;
    
    @Column(name="TITLE")
    private String title;
}
