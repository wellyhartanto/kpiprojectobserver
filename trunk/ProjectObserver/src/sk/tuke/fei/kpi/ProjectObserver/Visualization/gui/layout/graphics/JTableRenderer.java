/*
 * $Id: JTableRenderer.java,v 1.4 2010-05-28 13:57:32 gaudenz Exp $
 * Copyright (c) 2001-2005, Gaudenz Alder
 * 
 * All rights reserved.
 * 
 * See LICENSE file for license details. If you are unable to locate
 * this file please contact info (at) jgraph (dot) com.
 */
package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

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
			final mxGraphComponent graphContainer) {

		setLayout(new MigLayout("insets 0", "", "[]0[]0[]"));
		setOpaque(false);

		
		this.cell = cell;
		this.graphContainer = graphContainer;
		this.graph = graphContainer.getGraph();

		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setBackground(Color.RED);
		separator.setOpaque(true);
		JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
		separator1.setBackground(Color.RED);
		separator1.setOpaque(true);
		JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
		separator2.setBackground(Color.RED);
		separator2.setOpaque(true);
		
		

		JPanel name = new JPanel(new MigLayout("insets 0,fill"));
		JPanel fields = new JPanel(new MigLayout("insets 0"));
		JPanel methods = new JPanel(new MigLayout("insets 0"));
		name.setOpaque(false);
		fields.setOpaque(false);
		methods.setOpaque(false);

		JLabel classNameLbl = new JLabel("Class");
		name.add(classNameLbl);

		MyTable fieldsTable = new MyTable();
		MyTable methodsTable = new MyTable();

		fields.add(fieldsTable);
		methods.add(methodsTable);

		add(name, "wrap,span,center");
		add(separator, "wrap,span,growx");
		add(fields, "wrap");
		add(separator1, "wrap,span,growx");
		add(methods, "wrap");
		add(separator2, "wrap,span,growx");
		
		
	}
	
	
	

	public class MyTable extends JTable {

		private static final long serialVersionUID = 5841175227984561071L;

		Object[][] data;

		String[] colNames = new String[] { "A", "B"};

		public MyTable() {
			data = new Object[5][2];
			for (int i = 0; i < 5; i++) {
				data[i][0] = "Co " + i + 2;
				data[i][1] = "Col " + i;
		//		data[i][2] = "Colu " + i / 2;
			}

			setOpaque(false);
			Color alphaZero = new Color(0, true);
			setBackground(alphaZero);

			setEnabled(false);

			setModel(createModel());
			setTableHeader(null);
			setAutoscrolls(true);
			setGridColor(Color.WHITE);

			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		public Object[][] getData() {
			return data;
		}

		/**
		 * 
		 * @return the created table model
		 */
		public TableModel createModel() {
			return new AbstractTableModel() {

				/**
				 * 
				 */
				private static final long serialVersionUID = -3642207266816170738L;

				public int getColumnCount() {
					return colNames.length;
				}

				public int getRowCount() {
					return data.length;
				}

				public String getColumnName(int col) {
					return colNames[col];
				}

				public Object getValueAt(int row, int col) {
					return data[row][col];
				}

				@SuppressWarnings("unchecked")
				public Class getColumnClass(int c) {
					Object value = getValueAt(0, c);
					return (value != null) ? value.getClass() : ImageIcon.class;
				}

				/*
				 * Don't need to implement this method unless your table's
				 * editable.
				 */
				public boolean isCellEditable(int row, int col) {
					return col == 0;
				}

				/*
				 * Don't need to implement this method unless your table's data
				 * can change.
				 */
				public void setValueAt(Object value, int row, int col) {
					data[row][col] = value;
					fireTableCellUpdated(row, col);
				}
			};

		}

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
