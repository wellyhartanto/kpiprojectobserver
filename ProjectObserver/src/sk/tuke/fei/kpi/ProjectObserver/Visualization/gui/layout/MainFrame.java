package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.BorderLayout;
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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
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

	private static final long serialVersionUID = -1960464005712732926L;
	private JScrollPane scrollPane;
	private JXTable packageTable;

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

		rightPanel = new JPanel(new BorderLayout());

		scrollPane = new JScrollPane();

		scrollPane.setViewportView(packageTable);
		//
		rightPanel.add(new Label("ahoj ako sa mas a nejaky dalsi text"),
				BorderLayout.NORTH);
		rightPanel.add(new Label(), BorderLayout.SOUTH);
		rightPanel.add(new Label(), BorderLayout.EAST);
		// rightPanel.add(new Label("ahoj ako sa mas a nejaky dalsi text2"));
		rightPanel.add(scrollPane, BorderLayout.CENTER);
		splitPane = new JSplitPane();
		splitPane.setLeftComponent(leftScrollPane);
		splitPane.setRightComponent(rightPanel);

		getContentPane().add(splitPane);
		setVisible(true);

	}

	private void createPackageTable() {

		List<sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class> classes = new ArrayList<sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class>();

		Class cla = new Class();
		cla.setName("prva klasa");
		classes.add(cla);
		cla = new Class();
		cla.setName("druha klasa");
		classes.add(cla);
		cla = new Class();
		cla.setName("tretia klasa");
		classes.add(cla);
		cla = new Class();
		cla.setName("stvrta klasa");
		classes.add(cla);
		cla = new Class();
		cla.setName("5 klasa");
		classes.add(cla);
		cla = new Class();
		cla.setName("6 klasa");
		classes.add(cla);
		cla = new Class();
		cla.setName("7 klasa");
		classes.add(cla);
		cla = new Class();
		cla.setName("8 klasa");
		classes.add(cla);
		cla = new Class();
		cla.setName("9 klasa");
		classes.add(cla);
		cla = new Class();
		cla.setName("10 klasa");
		// classes.add(cla);

		packageTableModel = new PackageTableModel();
		packageTableModel.setClasses(classes);

		packageTable = new JXTable(packageTableModel);

		packageTable.setRolloverEnabled(true);
		packageTable.setHorizontalScrollEnabled(true);
		packageTable.setFillsViewportHeight(true);
		packageTable.setEditable(true);
		packageTable.getColumnExt(0).setTitle("nazov stlpca");
	}

	private void createTree() {

		List<Package> packages = new ArrayList<Package>();

		for (int i = 0; i < 7; i++) {
			Package p = new Package();
			p.setName("package nejaky");
			List<Class> cl = new ArrayList<Class>();
			for (int j = 0; j < 7; j++) {
				Class c = new Class();
				c.setName("class");
				cl.add(c);
			}
			p.setClasses(cl);
			packages.add(p);
		}

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Model");
		for (Package s : packages) {
			DefaultMutableTreeNode child1 = new DefaultMutableTreeNode(s);
			root.add(child1);
			for (Class s2 : s.getClasses()) {
				DefaultMutableTreeNode child2 = new DefaultMutableTreeNode(s2);
				child1.add(child2);

			}
		}
		navigationTree = new JTree(root);
		navigationTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		navigationTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) navigationTree
						.getLastSelectedPathComponent();

				/* if nothing is selected */
				if (node == null)
					return;

				/* retrieve the node that was selected */
				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof Package) {

					createPackageTable();

				}
				if (nodeInfo instanceof Class) {
					packageTable.getColumnExt(0).setTitle("novy nazov");
					packageTable.setVisible(false);
					packageTable.repaint();
				}

			}
		});
	}
}
