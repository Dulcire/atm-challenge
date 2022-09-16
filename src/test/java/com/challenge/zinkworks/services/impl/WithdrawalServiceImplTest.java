package com.challenge.zinkworks.services.impl;

import com.challenge.zinkworks.exceptions.ATMException;
import com.challenge.zinkworks.models.dtos.AccountDto;
import com.challenge.zinkworks.models.dtos.BillDto;
import com.challenge.zinkworks.models.dtos.DispenseDto;
import com.challenge.zinkworks.models.dtos.response.WithdrawalResponseDto;
import com.challenge.zinkworks.models.enums.ExceptionMessage;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WithdrawalServiceImplTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Autowired
    public WithdrawalServiceImpl withdrawalService;

    @MockBean
    private BillServiceImpl billService;

    @MockBean
    private AccountServiceImpl accountService;

    @Test
    public void withdrawalTest() {
        final String pin = "211091";
        final Long balanceExpected = 1175L;
        List<BillDto> withdrawalExpected = Arrays.asList(
                new BillDto(1L,50L, 2L),
                new BillDto(2L,20L, 1L),
                new BillDto(4L,5L, 1L));
        final WithdrawalResponseDto expected = new WithdrawalResponseDto(withdrawalExpected, balanceExpected);
        Mockito.when(accountService.getAccount(pin)).thenReturn(accountDtoGenerator());
        Mockito.when(billService.findAll()).thenReturn(ATMListGenerator());
        Assert.assertEquals(withdrawalService.withdrawal(pin, 125L), expected);
    }

    @Test
    public void withdrawalWithOverdraftTest() {
        final String pin = "211091";
        final Long balanceExpected = -200L;
        final AccountDto accountDto = accountDtoGenerator();
        accountDto.setBalance(0L);
        List<BillDto> withdrawalExpected = Arrays.asList(
                new BillDto(1L,50L, 4L));
        final WithdrawalResponseDto expected = new WithdrawalResponseDto(withdrawalExpected, balanceExpected);
        Mockito.when(accountService.getAccount(pin)).thenReturn(accountDto);
        Mockito.when(billService.findAll()).thenReturn(ATMListGenerator());
        Assert.assertEquals(withdrawalService.withdrawal(pin, 200L), expected);
    }

    @Test
    public void withdrawalLimitExceedTest() {
        exceptionRule.expect(ATMException.class);
        exceptionRule.expectMessage(ExceptionMessage.LIMIT_EXCEEDED.getMessage());
        final AccountDto accountDto = accountDtoGenerator();
        accountDto.setBalance(3000L);
        final String pin = "211091";
        Mockito.when(accountService.getAccount(pin)).thenReturn(accountDto);
        withdrawalService.withdrawal(pin, 2000l);
    }

    @Test
    public void withdrawalNotFoundsTest() {
        exceptionRule.expect(ATMException.class);
        exceptionRule.expectMessage(ExceptionMessage.INSUFFICIENT_FOUND.getMessage());
        final String pin = "211091";
        final AccountDto accountDto = accountDtoGenerator();
        accountDto.setBalance(50L);
        Mockito.when(accountService.getAccount(pin)).thenReturn(accountDto);
        withdrawalService.withdrawal(pin, 2000l);
    }

    @Test
    public void withdrawalBillsTest() {
        List<BillDto> withdrawalExpected = Arrays.asList(
                new BillDto(1L,50L, 2L),
                new BillDto(2L,20L, 1L),
                new BillDto(4L,5L, 1L));
        Assert.assertEquals( withdrawalService.withdrawalBills(125L,ATMListGenerator()),withdrawalExpected);
    }

    @Test
    public void withdrawlInfoTest() {
        DispenseDto dispenseDto = new DispenseDto(25L, new BillDto(1L,50L, 2L));
        Assert.assertEquals(dispenseDto, withdrawalService.withdrawlInfo(125L, new BillDto(1L,50L, 10L)));
    }

    @Test
    public void withdrawlInfoBillLowerTest() {
        DispenseDto dispenseDto = new DispenseDto(25L, new BillDto(3L,50L, 0L));
        Assert.assertEquals(dispenseDto, withdrawalService.withdrawlInfo(25L, new BillDto(3L,50L, 10L)));
    }

    @Test
    public void withdrawlInfoQuantityLowerTest() {
        DispenseDto dispenseDto = new DispenseDto(75L, new BillDto(1L,50L, 1L));
        Assert.assertEquals(dispenseDto, withdrawalService.withdrawlInfo(125L, new BillDto(1L,50L, 1L)));
    }

    @Test
    public void validationWithdrawTest() {
        List<BillDto> withdrawalExpected = Arrays.asList(
                new BillDto(1L,50L, 2L),
                new BillDto(2L,20L, 1L),
                new BillDto(4L,5L, 1L));
       Assert.assertEquals(withdrawalService.validationWithdraw(0L,withdrawalExpected), withdrawalExpected);
    }

    @Test
    public void validationNoWithdrawTest() {
        exceptionRule.expect(ATMException.class);
        exceptionRule.expectMessage(ExceptionMessage.INCORRECT_AMOUNT.getMessage());
        List<BillDto> withdrawalExpected = Arrays.asList(
                new BillDto(1L,50L, 2L),
                new BillDto(2L,20L, 1L),
                new BillDto(3L,10L, 0L),
                new BillDto(4L,5L, 1L));
        Assert.assertEquals(withdrawalService.validationWithdraw(1L,withdrawalExpected), withdrawalExpected);
    }

    @Test
    public void updateEmptyATM() {
        List<BillDto> list = Arrays.asList(
                new BillDto(1L,50L, 10L),
                new BillDto(2L,20L, 30L),
                new BillDto(3L,10L, 30L),
                new BillDto(4L,5L, 20L)
        );
        withdrawalService.updateATM(list, list);
        Mockito.verify(billService).save(new BillDto(1L,50L,0L));
        Mockito.verify(billService).save(new BillDto(2L,20L,0L));
        Mockito.verify(billService).save(new BillDto(3L,10L,0L));
        Mockito.verify(billService).save(new BillDto(4L,5L,0L));

    }

    @Test
    public void updateATM() {
        List<BillDto> list = Arrays.asList(
                new BillDto(1L,50L, 5L),
                new BillDto(2L,20L, 10L),
                new BillDto(3L,10L, 5L),
                new BillDto(4L,5L, 2L)
        );
        withdrawalService.updateATM(list, ATMListGenerator());
        Mockito.verify(billService).save(new BillDto(1L,50L,5L));
        Mockito.verify(billService).save(new BillDto(2L,20L,20L));
        Mockito.verify(billService).save(new BillDto(3L,10L,25L));
        Mockito.verify(billService).save(new BillDto(4L,5L,18L));

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

    public List<BillDto> listBillsGenerator() {
        return Arrays.asList(
                new BillDto(1L,50L, 2L),
                new BillDto(2L,30L, 0L),
                new BillDto(3L,20L, 1L),
                new BillDto(4L,10L, 0L),
                new BillDto(5L,5L, 1L)
        );
    }

    public List<BillDto> listBillsZeroGenerator() {
        return Arrays.asList(
                new BillDto(1L,50L, 0L),
                new BillDto(2L,20L, 0L),
                new BillDto(3L,10L, 0L),
                new BillDto(4L,5L, 0L)
        );
    }

    public List<BillDto> ATMListGenerator() {
        return Arrays.asList(
                new BillDto(1L,50L, 10L),
                new BillDto(2L,20L, 30L),
                new BillDto(3L,10L, 30L),
                new BillDto(4L,5L, 20L)
        );
    }
}
