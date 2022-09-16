package com.challenge.zinkworks.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class to manage Users with accounts.
 */
@Data
@AllArgsConstructor
public class DispenseDto {

    private Long amount;
    private BillDto dispense;
}
