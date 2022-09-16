package com.challenge.zinkworks.services.impl;

import com.challenge.zinkworks.exceptions.ATMException;
import com.challenge.zinkworks.models.dtos.AccountDto;
import com.challenge.zinkworks.models.dtos.response.AccountResponseDto;
import com.challenge.zinkworks.models.entities.Account;
import com.challenge.zinkworks.models.enums.ExceptionMessage;
import com.challenge.zinkworks.repositories.AccountRepository;
import com.challenge.zinkworks.services.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Account service to calculate all algorithms related to an account request.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Return the balance and maximun withdrawal related to the account pin.
     *
     * @param pin Pin access to the account.
     * @return
     */
    @Override
    public AccountResponseDto getBalanceAccount(final String pin) {
        final Account account = Optional.ofNullable(accountRepository.findByPin(pin))
                .orElseThrow(() -> new ATMException(ExceptionMessage.INCORRECT_PIN.getMessage(),
                        ExceptionMessage.INCORRECT_PIN.getMessage(),
                        ExceptionMessage.INCORRECT_PIN_CODE.getMessage()));
        final AccountDto accountDto = convertToDto(account);
        return new AccountResponseDto(accountDto.getBalance(), accountDto.getMaximun());
    }

    /**
     * Method to get an Account with the pin access.
     *
     * @param pin Pin access to the account.
     * @return
     */
    @Override
    public AccountDto getAccount(final String pin) {
        final Account account = Optional.ofNullable(accountRepository.findByPin(pin))
                .orElseThrow(() -> new ATMException(ExceptionMessage.INCORRECT_PIN.getMessage(),
                        ExceptionMessage.INCORRECT_PIN.getMessage(), ExceptionMessage.INCORRECT_PIN_CODE.getMessage()));
        return convertToDto(account);
    }

    /**
     * Save an account in database.
     *
     * @param accountDto account converted into DTO.
     * @return
     */
    @Override
    public AccountDto save(final AccountDto accountDto) {
        return convertToDto(accountRepository.save(convertToEntity(accountDto)));
    }

    private AccountDto convertToDto(final Account account) {
        return modelMapper.map(account, AccountDto.class);
    }

    private Account convertToEntity(final AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
    }
}
