package com.ejemplo.demopaginaweb.util.viewxlsx;

import com.ejemplo.demopaginaweb.entity.Factura;
import com.ejemplo.demopaginaweb.entity.ItemFactura;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.util.Map;

@Component("factura/factura.xlsx")
public class FacturaViewXlsx extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition","attachment; filename=\"factura_view.xlsx\"");
        Factura factura = (Factura) model.get("factura");
        Sheet sheet = workbook.createSheet("factura");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Detalles de Factura");
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("Datos del cliente");
        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellValue("Nombre: " + factura.getCliente().getNombre());
        row = sheet.createRow(4);
        cell = row.createCell(0);
        cell.setCellValue("Apellido: " + factura.getCliente().getApellidos());
        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellValue("Email: " + factura.getCliente().getEmail());

        // Agregar datos de la factura
        sheet.createRow(7).createCell(0).setCellValue("Datos de la factura");
        sheet.createRow(8).createCell(0).setCellValue("Nº Factura: " + factura.getId());
        sheet.createRow(9).createCell(0).setCellValue("Descripción: " + factura.getDescripcion());
        sheet.createRow(10).createCell(0).setCellValue("Fecha: " + factura.getFecha());

        // Estilo para las celdas del encabezado
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.GOLD.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);

        // Estilo para las celdas de los totales
        CellStyle bodyCellStyle = workbook.createCellStyle();
        bodyCellStyle.setBorderTop(BorderStyle.THIN);
        bodyCellStyle.setBorderBottom(BorderStyle.THIN);
        bodyCellStyle.setBorderLeft(BorderStyle.THIN);
        bodyCellStyle.setBorderRight(BorderStyle.THIN);

// Estilo para las celdas de los totales


        Row headerRow = sheet.createRow(12);
        headerRow.createCell(0).setCellValue("Producto");
        headerRow.createCell(1).setCellValue("Precio");
        headerRow.createCell(2).setCellValue("Cantidad");
        headerRow.createCell(3).setCellValue("Total");
        headerRow.getCell(0).setCellStyle(headerCellStyle);
        headerRow.getCell(1).setCellStyle(headerCellStyle);
        headerRow.getCell(2).setCellStyle(headerCellStyle);
        headerRow.getCell(3).setCellStyle(headerCellStyle);
        // Agregar detalles de los productos
        int rowNum = 13;
        for (ItemFactura item : factura.getItemFacturas()) {
            row = sheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue(item.getProducto().getNombre());
            cell.setCellStyle(bodyCellStyle);
            cell = row.createCell(1);
            cell.setCellValue(item.getProducto().getPrecio());
            cell.setCellStyle(bodyCellStyle);
            cell = row.createCell(2);
            cell.setCellValue(item.getCantidad());
            cell.setCellStyle(bodyCellStyle);
            cell = row.createCell(3);
            cell.setCellValue(item.calcularImporte());
            cell.setCellStyle(bodyCellStyle);
            rowNum++;
        }
        CellStyle totalCellStyle = workbook.createCellStyle();
        totalCellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        totalCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        totalCellStyle.setAlignment(HorizontalAlignment.RIGHT);
        totalCellStyle.setBorderTop(BorderStyle.THIN);
        totalCellStyle.setBorderBottom(BorderStyle.THIN);
        totalCellStyle.setBorderLeft(BorderStyle.THIN);
        totalCellStyle.setBorderRight(BorderStyle.THIN);

        Row rowTotal = sheet.createRow(rowNum++);
        Cell totalLabelCell = rowTotal.createCell(2);
        totalLabelCell.setCellValue("Total:");
        totalLabelCell.setCellStyle(totalCellStyle);

        Cell totalValueCell = rowTotal.createCell(3);
        totalValueCell.setCellValue(factura.calcularTotal());
        totalValueCell.setCellStyle(totalCellStyle);
    }

}
