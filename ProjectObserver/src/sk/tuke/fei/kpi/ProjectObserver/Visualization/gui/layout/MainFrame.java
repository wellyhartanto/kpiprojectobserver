package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.TestData;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonConstants;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.ClassesPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.EnumValuesPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.EnumsPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.ExceptionsPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.FieldsPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.InfoPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.InterfacesPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.MethodParamsPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.MethodsPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.PackagesPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.NavigationJTreeCellRenderer;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.GenericTableModel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1960464005712732926L;

	private JSplitPane splitPane;
	private JScrollPane leftScrollPane;
	private JPanel rightPanel;
	private JTree navigationTree;
	private JMenu menu;
	private JMenuBar menuBar;

	private JTabbedPane tabbedPane;

	private ImageIcon iconPackage;
	private ImageIcon iconInterface;
	private ImageIcon iconClass;
	private ImageIcon iconEnum;
	private ImageIcon iconMethod;
	private ImageIcon iconField;
	private ImageIcon iconEnumValue;
	private ImageIcon iconInfo;

	public MainFrame() {
		// initComponents();
		initMenu();

		// pack();
		setVisible(true);

	}

	private void initMenu() {

		setBounds(100, 100, 1000, 750);
		menuBar = new JMenuBar();
		menuBar.setName("menuBar");

		menu = new JMenu(MyResourceBundle.getMessage("main.menu.text"));
		JMenuItem open = new JMenuItem(MyResourceBundle.getMessage("main.menu.openfile"));
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fch = new JFileChooser();
				int retvalue = fch.showOpenDialog(MainFrame.this);

				if (retvalue == JFileChooser.APPROVE_OPTION) {

					initComponents();
				}
			}
		});

		menu.add(open);
		// menu.add(new
		// JMenuItem(MyResourceBundle.getMessage("main.menu.close")));
		menuBar.add(menu);

		setJMenuBar(menuBar);

		initComponents();

	}

	private void initComponents() {

		createTree();

		leftScrollPane = new JScrollPane();
		leftScrollPane.setName("leftScrollPane");

		leftScrollPane.setViewportView(navigationTree);

		rightPanel = new JPanel(new MigLayout("wrap 3", "[grow,fill,left]", "[grow,fill]2[grow,fill]"));
		rightPanel.setName("rightPanel");

		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(MyFonts.font2);

		iconPackage = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "package_obj.gif"));

		iconInterface = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "int_obj.gif"));
		iconClass = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "classes.gif"));
		iconEnum = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "enum_obj.gif"));
		iconMethod = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "method_obj.gif"));
		iconField = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "field_obj.gif"));
		iconEnumValue = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "enum_value_obj.gif"));
		iconInfo = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "information.gif"));

		rightPanel.add(tabbedPane);
		splitPane = new JSplitPane();
		splitPane.setName("splitPane");
		splitPane.setLeftComponent(leftScrollPane);
		splitPane.setRightComponent(rightPanel);

		getContentPane().add(splitPane);

		setVisible(true);
		TreePath tp = navigationTree.getPathForRow(0);
		navigationTree.setSelectionPath(tp);

	}

	private void documentTableMouseClicked(MouseEvent e, JXTable table) {
		if (e.getClickCount() == 2) {
			setDetailSelection(table);
		}
	}

	private void setDetailSelection(JXTable table) {

		int selectedrow = table.getSelectedRow();
		if (selectedrow != -1) {

			GenericTableModel<Object> p = (GenericTableModel<Object>) table.getModel();

			setSelected(p.getData().get(selectedrow));
		}
	}

	private void setSelected(Object o) {

		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = navigationTree.getPathForRow(0);

		parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());

		parentNode.getLeafCount();

		parentNode.getUserObjectPath();

		Enumeration<DefaultMutableTreeNode> e = parentNode.breadthFirstEnumeration();
		while (e.hasMoreElements()) {

			DefaultMutableTreeNode dmtn = e.nextElement();
			if (dmtn.getUserObject().equals(o)) {
				parentPath = new TreePath(dmtn.getPath());
				navigationTree.getSelectionModel().setSelectionPath(parentPath);
			}

		}

	}

	private DefaultMutableTreeNode createPackageTree(Package p) {
		DefaultMutableTreeNode packagex = new DefaultMutableTreeNode(p);

		for (Package s2 : p.getPackages()) {
			DefaultMutableTreeNode subpackagex = createPackageTree(s2);
			packagex.add(subpackagex);

		}

		for (Class s2 : p.getClasses()) {
			DefaultMutableTreeNode classx = createClassTree(s2);
			packagex.add(classx);

		}

		for (Interface s2 : p.getInterfaces()) {
			DefaultMutableTreeNode interfacex = createInterfaceTree(s2);
			packagex.add(interfacex);

		}

		for (Enum s2 : p.getEnums()) {
			DefaultMutableTreeNode enumx = new DefaultMutableTreeNode(s2);
			packagex.add(enumx);

		}

		return packagex;
	}

	private DefaultMutableTreeNode createClassTree(Class c) {
		DefaultMutableTreeNode classx = new DefaultMutableTreeNode(c);

		for (Method m : c.getMethods()) {
			DefaultMutableTreeNode methodx = new DefaultMutableTreeNode(m);
			classx.add(methodx);
		}
		for (Field f : c.getFields()) {
			DefaultMutableTreeNode fieldx = new DefaultMutableTreeNode(f);
			classx.add(fieldx);
		}

		return classx;
	}

	private DefaultMutableTreeNode createInterfaceTree(Interface c) {
		DefaultMutableTreeNode classx = new DefaultMutableTreeNode(c);

		for (Method m : c.getMethods()) {
			DefaultMutableTreeNode methodx = new DefaultMutableTreeNode(m);
			classx.add(methodx);
		}
		for (Field f : c.getFields()) {
			DefaultMutableTreeNode fieldx = new DefaultMutableTreeNode(f);
			classx.add(fieldx);
		}

		return classx;
	}

	private DefaultMutableTreeNode createApplicationTree(Application p) {
		DefaultMutableTreeNode packagex = new DefaultMutableTreeNode(p);

		for (Package s2 : p.getPackages()) {
			DefaultMutableTreeNode subpackagex = createPackageTree(s2);
			packagex.add(subpackagex);

		}

		for (Class s2 : p.getClasses()) {
			DefaultMutableTreeNode classx = createClassTree(s2);
			packagex.add(classx);

		}

		for (Interface s2 : p.getInterfaces()) {
			DefaultMutableTreeNode interfacex = createInterfaceTree(s2);
			packagex.add(interfacex);

		}

		for (Enum s2 : p.getEnums()) {
			DefaultMutableTreeNode enumx = new DefaultMutableTreeNode(s2);
			packagex.add(enumx);

		}

		return packagex;
	}

	private void createTree() {

		Application app = TestData.createTestData();

		DefaultMutableTreeNode root = createApplicationTree(app);

		navigationTree = new JTree(root);

		navigationTree.setCellRenderer(new NavigationJTreeCellRenderer());

		navigationTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		navigationTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				treeValueChangedAction();

			}

		});

	}

	private void treeValueChangedAction() {

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) navigationTree.getLastSelectedPathComponent();

		/* if nothing is selected */
		if (node == null)
			return;

		/* retrieve the node that was selected */
		Object nodeInfo = node.getUserObject();

		if (nodeInfo instanceof Application) {
			tabbedPane.removeAll();

			tabbedPane.addTab(MyResourceBundle.getMessage("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
					.asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.packages"), iconPackage, new PackagesPanelPresenter(
					((Application) nodeInfo).getPackages()).getDisplay().asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.classes"), iconClass, new ClassesPanelPresenter(
					((Application) nodeInfo).getClasses()).getDisplay().asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.interfaces"), iconInterface, new InterfacesPanelPresenter(
					((Application) nodeInfo).getInterfaces()).getDisplay().asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.enums"), iconEnum, new EnumsPanelPresenter(
					((Application) nodeInfo).getEnums()).getDisplay().asComponent());

		}

		if (nodeInfo instanceof Package) {
			tabbedPane.removeAll();

			tabbedPane.addTab(MyResourceBundle.getMessage("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
					.asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.packages"), iconPackage, new PackagesPanelPresenter(
					((Package) nodeInfo).getPackages()).getDisplay().asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.classes"), iconClass, new ClassesPanelPresenter(
					((Package) nodeInfo).getClasses()).getDisplay().asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.interfaces"), iconInterface, new InterfacesPanelPresenter(
					((Package) nodeInfo).getInterfaces()).getDisplay().asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.enums"), iconEnum, new EnumsPanelPresenter(((Package) nodeInfo)
					.getEnums()).getDisplay().asComponent());

		}
		if (nodeInfo instanceof Class) {
			tabbedPane.removeAll();

			tabbedPane.addTab(MyResourceBundle.getMessage("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
					.asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.methods"), iconMethod, new MethodsPanelPresenter(
					((Class) nodeInfo).getMethods()).getDisplay().asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.fields"), iconField, new FieldsPanelPresenter(((Class) nodeInfo)
					.getFields()).getDisplay().asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.classes"), iconClass, new ClassesPanelPresenter(
					((Class) nodeInfo).getClasses()).getDisplay().asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.enums"), iconEnum, new EnumsPanelPresenter(((Class) nodeInfo)
					.getEnums()).getDisplay().asComponent());

		}
		if (nodeInfo instanceof Interface) {
			tabbedPane.removeAll();

			tabbedPane.addTab(MyResourceBundle.getMessage("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
					.asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.methods"), iconMethod, new MethodsPanelPresenter(
					((Interface) nodeInfo).getMethods()).getDisplay().asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.fields"), iconField, new FieldsPanelPresenter(
					((Interface) nodeInfo).getFields()).getDisplay().asComponent());

		}
		if (nodeInfo instanceof Enum) {
			tabbedPane.removeAll();

			tabbedPane.addTab(MyResourceBundle.getMessage("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
					.asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.values"), iconEnumValue, new EnumValuesPresenter(
					((Enum) nodeInfo).getValues()).getDisplay().asComponent());

		}

		if (nodeInfo instanceof Method) {
			tabbedPane.removeAll();

			tabbedPane.addTab(MyResourceBundle.getMessage("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
					.asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.params"), iconInfo, new MethodParamsPresenter(
					((Method) nodeInfo).getParams()).getDisplay().asComponent());
			tabbedPane.addTab(MyResourceBundle.getMessage("title.exceptions"), iconInfo, new ExceptionsPanelPresenter(
					((Method) nodeInfo).getExceptions()).getDisplay().asComponent());

		}
		if (nodeInfo instanceof Field) {
			tabbedPane.removeAll();
			tabbedPane.addTab(MyResourceBundle.getMessage("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
					.asComponent());
		}

	}

}
