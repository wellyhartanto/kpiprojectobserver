package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.Project;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.MappingHolder;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Element;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Param;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.TypeElement;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonConstants;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml.ClassPanel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml.InterfacePanel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml.PackagePanel;
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
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;

public class MainPanelView extends JPanel implements MainPanelDisplay {

	private static final long serialVersionUID = -6427921875113787927L;

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JSplitPane splitPane;
	private JScrollPane leftScrollPane;
	private JPanel rightPanel;
	private JTree navigationTree;
	private JTabbedPane tabbedPane;
	private ImageIcon iconPackage;
	private ImageIcon iconInterface;
	private ImageIcon iconClass;
	private ImageIcon iconEnum;
	private ImageIcon iconMethod;
	private ImageIcon iconField;
	private ImageIcon iconEnumValue;
	private ImageIcon iconInfo;
	private ImageIcon iconClose;
	private JXHyperlink linkClose;

	private InfoPanelPresenter infoPanelPresenter;
	private PackagesPanelPresenter packagesPanelPresenter;
	private MethodsPanelPresenter methodsPanelPresenter;
	private ClassesPanelPresenter classesPanelPresenter;
	private FieldsPanelPresenter fieldsPanelPresenter;
	private InterfacesPanelPresenter interfacesPanelPresenter;
	private EnumsPanelPresenter enumsPanelPresenter;
	private EnumValuesPresenter enumValuesPresenter;
	private ExceptionsPanelPresenter exceptionsPanelPresenter;
	private MethodParamsPresenter methodParamsPresenter;

	private Project project;

	private ClassPanel umlClassPanel;
	private InterfacePanel umlInterfacePanel;
	private PackagePanel umlPackagePanel;

	private JPanel actions;
	private ImageIcon iconChangeProject;
	private ImageIcon iconSearch;
	private JXHyperlink changeProjectHl;
	private JXHyperlink searchHl;

	public MainPanelView(Project project) {
		this.project = project;

		initComponents();

	}

