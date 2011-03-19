package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.Project;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.TypeElement;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.MainFrame;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog.SearchDialogPresenter;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog.SearchDialogView;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.NavigationJTreeCellRenderer;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

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

		display.setSearchAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
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
		});
		display.setChangeProjectAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(new LoginPanelPresenter().getDisplay().asComponent());
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

		JTree navigationTree = new JTree(root);

		navigationTree.setCellRenderer(new NavigationJTreeCellRenderer());

		navigationTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

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

}
