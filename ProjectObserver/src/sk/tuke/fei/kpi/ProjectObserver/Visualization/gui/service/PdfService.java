package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.service;

import java.awt.Color;
import java.io.FileOutputStream;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util.StringUtil;
import sk.tuke.fei.kpi.akAgent.integration.Project;
import sk.tuke.fei.kpi.akAgent.integration.alignment.difference.Difference;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfService {

	private static Document document;
	private static PdfWriter writer;

	private static void initExport(String file) {
		document = new Document(PageSize.A4, 50, 50, 50, 50);
		if (!file.endsWith(".pdf")) {
			file += ".pdf";
		}

		try {
			writer = PdfWriter.getInstance(document,
			// System.getProperty("user.home") + "\\"+
					new FileOutputStream(file));
			document.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void finishExport() {
		document.close();
		System.out.println("export finished");
	}

	public static void exportAllFromSource(Project project, String file) {
		initExport(file);

		for (Package pack : project.getJavaModel().getAllPackages()) {
			try {
				document.add(new Paragraph(pack.getFullName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, new BaseColor(new Color(92,
						51, 23)))));
				document.add(new Paragraph(""));

				for (Interface interfac : pack.getInterfaces()) {
					document.add(new Paragraph(StringUtil.convertInterfaceToString(interfac), FontFactory.getFont(FontFactory.TIMES_ROMAN,
							13, new BaseColor(new Color(148, 0, 211)))));
					document.add(new Paragraph(""));
					for (Field field : interfac.getFields()) {
						document
								.add(new Paragraph(StringUtil.convertFieldToString(field), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					}
					document.add(new Paragraph(""));
					for (Method method : interfac.getMethods()) {
						document.add(new Paragraph(StringUtil.convertMethodToString(method), FontFactory.getFont(FontFactory.TIMES_ROMAN,
								10)));
					}
					document.add(new Paragraph(""));
				}

				for (Class clas : pack.getClasses()) {
					document.add(new Paragraph(StringUtil.convertClassToString(clas), FontFactory.getFont(FontFactory.TIMES_ROMAN, 13,
							new BaseColor(new Color(34, 139, 34)))));
					document.add(new Paragraph(""));
					for (Field field : clas.getFields()) {
						document
								.add(new Paragraph(StringUtil.convertFieldToString(field), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));
					}
					document.add(new Paragraph(""));
					for (Method method : clas.getMethods()) {
						document.add(new Paragraph(StringUtil.convertMethodToString(method), FontFactory.getFont(FontFactory.TIMES_ROMAN,
								10)));
					}
					document.add(new Paragraph(""));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		finishExport();
	}

	public static void exportAllFromUML(Project project, String file) {
		initExport(file);

		for (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Package pack : project.getClassDiagram().getAllPackages()) {
			try {
				document.add(new Paragraph(pack.getFullName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, new BaseColor(new Color(92,
						51, 23)))));
				document.add(new Paragraph(""));

				for (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Interface interfac : pack.getInterfaces()) {
					document.add(new Paragraph(StringUtil.convertUMLInterfaceToString(interfac), FontFactory.getFont(
							FontFactory.TIMES_ROMAN, 13, new BaseColor(new Color(148, 0, 211)))));
					document.add(new Paragraph(""));
					for (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Field field : interfac.getFields()) {
						document.add(new Paragraph(StringUtil.convertUMLFieldToString(field), FontFactory.getFont(FontFactory.TIMES_ROMAN,
								10)));
					}
					document.add(new Paragraph(""));
					for (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Method method : interfac.getMethods()) {
						document.add(new Paragraph(StringUtil.convertUMLMethodToString(method), FontFactory.getFont(
								FontFactory.TIMES_ROMAN, 10)));
					}
					document.add(new Paragraph(""));
				}

				for (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Class clas : pack.getClasses()) {
					document.add(new Paragraph(StringUtil.convertUMLClassToString(clas), FontFactory.getFont(FontFactory.TIMES_ROMAN, 13,
							new BaseColor(new Color(34, 139, 34)))));
					document.add(new Paragraph(""));
					for (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Field field : clas.getFields()) {
						document.add(new Paragraph(StringUtil.convertUMLFieldToString(field), FontFactory.getFont(FontFactory.TIMES_ROMAN,
								10)));
					}
					document.add(new Paragraph(""));
					for (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Method method : clas.getMethods()) {
						document.add(new Paragraph(StringUtil.convertUMLMethodToString(method), FontFactory.getFont(
								FontFactory.TIMES_ROMAN, 10)));
					}
					document.add(new Paragraph(""));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		finishExport();
	}

	public static void exportMissing(Project project, String file) {
		initExport(file);

		for (Package pack : project.getJavaModel().getAllPackages()) {
			try {
				boolean differs = false;
				for (Class clas : pack.getClasses()) {
					Difference diff = project.getMappingHolder().getDifference(clas.getFullyQualifiedName());
					if (differs==false && diff.differs()) {
						differs = true;
						document.add(new Paragraph(pack.getFullName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, new BaseColor(
								new Color(92, 51, 23)))));
						document.add(new Paragraph(""));
					}
				}
				if (differs == false) {
					for (Interface interfac : pack.getInterfaces()) {
						Difference diff = project.getMappingHolder().getDifference(interfac.getFullyQualifiedName());
						if (diff.differs()) {
							document.add(new Paragraph(pack.getFullName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, new BaseColor(
									new Color(92, 51, 23)))));
							document.add(new Paragraph(""));
						}
					}
				}
				if (differs) {
					for (Class clas : pack.getClasses()) {

						Difference diff = project.getMappingHolder().getDifference(clas.getFullyQualifiedName());
						if (diff.differs()) {

							document.add(new Paragraph(StringUtil.convertClassToString(clas), FontFactory.getFont(FontFactory.TIMES_ROMAN,
									13, new BaseColor(new Color(34, 139, 34)))));
							document.add(new Paragraph(""));

							for (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Field field : diff.getMissingFields()) {
								document.add(new Paragraph(StringUtil.convertUMLFieldToString(field), FontFactory.getFont(
										FontFactory.TIMES_ROMAN, 10)));
							}
							document.add(new Paragraph(""));
							for (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Method method : diff.getMissingMethods()) {
								document.add(new Paragraph(StringUtil.convertUMLMethodToString(method), FontFactory.getFont(
										FontFactory.TIMES_ROMAN, 10)));

							}
							document.add(new Paragraph(""));
						}
					}

					for (Interface interfac : pack.getInterfaces()) {

						Difference diff = project.getMappingHolder().getDifference(interfac.getFullyQualifiedName());
						if (diff.differs()) {

							document.add(new Paragraph(StringUtil.convertInterfaceToString(interfac), FontFactory.getFont(
									FontFactory.TIMES_ROMAN, 13, new BaseColor(new Color(148, 0, 211)))));
							document.add(new Paragraph(""));

							for (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Field field : diff.getMissingFields()) {
								document.add(new Paragraph(StringUtil.convertUMLFieldToString(field), FontFactory.getFont(
										FontFactory.TIMES_ROMAN, 10)));
							}
							document.add(new Paragraph(""));
							for (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Method method : diff.getMissingMethods()) {
								document.add(new Paragraph(StringUtil.convertUMLMethodToString(method), FontFactory.getFont(
										FontFactory.TIMES_ROMAN, 10)));

							}
							document.add(new Paragraph(""));
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		finishExport();
	}

	public static void exportExtra(Project project, String file) {
		initExport(file);

		for (Package pack : project.getJavaModel().getAllPackages()) {
			try {
				boolean differs = false;
				for (Class clas : pack.getClasses()) {
					Difference diff = project.getMappingHolder().getDifference(clas.getFullyQualifiedName());
					if (differs==false && diff.differs()) {
						differs = true;
						document.add(new Paragraph(pack.getFullName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, new BaseColor(
								new Color(92, 51, 23)))));
						document.add(new Paragraph(""));
					}
				}
				if (differs == false) {
					for (Interface interfac : pack.getInterfaces()) {
						Difference diff = project.getMappingHolder().getDifference(interfac.getFullyQualifiedName());
						if (diff.differs()) {
							document.add(new Paragraph(pack.getFullName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, new BaseColor(
									new Color(92, 51, 23)))));
							document.add(new Paragraph(""));
						}
					}
				}
				if (differs) {
					for (Class clas : pack.getClasses()) {

						Difference diff = project.getMappingHolder().getDifference(clas.getFullyQualifiedName());
						if (diff.differs()) {

							document.add(new Paragraph(StringUtil.convertClassToString(clas), FontFactory.getFont(FontFactory.TIMES_ROMAN,
									13, new BaseColor(new Color(34, 139, 34)))));
							document.add(new Paragraph(""));

							for (sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field field : diff.getExtraFields()) {
								document.add(new Paragraph(StringUtil.convertFieldToString(field), FontFactory.getFont(
										FontFactory.TIMES_ROMAN, 10)));
							}
							document.add(new Paragraph(""));
							for (sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method method : diff.getExtraMethods()) {
								document.add(new Paragraph(StringUtil.convertMethodToString(method), FontFactory.getFont(
										FontFactory.TIMES_ROMAN, 10)));

							}
							document.add(new Paragraph(""));
						}
					}

					for (Interface interfac : pack.getInterfaces()) {

						Difference diff = project.getMappingHolder().getDifference(interfac.getFullyQualifiedName());
						if (diff.differs()) {

							document.add(new Paragraph(StringUtil.convertInterfaceToString(interfac), FontFactory.getFont(
									FontFactory.TIMES_ROMAN, 13, new BaseColor(new Color(148, 0, 211)))));
							document.add(new Paragraph(""));

							for (sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field field : diff.getExtraFields()) {
								document.add(new Paragraph(StringUtil.convertFieldToString(field), FontFactory.getFont(
										FontFactory.TIMES_ROMAN, 10)));
							}
							document.add(new Paragraph(""));
							for (sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method method : diff.getExtraMethods()) {
								document.add(new Paragraph(StringUtil.convertMethodToString(method), FontFactory.getFont(
										FontFactory.TIMES_ROMAN, 10)));

							}
							document.add(new Paragraph(""));
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		finishExport();
	}

}
