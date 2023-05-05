package com.fastporte.reportmicroservice.service;

import com.fastporte.reportmicroservice.dto.ContractDto;
import com.fastporte.reportmicroservice.dto.ResponseDto;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface ContractReportService {
    ResponseDto generateReport(ContractDto contractDto) throws JRException, FileNotFoundException;

}
