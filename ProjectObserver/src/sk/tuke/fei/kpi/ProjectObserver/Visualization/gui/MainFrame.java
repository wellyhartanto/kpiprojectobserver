package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame {

	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JTree navigationTree;
	private JMenu menu;
	private JMenuBar menuBar;
	private ResourceBundle bundle;

	private static final long serialVersionUID = -1960464005712732926L;

	public static void main(String[] args) {
		 try {
			UIManager.setLookAndFeel(
			            UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		new MainFrame();
	}

	public MainFrame() {
		this.setSize(1000, 500);
		
		//setLocale(new Locale("en"));
			setLocale(new Locale("sk"));
		
		
		bundle = ResourceBundle.getBundle("sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.resources.resources",getLocale());

		menuBar = new JMenuBar();

		menu = new JMenu(bundle.getString("main.menu.text"));
		menu.add(new JMenuItem(bundle.getString("main.menu.openfile")));
		menu.add(new JMenuItem(bundle.getString("main.menu.close")));
		menuBar.add(menu);
		setJMenuBar(menuBar);

		scrollPane = new JScrollPane();

		splitPane = new JSplitPane();
		splitPane.setLeftComponent(scrollPane);

		navigationTree = new JTree();
		scrollPane.setViewportView(navigationTree);

		getContentPane().add(splitPane);
		setVisible(true);
	}

}
