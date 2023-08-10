package com.resources.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