	private void initComponents() {

		actions = new JPanel(new MigLayout("insets 0", "", "[]"));

		iconChangeProject = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "change24.png"));
		iconSearch = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "search24.png"));
		changeProjectHl = new JXHyperlink();
		changeProjectHl.setIcon(iconChangeProject);
		changeProjectHl.setSelected(false);
		searchHl = new JXHyperlink();
		searchHl.setIcon(iconSearch);
		searchHl.setSelected(false);
		actions.add(changeProjectHl, "gapleft 10");
		actions.add(searchHl);

		leftScrollPane = new JScrollPane();
		leftScrollPane.setName("leftScrollPane");
		rightPanel = new JPanel();
		rightPanel.setName("rightPanel");

		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(MyFonts.font2);

		// tabbedPane.setMaximumSize(new Dimension(1000, 350));

		iconPackage = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "package_obj.gif"));
		iconInterface = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "int_obj.gif"));
		iconClass = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "classes.gif"));
		iconEnum = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "enum_obj.gif"));
		iconMethod = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "method_obj.gif"));
		iconField = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "field_obj.gif"));
		iconEnumValue = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "enum_value_obj.gif"));
		iconInfo = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "information.gif"));
		iconClose = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "close.png"));
		linkClose = new JXHyperlink();
		linkClose.setIcon(iconClose);

		splitPane = new JSplitPane();
		splitPane.setName("splitPane");
		splitPane.setLeftComponent(leftScrollPane);
		splitPane.setRightComponent(rightPanel);

		sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package pack = project.getClassDiagram().getPackages().get(0);
		while (!pack.getPackages().isEmpty()) {

			pack = pack.getPackages().get(0);
		}
		umlClassPanel = new ClassPanel();
		umlInterfacePanel = new InterfacePanel();
		umlPackagePanel = new PackagePanel();

		// to initialize

		packagesPanelPresenter = PackagesPanelPresenter.getInstance(new ArrayList<Package>());
		classesPanelPresenter = ClassesPanelPresenter.getInstance(new ArrayList<Class>());
		interfacesPanelPresenter = InterfacesPanelPresenter.getInstance(new ArrayList<Interface>());
		enumsPanelPresenter = EnumsPanelPresenter.getInstance(new ArrayList<Enum>());
		methodsPanelPresenter = MethodsPanelPresenter.getInstance(new ArrayList<Method>());
		fieldsPanelPresenter = FieldsPanelPresenter.getInstance(new ArrayList<Field>());
		enumValuesPresenter = EnumValuesPresenter.getInstance(new String[] {});
		methodParamsPresenter = MethodParamsPresenter.getInstance(new ArrayList<Param>());
		exceptionsPanelPresenter = ExceptionsPanelPresenter.getInstance(new ArrayList<String>());

		setComponentsPosition();

	}

	private void setComponentsPosition() {
		setLayout(new MigLayout("fill,insets 0", "[]", "[growprio 50]0[]"));

		rightPanel.setLayout(new MigLayout("fill,insets 0"));
		rightPanel.add(tabbedPane, "growx,growy,wrap");
		rightPanel.add(umlClassPanel, "growx,growy");

		add(actions, "top,wrap");
		add(splitPane, "grow");
	}

	@Override
	public void setNavigationTree(JTree tree) {
		navigationTree = tree;

		navigationTree.setCellRenderer(new NavigationJTreeCellRenderer());

		navigationTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		navigationTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				treeValueChangedAction();

			}

		});

		leftScrollPane.setViewportView(navigationTree);

	}

	private void treeValueChangedAction() {

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) navigationTree.getLastSelectedPathComponent();

		/* if nothing is selected */
		if (node == null)
			return;

		/* retrieve the node that was selected */
		Object nodeInfo = node.getUserObject();

		rightPanel.remove(umlClassPanel);
		rightPanel.remove(umlInterfacePanel);
		rightPanel.remove(umlPackagePanel);

		if (nodeInfo instanceof Application) {
			tabbedPane.removeAll();

			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());

			packagesPanelPresenter = PackagesPanelPresenter.getInstance(((Application) nodeInfo).getPackages());
			tabbedPane.addTab(Messages.getMessage("title.packages"), iconPackage, packagesPanelPresenter.getDisplay().asComponent());

			classesPanelPresenter = ClassesPanelPresenter.getInstance(((Application) nodeInfo).getClasses());
			tabbedPane.addTab(Messages.getMessage("title.classes"), iconClass, classesPanelPresenter.getDisplay().asComponent());

			interfacesPanelPresenter = InterfacesPanelPresenter.getInstance(((Application) nodeInfo).getInterfaces());
			tabbedPane.addTab(Messages.getMessage("title.interfaces"), iconInterface, interfacesPanelPresenter.getDisplay().asComponent());

			enumsPanelPresenter = EnumsPanelPresenter.getInstance(((Application) nodeInfo).getEnums());
			tabbedPane.addTab(Messages.getMessage("title.enums"), iconEnum, enumsPanelPresenter.getDisplay().asComponent());

		}

		if (nodeInfo instanceof Package) {
			tabbedPane.removeAll();
			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());

			packagesPanelPresenter = PackagesPanelPresenter.getInstance(((Package) nodeInfo).getPackages());
			tabbedPane.addTab(Messages.getMessage("title.packages"), iconPackage, packagesPanelPresenter.getDisplay().asComponent());

			classesPanelPresenter = ClassesPanelPresenter.getInstance(((Package) nodeInfo).getClasses());
			tabbedPane.addTab(Messages.getMessage("title.classes"), iconClass, classesPanelPresenter.getDisplay().asComponent());

			interfacesPanelPresenter = InterfacesPanelPresenter.getInstance(((Package) nodeInfo).getInterfaces());
			tabbedPane.addTab(Messages.getMessage("title.interfaces"), iconInterface, interfacesPanelPresenter.getDisplay().asComponent());

			enumsPanelPresenter = EnumsPanelPresenter.getInstance(((Package) nodeInfo).getEnums());
			tabbedPane.addTab(Messages.getMessage("title.enums"), iconEnum, enumsPanelPresenter.getDisplay().asComponent());

			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package umlpackage = project.getMappingHolder().getJava2UmlMapping()
					.getPackage(

					((Package) nodeInfo).getFullyQualifiedName());

			if (umlpackage != null) {

				umlPackagePanel = new PackagePanel(umlpackage);
				umlPackagePanel.setVisible(true);
				rightPanel.add(umlPackagePanel, "growx,growy");
				// wrap,span,,top
			}

		}
		if (nodeInfo instanceof Class) {
			tabbedPane.removeAll();

			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());

			methodsPanelPresenter = MethodsPanelPresenter.getInstance(((Class) nodeInfo).getMethods());
			tabbedPane.addTab(Messages.getMessage("title.methods"), iconMethod, methodsPanelPresenter.getDisplay().asComponent());

			fieldsPanelPresenter = FieldsPanelPresenter.getInstance(((Class) nodeInfo).getFields());
			tabbedPane.addTab(Messages.getMessage("title.fields"), iconField, fieldsPanelPresenter.getDisplay().asComponent());

			classesPanelPresenter = ClassesPanelPresenter.getInstance(((Class) nodeInfo).getClasses());
			tabbedPane.addTab(Messages.getMessage("title.classes"), iconClass, classesPanelPresenter.getDisplay().asComponent());

			enumsPanelPresenter = EnumsPanelPresenter.getInstance(((Class) nodeInfo).getEnums());
			tabbedPane.addTab(Messages.getMessage("title.enums"), iconEnum, enumsPanelPresenter.getDisplay().asComponent());

			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class umlclass = project.getMappingHolder().getJava2UmlMapping().getClass(

			((Class) nodeInfo).getFullyQualifiedName());

			if (umlclass != null) {

				umlClassPanel = new ClassPanel(umlclass);
				umlClassPanel.setVisible(true);
				rightPanel.add(umlClassPanel, "growx,growy");
				// wrap,span,,top
			}

		}
		if (nodeInfo instanceof Interface) {
			tabbedPane.removeAll();
			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());

			methodsPanelPresenter = MethodsPanelPresenter.getInstance(((Interface) nodeInfo).getMethods());
			tabbedPane.addTab(Messages.getMessage("title.methods"), iconMethod, methodsPanelPresenter.getDisplay().asComponent());

			fieldsPanelPresenter = FieldsPanelPresenter.getInstance(((Interface) nodeInfo).getFields());
			tabbedPane.addTab(Messages.getMessage("title.fields"), iconField, fieldsPanelPresenter.getDisplay().asComponent());

			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Interface umlinterface = project.getMappingHolder().getJava2UmlMapping()
					.getInterface(

					((Interface) nodeInfo).getFullyQualifiedName());

			if (umlinterface != null) {

				umlInterfacePanel = new InterfacePanel(umlinterface);
				umlInterfacePanel.setVisible(true);
				rightPanel.add(umlInterfacePanel, "growx,growy");
				// wrap,span,,top
			}

		}
		if (nodeInfo instanceof Enum) {
			tabbedPane.removeAll();

			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());

			enumValuesPresenter = EnumValuesPresenter.getInstance(((Enum) nodeInfo).getValues());
			tabbedPane.addTab(Messages.getMessage("title.values"), iconEnumValue, enumValuesPresenter.getDisplay().asComponent());
		}

		if (nodeInfo instanceof Method) {
			tabbedPane.removeAll();
			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());

			methodParamsPresenter = MethodParamsPresenter.getInstance(((Method) nodeInfo).getParams());
			tabbedPane.addTab(Messages.getMessage("title.params"), iconInfo, methodParamsPresenter.getDisplay().asComponent());

			exceptionsPanelPresenter = ExceptionsPanelPresenter.getInstance(((Method) nodeInfo).getExceptions());
			tabbedPane.addTab(Messages.getMessage("title.exceptions"), iconInfo, exceptionsPanelPresenter.getDisplay().asComponent());
		}
		if (nodeInfo instanceof Field) {
			tabbedPane.removeAll();

			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());
		}

		repaint();

	}

	@Override
	public void setDetailSelection(JXTable table) {

		int selectedrow = table.getSelectedRow();
		if (selectedrow != -1) {

			GenericTableModel<Object> p = (GenericTableModel<Object>) table.getModel();

			selectElement(p.getData().get(selectedrow));
		}
	}

	private void selectElement(Object object) {

		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = navigationTree.getPathForRow(0);

		parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());

		parentNode.getLeafCount();

		parentNode.getUserObjectPath();

		Enumeration<DefaultMutableTreeNode> e = parentNode.breadthFirstEnumeration();
		while (e.hasMoreElements()) {

			DefaultMutableTreeNode dmtn = e.nextElement();
			if (dmtn.getUserObject().equals(object)) {
				parentPath = new TreePath(dmtn.getPath());
				navigationTree.getSelectionModel().setSelectionPath(parentPath);
			}

		}

	}

	@Override
	public void searchAndSelectElement(TypeElement element) {

		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = navigationTree.getPathForRow(0);

		parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());

		parentNode.getLeafCount();

		parentNode.getUserObjectPath();

		Enumeration<DefaultMutableTreeNode> e = parentNode.breadthFirstEnumeration();
		while (e.hasMoreElements()) {

			DefaultMutableTreeNode dmtn = e.nextElement();
			Object obj = dmtn.getUserObject();
			if (obj instanceof Class || obj instanceof Interface) {
				Element el = (Element) obj;
				if (el.getFullName().equalsIgnoreCase(element.getFullName())) {
					parentPath = new TreePath(dmtn.getPath());
					navigationTree.getSelectionModel().setSelectionPath(parentPath);
				}
			}
		}
	}

	@Override
	public void setTreeValueChangedAction() {

		navigationTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				treeValueChangedAction();

			}

		});

		TreePath tp = navigationTree.getPathForRow(0);
		navigationTree.setSelectionPath(tp);

	}

	@Override
	public ClassesPanelPresenter getClassesPanelPresenter() {
		return classesPanelPresenter;
	}

	@Override
	public EnumValuesPresenter getEnumValuesPresenter() {
		return enumValuesPresenter;
	}

	@Override
	public EnumsPanelPresenter getEnumsPanelPresenter() {
		return enumsPanelPresenter;
	}

	@Override
	public ExceptionsPanelPresenter getExceptionsPanelPresenter() {
		return exceptionsPanelPresenter;
	}

	@Override
	public FieldsPanelPresenter getFieldsPanelPresenter() {
		return fieldsPanelPresenter;
	}

	@Override
	public InfoPanelPresenter getInfoPanelPresenter() {
		return infoPanelPresenter;
	}

	@Override
	public InterfacesPanelPresenter getInterfacesPanelPresenter() {
		return interfacesPanelPresenter;
	}

	@Override
	public MethodParamsPresenter getMethodParamsPresenter() {
		return methodParamsPresenter;
	}

	@Override
	public MethodsPanelPresenter getMethodsPanelPresenter() {
		return methodsPanelPresenter;
	}

	@Override
	public PackagesPanelPresenter getPackagesPanelPresenter() {
		return packagesPanelPresenter;
	}

	@Override
	public void setChangeProjectAction(ActionListener l) {
		changeProjectHl.addActionListener(l);
	}

	@Override
	public void setSearchAction(ActionListener l) {
		searchHl.addActionListener(l);
	}

}
