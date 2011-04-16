package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class CommonFonts {

	private static Font bold = null;
	private static Font normal = null;
	private static CommonFonts instance;

	private static CommonFonts getInstance() {
		if (instance == null) {
			instance = new CommonFonts();
		}
		return instance;
	}

	private Font getLucidaGrandeBold() {
		if (bold == null) {
			try {
				File dir = new File(System.getProperty("java.io.tmpdir"));
				dir.mkdirs();
				File xmlFile = new File(dir, "LucidaGrandeBold.ttf");
				InputStream in =getClass().getResourceAsStream("LucidaGrandeBold.ttf");
				FileOutputStream out = new FileOutputStream(xmlFile);
				int numRead;

				while ((numRead = in.read()) >= 0) {
					out.write(numRead);
				}
				out.close();

				// File file = new File(CommonConstants.FONTS_FOLDER_PATH +
				// "LucidaGrandeBold.ttf");
				InputStream fis;

				System.out.println("citam bold");

				fis = new FileInputStream(xmlFile);
				bold = Font.createFont(Font.TRUETYPE_FONT, fis);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bold;
	}

	private Font getLucidaGrande() {
		if (normal == null) {

			try {
				File dir = new File(System.getProperty("java.io.tmpdir"));
				dir.mkdirs();
				File xmlFile = new File(dir, "LucidaGrande.ttf");
				InputStream in = getClass().getResourceAsStream("LucidaGrande.ttf");
				FileOutputStream out = new FileOutputStream(xmlFile);
				int numRead;

				while ((numRead = in.read()) >= 0) {
					out.write(numRead);
				}
				out.close();

				// File file = new File(CommonConstants.FONTS_FOLDER_PATH +
				// "LucidaGrande.ttf");
				InputStream fis;

				System.out.println("citam font");

				fis = new FileInputStream(xmlFile);
				normal = Font.createFont(Font.TRUETYPE_FONT, fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return normal;
	}

	public static Font getTableHeaderFont() {
		Font f = getInstance().getLucidaGrandeBold().deriveFont(12f);
		return f;
	}

	public static Font getTableContentFont() {
		Font f = getInstance().getLucidaGrande().deriveFont(12f);
		return f;
	}

	public static Font getTreeContentFont() {
		Font f = getInstance().getLucidaGrande().deriveFont(12f);
		return f;
	}

	public static Font getButtonFont() {
		Font f = getInstance().getLucidaGrande().deriveFont(13f);
		return f;
	}

	public static Font getNormalTextFont() {
		Font f = getInstance().getLucidaGrande().deriveFont(13f);
		return f;
	}

	public static Font getErrorTextFont() {
		Font f = getInstance().getLucidaGrande().deriveFont(11f);
		return f;
	}

	public static Font getTabFont() {
		Font f = getInstance().getLucidaGrande().deriveFont(13f);
		return f;
	}

	public static Font getHyperlinkFont() {
		Font f = getInstance().getLucidaGrandeBold().deriveFont(12f);
		return f;
	}

	public static Font getInfoLabelFont() {
		Font f = getInstance().getLucidaGrandeBold().deriveFont(18f);
		return f;
	}

	public static Font getToolTipFont() {
		Font f = getInstance().getLucidaGrande().deriveFont(11f);
		return f;
	}

	public static Font getAboutTextFont() {
		Font f = getInstance().getLucidaGrande().deriveFont(12f);
		return f;
	}

}
