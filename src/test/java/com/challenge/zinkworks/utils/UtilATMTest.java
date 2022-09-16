package com.challenge.zinkworks.utils;

import com.challenge.zinkworks.models.dtos.BillDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilATMTest {


    @Test
    public void isZeroTest() {
        Assert.assertTrue(UtilsATM.isZero(0L));
    }

    @Test
    public void isNotZeroTest() {
        Assert.assertFalse(UtilsATM.isZero(5L));
    }

    @Test
    public void isNotNegativeZeroTest() {
        Assert.assertFalse(UtilsATM.isZero(-5L));
    }

    @Test
    public void equalsToZeroTest() {
        final BillDto bill = new BillDto(1L,50L,0L);
        Assert.assertTrue(UtilsATM.equalsToZero(bill));
    }

    @Test
    public void equalsToZeroNoTest() {
        final BillDto bill = new BillDto(1L,50L,5L);
        Assert.assertFalse(UtilsATM.equalsToZero(bill));
    }

    @Test
    public void equalsBillToTest() {
        final BillDto billOne = new BillDto(1L,50L,5L);
        final BillDto billTwo = new BillDto(1L,50L,20L);
        Assert.assertTrue(UtilsATM.equalsBillTo(billOne,billTwo));
    }

    @Test
    public void NoequalsBillToTest() {
        final BillDto billOne = new BillDto(1L,50L,5L);
        final BillDto billTwo = new BillDto(1L,20L,20L);
        Assert.assertFalse(UtilsATM.equalsBillTo(billOne,billTwo));
    }

    @Test
    public void quantityResultTest() {
        final Long expected = 15L;
        final BillDto billOne = new BillDto(1L,50L,20L);
        final BillDto billTwo = new BillDto(1L,50L,5L);
        Assert.assertEquals(expected, UtilsATM.quantityResult(billOne,billTwo));
    }

    @Test
    public void lowerThanTest() {
        Assert.assertTrue(UtilsATM.lowerThan(10L,5L));
    }

    @Test
    public void noLowerThanTest() {
        Assert.assertFalse(UtilsATM.lowerThan(5L,10L));
    }

    @Test
    public void biggerThanTest() {
        Assert.assertTrue(UtilsATM.biggerThan(5L,10L));
    }

    @Test
    public void nobiggerThanTest() {
        Assert.assertFalse(UtilsATM.biggerThan(10L,5L));
    }

    @Test
    public void validateMaximunNullTest() {
        Assert.assertTrue(UtilsATM.validateMaximun(10L,null));
    }

    @Test
    public void validateMaximunNotNullAmountBiggerTest() {
        Assert.assertTrue(UtilsATM.biggerThan(10L,20L));
    }

    @Test
    public void validateMaximunNotNullAmountLowerTest() {
        Assert.assertFalse(UtilsATM.biggerThan(10L,5L));
    }

}

