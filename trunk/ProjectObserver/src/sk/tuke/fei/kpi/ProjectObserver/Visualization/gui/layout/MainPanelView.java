package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.application.SessionStorage;
import org.jdesktop.swingx.JXHyperlink;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonColors;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonConstants;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.TabbedPaneLabel;
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
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.GenericTableModel;
import sk.tuke.fei.kpi.akAgent.integration.Project;
import sk.tuke.fei.kpi.akAgent.integration.alignment.difference.Difference;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Application;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Element;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Param;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.TypeElement;

public class MainPanelView extends JPanel implements MainPanelDisplay {

	private static final long serialVersionUID = -6427921875113787927L;

	@Override
	public JComponent asComponent() {
		return this;
	}

	private SessionStorage sessionStorage;

	private JSplitPane splitPane;
	private JScrollPane leftScrollPane;
	private JSplitPane rightPanel;
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
	private ImageIcon iconExport;
	private ImageIcon iconHelp;

	private JButton changeProjectHl;
	private JButton searchHl;
	private JButton exportHl;
	private JButton helpHl;

	public MainPanelView(Project project) {

		this.project = project;

		initComponents();

	}

	private void initComponents() {

		final Color OS_X_UNIFIED_TOOLBAR_FOCUSED_BOTTOM_COLOR = new Color(64, 64, 64);
		final Color OS_X_UNIFIED_TOOLBAR_UNFOCUSED_BORDER_COLOR = new Color(135, 135, 135);
		actions = new JPanel(new MigLayout("insets 0", "[][][][]", "1[]1")) {
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				int w = getWidth();
				int h = getHeight();

				// Paint a gradient from top to bottom
				GradientPaint gp = new GradientPaint(0, 0, getBackground(), 0, h, getBackground().darker());
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}

			@Override
			public Border getBorder() {
				Window window = SwingUtilities.getWindowAncestor(this);
				return window != null && window.isFocused() ? BorderFactory.createMatteBorder(0, 0, 1, 0,
						OS_X_UNIFIED_TOOLBAR_FOCUSED_BOTTOM_COLOR) : BorderFactory.createMatteBorder(0, 0, 1, 0,
						OS_X_UNIFIED_TOOLBAR_UNFOCUSED_BORDER_COLOR);
			}

		};

