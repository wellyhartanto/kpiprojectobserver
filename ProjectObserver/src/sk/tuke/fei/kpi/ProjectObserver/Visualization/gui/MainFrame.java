package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonConstants;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.LoginPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.LoginPanelView;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.MainPanelView;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 4546822550504805365L;

	private static MainFrame mainFrame;
	private JComponent actualcomponent;

	public MainFrame() {
		setBounds(100, 100, 1000, 750);
		ImageIcon frameIcon = new ImageIcon(getClass().getResource(
				CommonConstants.IMAGES_FOLDER_PATH + "icon.png"));
		setIconImage(frameIcon.getImage());

		actualcomponent = new JComponent(){};
		setLayout(new MigLayout("insets 0,fill", "[]", "[]"));

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowListener windowListener = new WindowAdapter() {
			public void windowClosing(WindowEvent w) {

				if (actualcomponent instanceof LoginPanelView) {
					MainFrame.this.setVisible(false);
					MainFrame.this.dispose();
				}
				if (actualcomponent instanceof MainPanelView) {
					setPanel(new LoginPanelPresenter().getDisplay()
							.asComponent());

				}

			}
		};
		this.addWindowListener(windowListener);

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
		add(jComponent, "span,growx,growy,top");
		setVisible(true);
	}

}
