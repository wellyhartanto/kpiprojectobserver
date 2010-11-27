package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
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
	private ImageIcon iconMethod;
	private ImageIcon iconField;
	private ImageIcon iconEnumValue;
	public NavigationJTreeCellRenderer() {

		iconPackage = new ImageIcon(IMAGES_FOLDER_PATH + "package_obj.gif");
		iconInterface = new ImageIcon(IMAGES_FOLDER_PATH + "int_obj.gif");
		iconClass = new ImageIcon(IMAGES_FOLDER_PATH + "classes.gif");
		iconEnum = new ImageIcon(IMAGES_FOLDER_PATH + "enum_obj.gif");
		iconMethod = new ImageIcon(IMAGES_FOLDER_PATH + "method_obj.gif");
		iconField = new ImageIcon(IMAGES_FOLDER_PATH + "field_obj.gif");
		iconEnumValue = new ImageIcon(IMAGES_FOLDER_PATH + "enum_value_obj.gif");

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
		
		if (isMethod(value)) {
			setIcon(iconMethod);
		}
		if(isField(value)){
			setIcon(iconField);
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
	
	private boolean isMethod(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		if (node.getUserObject() instanceof Method) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isField(Object value) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		if (node.getUserObject() instanceof Field) {
			return true;
		} else {
			return false;
		}
	}

}
