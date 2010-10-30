package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;

public class MainFrame extends JFrame {

	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JTree navigationTree;
	private JMenu menu;
	private JMenuBar menuBar;
	private static final long serialVersionUID = -1960464005712732926L;

	public static void main(String[] args) {
		new MainFrame();
	}

	public MainFrame() {
		this.setSize(1000, 500);

		menuBar = new JMenuBar();

		menu = new JMenu("menu");
		menu.add(new JMenuItem("hello"));
		menu.add(new JMenuItem("hello2"));
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
