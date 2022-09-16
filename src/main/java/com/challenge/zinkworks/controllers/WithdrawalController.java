package com.challenge.zinkworks.controllers;

import com.challenge.zinkworks.models.dtos.response.WithdrawalResponseDto;
import com.challenge.zinkworks.services.impl.WithdrawalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawalController {

    @Autowired
    private WithdrawalServiceImpl withdrawalService;

    /**
     * Method to solicit Withdrawal.
     * @param pin Pin account validation.
     * @param amount Amount to withdrawal.
     * @return
     */
    @GetMapping(value = "/withdrawals")
    @ResponseBody
    public ResponseEntity<WithdrawalResponseDto> withdrawal(@RequestParam(name = "pin", required = true) final String pin, @RequestParam(name = "amount", required = true) final Long amount) {
        return ResponseEntity.ok().body(withdrawalService.withdrawal(pin, amount));
    }
}
