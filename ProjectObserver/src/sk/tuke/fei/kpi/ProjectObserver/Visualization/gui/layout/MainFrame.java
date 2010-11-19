package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.NavigationJTreeCellRenderer;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.ClassesTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.EnumsTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.InterfacesTableModel;

public class MainFrame extends JFrame  {

	
	
	
	
	private JSplitPane splitPane;
	private JScrollPane leftScrollPane;
	private JPanel rightPanel;
	private JTree navigationTree;
	private JMenu menu;
	private JMenuBar menuBar;
	private ResourceBundle bundle;
	private ClassesTableModel classesTableModel;
	private InterfacesTableModel interfacesTableModel;
	private EnumsTableModel enumsTableModel;
	
	private JTabbedPane tabbedPane;

	private static final long serialVersionUID = -1960464005712732926L;
	private JScrollPane scrollPaneClasses;
	private JScrollPane scrollPaneInterfaces;
	private JScrollPane scrollPaneEnums;
	private JXTable classesTable;
	private JXTable interfacesTable;
	private JXTable enumsTable;

	private static final String IMAGES_FOLDER_PATH = "src/sk/tuke/fei/kpi/ProjectObserver/Visualization/gui/resources/images/";

	private ImageIcon iconPackage;
	private ImageIcon iconInterface;
	private ImageIcon iconClass;
	private ImageIcon iconEnum;
	
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
		setName("myFirstframe");
		
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
		menuBar.setName("menuBar");

		menu = new JMenu(bundle.getString("main.menu.text"));
		menu.add(new JMenuItem(bundle.getString("main.menu.openfile")));
		menu.add(new JMenuItem(bundle.getString("main.menu.close")));
		menuBar.add(menu);
		setJMenuBar(menuBar);

		createTree();

		leftScrollPane = new JScrollPane();
		leftScrollPane.setName("leftScrollPane");
	
		leftScrollPane.setViewportView(navigationTree);
		
		
		rightPanel = new JPanel(new MigLayout("wrap 3", "[grow,fill,left]",
				"[grow,fill]2[grow,fill]"));
		rightPanel.setName("rightPanel");
		
		scrollPaneClasses = new JScrollPane();

		scrollPaneClasses.setViewportView(classesTable);

		scrollPaneInterfaces = new JScrollPane();
		scrollPaneInterfaces.setViewportView(interfacesTable);

		scrollPaneEnums = new JScrollPane();
		scrollPaneEnums.setViewportView(enumsTable);
		
	
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(MyFonts.font2);

		iconPackage = new ImageIcon(IMAGES_FOLDER_PATH + "package_obj.gif");
		iconInterface = new ImageIcon(IMAGES_FOLDER_PATH + "int_obj.gif");
		iconClass = new ImageIcon(IMAGES_FOLDER_PATH + "classes.gif");
		iconEnum = new ImageIcon(IMAGES_FOLDER_PATH + "enum_obj.gif");
	
		tabbedPane.addTab(bundle.getString("title.classes"),iconClass,scrollPaneClasses);
		
		tabbedPane.addTab(bundle.getString("title.interfaces"),iconInterface, scrollPaneInterfaces);

		tabbedPane.addTab(bundle.getString("title.enums"), iconEnum,scrollPaneEnums);
		
		rightPanel.add(tabbedPane);
		splitPane = new JSplitPane();
		splitPane.setName("splitPane");
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
	
	private void createEnumsTable(Package selectedpackage) {

		List<Enum> enums = selectedpackage.getEnums();

		enumsTableModel = new EnumsTableModel();
		enumsTableModel.setEnums(enums);

		enumsTable = new JXTable(enumsTableModel);

		enumsTable.setRolloverEnabled(true);
		enumsTable.setHorizontalScrollEnabled(true);
		enumsTable.setFillsViewportHeight(true);
		enumsTable.setEditable(true);
		enumsTable.getColumnExt(0).setTitle("nazov stlpca");

	}
	
	
	
	
	private DefaultMutableTreeNode createTree(Package p){
		DefaultMutableTreeNode packagex = new DefaultMutableTreeNode(p);
		
		for (Package s2 : p.getPackages()) {
			DefaultMutableTreeNode subpackagex = createTree(s2);
			packagex.add(subpackagex);

		}

		for (Class s2 : p.getClasses()) {
			DefaultMutableTreeNode classx = new DefaultMutableTreeNode(s2);
			packagex.add(classx);

		}

		
		for (Interface s2 : p.getInterfaces()) {
			DefaultMutableTreeNode interfacex = new DefaultMutableTreeNode(s2);
			packagex.add(interfacex);

		}
		
		for (Enum s2 : p.getEnums()) {
			DefaultMutableTreeNode enumx = new DefaultMutableTreeNode(s2);
			packagex.add(enumx);

		}
		
	
		
		
		
		
		return packagex;
	}

