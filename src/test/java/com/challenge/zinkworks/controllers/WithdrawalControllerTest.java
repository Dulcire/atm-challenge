package com.challenge.zinkworks.controllers;

import com.challenge.zinkworks.models.dtos.BillDto;
import com.challenge.zinkworks.models.dtos.response.WithdrawalResponseDto;
import com.challenge.zinkworks.services.impl.WithdrawalServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WithdrawalControllerTest {

    @Autowired
    private WithdrawalController withdrawalController;

    @MockBean
    private WithdrawalServiceImpl withdrawalService;

    @Test
    public void withdrawalTest() {
        final String pin = "123456";
        WithdrawalResponseDto response = new WithdrawalResponseDto(listBillsGenerator(),200L);
        Mockito.when(withdrawalService.withdrawal(pin,100L)).thenReturn(response);
        final ResponseEntity<WithdrawalResponseDto> responseEntity = withdrawalController.withdrawal(pin,10L);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
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
}
