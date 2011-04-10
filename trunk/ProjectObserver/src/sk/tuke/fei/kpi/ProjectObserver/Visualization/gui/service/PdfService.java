package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.service;

import java.awt.Color;
import java.io.FileOutputStream;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util.StringUtil;
import sk.tuke.fei.kpi.akAgent.integration.Project;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfService {

	private static Document document;
	private static PdfWriter writer;

	public static void main(String[] args) {
		document = new Document(PageSize.A4, 50, 50, 50, 50);

		try {
			writer = PdfWriter.getInstance(document,

			new FileOutputStream(System.getProperty("user.home") + "\\ITextTest.pdf"));
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

	private static void initExport(String file) {
		document = new Document(PageSize.A4, 50, 50, 50, 50);

		try {
			writer = PdfWriter.getInstance(document,

			new FileOutputStream(System.getProperty("user.home") + "\\"+file+".pdf"));
			document.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void finishExport(){
		document.close();
		System.out.println("export finished");
	}
	
	public static void exportAll(Project project, String file) {
		initExport(file);

		for (Package pack : project.getJavaModel().getAllPackages()) {
			try {
				document.add(new Paragraph(pack.getFullName(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, new BaseColor(new Color(92,51,23	)))));
				document.add(new Paragraph(""));
				for (Class clas : pack.getClasses()) {
					document.add(new Paragraph(StringUtil.convertClassToString(clas),FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, new BaseColor(new Color(34,139,34)))));
					document.add(new Paragraph(""));
					for (Field field : clas.getFields()) {
						document.add(new Paragraph(StringUtil.convertFieldToString(field),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					}
					document.add(new Paragraph(""));
					for (Method method : clas.getMethods()) {
						document.add(new Paragraph(StringUtil.convertMethodToString(method),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					}
					document.add(new Paragraph(""));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		finishExport();
	}

}
