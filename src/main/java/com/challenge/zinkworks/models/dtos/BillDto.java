package com.challenge.zinkworks.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class to manage all bills.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    private Long billId;
    private Long bill;
    private Long quantity;


}
