package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
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
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.NavigationJTreeCellRenderer;
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

	private JTabbedPane tabbedPane;

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

	private static final String IMAGES_FOLDER_PATH = "src/sk/tuke/fei/kpi/ProjectObserver/Visualization/gui/resources/images/";

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
		this.setSize(1000, 500);
		setName("myFirstframe");

		// setLocale(new Locale("en"));
		setLocale(new Locale("sk"));

		bundle = MyResourceBundle.getResourceBundle(getLocale());

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

		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(MyFonts.font2);

		iconPackage = new ImageIcon(IMAGES_FOLDER_PATH + "package_obj.gif");
		iconInterface = new ImageIcon(IMAGES_FOLDER_PATH + "int_obj.gif");
		iconClass = new ImageIcon(IMAGES_FOLDER_PATH + "classes.gif");
		iconEnum = new ImageIcon(IMAGES_FOLDER_PATH + "enum_obj.gif");
		iconMethod = new ImageIcon(IMAGES_FOLDER_PATH + "method_obj.gif");
		iconField = new ImageIcon(IMAGES_FOLDER_PATH + "field_obj.gif");
		iconEnumValue = new ImageIcon(IMAGES_FOLDER_PATH + "enum_value_obj.gif");
		iconInfo = new ImageIcon(IMAGES_FOLDER_PATH + "information.gif");
		tablesHeaderFont = MyFonts.font3;

		ImageIcon kpilogo = new ImageIcon(IMAGES_FOLDER_PATH + "tu_kpi.jpg");
		setIconImage(kpilogo.getImage());
		setTitle("Project Observer");

		rightPanel.add(tabbedPane);
		splitPane = new JSplitPane();
		splitPane.setName("splitPane");
		splitPane.setLeftComponent(leftScrollPane);
		splitPane.setRightComponent(rightPanel);

		getContentPane().add(splitPane);

		setVisible(true);

	}

	private void createPackagesTable(List<Package> packages) {

		packagesTableModel = new PackagesTableModel(getLocale());
		packagesTableModel.setData(packages);

		packagesTable = new JXTable(packagesTableModel);
		packagesTable.getTableHeader().setFont(tablesHeaderFont);
		packagesTable.setRolloverEnabled(true);
		packagesTable.setHorizontalScrollEnabled(true);
		packagesTable.setFillsViewportHeight(true);
		packagesTable.setEditable(true);

	}

	private void createClassesTable(List<Class> classes) {

		// List<Class> classes = selectedpackage.getClasses();

		classesTableModel = new ClassesTableModel(getLocale());
		classesTableModel.setData(classes);

		classesTable = new JXTable(classesTableModel);
		classesTable.getTableHeader().setFont(tablesHeaderFont);
		classesTable.setRolloverEnabled(true);
		classesTable.setHorizontalScrollEnabled(true);
		classesTable.setFillsViewportHeight(true);
		classesTable.setEditable(true);

	}

	private void createInterfacesTable(List<Interface> interfaces) {

		// List<Interface> interfaces = selectedpackage.getInterfaces();

		interfacesTableModel = new InterfacesTableModel(getLocale());
		interfacesTableModel.setData(interfaces);

		interfacesTable = new JXTable(interfacesTableModel);
		interfacesTable.getTableHeader().setFont(tablesHeaderFont);
		interfacesTable.setRolloverEnabled(true);
		interfacesTable.setHorizontalScrollEnabled(true);
		interfacesTable.setFillsViewportHeight(true);
		interfacesTable.setEditable(true);

	}

	private void createEnumsTable(List<Enum> enums) {

		// List<Enum> enums = selectedpackage.getEnums();

		enumsTableModel = new EnumsTableModel(getLocale());
		enumsTableModel.setData(enums);

		enumsTable = new JXTable(enumsTableModel);
		enumsTable.getTableHeader().setFont(tablesHeaderFont);
		enumsTable.setRolloverEnabled(true);
		enumsTable.setHorizontalScrollEnabled(true);
		enumsTable.setFillsViewportHeight(true);
		enumsTable.setEditable(true);

	}

	private void createClassFieldsTable(Class selectedClass) {

		List<Field> fields = selectedClass.getFields();

		fieldsTableModel = new FieldsTableModel(getLocale());
		fieldsTableModel.setData(fields);

		fieldsTable = new JXTable(fieldsTableModel);
		fieldsTable.getTableHeader().setFont(tablesHeaderFont);
		fieldsTable.setRolloverEnabled(true);
		fieldsTable.setHorizontalScrollEnabled(true);
		fieldsTable.setFillsViewportHeight(true);
		fieldsTable.setEditable(true);

	}

	private void createClassMethodsTable(Class selectedClass) {

		List<Method> methods = selectedClass.getMethods();

		methodsTableModel = new MethodsTableModel(getLocale());
		methodsTableModel.setData(methods);

		methodsTable = new JXTable(methodsTableModel);
		methodsTable.getTableHeader().setFont(tablesHeaderFont);
		methodsTable.setRolloverEnabled(true);
		methodsTable.setHorizontalScrollEnabled(true);
		methodsTable.setFillsViewportHeight(true);
		methodsTable.setEditable(true);

	}

	private void createInterfaceFieldsTable(Interface selectedInterface) {

		List<Field> fields = selectedInterface.getFields();

		fieldsTableModel = new FieldsTableModel(getLocale());
		fieldsTableModel.setData(fields);

		fieldsTable = new JXTable(fieldsTableModel);
		fieldsTable.getTableHeader().setFont(tablesHeaderFont);
		fieldsTable.setRolloverEnabled(true);
		fieldsTable.setHorizontalScrollEnabled(true);
		fieldsTable.setFillsViewportHeight(true);
		fieldsTable.setEditable(true);

	}

	private void createInterfaceMethodsTable(Interface selectedInterface) {

		List<Method> methods = selectedInterface.getMethods();

		methodsTableModel = new MethodsTableModel(getLocale());
		methodsTableModel.setData(methods);

		methodsTable = new JXTable(methodsTableModel);
		methodsTable.getTableHeader().setFont(tablesHeaderFont);
		methodsTable.setRolloverEnabled(true);
		methodsTable.setHorizontalScrollEnabled(true);
		methodsTable.setFillsViewportHeight(true);
		methodsTable.setEditable(true);

	}

	private void createEnumValuesTable(Enum selectedEnum) {

		List<String> values = Arrays.asList(selectedEnum.getValues());

		enumValuesTableModel = new EnumValuesTableModel(getLocale());
		enumValuesTableModel.setData(values);

		enumValuesTable = new JXTable(enumValuesTableModel);
		enumValuesTable.getTableHeader().setFont(tablesHeaderFont);
		enumValuesTable.setRolloverEnabled(true);
		enumValuesTable.setHorizontalScrollEnabled(true);
		enumValuesTable.setFillsViewportHeight(true);
		enumValuesTable.setEditable(true);

	}

	private void createExceptionsTable(Method selectedMethod) {

		// List<String> exceptions =
		// Arrays.asList(selectedMethod.getExceptions());
		List<String> exceptions = null;
		exceptionsTableModel = new ExceptionsTableModel(getLocale());
		exceptionsTableModel.setData(exceptions);

		exceptionsTable = new JXTable(exceptionsTableModel);
		exceptionsTable.getTableHeader().setFont(tablesHeaderFont);
		exceptionsTable.setRolloverEnabled(true);
		exceptionsTable.setHorizontalScrollEnabled(true);
		exceptionsTable.setFillsViewportHeight(true);
		exceptionsTable.setEditable(true);

	}

	private void createParamsTable(Method selectedMethod) {

		// List<Param> values = Arrays.asList(selectedMethod.getParams());
		List<Param> values = null;
		paramsTableModel = new ParamsTableModel(getLocale());
		paramsTableModel.setData(values);

		paramsTable = new JXTable(paramsTableModel);
		paramsTable.getTableHeader().setFont(tablesHeaderFont);
		paramsTable.setRolloverEnabled(true);
		paramsTable.setHorizontalScrollEnabled(true);
		paramsTable.setFillsViewportHeight(true);
		paramsTable.setEditable(true);

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

		Application app = createTestData();

		DefaultMutableTreeNode root = createApplicationTree(app);

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

	private void treeValueChangedAction() {

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) navigationTree
				.getLastSelectedPathComponent();

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

			tabbedPane.addTab(bundle.getString("title.info"), iconInfo, null);
			tabbedPane.addTab(bundle.getString("title.packages"), iconPackage,
					scrollPanePackages);
			tabbedPane.addTab(bundle.getString("title.classes"), iconClass,
					scrollPaneClasses);
			tabbedPane.addTab(bundle.getString("title.interfaces"),
					iconInterface, scrollPaneInterfaces);
			tabbedPane.addTab(bundle.getString("title.enums"), iconEnum,
					scrollPaneEnums);

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

			tabbedPane.addTab(bundle.getString("title.info"), iconInfo, null);
			tabbedPane.addTab(bundle.getString("title.packages"), iconPackage,
					scrollPanePackages);
			tabbedPane.addTab(bundle.getString("title.classes"), iconClass,
					scrollPaneClasses);
			tabbedPane.addTab(bundle.getString("title.interfaces"),
					iconInterface, scrollPaneInterfaces);
			tabbedPane.addTab(bundle.getString("title.enums"), iconEnum,
					scrollPaneEnums);

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

			tabbedPane.addTab(bundle.getString("title.info"), iconInfo, null);
			tabbedPane.addTab(bundle.getString("title.methods"), iconMethod,
					scrollPaneMethods);
			tabbedPane.addTab(bundle.getString("title.fields"), iconField,
					scrollPaneFields);
			tabbedPane.addTab(bundle.getString("title.classes"), iconClass,
					scrollPaneClasses);
			tabbedPane.addTab(bundle.getString("title.enums"), iconEnum,
					scrollPaneEnums);

		}
		if (nodeInfo instanceof Interface) {
			tabbedPane.removeAll();

			createInterfaceMethodsTable((Interface) nodeInfo);
			createInterfaceFieldsTable((Interface) nodeInfo);

			scrollPaneMethods = new JScrollPane(methodsTable);
			scrollPaneFields = new JScrollPane(fieldsTable);

			tabbedPane.addTab(bundle.getString("title.info"), iconInfo, null);
			tabbedPane.addTab(bundle.getString("title.methods"), iconMethod,
					scrollPaneMethods);
			tabbedPane.addTab(bundle.getString("title.fields"), iconField,
					scrollPaneFields);

		}
		if (nodeInfo instanceof Enum) {
			tabbedPane.removeAll();

			createEnumValuesTable((Enum) nodeInfo);
			scrollPaneEnumValues = new JScrollPane(enumValuesTable);

			tabbedPane.addTab(bundle.getString("title.info"), iconInfo, null);
			tabbedPane.addTab(bundle.getString("title.values"), iconEnumValue,
					scrollPaneEnumValues);

		}

		if (nodeInfo instanceof Method) {
			tabbedPane.removeAll();

			createExceptionsTable((Method) nodeInfo);
			createParamsTable((Method) nodeInfo);
			scrollPaneExceptions = new JScrollPane(exceptionsTable);
			scrollPaneParams = new JScrollPane(paramsTable);
			tabbedPane.addTab(bundle.getString("title.info"), iconInfo, null);
			tabbedPane.addTab(bundle.getString("title.params"), iconInfo,
					scrollPaneParams);
			tabbedPane.addTab(bundle.getString("title.exceptions"), iconInfo,
					scrollPaneExceptions);

		}
		if (nodeInfo instanceof Field) {
			tabbedPane.removeAll();
			tabbedPane.addTab(bundle.getString("title.info"), iconInfo, null);
		}

	}

	public Application createTestData() {

		Application app = new Application();

		List<Package> packages = new ArrayList<Package>();

		for (int i = 0; i < 7; i++) {
			Package p = new Package();
			p.setName("sk.tuke.fei.kpi.package" + i);
			List<Class> cl = new ArrayList<Class>();
			for (int j = 0; j < 7; j++) {
				Class c = new Class();
				c.setName("class" + i + "." + j);

				List<Method> methods = new ArrayList<Method>();
				List<Field> fields = new ArrayList<Field>();

				for (int x = 0; x < 3; x++) {

					Method m = new Method();
					m.setName("method" + x);
					methods.add(m);

					Field f = new Field();
					f.setName("field" + x);
					fields.add(f);
				}
				c.setMethods(methods);
				c.setFields(fields);

				cl.add(c);
			}
			List<Interface> inf = new ArrayList<Interface>();
			for (int j = 0; j < 5; j++) {
				Interface c = new Interface();
				c.setName("interface" + i + "." + j);

				List<Method> methods = new ArrayList<Method>();
				List<Field> fields = new ArrayList<Field>();

				for (int x = 0; x < 2; x++) {

					Method m = new Method();
					m.setName("method" + x);
					methods.add(m);

					Field f = new Field();
					f.setName("field" + x);
					fields.add(f);
				}
				c.setMethods(methods);
				c.setFields(fields);

				inf.add(c);
			}

			List<Enum> en = new ArrayList<Enum>();
			for (int j = 0; j < 4; j++) {
				Enum c = new Enum();
				c.setName("enum" + i + "." + j);

				String[] s = { "value1", "value2" };

				c.setValues(s);

				en.add(c);
			}

			List<Package> pcg = new ArrayList<Package>();
			for (int j = 0; j < 3; j++) {
				Package c = new Package();
				c.setName(p.getName() + "sub" + i + "." + j);
				pcg.add(c);

				List<Class> cl2 = new ArrayList<Class>();
				for (int z = 0; z < 3; z++) {
					Class c2 = new Class();
					c2.setName("class" + i + "." + z);

					List<Method> methods = new ArrayList<Method>();
					List<Field> fields = new ArrayList<Field>();

					for (int x = 0; x < 3; x++) {

						Method m = new Method();
						m.setName("method" + x);
						methods.add(m);

						Field f = new Field();
						f.setName("field" + x);
						fields.add(f);
					}
					c2.setMethods(methods);
					c2.setFields(fields);

					cl2.add(c2);
				}
				List<Interface> inf2 = new ArrayList<Interface>();
				for (int z = 0; z < 2; z++) {
					Interface c2 = new Interface();
					c2.setName("interface" + i + "." + z);

					List<Method> methods = new ArrayList<Method>();
					List<Field> fields = new ArrayList<Field>();

					for (int x = 0; x < 2; x++) {

						Method m = new Method();
						m.setName("method" + x);
						methods.add(m);

						Field f = new Field();
						f.setName("field" + x);
						fields.add(f);
					}
					c2.setMethods(methods);
					c2.setFields(fields);
					inf2.add(c2);
				}

				List<Enum> en2 = new ArrayList<Enum>();
				for (int z = 0; z < 1; z++) {
					Enum c2 = new Enum();
					c2.setName("enum" + i + "." + z);
					String[] s = { "value1", "value2" };

					c2.setValues(s);
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

		app.setPackages(packages);
		app.setClasses(packages.get(0).getClasses());
		app.setEnums(packages.get(0).getEnums());
		app.setInterfaces(packages.get(0).getInterfaces());
		app.setFilename("defaultfile.owl");
		app.setName("MyExampleApplication");

		return app;
	}

}
