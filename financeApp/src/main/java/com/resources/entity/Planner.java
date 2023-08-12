package com.resources.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author Praca
 */
@Entity
@Getter
@Setter
@Table(name = "PLANNER")
public class Planner {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "CATEGORY_ID")
    private int categoryId;

    @Column(name = "PLANNED_AMOUNT")
    private double scheduledAmount;

    @Column(name = "TITLE")
    private String title;
}
