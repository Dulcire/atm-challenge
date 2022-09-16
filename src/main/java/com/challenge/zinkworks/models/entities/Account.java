package com.challenge.zinkworks.models.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Class to manage Account database information.
 */
@Entity
@Table
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column
    private String accountNumber;

    @Column
    private Long balance;

    @Column
    private Long overdraft;

    @Column
    private Long maximun;

    @Column
    private String pin;
}
