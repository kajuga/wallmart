package com.fedorov.wallmart.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(schema = "wallmart", name = "producer")
@Getter
@Setter
@ToString
public class Producer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

}
