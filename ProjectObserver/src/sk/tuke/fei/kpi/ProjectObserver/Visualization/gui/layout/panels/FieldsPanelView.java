package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.FieldCellRenderer;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.FieldsTableModel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Field;

public class FieldsPanelView extends JPanel implements FieldsPanelDisplay {

	private static final long serialVersionUID = -8763946371835046265L;
	private JTable fieldsTable;
	private FieldsTableModel fieldsTableModel;

	public FieldsPanelView(List<Field> fields) {
		setLayout(new MigLayout("fill"));
		add(new JScrollPane(createFieldsTable(fields)), "grow");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JTable createFieldsTable(List<Field> fields) {

		fieldsTableModel = new FieldsTableModel();
		fieldsTableModel.setData(fields);
		fieldsTable = new JTable(fieldsTableModel);
		fieldsTable.getTableHeader().setFont(CommonFonts.tahoma14);
		// fieldsTable.setRolloverEnabled(true);
		// fieldsTable.setHorizontalScrollEnabled(true);
		fieldsTable.setFillsViewportHeight(true);
		// fieldsTable.setEditable(true);
		fieldsTable.setAutoscrolls(true);
		fieldsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SelectionListener listener = new SelectionListener(fieldsTable);
		fieldsTable.getSelectionModel().addListSelectionListener(listener);
		fieldsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);
		fieldsTable.setDefaultRenderer(Object.class, new FieldCellRenderer());
		return fieldsTable;
	}

	@Override
	public void setMouseListener(MouseListener l) {
		fieldsTable.addMouseListener(l);
	}

	@Override
	public JTable getTable() {
		return fieldsTable;

	};

	@Override
	public void setData(List<Field> fields) {
		fieldsTableModel.setData(fields);
		fieldsTableModel.fireTableDataChanged();

	}

	@Override
	public void setExtraFields(List<Field> fields) {
		fieldsTableModel.setExtraFields(fields);

	}
}
