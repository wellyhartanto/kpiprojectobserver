package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ClassMethodsTable extends JTable{


	private static final long serialVersionUID = 5841175227984561071L;


	String[] colNames = new String[] { "A", "B"};

	public ClassMethodsTable() {

		setOpaque(false);
		Color alphaZero = new Color(0, true);
		setBackground(alphaZero);

		setEnabled(false);

		setModel(new ClassMethodsTableModel());
		setTableHeader(null);
		setAutoscrolls(true);
		setGridColor(Color.WHITE);

		
	//	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	



}
