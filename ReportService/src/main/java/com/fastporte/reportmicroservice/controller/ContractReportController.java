package com.fastporte.reportmicroservice.controller;

import com.fastporte.reportmicroservice.dto.ContractDto;
import com.fastporte.reportmicroservice.dto.ResponseDto;
import com.fastporte.reportmicroservice.service.ContractReportService;
import com.fastporte.reportmicroservice.service.FirebaseStorageService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/api/microservice/report")
public class ContractReportController {

    private final ContractReportService contractReportService;
    private final FirebaseStorageService firebaseStorageService;

    public ContractReportController(ContractReportService contractReportService, FirebaseStorageService firebaseStorageService) {
        this.contractReportService = contractReportService;
        this.firebaseStorageService = firebaseStorageService;
    }

    @PostMapping
    public ResponseDto generateReport(@RequestBody ContractDto contractDto) throws JRException,
            FileNotFoundException {
        return contractReportService.generateReport(contractDto);
    }

}
