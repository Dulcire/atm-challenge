package com.challenge.zinkworks.utils;

import com.challenge.zinkworks.models.dtos.BillDto;

public class UtilsATM {

    public static boolean isZero(final Long first) {
        return first.compareTo(0L) == 0;
    }

    public static boolean equalsToZero(final BillDto bill) {
        return bill.getQuantity().equals(0L);
    }

    public static boolean equalsBillTo(final BillDto billOne, final BillDto billTwo) {
        return billOne.getBill().equals(billTwo.getBill());
    }

    public static Long quantityResult(final BillDto first, final BillDto second) {
        return first.getQuantity() - second.getQuantity();
    }

    public static boolean lowerThan(final Long first, final Long second) {
        return first.compareTo(second) >= 0;
    }

    public static boolean biggerThan(final Long first, final Long second) {
        return first.compareTo(second) <= 0;
    }

    public static boolean validateMaximun(final Long first, final Long second) {
        if (second == null) {
            return true;
        }
        return biggerThan(first, second);

    }
}
