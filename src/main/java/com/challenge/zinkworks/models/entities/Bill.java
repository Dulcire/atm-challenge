package com.challenge.zinkworks.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @Column
    private Long bill;

    @Column
    private Long quantity;


}
