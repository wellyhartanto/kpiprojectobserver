package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.MethodCellRenderer;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.MethodsTableModel;

public class MethodsPanelView extends JPanel implements MethodsPanelDisplay {

	private static final long serialVersionUID = 6261755584520980692L;
	private JTable methodsTable;
	private MethodsTableModel methodsTableModel;

	public MethodsPanelView(List<Method> methods) {
		setLayout(new MigLayout("fill"));
		add(new JScrollPane(createMethodsTable(methods)), "grow");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JTable createMethodsTable(List<Method> methods) {
		methodsTableModel = new MethodsTableModel();
		methodsTableModel.setData(methods);
		methodsTable = new JTable(methodsTableModel);
		methodsTable.getTableHeader().setFont(MyFonts.tahoma14);
		// methodsTable.setRolloverEnabled(true);
		// methodsTable.setHorizontalScrollEnabled(true);
		methodsTable.setFillsViewportHeight(true);
		// methodsTable.setEditable(true);
		methodsTable.setAutoCreateRowSorter(true);
		methodsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SelectionListener listener = new SelectionListener(methodsTable);
		methodsTable.getSelectionModel().addListSelectionListener(listener);
		methodsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);
		methodsTable.setDefaultRenderer(Object.class, new MethodCellRenderer());
		return methodsTable;

	}

	@Override
	public void setMouseListener(MouseListener l) {
		methodsTable.addMouseListener(l);
	}

	public JTable getTable() {
		return methodsTable;
	};

	@Override
	public void setData(List<Method> methods) {
		methodsTableModel.setData(methods);
		methodsTableModel.fireTableDataChanged();
	}

	@Override
	public void setExtraMethods(List<Method> methods) {
		methodsTableModel.setExtraMethods(methods);
	}
}
