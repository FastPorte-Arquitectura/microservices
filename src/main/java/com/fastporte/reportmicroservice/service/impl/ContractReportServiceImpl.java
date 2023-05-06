package com.fastporte.reportmicroservice.service.impl;

import com.fastporte.reportmicroservice.ReportMicroserviceApplication;
import com.fastporte.reportmicroservice.dto.ContractDto;
import com.fastporte.reportmicroservice.dto.ResponseDto;
import com.fastporte.reportmicroservice.service.ContractReportService;
import com.fastporte.reportmicroservice.service.FirebaseStorageService;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ContractReportServiceImpl implements ContractReportService {

    private final FirebaseStorageService firebaseStorageService = new FirebaseStorageServiceImpl();

    @Override
    public ResponseDto generateReport(ContractDto contractDto) throws JRException, FileNotFoundException {
        JasperReport jasperReport = getJasperReport();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("P_LOGO", "logo.png");
        parameters.put("P_LOGO_UPC", "logo_upc.png");
        parameters.put("P_CODIGO_CONTRATO", contractDto.getContractId());
        parameters.put("P_CLIENTE", contractDto.getClient());
        parameters.put("P_DRIVER", contractDto.getDriver());
        parameters.put("P_DESCRIPCION", contractDto.getDescripcion());
        parameters.put("P_PRECIO", contractDto.getPrecio());
        parameters.put("P_INICIO", contractDto.getInicio());
        parameters.put("P_DESTINO", contractDto.getDestino());
        parameters.put("P_HORA_INICIO", contractDto.getHoraInicio());
        parameters.put("P_HORA_FIN", contractDto.getHoraFin());
        parameters.put("P_FECHA_CONTRATO", contractDto.getFechaContrato());

        JasperPrint jasperPrint;

        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            File tempFile = File.createTempFile("contract_", ".pdf");
            tempFile.deleteOnExit();
            JasperExportManager.exportReportToPdfFile(jasperPrint, tempFile.getAbsolutePath());

            String url = firebaseStorageService.uploadFile(tempFile.getAbsolutePath(), contractDto.getContractId() + ".pdf");
            ResponseDto responseDto = new ResponseDto();
            responseDto.setUrl(url);

            // Delete the temporary file
            tempFile.delete();

            return responseDto;
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static JasperReport getJasperReport() throws FileNotFoundException, JRException {
        ClassLoader classLoader = ReportMicroserviceApplication.class.getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream("REPORTE_CONTRATO.jrxml");
        return JasperCompileManager.compileReport(inputStream);
    }

}
