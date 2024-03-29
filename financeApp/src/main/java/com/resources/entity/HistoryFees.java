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
@Entity
@Table(name = "HISTORY_FEES")
public class HistoryFees {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "AMOUNT")
    private double amountFees;

    @Column(name = "DISTRIBUTORS_ID")
    private int distributorsId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "FEES_DT")
    private Date feesDt;

    @Column(name = "TITLE")
    private String title;
}
