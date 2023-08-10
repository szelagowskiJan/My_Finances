package com.resources.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
