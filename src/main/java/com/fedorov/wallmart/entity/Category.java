package com.fedorov.wallmart.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(schema = "wallmart", name = "category")
@Getter
@Setter
@ToString
public class Category extends BaseEntity{

    @Column(name = "name")
    private String name;
}