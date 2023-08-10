package com.resources.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Praca
 */
@Setter
@Getter
@RequiredArgsConstructor
@Entity
@Table(name="CATEGORY")
public class Category {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "NAME", nullable=false, unique=true)
    private String categoryName;

    @OneToMany()
    @JoinColumn(name="CATEGORY_ID",referencedColumnName="CATEGORY_ID")
    private List<Product> product = new ArrayList<>();
}
