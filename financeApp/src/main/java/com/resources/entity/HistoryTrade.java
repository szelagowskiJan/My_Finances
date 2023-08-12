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
@NoArgsConstructor
@Entity
@Table(name = "HISTORY_TRADE")
@Data
public class HistoryTrade {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "AMOUNT", nullable = false)
    private double amount;

    @Column(name = "EXPENSE", nullable = false)
    private int expense;

    @Column(name = "PRODUCT_ID", nullable = false)
    private int productId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "TRADE_DT", nullable = false)
    private Date tradeDt;

    @Column(name = "TITLE")
    private String title;
}
