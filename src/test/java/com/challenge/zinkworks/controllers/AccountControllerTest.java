package com.challenge.zinkworks.controllers;

import com.challenge.zinkworks.exceptions.ATMException;
import com.challenge.zinkworks.models.dtos.AccountDto;
import com.challenge.zinkworks.models.dtos.response.AccountResponseDto;
import com.challenge.zinkworks.models.enums.ExceptionMessage;
import com.challenge.zinkworks.services.impl.AccountServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountControllerTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Autowired
    AccountController accountController;

    @MockBean
    AccountServiceImpl accountService;

    @Before
    public void init() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void saveAccountTest() {
        final ResponseEntity<AccountDto> responseEntity = accountController.saveAcccount(accountDtoGenerator());
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

    @Test
    public void getBalanceAccountTest() {
        final String pin = "1234";
        Mockito.when(accountService.getBalanceAccount(pin)).thenReturn(accountResponseDto());
        final ResponseEntity<AccountResponseDto> responseEntity = accountController.getBalanceAccount(pin);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    public AccountDto accountDtoGenerator() {
        AccountDto account = new AccountDto();
        account.setAccountId(1L);
        account.setBalance(1300L);
        account.setMaximun(500L);
        account.setOverdraft(200L);
        account.setPin("211091");
        return account;
    }

    public AccountResponseDto accountResponseDto(){
        return new AccountResponseDto(1300L,500L);
    }
}
