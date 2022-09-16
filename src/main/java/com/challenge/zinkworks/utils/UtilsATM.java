package com.challenge.zinkworks.utils;

import com.challenge.zinkworks.models.dtos.BillDto;

public class UtilsATM {

    /**
     * Method to check if a value is zero.
     * @param first Long value
     * @return
     */
    public static boolean isZero(final Long first) {
        return first.compareTo(0L) == 0;
    }

    /**
     * Method to check if a bill quantity is zero.
     * @param bill BillDto
     * @return
     */
    public static boolean equalsToZero(final BillDto bill) {
        return bill.getQuantity().equals(0L);
    }

    /**
     * Method to check if two bills has the same bill atribute.
     * @param billOne BillDto
     * @param billTwo  Bill Dto
     * @return
     */
    public static boolean equalsBillTo(final BillDto billOne, final BillDto billTwo) {
        return billOne.getBill().equals(billTwo.getBill());
    }

    /**
     * Mehtod to calculate the quntity result after a withdrawal
     * @param first BillDto actual
     * @param second BillDto new
     * @return
     */
    public static Long quantityResult(final BillDto first, final BillDto second) {
        return first.getQuantity() - second.getQuantity();
    }

    /**
     * Method to check if the second value is lower than the first value.
     * @param first Long value to compare
     * @param second Long value to compare
     * @return
     */
    public static boolean lowerThan(final Long first, final Long second) {
        return first.compareTo(second) >= 0;
    }

    /**
     * Method to check if the second value is bigger than the first value.
     * @param first Long value to compare
     * @param second Long value to compare
     * @return
     */
    public static boolean biggerThan(final Long first, final Long second) {
        return first.compareTo(second) <= 0;
    }

    /**
     * Methos to validate if the secondValue is null and ignore it.
     * @param first Long value to compare
     * @param second Long value to compare
     * @return
     */
    public static boolean validateMaximun(final Long first, final Long second) {
        if (second == null) {
            return true;
        }
        return biggerThan(first, second);

    }
}
