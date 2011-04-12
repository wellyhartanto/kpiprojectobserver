package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonConstants;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.MainPanelDisplay;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 4546822550504805365L;

	private static MainFrame mainFrame;
	private JComponent actualcomponent;
	
	private JScrollPane scrollPane;

	public MainFrame() {
		
		setSize(1000, 700);
		
//		ImageIcon frameIcon = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "icon.png"));
		
		ImageIcon frameIcon = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "iconblue.png"));

		
		setIconImage(frameIcon.getImage());
		setTitle("Project Observer");

		actualcomponent = new JComponent() {
		};
		setLayout(new MigLayout("insets 0,fill", "[]", "[]"));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				if (actualcomponent instanceof MainPanelDisplay) {
					((MainPanelDisplay) actualcomponent).saveWindowPrefs();
				}
			}
		});

		
		scrollPane = new JScrollPane();
	}

	public static MainFrame getMainFrame() {

		if (mainFrame == null) {
			mainFrame = new MainFrame();
		}

		return mainFrame;

	}

	public void setPanel(JComponent jComponent) {
		
//		remove(scrollPane);
//		actualcomponent = jComponent;
//		
//		scrollPane = new JScrollPane(jComponent);
//		if(jComponent instanceof MainPanelDisplay){
//		scrollPane.setName("scrollpanemain");
//		}
//		setContentPane(scrollPane);
//		setVisible(true);
		
		
		
		remove(actualcomponent);
		actualcomponent = jComponent;
		add(jComponent, "span,growx,growy,top");
		setVisible(true);
		actualcomponent.setVisible(true);
		
		  

	}

}
