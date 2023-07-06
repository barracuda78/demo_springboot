package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "country")
public class Country {

    @Id
    @Column
    private Long id;

    @Column
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;

}
