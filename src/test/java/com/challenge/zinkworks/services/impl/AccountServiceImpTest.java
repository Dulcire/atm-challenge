package com.challenge.zinkworks.services.impl;

import com.challenge.zinkworks.exceptions.ATMException;
import com.challenge.zinkworks.models.dtos.AccountDto;
import com.challenge.zinkworks.models.dtos.response.AccountResponseDto;
import com.challenge.zinkworks.models.entities.Account;
import com.challenge.zinkworks.models.enums.ExceptionMessage;
import com.challenge.zinkworks.repositories.AccountRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceImpTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Autowired
    private AccountServiceImpl accountService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void getBalanceAccountTest() {
        final String pin = "211091";
        Mockito.when(accountRepository.findByPin(pin)).thenReturn(accountGenerator());
        Mockito.when(modelMapper.map(accountGenerator(), AccountDto.class)).thenReturn(accountDtoGenerator());
        Assert.assertEquals(accountService.getBalanceAccount(pin),new AccountResponseDto(1300L,500L));

    }

    @Test
    public void getBalanceAccountIncorrectPinTest() {
        exceptionRule.expect(ATMException.class);
        exceptionRule.expectMessage(ExceptionMessage.INCORRECT_PIN.getMessage());
        final String pin = "211091";
        Mockito.when(accountRepository.findByPin(pin)).thenReturn(null);
        accountService.getBalanceAccount(pin);
    }

    @Test
    public void getAccountTest() {
        final String pin = "211091";
        Mockito.when(accountRepository.findByPin(pin)).thenReturn(accountGenerator());
        Mockito.when(modelMapper.map(accountGenerator(), AccountDto.class)).thenReturn(accountDtoGenerator());
        Assert.assertEquals(accountService.getAccount(pin), accountDtoGenerator());
    }

    @Test
    public void getAccountIncorrectPinTest() {
        exceptionRule.expect(ATMException.class);
        exceptionRule.expectMessage(ExceptionMessage.INCORRECT_PIN.getMessage());
        final String pin = "211091";
        Mockito.when(accountRepository.findByPin(pin)).thenReturn(null);
        accountService.getAccount(pin);
    }

    @Test
    public void saveTest() {
        AccountDto expected = accountDtoGenerator();
        expected.setAccountId(1L);
        Mockito.when(modelMapper.map(accountDtoGenerator(), Account.class)).thenReturn(accountGenerator());
        Mockito.when(modelMapper.map(accountGenerator(), AccountDto.class)).thenReturn(accountDtoGenerator());
        Mockito.when(accountRepository.save(accountGenerator())).thenReturn(accountGenerator());
        Assert.assertEquals(expected,accountService.save(accountDtoGenerator()));
    }

    public Account accountGenerator() {
        Account account = new Account();
        account.setAccountNumber("123456789");
        account.setAccountId(1L);
        account.setBalance(1300L);
        account.setMaximun(500L);
        account.setOverdraft(200L);
        account.setPin("211091");
        return account;
    }

    public AccountDto accountDtoGenerator() {
        AccountDto account = new AccountDto();
        account.setAccountNumber("987654321");
        account.setAccountId(1L);
        account.setBalance(1300L);
        account.setMaximun(500L);
        account.setOverdraft(200L);
        account.setPin("211091");
        return account;
    }

}
