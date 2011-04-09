package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.service;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfService {

	
	
	public static void main(String[] args) {
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, 

			new FileOutputStream(System.getProperty("user.home")+"\\ITextTest.pdf"));
			document.open();
			
			document.add(new Paragraph("First page of the document."));

			document.add(new Paragraph("Some more text on the first page with different color and font type.", 

			FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
			document.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		document.open();
	}
	
}
