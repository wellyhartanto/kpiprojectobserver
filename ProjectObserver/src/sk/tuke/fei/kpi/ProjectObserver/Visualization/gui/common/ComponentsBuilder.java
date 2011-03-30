package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.jdesktop.swingx.JXHyperlink;

public class ComponentsBuilder {

	public static JLabel createErrorLabel(String text) {

		JLabel errorLabel = new JLabel();
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(CommonFonts.tahoma11);
		errorLabel.setText(text);
		return errorLabel;

	}

	public static JLabel createDiagramLabel(String text) {

		JLabel label = new JLabel();
		label.setForeground(Color.DARK_GRAY);
		label.setFont(CommonFonts.tahomaBold10);
		label.setText(text);
		return label;

	}

	public static JButton createLoginPanelButton(String text) {
		Font buttonsFont = CommonFonts.tahomaBold14;
		Dimension buttonsSize = new Dimension(120, 35);

		JButton button = new JButton(text);
		button.setBackground(CommonColors.LOGINPANEL_BUTTON_COLOR);
		button.setForeground(CommonColors.LOGINPANEL_BUTTON_TEXT_COLOR);
		button.setFont(buttonsFont);
		button.setMinimumSize(buttonsSize);
		return button;
	}

	public static JXHyperlink createLoginAboutHyperlink(String text) {

		JXHyperlink hyperlink = new JXHyperlink();
		hyperlink.setText(text);
		hyperlink.setForeground(CommonColors.LOGIN_ABOUT_HYPERLINK_COLOR);
		hyperlink.setFont(CommonFonts.tahomaBold14);
		return hyperlink;

	}

}
