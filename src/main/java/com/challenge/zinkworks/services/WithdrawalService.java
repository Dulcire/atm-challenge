package com.challenge.zinkworks.services;

import com.challenge.zinkworks.models.dtos.BillDto;
import com.challenge.zinkworks.models.dtos.DispenseDto;
import com.challenge.zinkworks.models.dtos.response.WithdrawalResponseDto;

import java.util.List;

public interface WithdrawalService {

    List<BillDto> withdrawalBills(final Long amount, List<BillDto> atmBills);

    List<BillDto> validationWithdraw(final Long amount, final List<BillDto> withdrawal);

    void updateATM(final List<BillDto> withdrawal, List<BillDto> atmBills);

    WithdrawalResponseDto withdrawal(final String pin, Long amount);

    DispenseDto withdrawlInfo(final Long amount, final BillDto bill);


}
