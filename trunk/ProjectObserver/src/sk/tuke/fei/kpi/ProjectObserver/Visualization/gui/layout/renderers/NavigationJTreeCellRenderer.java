package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.akAgent.integration.Project;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package;

public class NavigationJTreeCellRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 5876364977896542973L;

	private static final String IMAGES_FOLDER_PATH = "/sk/tuke/fei/kpi/ProjectObserver/Visualization/gui/resources/images/";

	private ImageIcon iconPackage;
	private ImageIcon iconInterface;
	private ImageIcon iconClass;
	private ImageIcon iconEnum;
	private ImageIcon iconMethod;
	private ImageIcon iconField;
	private ImageIcon iconEnumValue;

	private ImageIcon iconPackageDif;
	private ImageIcon iconClassDif;
	private ImageIcon iconInterfaceDif;

	private Project project;
	private JTree tree;

	public NavigationJTreeCellRenderer(Project project, JTree tree) {

		this.project = project;
		this.tree = tree;

		setOpaque(true);

		iconPackage = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "package_obj.gif"));
		iconInterface = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "int_obj.gif"));
		iconClass = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "classes.gif"));
		iconEnum = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "enum_obj.gif"));
		iconMethod = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "method_obj.gif"));
		iconField = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "field_obj.gif"));
		iconEnumValue = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "enum_value_obj.gif"));
		iconPackageDif = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "packagedif.gif"));

		iconClassDif = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "classdif.gif"));
		iconInterfaceDif = new ImageIcon(getClass().getResource(IMAGES_FOLDER_PATH + "interfacedif.gif"));

	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		setBackground(new Color(255, 255, 255, 0));

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

		setToolTipText(null);

		setPackage(node);

		setInterface(node);

		setClass(node);

		setEnum(node);

		setMethod(node);

		setField(node);

		return this;
	}

	private void setPackage(DefaultMutableTreeNode node) {
		if (node.getUserObject() instanceof Package) {
			setIcon(iconPackage);
			boolean hasd = hasPackageDifferences((Package) node.getUserObject());
			if (hasPackageDifferences((Package) node.getUserObject()) && !isNodeExpanded(node)) {
				setIcon(iconPackageDif);
				// setForeground(Color.RED);
				setToolTipText(Messages.getMessage("tooltip.packagediffers"));
			}
		}
	}

	private void setInterface(DefaultMutableTreeNode node) {
		if (node.getUserObject() instanceof Interface) {
			setIcon(iconInterface);
			if (project.getMappingHolder().getDifference(((Interface) node.getUserObject()).getFullyQualifiedName()) != null
					&& project.getMappingHolder().getDifference(((Interface) node.getUserObject()).getFullyQualifiedName()).differs()) {
				setIcon(iconInterfaceDif);
				// setForeground(Color.RED);
				setToolTipText(Messages.getMessage("tooltip.containdifferences"));
			}

		}
	}

	private void setClass(DefaultMutableTreeNode node) {

		if (node.getUserObject() instanceof Class) {
			setIcon(iconClass);
			if (project.getMappingHolder().getDifference(((Class) node.getUserObject()).getFullyQualifiedName()) != null
					&& project.getMappingHolder().getDifference(((Class) node.getUserObject()).getFullyQualifiedName()).differs()) {
				setIcon(iconClassDif);
				// setForeground(Color.RED);
				setToolTipText(Messages.getMessage("tooltip.containdifferences"));
			}

		}
	}

	private void setEnum(DefaultMutableTreeNode node) {
		if (node.getUserObject() instanceof Enum) {
			setIcon(iconEnum);
		}
	}

	private void setMethod(DefaultMutableTreeNode node) {
		if (node.getUserObject() instanceof Method) {
			setIcon(iconMethod);
		}
	}

	private void setField(DefaultMutableTreeNode node) {

		if (node.getUserObject() instanceof Field) {
			setIcon(iconField);
		}
	}

	private boolean hasPackageDifferences(Package package1) {
		boolean result = false;

		for (Package pack : package1.getPackages()) {
			result = hasPackageDifferences(pack);
			if (result) {
				return true;
			}
		}
		for (Class class1 : package1.getClasses()) {

			if (project.getMappingHolder().getDifference(class1.getFullyQualifiedName()) != null
					&& project.getMappingHolder().getDifference(class1.getFullyQualifiedName()).differs()) {
				result = true;
			}
		}
		for (Interface interface1 : package1.getInterfaces()) {
			if (project.getMappingHolder().getDifference(interface1.getFullyQualifiedName()) != null
					&& project.getMappingHolder().getDifference(interface1.getFullyQualifiedName()).differs()) {
				result = true;
			}
		}
		return result;
	}

	private boolean isNodeExpanded(DefaultMutableTreeNode node) {
		TreeNode[] ptr = node.getPath();
		TreePath tp = new TreePath(ptr);
		return tree.isExpanded(tp);
	}

}
