package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class JErrorPanel extends JPanel {
	private static final long serialVersionUID = -6467424007660707616L;

	private JLabel errorLabel;

	public JErrorPanel(JComponent component, String errorMessage) {

		setLayout(new MigLayout("insets 0","","[]1[]2"));

		errorLabel = ComponentsBuilder.createErrorLabel(errorMessage);
		errorLabel.setVisible(false);
		
		
		
		add(component, "wrap");
		add(errorLabel,"gapbefore 5");

	}

	public void showErrorMessage(boolean show) {

		if (show) {
			setBackground(Color.PINK);
			errorLabel.setVisible(true);
			
			
		} else {
			setBackground(new JLabel().getBackground());
			errorLabel.setVisible(false);
		}

	}

}
