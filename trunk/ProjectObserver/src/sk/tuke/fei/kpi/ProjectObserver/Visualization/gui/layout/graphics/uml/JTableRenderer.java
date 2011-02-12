/*
 * $Id: JTableRenderer.java,v 1.4 2010-05-28 13:57:32 gaudenz Exp $
 * Copyright (c) 2001-2005, Gaudenz Alder
 * 
 * All rights reserved.
 * 
 * See LICENSE file for license details. If you are unable to locate
 * this file please contact info (at) jgraph (dot) com.
 */
package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

/**
 * @author Administrator
 * 
 */
public class JTableRenderer extends JPanel {

	private static final long serialVersionUID = 2106746763664760745L;
	protected static JTableRenderer dragSource = null;
	protected static int sourceRow = 0;
	protected Object cell;
	protected mxGraphComponent graphContainer;
	protected mxGraph graph;
	public JTable table;

	public JTableRenderer(final Object cell,
			final mxGraphComponent graphContainer,sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class umlClass) {

		setLayout(new MigLayout("insets 0", "", "[]0[]0[]"));
		setOpaque(false);

		
		this.cell = cell;
		this.graphContainer = graphContainer;
		this.graph = graphContainer.getGraph();

		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setBackground(Color.BLACK);
		separator.setOpaque(true);
		JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
		separator1.setBackground(Color.BLACK);
		separator1.setOpaque(true);
		
		

		JPanel name = new JPanel(new MigLayout("insets 0,fill"));
		JPanel fields = new JPanel(new MigLayout("insets 0"));
		JPanel methods = new JPanel(new MigLayout("insets 0"));
		name.setOpaque(false);
		fields.setOpaque(false);
		methods.setOpaque(false);

		JLabel classNameLbl = new JLabel("Class");
		name.add(classNameLbl);

		ClassFieldsTable fieldsTable = new ClassFieldsTable();
		ClassMethodsTable methodsTable = new ClassMethodsTable();

		ClassFieldsTableModel fieldsTableModel = new ClassFieldsTableModel();
		fieldsTableModel.setData(umlClass.getFields());
		ClassMethodsTableModel methodsTableModel = new ClassMethodsTableModel();
		methodsTableModel.setData(umlClass.getMethods());
		
		fieldsTable.setModel(fieldsTableModel);
		methodsTable.setModel(methodsTableModel);
		
		
		fields.add(fieldsTable);
		methods.add(methodsTable);

		add(name, "wrap,span,center");
		add(separator, "wrap,span,growx");
		add(fields, "wrap");
		add(separator1, "wrap,span,growx");
		add(methods, "wrap");
		
	}
	
	
	


	/**
	 * 
	 */
	public static JTableRenderer getVertex(Component component) {
		while (component != null) {
			if (component instanceof JTableRenderer) {
				return (JTableRenderer) component;
			}
			component = component.getParent();
		}

		return null;
	}

}
