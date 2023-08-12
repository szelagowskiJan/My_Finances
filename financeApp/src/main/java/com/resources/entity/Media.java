package com.resources.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

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
