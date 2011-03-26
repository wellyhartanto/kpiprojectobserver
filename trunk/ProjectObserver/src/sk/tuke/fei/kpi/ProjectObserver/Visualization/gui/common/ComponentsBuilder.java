package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;
 
import java.awt.Color;

import javax.swing.JLabel;

public class ComponentsBuilder {

	public static JLabel createErrorLabel(String text) {

		JLabel errorLabel = new JLabel();
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(MyFonts.tahoma11);
		errorLabel.setText(text);
 		return errorLabel;

	}

	
	public static JLabel createDiagramLabel(String text) {

		JLabel label = new JLabel();
		label.setForeground(Color.DARK_GRAY);
		label.setFont(MyFonts.tahomaBold10);
		label.setText(text);
 		return label;

	}
	
}
