package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

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

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.ClassesTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.InterfacesTableModel;

public class MainFrame extends JFrame {

	private JSplitPane splitPane;
	private JScrollPane leftScrollPane;
	private JPanel rightPanel;
	private JTree navigationTree;
	private JMenu menu;
	private JMenuBar menuBar;
	private ResourceBundle bundle;
	private ClassesTableModel classesTableModel;
	private InterfacesTableModel interfacesTableModel;

	private static final long serialVersionUID = -1960464005712732926L;
	private JScrollPane scrollPaneClasses;
	private JScrollPane scrollPaneInterfaces;
	private JXTable classesTable;
	private JXTable interfacesTable;

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

		leftScrollPane = new JScrollPane();
		leftScrollPane.setViewportView(navigationTree);

		rightPanel = new JPanel(new MigLayout("wrap 3", "[grow,fill,left]",
				"[grow,fill]2[grow,fill]"));

		scrollPaneClasses = new JScrollPane();

		scrollPaneClasses.setViewportView(classesTable);

		scrollPaneInterfaces = new JScrollPane();
		scrollPaneInterfaces.setViewportView(interfacesTable);

		//
		// rightPanel.add(new Label("ahoj ako sa mas a nejaky dalsi text"),
		// BorderLayout.NORTH);
		// rightPanel.add(new Label(), BorderLayout.SOUTH);
		// rightPanel.add(new Label(), BorderLayout.EAST);
		// rightPanel.add(new Label("ahoj ako sa mas a nejaky dalsi text2"));
		// rightPanel.add(scrollPane, BorderLayout.CENTER);

		rightPanel.add(scrollPaneClasses, "wrap");
		rightPanel.add(scrollPaneInterfaces);
		splitPane = new JSplitPane();
		splitPane.setLeftComponent(leftScrollPane);
		splitPane.setRightComponent(rightPanel);

		getContentPane().add(splitPane);
		setVisible(true);

	}

	private void createClassesTable(Package selectedpackage) {

		List<Class> classes = selectedpackage.getClasses();

		classesTableModel = new ClassesTableModel();
		classesTableModel.setClasses(classes);

		classesTable = new JXTable(classesTableModel);

		classesTable.setRolloverEnabled(true);
		classesTable.setHorizontalScrollEnabled(true);
		classesTable.setFillsViewportHeight(true);
		classesTable.setEditable(true);
		classesTable.getColumnExt(0).setTitle("nazov stlpca");
	}

	private void createInterfacesTable(Package selectedpackage) {

		List<Interface> interfaces = selectedpackage.getInterfaces();

		interfacesTableModel = new InterfacesTableModel();
		interfacesTableModel.setInterfaces(interfaces);

		interfacesTable = new JXTable(interfacesTableModel);

		interfacesTable.setRolloverEnabled(true);
		interfacesTable.setHorizontalScrollEnabled(true);
		interfacesTable.setFillsViewportHeight(true);
		interfacesTable.setEditable(true);
		interfacesTable.getColumnExt(0).setTitle("nazov stlpca");

	}

	private void createTree() {

		List<Package> packages = createTestData();

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Model");
		for (Package s : packages) {
			DefaultMutableTreeNode child1 = new DefaultMutableTreeNode(s);
			root.add(child1);

			DefaultMutableTreeNode classes = new DefaultMutableTreeNode(
					"Classes");
			child1.add(classes);

			for (Class s2 : s.getClasses()) {
				DefaultMutableTreeNode child2 = new DefaultMutableTreeNode(s2);
				classes.add(child2);

			}

			DefaultMutableTreeNode interfaces = new DefaultMutableTreeNode(
					"Iterfaces");
			child1.add(interfaces);
			for (Interface s2 : s.getInterfaces()) {
				DefaultMutableTreeNode child2 = new DefaultMutableTreeNode(s2);
				interfaces.add(child2);

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

					createClassesTable((Package) nodeInfo);
					createInterfacesTable((Package) nodeInfo);

					scrollPaneClasses.setViewportView(classesTable);
					scrollPaneInterfaces.setViewportView(interfacesTable);

				}
				if (nodeInfo instanceof Class) {
					classesTable.getColumnExt(0).setTitle("novy nazov");
					classesTable.setVisible(false);
					classesTable.repaint();
				}

			}

		});
	}

	public List<Package> createTestData() {

		List<Package> packages = new ArrayList<Package>();

		for (int i = 0; i < 7; i++) {
			Package p = new Package();
			p.setName("package" + i);
			List<Class> cl = new ArrayList<Class>();
			for (int j = 0; j < 7; j++) {
				Class c = new Class();
				c.setName("class" + i + "." + j);
				cl.add(c);
			}
			List<Interface> inf = new ArrayList<Interface>();
			for (int j = 0; j < 7; j++) {
				Interface c = new Interface();
				c.setName("interface" + i + "." + j);
				inf.add(c);
			}

			p.setClasses(cl);
			p.setInterfaces(inf);
			packages.add(p);
		}

		return packages;
	}

}
