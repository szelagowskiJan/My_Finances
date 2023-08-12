package com.resources.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author Praca
 */
    
@Setter
@Getter
@RequiredArgsConstructor
@Entity
@Table(name="PRODUCT")
public class Product {

    public Product(int productId, String productName)
    {
        this.productId = productId;
        this.productName = productName;
    }
    
    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
   
    @Column(name = "PRODUCT_NAME", nullable=false, unique=true)
    private String productName;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="CATEGORY_ID",referencedColumnName="CATEGORY_ID")
    private Category Category;
}

