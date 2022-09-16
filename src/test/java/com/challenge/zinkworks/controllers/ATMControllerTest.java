package com.challenge.zinkworks.controllers;

import com.challenge.zinkworks.models.dtos.BillDto;
import com.challenge.zinkworks.services.impl.BillServiceImpl;
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

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ATMControllerTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Autowired
    private ATMController atmController;

    @MockBean
    private BillServiceImpl billService;

    @Before
    public void init() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void getBillsTest() {
        Mockito.when(billService.findAll()).thenReturn(ATMListDtoGenerator());
        final ResponseEntity<List<BillDto>> responseEntity = atmController.getBills();
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    public void saveBillTest() {
        Mockito.when(billService.save(ATMListDtoGenerator().get(0))).thenReturn(ATMListDtoGenerator().get(0));
        final ResponseEntity<BillDto> responseEntity = atmController.saveBill(ATMListDtoGenerator().get(0));
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

    public List<BillDto> ATMListDtoGenerator() {
        final BillDto billDto = new BillDto(1L, 50L, 20L);
        return Arrays.asList(
                new BillDto(1L, 50L, 20L),
                new BillDto(1L, 20L, 20L),
                new BillDto(1L, 10L, 20L));

    }
}
