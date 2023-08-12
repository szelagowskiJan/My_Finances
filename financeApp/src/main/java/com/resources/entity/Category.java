package com.resources.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

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
