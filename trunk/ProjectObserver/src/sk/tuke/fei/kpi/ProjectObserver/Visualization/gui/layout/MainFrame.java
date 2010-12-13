package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Param;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.TestData;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.InfoPanelPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.NavigationJTreeCellRenderer;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.GenericTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.ClassesTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.EnumValuesTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.EnumsTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.ExceptionsTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.FieldsTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.InterfacesTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.MethodsTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.PackagesTableModel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.ParamsTableModel;

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
    private EnumsTableModel enumsTableModel;
    private FieldsTableModel fieldsTableModel;
    private MethodsTableModel methodsTableModel;
    private EnumValuesTableModel enumValuesTableModel;
    private PackagesTableModel packagesTableModel;
    private ExceptionsTableModel exceptionsTableModel;
    private ParamsTableModel paramsTableModel;

    private JPanel infoPanel;

    private JTabbedPane tabbedPane;

    private JPanel languages;
    private final JRadioButton sklanguage;
    private final JRadioButton enlanguage;

    private static final long serialVersionUID = -1960464005712732926L;
    private JScrollPane scrollPaneClasses;
    private JScrollPane scrollPaneInterfaces;
    private JScrollPane scrollPaneEnums;
    private JScrollPane scrollPaneMethods;
    private JScrollPane scrollPaneFields;
    private JScrollPane scrollPaneEnumValues;
    private JScrollPane scrollPanePackages;
    private JScrollPane scrollPaneExceptions;
    private JScrollPane scrollPaneParams;

    private JXTable classesTable;
    private JXTable interfacesTable;
    private JXTable enumsTable;
    private JXTable fieldsTable;
    private JXTable methodsTable;
    private JXTable enumValuesTable;
    private JXTable packagesTable;
    private JXTable exceptionsTable;
    private JXTable paramsTable;

    private static final String IMAGES_FOLDER_PATH = "/sk/tuke/fei/kpi/ProjectObserver/Visualization/gui/resources/images/";

    private ImageIcon iconPackage;
    private ImageIcon iconInterface;
    private ImageIcon iconClass;
    private ImageIcon iconEnum;
    private ImageIcon iconMethod;
    private ImageIcon iconField;
    private ImageIcon iconEnumValue;
    private ImageIcon iconInfo;

    private Font tablesHeaderFont;

    public static void main(String[] args) {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {

	    e.printStackTrace();
	}

	new MainFrame();
    }

    public MainFrame() {

	ImageIcon kpilogo = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "icon.png"));

	setIconImage(kpilogo.getImage());

	setName("myFirstframe");
	setBounds(500, 200, 120, 120);
	// setLocale(new Locale("en"));
	Locale.setDefault(new Locale("sk"));

	bundle = MyResourceBundle.getResourceBundle(Locale.getDefault());

	languages = new JPanel(new MigLayout());
	sklanguage = new JRadioButton("Slovenský");
	sklanguage.setSelected(true);
	enlanguage = new JRadioButton("English");
	ButtonGroup lang = new ButtonGroup();
	lang.add(sklanguage);
	lang.add(enlanguage);
	JLabel languageLbl = new JLabel("Jazyk / Language");
	languageLbl.setFont(MyFonts.font4);
	languages.add(languageLbl, "wrap");
	languages.add(sklanguage, "wrap");
	languages.add(enlanguage, "wrap");
	JButton okBttn = new JButton("OK");
	okBttn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (enlanguage.isSelected()) {
		    Locale.setDefault(new Locale("en"));
		}
		bundle = MyResourceBundle.getResourceBundle(Locale.getDefault());
		initMenu();
		remove(languages);
	    }
	});
	languages.add(okBttn, "center,wrap");
	// languages.add(new JLabel(kpilogo), "center");

	add(languages);

	// initComponents();

	pack();
	setVisible(true);

    }

    private void initMenu() {

	setBounds(100, 100, 1000, 500);
	menuBar = new JMenuBar();
	menuBar.setName("menuBar");

	menu = new JMenu(bundle.getString("main.menu.text"));
	JMenuItem open = new JMenuItem(bundle.getString("main.menu.openfile"));
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
	// menu.add(new JMenuItem(bundle.getString("main.menu.close")));
	menuBar.add(menu);

	setJMenuBar(menuBar);

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

	iconPackage = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "package_obj.gif"));

	iconInterface = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "int_obj.gif"));
	iconClass = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "classes.gif"));
	iconEnum = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "enum_obj.gif"));
	iconMethod = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "method_obj.gif"));
	iconField = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "field_obj.gif"));
	iconEnumValue = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "enum_value_obj.gif"));
	iconInfo = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "information.gif"));

	tablesHeaderFont = MyFonts.font3;

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

    private void createPackagesTable(List<Package> packages) {

	packagesTableModel = new PackagesTableModel();
	packagesTableModel.setData(packages);

	packagesTable = new JXTable(packagesTableModel);
	packagesTable.getTableHeader().setFont(tablesHeaderFont);
	packagesTable.setRolloverEnabled(true);
	packagesTable.setHorizontalScrollEnabled(true);
	packagesTable.setFillsViewportHeight(true);
	packagesTable.setEditable(true);

	packagesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	SelectionListener listener = new SelectionListener(packagesTable);
	packagesTable.getSelectionModel().addListSelectionListener(listener);
	packagesTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

	packagesTable.addMouseListener(new MouseAdapter() {

	    @Override
	    public void mouseClicked(MouseEvent e) {
		documentTableMouseClicked(e, packagesTable);
	    }
	});

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

    private void createClassesTable(List<Class> classes) {

	// List<Class> classes = selectedpackage.getClasses();

	classesTableModel = new ClassesTableModel();
	classesTableModel.setData(classes);

	classesTable = new JXTable(classesTableModel);
	classesTable.getTableHeader().setFont(tablesHeaderFont);
	classesTable.setRolloverEnabled(true);
	classesTable.setHorizontalScrollEnabled(true);
	classesTable.setFillsViewportHeight(true);
	classesTable.setEditable(true);

	classesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	SelectionListener listener = new SelectionListener(classesTable);
	classesTable.getSelectionModel().addListSelectionListener(listener);
	classesTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

	classesTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		documentTableMouseClicked(e, classesTable);
	    }
	});

    }

    private void createInterfacesTable(List<Interface> interfaces) {

	// List<Interface> interfaces = selectedpackage.getInterfaces();

	interfacesTableModel = new InterfacesTableModel();
	interfacesTableModel.setData(interfaces);

	interfacesTable = new JXTable(interfacesTableModel);
	interfacesTable.getTableHeader().setFont(tablesHeaderFont);
	interfacesTable.setRolloverEnabled(true);
	interfacesTable.setHorizontalScrollEnabled(true);
	interfacesTable.setFillsViewportHeight(true);
	interfacesTable.setEditable(true);

	interfacesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	SelectionListener listener = new SelectionListener(interfacesTable);
	interfacesTable.getSelectionModel().addListSelectionListener(listener);
	interfacesTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

	interfacesTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		documentTableMouseClicked(e, interfacesTable);
	    }
	});
    }

    private void createEnumsTable(List<Enum> enums) {

	// List<Enum> enums = selectedpackage.getEnums();

	enumsTableModel = new EnumsTableModel();
	enumsTableModel.setData(enums);

	enumsTable = new JXTable(enumsTableModel);
	enumsTable.getTableHeader().setFont(tablesHeaderFont);
	enumsTable.setRolloverEnabled(true);
	enumsTable.setHorizontalScrollEnabled(true);
	enumsTable.setFillsViewportHeight(true);
	enumsTable.setEditable(true);

	enumsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	SelectionListener listener = new SelectionListener(enumsTable);
	enumsTable.getSelectionModel().addListSelectionListener(listener);
	enumsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

	enumsTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		documentTableMouseClicked(e, enumsTable);
	    }
	});
    }

    private void createClassFieldsTable(Class selectedClass) {

	List<Field> fields = selectedClass.getFields();

	fieldsTableModel = new FieldsTableModel();
	fieldsTableModel.setData(fields);

	fieldsTable = new JXTable(fieldsTableModel);
	fieldsTable.getTableHeader().setFont(tablesHeaderFont);
	fieldsTable.setRolloverEnabled(true);
	fieldsTable.setHorizontalScrollEnabled(true);
	fieldsTable.setFillsViewportHeight(true);
	fieldsTable.setEditable(true);

	fieldsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	SelectionListener listener = new SelectionListener(fieldsTable);
	fieldsTable.getSelectionModel().addListSelectionListener(listener);
	fieldsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

	fieldsTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		documentTableMouseClicked(e, fieldsTable);
	    }
	});

    }

    private void createClassMethodsTable(Class selectedClass) {

	List<Method> methods = selectedClass.getMethods();

	methodsTableModel = new MethodsTableModel();
	methodsTableModel.setData(methods);

	methodsTable = new JXTable(methodsTableModel);
	methodsTable.getTableHeader().setFont(tablesHeaderFont);
	methodsTable.setRolloverEnabled(true);
	methodsTable.setHorizontalScrollEnabled(true);
	methodsTable.setFillsViewportHeight(true);
	methodsTable.setEditable(true);

	methodsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	SelectionListener listener = new SelectionListener(methodsTable);
	methodsTable.getSelectionModel().addListSelectionListener(listener);
	methodsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

	methodsTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		documentTableMouseClicked(e, methodsTable);
	    }
	});

    }

    private void createInterfaceFieldsTable(Interface selectedInterface) {

	List<Field> fields = selectedInterface.getFields();

	fieldsTableModel = new FieldsTableModel();
	fieldsTableModel.setData(fields);

	fieldsTable = new JXTable(fieldsTableModel);
	fieldsTable.getTableHeader().setFont(tablesHeaderFont);
	fieldsTable.setRolloverEnabled(true);
	fieldsTable.setHorizontalScrollEnabled(true);
	fieldsTable.setFillsViewportHeight(true);
	fieldsTable.setEditable(true);

	fieldsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	SelectionListener listener = new SelectionListener(fieldsTable);
	fieldsTable.getSelectionModel().addListSelectionListener(listener);
	fieldsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

	fieldsTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		documentTableMouseClicked(e, fieldsTable);
	    }
	});

    }

    private void createInterfaceMethodsTable(Interface selectedInterface) {

	List<Method> methods = selectedInterface.getMethods();

	methodsTableModel = new MethodsTableModel();
	methodsTableModel.setData(methods);

	methodsTable = new JXTable(methodsTableModel);
	methodsTable.getTableHeader().setFont(tablesHeaderFont);
	methodsTable.setRolloverEnabled(true);
	methodsTable.setHorizontalScrollEnabled(true);
	methodsTable.setFillsViewportHeight(true);
	methodsTable.setEditable(true);

	methodsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	SelectionListener listener = new SelectionListener(methodsTable);
	methodsTable.getSelectionModel().addListSelectionListener(listener);
	methodsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

	methodsTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		documentTableMouseClicked(e, methodsTable);
	    }
	});

    }

    private void createEnumValuesTable(Enum selectedEnum) {

	List<String> values = Arrays.asList(selectedEnum.getValues());

	enumValuesTableModel = new EnumValuesTableModel();
	enumValuesTableModel.setData(values);

	enumValuesTable = new JXTable(enumValuesTableModel);
	enumValuesTable.getTableHeader().setFont(tablesHeaderFont);
	enumValuesTable.setRolloverEnabled(true);
	enumValuesTable.setHorizontalScrollEnabled(true);
	enumValuesTable.setFillsViewportHeight(true);
	enumValuesTable.setEditable(true);

	enumValuesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	SelectionListener listener = new SelectionListener(enumValuesTable);
	enumValuesTable.getSelectionModel().addListSelectionListener(listener);
	enumValuesTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

	enumValuesTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		documentTableMouseClicked(e, enumValuesTable);
	    }
	});

    }

    private void createExceptionsTable(Method selectedMethod) {

	// List<String> exceptions =
	// Arrays.asList(selectedMethod.getExceptions());
	List<String> exceptions = null;
	exceptionsTableModel = new ExceptionsTableModel();
	exceptionsTableModel.setData(exceptions);

	exceptionsTable = new JXTable(exceptionsTableModel);
	exceptionsTable.getTableHeader().setFont(tablesHeaderFont);
	exceptionsTable.setRolloverEnabled(true);
	exceptionsTable.setHorizontalScrollEnabled(true);
	exceptionsTable.setFillsViewportHeight(true);
	exceptionsTable.setEditable(true);

	exceptionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	SelectionListener listener = new SelectionListener(exceptionsTable);
	exceptionsTable.getSelectionModel().addListSelectionListener(listener);
	exceptionsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

	exceptionsTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		documentTableMouseClicked(e, exceptionsTable);
	    }
	});

    }

    private void createParamsTable(Method selectedMethod) {

	// List<Param> values = Arrays.asList(selectedMethod.getParams());
	List<Param> values = null;
	paramsTableModel = new ParamsTableModel();
	paramsTableModel.setData(values);

	paramsTable = new JXTable(paramsTableModel);
	paramsTable.getTableHeader().setFont(tablesHeaderFont);
	paramsTable.setRolloverEnabled(true);
	paramsTable.setHorizontalScrollEnabled(true);
	paramsTable.setFillsViewportHeight(true);
	paramsTable.setEditable(true);

	paramsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	SelectionListener listener = new SelectionListener(paramsTable);
	paramsTable.getSelectionModel().addListSelectionListener(listener);
	paramsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

	paramsTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		documentTableMouseClicked(e, paramsTable);
	    }
	});

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

	    tabbedPane.removeAll();

	    createClassesTable(((Application) nodeInfo).getClasses());
	    createInterfacesTable(((Application) nodeInfo).getInterfaces());
	    createEnumsTable(((Application) nodeInfo).getEnums());
	    createPackagesTable(((Application) nodeInfo).getPackages());

	    scrollPaneClasses = new JScrollPane(classesTable);
	    scrollPaneInterfaces = new JScrollPane(interfacesTable);
	    scrollPaneEnums = new JScrollPane(enumsTable);
	    scrollPanePackages = new JScrollPane(packagesTable);

	    tabbedPane.addTab(bundle.getString("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
		    .asComponent());
	    tabbedPane.addTab(bundle.getString("title.packages"), iconPackage, scrollPanePackages);
	    tabbedPane.addTab(bundle.getString("title.classes"), iconClass, scrollPaneClasses);
	    tabbedPane.addTab(bundle.getString("title.interfaces"), iconInterface, scrollPaneInterfaces);
	    tabbedPane.addTab(bundle.getString("title.enums"), iconEnum, scrollPaneEnums);

	}

	if (nodeInfo instanceof Package) {
	    tabbedPane.removeAll();

	    createPackagesTable(((Package) nodeInfo).getPackages());
	    createClassesTable(((Package) nodeInfo).getClasses());
	    createInterfacesTable(((Package) nodeInfo).getInterfaces());
	    createEnumsTable(((Package) nodeInfo).getEnums());

	    scrollPanePackages = new JScrollPane(packagesTable);
	    scrollPaneClasses = new JScrollPane(classesTable);
	    scrollPaneInterfaces = new JScrollPane(interfacesTable);
	    scrollPaneEnums = new JScrollPane(enumsTable);

	    tabbedPane.addTab(bundle.getString("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
		    .asComponent());
	    tabbedPane.addTab(bundle.getString("title.packages"), iconPackage, scrollPanePackages);
	    tabbedPane.addTab(bundle.getString("title.classes"), iconClass, scrollPaneClasses);
	    tabbedPane.addTab(bundle.getString("title.interfaces"), iconInterface, scrollPaneInterfaces);
	    tabbedPane.addTab(bundle.getString("title.enums"), iconEnum, scrollPaneEnums);

	}
	if (nodeInfo instanceof Class) {
	    tabbedPane.removeAll();

	    createClassMethodsTable((Class) nodeInfo);
	    createClassFieldsTable((Class) nodeInfo);
	    createEnumsTable(((Class) nodeInfo).getEnums());
	    createClassesTable(((Class) nodeInfo).getClasses());

	    scrollPaneMethods = new JScrollPane(methodsTable);
	    scrollPaneFields = new JScrollPane(fieldsTable);
	    scrollPaneEnums = new JScrollPane(enumsTable);
	    scrollPaneClasses = new JScrollPane(classesTable);

	    tabbedPane.addTab(bundle.getString("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
		    .asComponent());
	    tabbedPane.addTab(bundle.getString("title.methods"), iconMethod, scrollPaneMethods);
	    tabbedPane.addTab(bundle.getString("title.fields"), iconField, scrollPaneFields);
	    tabbedPane.addTab(bundle.getString("title.classes"), iconClass, scrollPaneClasses);
	    tabbedPane.addTab(bundle.getString("title.enums"), iconEnum, scrollPaneEnums);

	}
	if (nodeInfo instanceof Interface) {
	    tabbedPane.removeAll();

	    createInterfaceMethodsTable((Interface) nodeInfo);
	    createInterfaceFieldsTable((Interface) nodeInfo);

	    scrollPaneMethods = new JScrollPane(methodsTable);
	    scrollPaneFields = new JScrollPane(fieldsTable);

	    tabbedPane.addTab(bundle.getString("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
		    .asComponent());
	    tabbedPane.addTab(bundle.getString("title.methods"), iconMethod, scrollPaneMethods);
	    tabbedPane.addTab(bundle.getString("title.fields"), iconField, scrollPaneFields);

	}
	if (nodeInfo instanceof Enum) {
	    tabbedPane.removeAll();

	    createEnumValuesTable((Enum) nodeInfo);
	    scrollPaneEnumValues = new JScrollPane(enumValuesTable);

	    tabbedPane.addTab(bundle.getString("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
		    .asComponent());
	    tabbedPane.addTab(bundle.getString("title.values"), iconEnumValue, scrollPaneEnumValues);

	}

	if (nodeInfo instanceof Method) {
	    tabbedPane.removeAll();

	    createExceptionsTable((Method) nodeInfo);
	    createParamsTable((Method) nodeInfo);
	    scrollPaneExceptions = new JScrollPane(exceptionsTable);
	    scrollPaneParams = new JScrollPane(paramsTable);
	    tabbedPane.addTab(bundle.getString("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
		    .asComponent());
	    tabbedPane.addTab(bundle.getString("title.params"), iconInfo, scrollPaneParams);
	    tabbedPane.addTab(bundle.getString("title.exceptions"), iconInfo, scrollPaneExceptions);

	}
	if (nodeInfo instanceof Field) {
	    tabbedPane.removeAll();
	    tabbedPane.addTab(bundle.getString("title.info"), iconInfo, new InfoPanelPresenter(nodeInfo).getDisplay()
		    .asComponent());
	}

    }

    class SelectionListener implements ListSelectionListener {
	JXTable table;

	SelectionListener(JXTable table) {
	    this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {

	    } else if (e.getSource() == table.getColumnModel().getSelectionModel() && table.getColumnSelectionAllowed()) {

	    }

	}

    }

}
