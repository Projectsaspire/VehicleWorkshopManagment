package com.rt.springboot.app.view.pdf;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.rt.springboot.app.models.entity.Invoice;
import com.rt.springboot.app.models.entity.ItemInvoice;
import com.rt.springboot.app.models.entity.ItemInvoice2;

@Component("invoice/view")
public class InvoicePdfView extends AbstractPdfView {
	
	

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Invoice invoice = (Invoice) model.get("invoice");

		Invoice invoice2 = (Invoice) model.get("invoice");

		MessageSourceAccessor message = getMessageSourceAccessor();

		

		// Header Section
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(30);
		headerTable.setSpacingAfter(20);

		PdfPCell headerCell = new PdfPCell(new Phrase("Vehicle Workshop"));
		 headerCell.setBorderColor(new Color(255, 184, 184)); // You can adjust the
		headerCell.setBackgroundColor(new Color(128, 128, 128));
		headerCell.setPadding(8f);
		headerTable.addCell(headerCell);

		// Add name, address, and email to the header table
		
		headerTable.addCell(createCell("Address: City, Country",Element.ALIGN_RIGHT));
		headerTable.addCell(createCell("Phone: +(91)9999999999",Element.ALIGN_RIGHT));
		headerTable.addCell(createCell("Email: abc@example.com",Element.ALIGN_RIGHT));


		// Customer Details
		PdfPTable table = new PdfPTable(1);

		table.setSpacingAfter(20);
		PdfPCell cell = null;
		cell = new PdfPCell(new Phrase(message.getMessage("text.factura.ver.datos.cliente")));
		cell.setBackgroundColor(new Color(128, 128, 128));
		cell.setPadding(8f);
		table.addCell(cell);
		table.addCell(invoice.getClient().getFirstName() + " " + invoice.getClient().getLastName());
		table.addCell(invoice.getClient().getEmail());
		table.addCell(invoice.getClient().getvNo());
		
		// Invoice Details
		PdfPTable table2 = new PdfPTable(1);
		table2.setSpacingAfter(20);
		cell = new PdfPCell(new Phrase(message.getMessage("text.factura.ver.datos.factura")));
		cell.setBackgroundColor(new Color(128, 128, 128));
		cell.setPadding(8f);
		table2.addCell(cell);
		table2.addCell(message.getMessage("text.cliente.factura.folio") + ": " + invoice.getId());
		table2.addCell(message.getMessage("text.cliente.factura.descripcion") + ": " + invoice.getDescription());
		table2.addCell(message.getMessage("text.cliente.factura.fecha") + ": " + invoice.getCreateAt());

		
		
		
		
		PdfPTable table5 = new PdfPTable(1);
		
		table5.setSpacingAfter(0);
		cell = new PdfPCell(new Phrase(message.getMessage("text.factura.form.particulars")));
		cell.setPadding(8f);
		cell.setBackgroundColor(new Color(128, 128, 128));
		table5.addCell(cell);
		
		
		// Product
		PdfPTable table3 = new PdfPTable(5);
		
		table3.setWidths(new float[] { 1,3.5f, 1, 1, 1 });
		table3.addCell(message.getMessage("text.factura.form.serialnumber"));
		table3.addCell(message.getMessage("text.factura.form.item.nombre"));
		table3.addCell(message.getMessage("text.factura.form.item.precio"));
		table3.addCell(message.getMessage("text.factura.form.item.cantidad"));
		table3.addCell(message.getMessage("text.factura.form.item.total"));

		int serialNumber = 1; 
		
		for (ItemInvoice item : invoice.getItems()) {
			
			table3.addCell(String.valueOf(serialNumber++));
			table3.addCell(item.getProduct().getName());
			table3.addCell(item.getProduct().getPrice().toString());
			cell = new PdfPCell(new Phrase(item.getAmount().toString()));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table3.addCell(cell);
			table3.addCell(item.calculateImport().toString());
		}
		

		// Table Labour
		PdfPTable table4 = new PdfPTable(5);
		table4.setWidths(new float[] { 1,3.5f, 1, 1, 1 });
		table4.addCell(message.getMessage("text.factura.form.serialnumber"));
		table4.addCell(message.getMessage("text.factura.form.item.labour"));
		table4.addCell(message.getMessage("text.factura.form.item.precio"));
		table4.addCell(message.getMessage("text.factura.form.item.cantidad"));
		table4.addCell(message.getMessage("text.factura.form.item.total"));

		int serialNumber2 = 1; 
		for (ItemInvoice2 item : invoice.getItems2()) {
			table4.addCell(String.valueOf(serialNumber2++));
			table4.addCell(item.getLabour().getName());
			table4.addCell(item.getLabour().getPrice().toString());
			cell = new PdfPCell(new Phrase(item.getAmounts().toString()));
			
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table4.addCell(cell);
			table4.addCell(item.calculateImport2().toString());
		}

		cell = new PdfPCell(new Phrase(message.getMessage("text.factura.form.total")));
		cell.setColspan(4);
		cell.setPadding(8f);
		cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
		table3.setSpacingAfter(200);
		table3.addCell(cell);
		
		table3.addCell(invoice.getTotal().toString());
		
		
		
		
		
		// Footer Section
				PdfPTable footTable = new PdfPTable(1);
				
				/*
				 * footTable.setWidthPercentage(80); footTable.setSpacingAfter(20);
				 */

				PdfPCell footCell = new PdfPCell(new Phrase("Declaration"+  "              "+"                                                                 Authority Sign"));
				footCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		
				
				footTable.addCell(footCell);
				
				// Add name, address, and email to the header table
				
				footTable.addCell(createCell("E & O.E",Element.ALIGN_LEFT));
				footTable.addCell(createCell("1. Goods Once Sold not taken Back.",Element.ALIGN_LEFT));
				footTable.addCell(createCell("2. Subject to Nashik Jurisdiction",Element.ALIGN_LEFT));
				footTable.addCell(createCell("we Declare that this invoice shows the actual price of the goods",Element.ALIGN_LEFT));
				footTable.addCell(createCell("Description and that all Particulars are True and Correct. ",Element.ALIGN_LEFT));
				
		
		
		
		
		document.add(headerTable);

		document.add(table);
		document.add(table2);
		document.add(table5);
		document.add(table4);
		document.add(table3);
		document.add(footTable);
			}

	private static PdfPCell createCell(String text, int alignLeft) {
		PdfPCell cell = new PdfPCell(new Phrase(text));
		cell.setBorder(Rectangle.NO_BORDER); // Optional: Remove cell borders
		return cell;
	}

}
