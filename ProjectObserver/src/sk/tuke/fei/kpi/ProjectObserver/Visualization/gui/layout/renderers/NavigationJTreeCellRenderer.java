package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;

public class NavigationJTreeCellRenderer extends DefaultTreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5876364977896542973L;

	private static final String IMAGES_FOLDER_PATH = "src/sk/tuke/fei/kpi/ProjectObserver/Visualization/gui/resources/images/";

	private ImageIcon iconPackage;
	private ImageIcon iconInterface;
	private ImageIcon iconClass;
	private ImageIcon iconEnum;

	public NavigationJTreeCellRenderer() {

		iconPackage = new ImageIcon(IMAGES_FOLDER_PATH + "package_obj.gif");
		iconInterface = new ImageIcon(IMAGES_FOLDER_PATH + "int_obj.gif");
		iconClass = new ImageIcon(IMAGES_FOLDER_PATH + "classes.gif");
		iconEnum = new ImageIcon(IMAGES_FOLDER_PATH + "enum_obj.gif");

	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		if (isPackage(value)) {
			setIcon(iconPackage);
		} 
		if (isInterface(value)) {
			setIcon(iconInterface);
		}

		if (isClass(value)) {
			setIcon(iconClass);
		}

		if (isEnum(value)) {
			setIcon(iconEnum);
		}

		return this;
	}

	private boolean isPackage(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		if (node.getUserObject() instanceof Package) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isInterface(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		if (node.getUserObject() instanceof Interface) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isClass(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		if (node.getUserObject() instanceof Class) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isEnum(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		if (node.getUserObject() instanceof Enum) {
			return true;
		} else {
			return false;
		}
	}

}
