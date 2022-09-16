package com.challenge.zinkworks.services.impl;

import com.challenge.zinkworks.exceptions.ATMException;
import com.challenge.zinkworks.models.dtos.AccountDto;
import com.challenge.zinkworks.models.dtos.BillDto;
import com.challenge.zinkworks.models.dtos.DispenseDto;
import com.challenge.zinkworks.models.dtos.response.WithdrawalResponseDto;
import com.challenge.zinkworks.models.enums.ExceptionMessage;
import com.challenge.zinkworks.services.WithdrawalService;
import com.challenge.zinkworks.utils.UtilsATM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Account service to calculate all algorithms related to a withdrawal request.
 */
@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private BillServiceImpl billService;

    /**
     * This method calculate the total banknotes to be dispensed by the ATM and the new balance.
     * in the account.
     *
     * @param pin    account pin identification.
     * @param amount amount to be withdrawal.
     * @return
     */
    @Override
    public WithdrawalResponseDto withdrawal(final String pin, final Long amount) {
        final AccountDto account = accountService.getAccount(pin);
        final List<BillDto> atmBills = billService.findAll();
        final Long newbalance = account.getBalance() + account.getOverdraft();
        if (UtilsATM.biggerThan(amount, newbalance)) {
            if (UtilsATM.validateMaximun(amount, account.getMaximun())) {

                final List<BillDto> withdrawalBills = withdrawalBills(amount, atmBills);

                updateATM(withdrawalBills, atmBills);

                final Long balance = account.getBalance() - amount;
                final AccountDto accountDto = accountService.getAccount(pin);
                accountDto.setBalance(balance);
                accountService.save(accountDto);

                return new WithdrawalResponseDto(withdrawalBills, balance);

            } else {
                throw new ATMException(ExceptionMessage.LIMIT_EXCEEDED.getMessage(), ExceptionMessage.LIMIT_EXCEEDED.getMessage(),
                        ExceptionMessage.LIMIT_EXCEEDED_CODE.getMessage());
            }

        } else {
            throw new ATMException(ExceptionMessage.INSUFFICIENT_FOUND.getMessage(), ExceptionMessage.INSUFFICIENT_FOUND.getMessage(),
                    ExceptionMessage.INSUFFICIENT_FOUND_CODE.getMessage());
        }
    }

    /**
     * Get the list of bills to dispense.
     *
     * @param amount amount requested to dispense.
     * @return
     */
    public List<BillDto> withdrawalBills(final Long amount, final List<BillDto> atmBills) {
        final List<BillDto> withdrawalBills = new ArrayList<>();
        Long finalAmount = amount;
        for (BillDto bill : atmBills) {
            final DispenseDto operation = withdrawlInfo(finalAmount, bill);
            withdrawalBills.add(operation.getDispense());
            finalAmount = operation.getAmount();
        }
        return validationWithdraw(finalAmount, withdrawalBills);

    }

    /**
     * Method wich return the quantity of bills to return and the new amount rested.
     *
     * @param amount amount to be withdrawal.
     * @return
     */
    public DispenseDto withdrawlInfo(final Long amount, final BillDto bill) {
        final BillDto dispense;
        Long newamount = amount;
        Long billQuantity = 0L;
        if (UtilsATM.lowerThan(amount, bill.getBill())) {
            billQuantity = amount / bill.getBill();
            if (UtilsATM.lowerThan(bill.getQuantity(), billQuantity)) {
                newamount = amount % bill.getBill();
            } else {
                newamount = amount - (bill.getBill() * bill.getQuantity());
                billQuantity = bill.getQuantity();
            }
        }
        dispense = new BillDto(bill.getBillId(), bill.getBill(), billQuantity);
        return new DispenseDto(newamount, dispense);
    }

    /**
     * This method validate than the amount is withdrawable.
     *
     * @param amount     amount requested to dispense.
     * @param withdrawal list of bills to be dispensed.
     * @return
     */
    public List<BillDto> validationWithdraw(final Long amount, final List<BillDto> withdrawal) {
        if (UtilsATM.isZero(amount)) {
            return withdrawal.stream()
                    .filter(billDto -> !UtilsATM.equalsToZero(billDto))
                    .collect(Collectors.toList());
        } else {
            throw new ATMException(ExceptionMessage.INCORRECT_AMOUNT.getMessage(),
                    ExceptionMessage.INCORRECT_AMOUNT.getMessage(),
                    ExceptionMessage.INCORRECT_AMOUNT_CODE.getMessage());
        }
    }

    /**
     * This method update the quantity of bills in the ATM after withdraw.
     *
     * @param withdrawal amount requested to dispense.
     */
    public void updateATM(final List<BillDto> withdrawal, final List<BillDto> atmBills) {
        withdrawal.stream().forEach(billDto -> {
            atmBills.stream()
                    .filter(billATM ->
                            UtilsATM.equalsBillTo(billATM, billDto))
                    .forEach(billATM -> {
                        billATM.setQuantity(UtilsATM.quantityResult(billATM, billDto));
                        billService.save(billATM);
                    });
        });
    }


}
