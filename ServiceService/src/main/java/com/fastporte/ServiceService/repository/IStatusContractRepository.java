package com.fastporte.ServiceService.repository;

import com.fastporte.ServiceService.entities.StatusContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusContractRepository extends JpaRepository<StatusContract, Long> {


}
