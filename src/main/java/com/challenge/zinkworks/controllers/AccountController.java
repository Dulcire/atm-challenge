package com.challenge.zinkworks.controllers;

import com.challenge.zinkworks.models.dtos.AccountDto;
import com.challenge.zinkworks.models.dtos.response.AccountResponseDto;
import com.challenge.zinkworks.services.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    /**
     * Create a new Account in database.
     *
     * @param accountDto Account input to be saved.
     * @return
     */
    @PostMapping(value = "/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<AccountDto> saveAcccount(@RequestBody final AccountDto accountDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(accountDto));
    }

    /**
     * Return the balance and maximun withdrawal related to the account pin.
     *
     * @param pin pin account identification
     * @return
     */
    @GetMapping(value = "/accounts")
    @ResponseBody
    public ResponseEntity<AccountResponseDto> getBalanceAccount(@RequestParam(name = "pin", required = false) final String pin) {
        return ResponseEntity.ok().body(accountService.getBalanceAccount(pin));
    }


}
