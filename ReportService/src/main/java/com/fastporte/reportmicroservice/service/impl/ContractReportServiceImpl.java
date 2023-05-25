package com.fastporte.reportmicroservice.service.impl;

import com.fastporte.reportmicroservice.dto.ContractDto;
import com.fastporte.reportmicroservice.dto.ResponseDto;
import com.fastporte.reportmicroservice.service.ContractReportService;
import com.fastporte.reportmicroservice.service.FirebaseStorageService;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ContractReportServiceImpl implements ContractReportService {

    private final FirebaseStorageService firebaseStorageService = new FirebaseStorageServiceImpl();
    private static final String PATH_TEMP = "src/main/java/com/fastporte/reportmicroservice/tempFiles";

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
            String urlPdf = PATH_TEMP + File.separator + contractDto.getContractId() + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, urlPdf);
            String url = firebaseStorageService.uploadFile(urlPdf, contractDto.getContractId() + ".pdf");
            ResponseDto responseDto = new ResponseDto();
            responseDto.setUrl(url);
            return responseDto;
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private static JasperReport getJasperReport() throws FileNotFoundException, JRException {
        return JasperCompileManager.compileReport("src/main/resources/REPORTE_CONTRATO.jrxml");
    }
}
