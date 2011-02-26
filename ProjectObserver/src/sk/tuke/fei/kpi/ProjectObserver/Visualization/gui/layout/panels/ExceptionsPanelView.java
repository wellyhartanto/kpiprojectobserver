package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.ExceptionsTableModel;

public class ExceptionsPanelView extends JPanel implements ExceptionsPanelDisplay {

	private static final long serialVersionUID = 2225947043959574746L;
	private JXTable exceptionsTable;
	private ExceptionsTableModel exceptionsTableModel;

	public ExceptionsPanelView(String[] exceptions) {
		setLayout(new MigLayout("fillx"));
		add(new JScrollPane(createExceptionsTable(exceptions)), "growx");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JXTable createExceptionsTable(String[] exceptions) {

		List<String> exceptionstmp = Arrays.asList(exceptions);
		exceptionsTableModel = new ExceptionsTableModel();
		exceptionsTableModel.setData(exceptionstmp);

		exceptionsTable = new JXTable(exceptionsTableModel);
		exceptionsTable.getTableHeader().setFont(MyFonts.font3);
		exceptionsTable.setRolloverEnabled(true);
		exceptionsTable.setHorizontalScrollEnabled(true);
		exceptionsTable.setFillsViewportHeight(true);
		exceptionsTable.setEditable(true);

		exceptionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SelectionListener listener = new SelectionListener(exceptionsTable);
		exceptionsTable.getSelectionModel().addListSelectionListener(listener);
		exceptionsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

		exceptionsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// documentTableMouseClicked(e, exceptionsTable);
			}
		});
		return exceptionsTable;

	}

	@Override
	public void setMouseListener(MouseListener l) {
		exceptionsTable.addMouseListener(l);
	}

	@Override
	public JXTable getTable() {
		return exceptionsTable;

	};
	
	@Override
	public void setData(String[] exceptions) {

		List<String> exceptionstmp = Arrays.asList(exceptions);
		exceptionsTableModel.setData(exceptionstmp);	
		exceptionsTableModel.fireTableDataChanged();
	}
}
