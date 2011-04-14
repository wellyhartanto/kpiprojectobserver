package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common;

import javax.swing.JLabel;

public class TabbedPaneLabel extends JLabel {

	public TabbedPaneLabel() {
		super();

		setFont(CommonFonts.getTabFont());
	}

	public TabbedPaneLabel(String message) {
		super(message);
		setFont(CommonFonts.getTabFont());
	}

}
