package com.challenge.zinkworks.services.impl;

import com.challenge.zinkworks.exceptions.ATMException;
import com.challenge.zinkworks.models.dtos.BillDto;
import com.challenge.zinkworks.models.entities.Bill;
import com.challenge.zinkworks.models.enums.ExceptionMessage;
import com.challenge.zinkworks.repositories.BillRepository;
import com.challenge.zinkworks.services.BillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Bill service to calculate all algorithms related to the bills request.
 */
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method to save or update bills into the ATM.
     *
     * @param billDto Bill to be added into the ATM
     * @return
     */
    @Override
    public BillDto save(final BillDto billDto) {
        return convertToDto(billRepository.save(convertToEntity(billDto)));
    }

    /**
     * Method to get all bills in the ATM.
     *
     * @return
     */
    @Override
    public List<BillDto> findAll() {
        final List<BillDto> atmBills = billRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        if (atmBills.isEmpty()) {
            throw new ATMException(ExceptionMessage.NO_BILLS_ATM.getMessage(), ExceptionMessage.NO_BILLS_ATM.getMessage(),
                    ExceptionMessage.NO_BILLS_ATM_CODE.getMessage());
        }
        return atmBills;
    }


    private BillDto convertToDto(final Bill bill) {
        return modelMapper.map(bill, BillDto.class);
    }

    private Bill convertToEntity(final BillDto billDto) {
        return modelMapper.map(billDto, Bill.class);
    }
}
