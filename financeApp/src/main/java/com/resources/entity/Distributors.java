package com.resources.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author Praca
 */
@Getter
@Setter
@Entity
@Table(name = "DISTRIBUTORS")
public class Distributors {

    @Id
    @Column(name = "DISTRIBUTORS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int distributorsId;

    @Column(name = "DISTRIBUTORS_NAME")
    private String distributorsName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "MEDIA_ID", referencedColumnName = "MEDIA_ID")
    private Media media;
}