	private void createTree() {

		List<Package> packages = createTestData();

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Model");
		for (Package p : packages) {
			DefaultMutableTreeNode packagex = createTree(p);

			root.add(packagex);

		


		}
		navigationTree = new JTree(root);
		

		navigationTree.setCellRenderer(new NavigationJTreeCellRenderer());

		navigationTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		navigationTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				treeValueChangedAction();
				
			}
			
		});
	}
	
	
	private void treeValueChangedAction(){


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
			createEnumsTable((Package)nodeInfo);
			
			scrollPaneClasses.setViewportView(classesTable);
			scrollPaneInterfaces.setViewportView(interfacesTable);
			scrollPaneEnums.setViewportView(enumsTable);
			
			tabbedPane.addTab(bundle.getString("title.classes"),iconClass,scrollPaneClasses);
			
			tabbedPane.addTab(bundle.getString("title.interfaces"),iconInterface, scrollPaneInterfaces);

			tabbedPane.addTab(bundle.getString("title.enums"), iconEnum,scrollPaneEnums);
			
		}
		if (nodeInfo instanceof Class) {
			tabbedPane.removeAll();
			
	//		classesTable.getColumnExt(0).setTitle("novy nazov");
	//		classesTable.setVisible(false);
	//		classesTable.repaint();
		}
		if (nodeInfo instanceof Class) {
			tabbedPane.removeAll();
			}
		if (nodeInfo instanceof Class) {
			tabbedPane.removeAll();
			}

	
	
	}

	public List<Package> createTestData() {

		List<Package> packages = new ArrayList<Package>();

		for (int i = 0; i < 7; i++) {
			Package p = new Package();
			p.setName("sk.tuke.fei.kpi.package" + i);
			List<Class> cl = new ArrayList<Class>();
			for (int j = 0; j < 7; j++) {
				Class c = new Class();
				c.setName("class" + i + "." + j);
				cl.add(c);
			}
			List<Interface> inf = new ArrayList<Interface>();
			for (int j = 0; j < 5; j++) {
				Interface c = new Interface();
				c.setName("interface" + i + "." + j);
				inf.add(c);
			}

			
			List<Enum> en = new ArrayList<Enum>();
			for (int j = 0; j < 4; j++) {
				Enum c = new Enum();
				c.setName("enum" + i + "." + j);
				en.add(c);
			}
			
			List<Package> pcg = new ArrayList<Package>();
			for (int j = 0; j < 3; j++) {
				Package c = new Package();
				c.setName(p.getName()+"sub" + i + "." + j);
				pcg.add(c);
				
				List<Class> cl2 = new ArrayList<Class>();
				for (int z = 0; z < 3; z++) {
					Class c2 = new Class();
					c2.setName("class" + i + "." + z);
					cl2.add(c2);
				}
				List<Interface> inf2 = new ArrayList<Interface>();
				for (int z = 0; z < 2; z++) {
					Interface c2 = new Interface();
					c2.setName("interface" + i + "." + z);
					inf2.add(c2);
				}

				
				List<Enum> en2 = new ArrayList<Enum>();
				for (int z = 0; z < 1; z++) {
					Enum c2 = new Enum();
					c2.setName("enum" + i + "." + z);
					en2.add(c2);
				}
				
				c.setClasses(cl2);
				c.setInterfaces(inf2);
				c.setEnums(en2);
				pcg.add(c);
				
				
			}
			
			p.setPackages(pcg);
			p.setClasses(cl);
			p.setInterfaces(inf);
			p.setEnums(en);
			packages.add(p);
		}

		return packages;
	}

}
