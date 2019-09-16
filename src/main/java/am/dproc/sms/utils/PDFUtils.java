package am.dproc.sms.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;

public class PDFUtils {

	public static File manipulatePdf() throws Exception {
		File pdf = File.createTempFile("test", "pdf");
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdf));
		Document doc = new Document(pdfDoc);

		Table table = new Table(UnitValue.createPercentArray(8)).useAllAvailableWidth();
		for (int i = 0; i < 16; i++) {
			table.addCell("hi");
		}
		doc.add(table);

		doc.close();
		return pdf;
	}
}
