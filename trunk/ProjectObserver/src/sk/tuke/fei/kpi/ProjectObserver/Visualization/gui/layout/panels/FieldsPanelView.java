package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.FieldsTableModel;

public class FieldsPanelView extends JPanel implements FieldsPanelDisplay {

	private static final long serialVersionUID = -8763946371835046265L;

	public FieldsPanelView(List<Field> fields) {
		setLayout(new MigLayout("fillx"));
		add(new JScrollPane(createFieldsTable(fields)), "growx");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JXTable createFieldsTable(List<Field> fields) {

		FieldsTableModel fieldsTableModel = new FieldsTableModel();
		fieldsTableModel.setData(fields);

		JXTable fieldsTable = new JXTable(fieldsTableModel);
		fieldsTable.getTableHeader().setFont(MyFonts.font3);
		fieldsTable.setRolloverEnabled(true);
		fieldsTable.setHorizontalScrollEnabled(true);
		fieldsTable.setFillsViewportHeight(true);
		fieldsTable.setEditable(true);

		fieldsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SelectionListener listener = new SelectionListener(fieldsTable);
		fieldsTable.getSelectionModel().addListSelectionListener(listener);
		fieldsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

		fieldsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// documentTableMouseClicked(e, fieldsTable);
			}
		});
		return fieldsTable;
	}
}
