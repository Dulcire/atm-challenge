package com.challenge.zinkworks.controllers;

import com.challenge.zinkworks.models.dtos.BillDto;
import com.challenge.zinkworks.services.impl.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ATMController {
    @Autowired
    private BillServiceImpl billService;

    /**
     * Method to add bills into the ATM.
     *
     * @param bill bill to add
     * @return
     */
    @PostMapping(value = "/atm")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<BillDto> saveBill(@RequestBody final BillDto bill) {
        return ResponseEntity.status(HttpStatus.CREATED).body(billService.save(bill));
    }

    /**
     * Method to get the total bills in the ATM.
     *
     * @return list of bills
     */
    @GetMapping(value = "/atm/bills")
    @ResponseBody
    public ResponseEntity<List<BillDto>> getBills() {
        return ResponseEntity.ok().body(billService.findAll());
    }


}
