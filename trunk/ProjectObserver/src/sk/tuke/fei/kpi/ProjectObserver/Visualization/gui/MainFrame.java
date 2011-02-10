package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui;

import javax.swing.JComponent;
import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.LoginPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.MainPanelPresenter;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 4546822550504805365L;

	private static MainFrame mainFrame;
	private JComponent actualcomponent;

	public MainFrame() {
		setBounds(100, 100, 1000, 750);

		setLayout(new MigLayout("insets 0,fill", "[]", "[]"));
		actualcomponent = new LoginPanelPresenter().getDisplay().asComponent();
		add(actualcomponent, "span,growx,growy");
		setVisible(true);
	}

	public static MainFrame getMainFrame() {

		if (mainFrame == null) {
			mainFrame = new MainFrame();
		}

		return mainFrame;

	}

	public void setPanel(JComponent jComponent) {

		remove(actualcomponent);
		actualcomponent = jComponent;
		setLayout(new MigLayout("insets 0,fill", "[]", "[]"));
		add(jComponent, "span,growx,growy");
		setVisible(true);
	}

}