		actions.setBackground(CommonColors.MAIN_BACKGROUND_COLOR);
		actions.setOpaque(false);
		iconChangeProject = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "change24.png"));
		iconSearch = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "search24.png"));
		iconExport = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "export_icon24.png"));
		iconHelp = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "help_24.png"));

		// iconChangeProject = new
		// ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH +
		// "changeblue.png"));
		// iconSearch = new
		// ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH +
		// "searchblue.png"));
		// iconExport = new
		// ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH +
		// "exportblue.png"));

		// iconChangeProject = new
		// ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH +
		// "exchange32.png"));
		// iconSearch = new
		// ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH +
		// "search24.png"));
		// iconExport = new
		// ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH +
		// "export_32.png"));

		changeProjectHl = new JButton();
		changeProjectHl.setIcon(iconChangeProject);
		changeProjectHl.setSelected(false);
		changeProjectHl.setToolTipText(Messages.getMessage("tooltip.changeproject"));
		changeProjectHl.setForeground(Color.BLACK);
		changeProjectHl.setBackground(Color.GRAY);
		changeProjectHl.setOpaque(false);

		searchHl = new JButton();
		searchHl.setIcon(iconSearch);
		searchHl.setSelected(false);
		searchHl.setToolTipText(Messages.getMessage("tooltip.search"));
		searchHl.setForeground(Color.BLACK);
		searchHl.setBackground(Color.GRAY);
		searchHl.setOpaque(false);

		exportHl = new JButton();
		exportHl.setSelected(false);
		exportHl.setIcon(iconExport);
		exportHl.setToolTipText(Messages.getMessage("tooltip.export"));
		// exportHl.setText(Messages.getMessage("main.menu.export"));
		exportHl.setForeground(Color.BLACK);
		exportHl.setBackground(Color.GRAY);
		exportHl.setOpaque(false);

		helpHl = new JButton();
		helpHl.setIcon(iconHelp);
		helpHl.setSelected(false);
		helpHl.setToolTipText(Messages.getMessage("tooltip.help"));
		helpHl.setForeground(Color.BLACK);
		helpHl.setBackground(Color.GRAY);
		helpHl.setOpaque(false);

		actions.add(changeProjectHl, "gapleft 10");
		actions.add(exportHl);
		actions.add(searchHl);
		actions.add(helpHl);

		leftScrollPane = new JScrollPane();
		leftScrollPane.setName("leftScrollPane");
		leftScrollPane.setPreferredSize(new Dimension(220, leftScrollPane.getPreferredSize().height));

		rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		rightPanel.setName("rightPanel");
		rightPanel.setDividerSize(2);

		tabbedPane = new JTabbedPane();
		tabbedPane.setName("tabbedPane");
		tabbedPane.setFont(CommonFonts.getTabFont());

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
		splitPane.setDividerSize(1);

		sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Package pack = project.getClassDiagram().getPackages().get(0);
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

		setBackground(CommonColors.MAIN_BACKGROUND_COLOR);

		// rightPanel.setLayout(new MigLayout("fill,insets 0", "",
		// "[growprio 50][]"));
		// rightPanel.add(tabbedPane, "growx,growy,wrap");
		// rightPanel.add(umlClassPanel, "growx,growy");

		rightPanel.setTopComponent(tabbedPane);
		rightPanel.setBottomComponent(umlClassPanel);

		rightPanel.getTopComponent().addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);

				saveWindowPrefs();
			}

		});

		// add(actions, "top,wrap");
		// add(splitPane, "grow");

		JPanel mainJp = new JPanel(new MigLayout("fill,insets 0", "[]", "[growprio 50]0[]"));

		mainJp.setBackground(CommonColors.MAIN_BACKGROUND_COLOR);
		setLayout(new MigLayout("fill,insets 0"));
		// mainJp.add(actions, "top,growx,wrap");

		JToolBar toolbar = new JToolBar();
		JButton changeButton = new JButton(iconChangeProject);
		JButton searchButton = new JButton(iconSearch);
		JButton exportButton = new JButton(iconExport);
		changeButton.setForeground(Color.BLACK);
		changeButton.setBackground(Color.DARK_GRAY);
		// Border line = new LineBorder(Color.BLACK);
		// Border margin = new EmptyBorder(5, 15, 5, 15);
		// Border compound = new CompoundBorder(line, margin);
		// changeButton.setBorder(compound);
		changeButton.setOpaque(false);
		changeButton.setText("");

		// actions.add(changeButton);

		// toolbar.add(changeButton);
		toolbar.add(searchButton);
		toolbar.add(exportButton);
		// mainJp.add(toolbar,"top,growx,wrap");
		mainJp.add(actions, "top,growx,wrap");
		mainJp.add(splitPane, "grow");
		add(mainJp, "grow");

	}

	@Override
	public void setNavigationTree(JTree tree) {
		navigationTree = tree;
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

			// packagesPanelPresenter = PackagesPanelPresenter
			// .getInstance(((Application) nodeInfo).getPackages());
			// tabbedPane.addTab(Messages.getMessage("title.packages"),
			// iconPackage, packagesPanelPresenter.getDisplay()
			// .asComponent());
			//
			// classesPanelPresenter = ClassesPanelPresenter
			// .getInstance(((Application) nodeInfo).getClasses());
			// tabbedPane.addTab(Messages.getMessage("title.classes"),
			// iconClass,
			// classesPanelPresenter.getDisplay().asComponent());
			//
			// interfacesPanelPresenter = InterfacesPanelPresenter
			// .getInstance(((Application) nodeInfo).getInterfaces());
			// tabbedPane.addTab(Messages.getMessage("title.interfaces"),
			// iconInterface, interfacesPanelPresenter.getDisplay()
			// .asComponent());
			//
			// enumsPanelPresenter = EnumsPanelPresenter
			// .getInstance(((Application) nodeInfo).getEnums());
			// tabbedPane.addTab(Messages.getMessage("title.enums"), iconEnum,
			// enumsPanelPresenter.getDisplay().asComponent());

		}

		if (nodeInfo instanceof Package) {
			tabbedPane.removeAll();
			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());

			JLabel lbl = new TabbedPaneLabel(Messages.getMessage("title.info"));
			lbl.setIcon(iconInfo);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			packagesPanelPresenter = PackagesPanelPresenter.getInstance(((Package) nodeInfo).getPackages());
			tabbedPane.addTab(Messages.getMessage("title.packages"), iconPackage, packagesPanelPresenter.getDisplay().asComponent());

			lbl = new TabbedPaneLabel(Messages.getMessage("title.packages"));
			lbl.setIcon(iconPackage);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			classesPanelPresenter = ClassesPanelPresenter.getInstance(((Package) nodeInfo).getClasses());
			tabbedPane.addTab(Messages.getMessage("title.classes"), iconClass, classesPanelPresenter.getDisplay().asComponent());
			lbl = new TabbedPaneLabel(Messages.getMessage("title.classes"));
			lbl.setIcon(iconClass);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			interfacesPanelPresenter = InterfacesPanelPresenter.getInstance(((Package) nodeInfo).getInterfaces());
			tabbedPane.addTab(Messages.getMessage("title.interfaces"), iconInterface, interfacesPanelPresenter.getDisplay().asComponent());
			lbl = new TabbedPaneLabel(Messages.getMessage("title.interfaces"));
			lbl.setIcon(iconInterface);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			enumsPanelPresenter = EnumsPanelPresenter.getInstance(((Package) nodeInfo).getEnums());
			tabbedPane.addTab(Messages.getMessage("title.enums"), iconEnum, enumsPanelPresenter.getDisplay().asComponent());
			lbl = new TabbedPaneLabel(Messages.getMessage("title.enums"));
			lbl.setIcon(iconEnum);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Package umlpackage = project.getMappingHolder()
					.getJava2UmlMapping().getPackage(

					((Package) nodeInfo).getFullyQualifiedName());

			// if (umlpackage != null) {
			umlPackagePanel = new PackagePanel(umlpackage);
			umlPackagePanel.setVisible(true);
			rightPanel.setBottomComponent(umlPackagePanel);
			// }

		}
		if (nodeInfo instanceof Class) {
			tabbedPane.removeAll();

			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());
			JLabel lbl = new TabbedPaneLabel(Messages.getMessage("title.info"));
			lbl.setIcon(iconInfo);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			fieldsPanelPresenter = FieldsPanelPresenter.getInstance(((Class) nodeInfo).getFields());
			tabbedPane.addTab(Messages.getMessage("title.fields"), iconField, fieldsPanelPresenter.getDisplay().asComponent());
			lbl = new TabbedPaneLabel(Messages.getMessage("title.fields"));
			lbl.setIcon(iconField);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			methodsPanelPresenter = MethodsPanelPresenter.getInstance(((Class) nodeInfo).getMethods());
			tabbedPane.addTab(Messages.getMessage("title.methods"), iconMethod, methodsPanelPresenter.getDisplay().asComponent());
			lbl = new TabbedPaneLabel(Messages.getMessage("title.methods"));
			lbl.setIcon(iconMethod);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			classesPanelPresenter = ClassesPanelPresenter.getInstance(((Class) nodeInfo).getClasses());
			tabbedPane.addTab(Messages.getMessage("title.classes"), iconClass, classesPanelPresenter.getDisplay().asComponent());
			lbl = new TabbedPaneLabel(Messages.getMessage("title.classes"));
			lbl.setIcon(iconClass);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			enumsPanelPresenter = EnumsPanelPresenter.getInstance(((Class) nodeInfo).getEnums());
			tabbedPane.addTab(Messages.getMessage("title.enums"), iconEnum, enumsPanelPresenter.getDisplay().asComponent());
			lbl = new TabbedPaneLabel(Messages.getMessage("title.enums"));
			lbl.setIcon(iconEnum);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			Difference difference = project.getMappingHolder().getDifference(((Class) nodeInfo).getFullyQualifiedName());
			if (difference != null && difference.differs()) {
				methodsPanelPresenter.setExtraMethods(difference.getExtraMethods());
				fieldsPanelPresenter.setExtraFields(difference.getExtraFields());
			}

			sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Class umlclass = project.getMappingHolder().getJava2UmlMapping()
					.getClass(((Class) nodeInfo).getFullyQualifiedName());

			// if (umlclass != null) {
			umlClassPanel = new ClassPanel(umlclass, difference);
			umlClassPanel.setVisible(true);
			rightPanel.setBottomComponent(umlClassPanel);
			// }

		}
		if (nodeInfo instanceof Interface) {
			tabbedPane.removeAll();
			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());
			JLabel lbl = new TabbedPaneLabel(Messages.getMessage("title.info"));
			lbl.setIcon(iconInfo);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			fieldsPanelPresenter = FieldsPanelPresenter.getInstance(((Interface) nodeInfo).getFields());
			tabbedPane.addTab(Messages.getMessage("title.fields"), iconField, fieldsPanelPresenter.getDisplay().asComponent());
			lbl = new TabbedPaneLabel(Messages.getMessage("title.fields"));
			lbl.setIcon(iconField);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			methodsPanelPresenter = MethodsPanelPresenter.getInstance(((Interface) nodeInfo).getMethods());
			tabbedPane.addTab(Messages.getMessage("title.methods"), iconMethod, methodsPanelPresenter.getDisplay().asComponent());
			lbl = new TabbedPaneLabel(Messages.getMessage("title.methods"));
			lbl.setIcon(iconMethod);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Interface umlinterface = project.getMappingHolder()
					.getJava2UmlMapping().getInterface(

					((Interface) nodeInfo).getFullyQualifiedName());

			if (umlinterface != null) {
				umlInterfacePanel = new InterfacePanel(umlinterface);
				umlInterfacePanel.setVisible(true);
				rightPanel.setBottomComponent(umlInterfacePanel);
			}
		}
		if (nodeInfo instanceof Enum) {
			tabbedPane.removeAll();

			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());
			JLabel lbl = new TabbedPaneLabel(Messages.getMessage("title.info"));
			lbl.setIcon(iconInfo);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			enumValuesPresenter = EnumValuesPresenter.getInstance(((Enum) nodeInfo).getValues());
			tabbedPane.addTab(Messages.getMessage("title.values"), iconEnumValue, enumValuesPresenter.getDisplay().asComponent());
			lbl = new TabbedPaneLabel(Messages.getMessage("title.values"));
			lbl.setIcon(iconEnumValue);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

		}

		if (nodeInfo instanceof Method) {
			tabbedPane.removeAll();
			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());
			JLabel lbl = new TabbedPaneLabel(Messages.getMessage("title.info"));
			lbl.setIcon(iconInfo);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			methodParamsPresenter = MethodParamsPresenter.getInstance(((Method) nodeInfo).getParams());
			tabbedPane.addTab(Messages.getMessage("title.params"), iconInfo, methodParamsPresenter.getDisplay().asComponent());
			lbl = new TabbedPaneLabel(Messages.getMessage("title.params"));
			lbl.setIcon(iconInfo);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

			exceptionsPanelPresenter = ExceptionsPanelPresenter.getInstance(((Method) nodeInfo).getExceptions());
			tabbedPane.addTab(Messages.getMessage("title.exceptions"), iconInfo, exceptionsPanelPresenter.getDisplay().asComponent());
			lbl = new JLabel(Messages.getMessage("title.exceptions"));
			lbl.setIcon(iconInfo);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

		}
		if (nodeInfo instanceof Field) {
			tabbedPane.removeAll();

			infoPanelPresenter = new InfoPanelPresenter(nodeInfo);
			tabbedPane.addTab(Messages.getMessage("title.info"), iconInfo, infoPanelPresenter.getDisplay().asComponent());
			JLabel lbl = new TabbedPaneLabel(Messages.getMessage("title.info"));
			lbl.setIcon(iconInfo);
			lbl.setIconTextGap(2);
			lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, lbl);

		}
		if ((nodeInfo instanceof Package) || (nodeInfo instanceof Class) || (nodeInfo instanceof Interface)) {
			restoreWindowPrefs();
		}

		repaint();

	}

	@Override
	public void setDetailSelection(JTable table) {

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

	@Override
	public void setHelpAction(ActionListener l) {
		helpHl.addActionListener(l);
	}

	@Override
	public void setExportAction(ActionListener l) {
		exportHl.addActionListener(l);
	}

	private static final String TABLE_PROPERTIES_FILE = "projectobserverprefs.xml";

	private void initTableProperties() {
		org.jdesktop.application.Application.getInstance().getContext().getLocalStorage().setDirectory(
				new File(System.getProperty("java.io.tmpdir")));
		sessionStorage = org.jdesktop.application.Application.getInstance().getContext().getSessionStorage();

	}

	@Override
	public void saveWindowPrefs() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) navigationTree.getLastSelectedPathComponent();
		/* if nothing is selected */
		if (node == null)
			return;
		/* retrieve the node that was selected */
		Object nodeInfo = node.getUserObject();
		if ((nodeInfo instanceof Package) || (nodeInfo instanceof Class) || (nodeInfo instanceof Interface)) {
			initTableProperties();
			try {
				sessionStorage.save(this, TABLE_PROPERTIES_FILE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void restoreWindowPrefs() {
		initTableProperties();
		// restore here
		try {
			sessionStorage.restore(this, TABLE_PROPERTIES_FILE);
		} catch (Exception e1) {
		}

		if (tabbedPane.getTabCount() > 0) {
			tabbedPane.setSelectedIndex(0);
		}

	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		restoreWindowPrefs();
	}

}
