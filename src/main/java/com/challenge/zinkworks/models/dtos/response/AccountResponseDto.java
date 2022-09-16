package com.challenge.zinkworks.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class to map the Account data from database.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {

    private Long balance;
    private Long maximun;
}
