package com.ejemplo.demopaginaweb.util.viewpdf;

import com.ejemplo.demopaginaweb.entity.Factura;
import com.ejemplo.demopaginaweb.entity.ItemFactura;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.util.Map;

@Component("factura/factura")
public class FacturaViewPdf extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        Factura factura = (Factura) model.get("factura");

        PdfPTable tablaT = new PdfPTable(1);
        tablaT.setSpacingAfter(20);
        PdfPCell cell = null;
        cell = new PdfPCell(new Phrase("Detalles de Factura", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        cell.setBorder(0);
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaT.addCell(cell);
        document.add(tablaT);

        PdfPTable tabla = new PdfPTable(3); //  2 columnas para datos del cliente y factura
        tabla.setWidthPercentage(100); // Ajustar el ancho de la tabla al 100%
        tabla.setWidths(new float[]{8, 1, 8});


        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(8f);
        cell.setBackgroundColor(new Color(184, 218, 255));

// Datos del Cliente
        cell.addElement(new Paragraph((new Phrase("Datos del Cliente", FontFactory.getFont(FontFactory.HELVETICA_BOLD)))));
        cell.addElement(new Paragraph("Nombre: " + factura.getCliente().getNombre()));
        cell.addElement(new Paragraph("Apellido: " + factura.getCliente().getApellidos()));
        cell.addElement(new Paragraph("Email: " + factura.getCliente().getEmail()));

        tabla.addCell(cell);

        // Celda vacía para el espacio
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setColspan(1);
        tabla.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(8f);
        cell.setBackgroundColor(new Color(195, 230, 203));

// Datos de la Factura
        cell.addElement(new Paragraph(new Phrase("Datos de la factura", FontFactory.getFont(FontFactory.HELVETICA_BOLD))));
        cell.addElement(new Paragraph("Nº Factura: " + factura.getId()));
        cell.addElement(new Paragraph("Descripción: " + factura.getDescripcion()));
        cell.addElement(new Paragraph("Fecha: " + factura.getFecha()));

        tabla.addCell(cell);

        tabla.setSpacingAfter(20);

        document.add(tabla);

        PdfPTable tabla3 = new PdfPTable(4);
        tabla3.setWidthPercentage(100); // Ajustar el ancho de la tabla al 100%
        tabla3.setWidths(new float[]{3.5f, 1, 1, 1});
        tabla3.setSpacingAfter(20);

// Establecer el color de fondo de las celdas de los nombres de las columnas
        Color colorGris = new Color(192, 192, 192);
        cell = new PdfPCell(new Phrase("Producto"));
        cell.setBackgroundColor(colorGris);
        cell.setBorder(PdfPCell.NO_BORDER);
        tabla3.addCell(cell);

        cell = new PdfPCell(new Phrase("Precio"));
        cell.setBackgroundColor(colorGris);
        cell.setBorder(PdfPCell.NO_BORDER);
        tabla3.addCell(cell);

        cell = new PdfPCell(new Phrase("Cantidad"));
        cell.setBackgroundColor(colorGris);
        cell.setBorder(PdfPCell.NO_BORDER);
        tabla3.addCell(cell);

        cell = new PdfPCell(new Phrase("Total"));
        cell.setBackgroundColor(colorGris);
        cell.setBorder(PdfPCell.NO_BORDER);
        tabla3.addCell(cell);

        for (ItemFactura item : factura.getItemFacturas()) {
            cell = new PdfPCell(new Phrase(item.getProducto().getNombre()));
            cell.setFixedHeight(30);
            cell.setBorder(PdfPCell.NO_BORDER); //
            tabla3.addCell(cell);

            cell = new PdfPCell(new Phrase(item.getProducto().getPrecio().toString()));
            cell.setFixedHeight(30);
            cell.setBorder(PdfPCell.NO_BORDER); //

            tabla3.addCell(cell);

            cell = new PdfPCell(new Phrase(item.getCantidad().toString()));
            cell.setFixedHeight(30);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER); //

            tabla3.addCell(cell);

            cell = new PdfPCell(new Phrase(item.calcularImporte().toString()));
            cell.setFixedHeight(30);
            cell.setBorder(PdfPCell.NO_BORDER); //
            tabla3.addCell(cell);
        }

        document.add(tabla3);

        PdfPTable tablaTotal = new PdfPTable(1);
        tablaTotal.setWidthPercentage(20);
        tablaTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);

        cell = new PdfPCell(new Phrase("Total: "+factura.calcularTotal().toString(), FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        cell.setBackgroundColor(colorGris);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        tablaTotal.addCell(cell);

        tablaTotal.setSpacingAfter(20);

        document.add(tablaTotal);


        PdfPTable tablaObservacion = new PdfPTable(2);
        tablaObservacion.setWidthPercentage(100); // Ajustar el ancho de la tabla al 100%
        tablaObservacion.setWidths(new float[]{1, 4});
        cell = new PdfPCell(new Phrase("Observación"));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBackgroundColor(new Color(195, 230, 203));
        cell.setPadding(8f);
        tablaObservacion.addCell(cell);

// Crea una celda vacía para ocupar el espacio en blanco

        cell = new PdfPCell(new Phrase( factura.getObservacion()));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        tablaObservacion.addCell(cell);

        document.add(tablaObservacion);
    }
}
