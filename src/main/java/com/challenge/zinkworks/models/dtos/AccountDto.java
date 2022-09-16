package com.challenge.zinkworks.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class to map the Account databes information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private String accountNumber;
    private Long accountId;
    private Long balance;
    private Long overdraft;
    private Long maximun;
    private String pin;

}
