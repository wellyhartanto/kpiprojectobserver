package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.jdesktop.swingx.JXHyperlink;

public class ComponentsBuilder {

	public static JLabel createErrorLabel(String text) {

		JLabel errorLabel = new JLabel();
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(CommonFonts.getErrorTextFont());
		errorLabel.setText(text);
		return errorLabel;

	}

	public static JLabel createDiagramLabel(String text) {

		JLabel label = new JLabel();
//		label.setForeground(Color.DARK_GRAY);
	//	label.setFont(CommonFonts.tahomaBold10);
		label.setText(text);
		return label;

	}

	public static JButton createLoginPanelButton(String text) {
		Dimension buttonsSize = new Dimension(120, 25);

		JButton button = new JButton(text);
//		button.setBackground(CommonColors.LOGINPANEL_BUTTON_COLOR);
//		button.setForeground(CommonColors.LOGINPANEL_BUTTON_TEXT_COLOR);
		button.setFont(CommonFonts.getButtonFont());
		button.setMinimumSize(buttonsSize);
		return button;
	}

	public static JXHyperlink createLoginAboutHyperlink(String text) {

		JXHyperlink hyperlink = new JXHyperlink();
		hyperlink.setText(text);
		hyperlink.setForeground(CommonColors.LOGIN_ABOUT_HYPERLINK_COLOR);
		hyperlink.setFont(CommonFonts.getHyperlinkFont());
		return hyperlink;

	}

}
