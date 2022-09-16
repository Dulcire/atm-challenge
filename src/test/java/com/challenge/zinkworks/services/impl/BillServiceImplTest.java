package com.challenge.zinkworks.services.impl;

import com.challenge.zinkworks.models.dtos.BillDto;
import com.challenge.zinkworks.models.entities.Bill;
import com.challenge.zinkworks.repositories.BillRepository;
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

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BillServiceImplTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Autowired
    private BillServiceImpl billService;

    @MockBean
    private BillRepository billRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void saveTest() {
        final Bill bill = new Bill();
        bill.setBill(50L);
        bill.setQuantity(20L);
        bill.setBillId(1L);
        final BillDto expected = new BillDto(1L, 50L, 20L);
        Mockito.when(modelMapper.map(bill, BillDto.class)).thenReturn(expected);
        Mockito.when(modelMapper.map(expected, Bill.class)).thenReturn(bill);
        Mockito.when(billRepository.save(bill)).thenReturn(bill);
        Assert.assertEquals(billService.save(expected), expected);
    }

    @Test
    public void findAllTest() {
        final Bill bill = new Bill();
        bill.setBill(50L);
        bill.setQuantity(20L);
        bill.setBillId(1L);
        final BillDto expected = new BillDto(1L, 50L, 20L);
        Mockito.when(billRepository.findAll()).thenReturn(ATMListGenerator());
        Mockito.when(modelMapper.map(bill, BillDto.class)).thenReturn(expected);
        Mockito.when(modelMapper.map(expected, Bill.class)).thenReturn(bill);
        Assert.assertEquals(ATMListDtoGenerator(), billService.findAll());
    }

    public List<Bill> ATMListGenerator() {
        final Bill bill = new Bill();
        bill.setBill(50L);
        bill.setQuantity(20L);
        bill.setBillId(1L);
        return Arrays.asList(bill, bill, bill);

    }

    public List<BillDto> ATMListDtoGenerator() {
        final BillDto billDto = new BillDto(1L, 50L, 20L);
        return Arrays.asList(billDto, billDto, billDto);

    }

}
