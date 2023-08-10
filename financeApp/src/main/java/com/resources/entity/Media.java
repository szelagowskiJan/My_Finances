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
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name= "MEDIA")
public class Media {
  
    @Id
    @Column(name = "MEDIA_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "MEDIA_NAME")
    private String mediaName;
    
    @OneToMany()
    @JoinColumn(name="MEDIA_ID",referencedColumnName="MEDIA_ID")
    private List<Distributors> distributors = new ArrayList();
}
