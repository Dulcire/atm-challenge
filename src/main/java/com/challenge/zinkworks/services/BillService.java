package com.challenge.zinkworks.services;

import com.challenge.zinkworks.models.dtos.BillDto;

import java.util.List;

public interface BillService {

    BillDto save(BillDto billDto);

    List<BillDto> findAll();
}
