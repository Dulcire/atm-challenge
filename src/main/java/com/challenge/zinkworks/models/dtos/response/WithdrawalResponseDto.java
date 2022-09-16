package com.challenge.zinkworks.models.dtos.response;

import com.challenge.zinkworks.models.dtos.BillDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class to map the Withdrawal data to be showed.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalResponseDto {
    private List<BillDto> banknotes;
    private Long balance;
}
