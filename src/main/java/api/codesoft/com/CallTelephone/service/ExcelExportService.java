package api.codesoft.com.CallTelephone.service;


import api.codesoft.com.CallTelephone.model.LeadSilbo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {

    private final LeadService leadService;

    public ExcelExportService(LeadService leadService) {
        this.leadService = leadService;
    }

    public byte[] generateExcelFile() throws IOException {
        List<LeadSilbo> leads = leadService.findAllLeads();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Leads");

        // Crear encabezado
        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID", "Teléfono", "Operador", "Segmento", "Email", "Orden", "Fecha Creación", "ID Campaña"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            cell.setCellStyle(headerStyle);
        }

        // Llenar los datos
        int rowNum = 1;
        for (LeadSilbo lead : leads) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(lead.getId());
            row.createCell(1).setCellValue(lead.getPhone());
            row.createCell(2).setCellValue(lead.getOperator());
            row.createCell(3).setCellValue(lead.getSegment());
            row.createCell(4).setCellValue(lead.getEmail());
            row.createCell(5).setCellValue(lead.getProductOrder());
            row.createCell(6).setCellValue(lead.getFechaCreacion().toString());
            row.createCell(7).setCellValue(lead.getCampaignId());
        }

        // Ajustar tamaño de columnas
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Convertir el archivo a un array de bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
