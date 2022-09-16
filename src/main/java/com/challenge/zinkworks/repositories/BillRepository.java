package com.challenge.zinkworks.repositories;

import com.challenge.zinkworks.models.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
