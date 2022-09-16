package com.challenge.zinkworks.services;

import com.challenge.zinkworks.models.dtos.AccountDto;
import com.challenge.zinkworks.models.dtos.response.AccountResponseDto;

public interface AccountService {

    AccountResponseDto getBalanceAccount(String pin);

    AccountDto getAccount(String pin);

    AccountDto save(final AccountDto accountDto);
}
