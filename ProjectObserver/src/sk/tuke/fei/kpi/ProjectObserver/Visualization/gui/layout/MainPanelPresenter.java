package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.MainFrame;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonColors;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog.ExportDialogPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog.HelpMainDialogView;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog.SearchDialogPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.NavigationJTreeCellRenderer;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;
import sk.tuke.fei.kpi.akAgent.integration.Project;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Application;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.TypeElement;

public class MainPanelPresenter extends BasicPresenter<MainPanelDisplay> {

	private Project project;
	private SearchDialogPresenter searchDialog;

	public MainPanelPresenter(Project project) {
		this.project = project;
		display = new MainPanelView(this.project);
		bind();
	}

	@Override
	protected void onBind() {
		super.onBind();
		display.setNavigationTree(createTree());
		display.setTreeValueChangedAction();
		setListeners();
	}

	private void findSelectedElement() {
		TypeElement element = (TypeElement) searchDialog.getDisplay().getList().getSelectedValue();
		display.searchAndSelectElement(element);
		searchDialog.getDisplay().showDialog(false);
	}

	private void setListeners() {

		InputMap inputMap = display.asComponent().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK), "search");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK), "search");
		display.asComponent().getActionMap().put("search", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchaction();
			}
		});

		display.setSearchAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchaction();
			}
		});
		display.setChangeProjectAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				display.saveWindowPrefs();

				MainFrame.getMainFrame().setPanel(new LoginPanelPresenter().getDisplay().asComponent());
			}
		});

		display.setExportAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportAction();
			}
		});
		
		display.setHelpAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new HelpMainDialogView().setVisible(true);
			}
		});

		display.getClassesPanelPresenter().getDisplay().setMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					display.setDetailSelection(display.getClassesPanelPresenter().getDisplay().getTable());
				}
			}
		});

		display.getEnumsPanelPresenter().getDisplay().setMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					display.setDetailSelection(display.getEnumsPanelPresenter().getDisplay().getTable());
				}
			}
		});

		display.getEnumValuesPresenter().getDisplay().setMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					display.setDetailSelection(display.getEnumValuesPresenter().getDisplay().getTable());
				}
			}
		});

		display.getExceptionsPanelPresenter().getDisplay().setMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					display.setDetailSelection(display.getExceptionsPanelPresenter().getDisplay().getTable());
				}
			}
		});

		display.getFieldsPanelPresenter().getDisplay().setMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					display.setDetailSelection(display.getFieldsPanelPresenter().getDisplay().getTable());
				}
			}
		});

		display.getInterfacesPanelPresenter().getDisplay().setMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					display.setDetailSelection(display.getInterfacesPanelPresenter().getDisplay().getTable());
				}
			}
		});

		display.getMethodParamsPresenter().getDisplay().setMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					display.setDetailSelection(display.getMethodParamsPresenter().getDisplay().getTable());
				}
			}
		});

		display.getMethodsPanelPresenter().getDisplay().setMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					display.setDetailSelection(display.getMethodsPanelPresenter().getDisplay().getTable());
				}
			}
		});

		display.getPackagesPanelPresenter().getDisplay().setMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					display.setDetailSelection(display.getPackagesPanelPresenter().getDisplay().getTable());
				}
			}
		});

	}

	private JTree createTree() {
		Application app = null;
		// TestData.createTestData();
		// app.setName("testproject");
		app = project.getJavaModel();
		DefaultMutableTreeNode root = createApplicationTree(app);
		JTree navigationTree = new JTree(root){
			
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				int w = getWidth();
				int h = getHeight();

				// Paint a gradient from top to bottom
				GradientPaint gp = new GradientPaint(
						0, 0, new Color(248,248,248),
						0, w, new Color(253,253,253));
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
				
				setOpaque( false );
			    super.paintComponent( g );
			    setOpaque( true );
				
			}
		};
		navigationTree.setName("navigationTree");
		navigationTree.setCellRenderer(new NavigationJTreeCellRenderer(project, navigationTree));
		navigationTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		navigationTree.setFont(CommonFonts.getTreeContentFont());
		javax.swing.ToolTipManager.sharedInstance().registerComponent(navigationTree);
		navigationTree.setRootVisible(false);
		// set default width
	//	navigationTree.setPreferredSize(new Dimension(220, navigationTree.getPreferredScrollableViewportSize().height));
		
		navigationTree.setScrollsOnExpand(true);
		navigationTree.validate();
		navigationTree.setBackground(CommonColors.JTREE_BACKGROUND_COLOR);

		return navigationTree;
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

	private void searchaction() {

		searchDialog = new SearchDialogPresenter(project);
		searchDialog.getDisplay().setListClickedAction(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() >= 2) {
					findSelectedElement();
				}
			}
		});

		searchDialog.getDisplay().setOKAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				findSelectedElement();
			}
		});

		searchDialog.getDisplay().setListEnterAction(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					findSelectedElement();
				}
			}
		});

	}

	private void exportAction() {

		ExportDialogPresenter dialogPresenter = new ExportDialogPresenter(project);
		dialogPresenter.getDisplay().showDialog(true);

	}
}
