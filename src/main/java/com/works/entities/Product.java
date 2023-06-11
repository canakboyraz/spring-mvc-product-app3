package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long pid;
    private String title;
    private String detail;
    private Integer price;
    private Long cid;
}
