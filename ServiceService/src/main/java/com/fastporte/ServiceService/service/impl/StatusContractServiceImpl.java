package com.fastporte.ServiceService.service.impl;

import com.fastporte.ServiceService.entities.StatusContract;
import com.fastporte.ServiceService.repository.IStatusContractRepository;
import com.fastporte.ServiceService.service.IStatusContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // Solo servicios de lectura
public class StatusContractServiceImpl implements IStatusContractService {

    private final IStatusContractRepository statusContractRepository;

    public StatusContractServiceImpl(IStatusContractRepository statusContractRepository) {
        this.statusContractRepository = statusContractRepository;
    }

    @Override
    public StatusContract save(StatusContract statusContract) {
        return statusContractRepository.save(statusContract);
    }

    @Override
    public void delete(Long id) {
        statusContractRepository.deleteById(id);
    }

    @Override
    public List<StatusContract> getAll() throws Exception {
        return statusContractRepository.findAll();
    }

    @Override
    public Optional<StatusContract> getById(Long id) throws Exception {
        return statusContractRepository.findById(id);
    }

}