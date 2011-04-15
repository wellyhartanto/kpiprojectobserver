package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class CommonFonts {

	// public static Font dejavuSans13 = new Font("DejaVu Sans", Font.PLAIN,
	// 13);
	// public static Font dejavuSans12 = new Font("DejaVu Sans", Font.PLAIN,
	// 12);
	//
	// public static Font tahoma13 = new Font("Tahoma", Font.PLAIN, 13);
	//
	// public static Font segoeUIBold12 =new Font("Segoe UI", Font.BOLD, 12);
	// public static Font segoeUI11 =new Font("Segoe UI", Font.PLAIN, 11);
	// public static Font segoeUI12 =new Font("Segoe UI", Font.PLAIN, 12);
	//
	// public static Font helvetica12 =new Font("Helvetica", Font.PLAIN, 12);
	// public static Font test =new Font("Serif", Font.PLAIN, 12);

	private static Font bold = null;
	private static Font normal = null;

	private static Font getLucidaGrandeBold() {
		if (bold == null) {
			File file = new File(CommonConstants.FONTS_FOLDER_PATH + "LucidaGrandeBold.ttf");
			InputStream fis;
			try {

				System.out.println("citam bold");

				fis = new FileInputStream(file);
				bold = Font.createFont(Font.TRUETYPE_FONT, fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bold;
	}

	private static Font getLucidaGrande() {
		if (normal == null) {
			File file = new File(CommonConstants.FONTS_FOLDER_PATH + "LucidaGrande.ttf");
			InputStream fis;
			try {

				System.out.println("citam font");

				fis = new FileInputStream(file);
				normal = Font.createFont(Font.TRUETYPE_FONT, fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return normal;
	}

	public static Font getTableHeaderFont() {
		Font f = getLucidaGrandeBold().deriveFont(12f);
		return f;
	}

	public static Font getTableContentFont() {
		Font f = getLucidaGrande().deriveFont(12f);
		return f;
	}

	public static Font getTreeContentFont() {
		Font f = getLucidaGrande().deriveFont(12f);
		return f;
	}

	public static Font getButtonFont() {
		Font f = getLucidaGrande().deriveFont(13f);
		return f;
	}

	public static Font getNormalTextFont() {
		Font f = getLucidaGrande().deriveFont(13f);
		return f;
	}

	public static Font getErrorTextFont() {
		Font f = getLucidaGrande().deriveFont(11f);
		return f;
	}

	public static Font getTabFont() {
		Font f = getLucidaGrande().deriveFont(13f);
		return f;
	}
	
	public static Font getHyperlinkFont() {
		Font f = getLucidaGrandeBold().deriveFont(12f);
		return f;
	}
	
	public static Font getInfoLabelFont() {
		Font f = getLucidaGrandeBold().deriveFont(18f);
		return f;
	}
	public static Font getToolTipFont() {
		Font f = getLucidaGrande().deriveFont(11f);
		return f;
	}
	public static Font getAboutTextFont() {
		Font f = getLucidaGrande().deriveFont(12f);
		return f;
	}
	
	
}
