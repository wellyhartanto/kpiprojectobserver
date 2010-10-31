package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.Label;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.PackageTableModel;

public class MainFrame extends JFrame {

	private JSplitPane splitPane;
	private JScrollPane leftScrollPane;
	private JPanel rightPanel;
	private JTree navigationTree;
	private JMenu menu;
	private JMenuBar menuBar;
	private ResourceBundle bundle;
	private PackageTableModel packageTableModel;
	private JXTable packageTable;

	private static final long serialVersionUID = -1960464005712732926L;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

			e.printStackTrace();
		}

		new MainFrame();
	}

	public MainFrame() {
		this.setSize(1000, 500);

		// setLocale(new Locale("en"));
		setLocale(new Locale("sk"));

		bundle = ResourceBundle
				.getBundle(
						"sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.resources.resources",
						getLocale());

		initComponents();
	}

	private void initComponents() {
		menuBar = new JMenuBar();

		menu = new JMenu(bundle.getString("main.menu.text"));
		menu.add(new JMenuItem(bundle.getString("main.menu.openfile")));
		menu.add(new JMenuItem(bundle.getString("main.menu.close")));
		menuBar.add(menu);
		setJMenuBar(menuBar);

		createTree();
		createPackageTable();

		leftScrollPane = new JScrollPane();
		leftScrollPane.setViewportView(navigationTree);

		rightPanel = new JPanel();
		rightPanel.setLayout(new MigLayout());
		rightPanel.add(packageTable, "span 2,wrap");

		rightPanel.add(new Label("ahoj ako sa mas a nejaky dalsi text"));
		rightPanel.add(new Label("ahoj ako sa mas a nejaky dalsi text2"));
		splitPane = new JSplitPane();
		splitPane.setLeftComponent(leftScrollPane);
		splitPane.setRightComponent(rightPanel);

		getContentPane().add(splitPane);
		setVisible(true);

	}

	private void createPackageTable() {

		List<sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class> classes = new ArrayList<sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class>();
		classes.add(new Class());
		classes.add(new Class());
		classes.add(new Class());
		classes.add(new Class());
		classes.add(new Class());

		packageTableModel = new PackageTableModel();
		packageTableModel.setClasses(classes);

		packageTable = new JXTable(packageTableModel);
		packageTable.setRolloverEnabled(true);
		packageTable.setHorizontalScrollEnabled(true);
		packageTable.setEditable(true);
		packageTable.getColumnExt(0).setTitle("nazov ");
		packageTable.getColumnExt(0).setHeaderValue("tab");

		packageTable.setVisible(true);

	}

	private void createTree() {

		List<String> packages = getPackages();

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Model");
		for (String s : packages) {
			DefaultMutableTreeNode child1 = new DefaultMutableTreeNode(s);
			root.add(child1);
			for (String s2 : packages) {
				DefaultMutableTreeNode child2 = new DefaultMutableTreeNode(s);
				child1.add(child2);

			}
		}
		navigationTree = new JTree(root);
	}

	private List<String> getPackages() {
		List<String> packages = new ArrayList<String>();
		packages.add("balik 1");
		packages.add("balik 2");
		packages.add("balik 3");
		packages.add("balik 4");

		return packages;
	}

}
