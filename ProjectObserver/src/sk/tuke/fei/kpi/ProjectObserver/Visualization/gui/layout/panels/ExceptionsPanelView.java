package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;


import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.ZebraJTable;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.ExceptionsTableModel;

public class ExceptionsPanelView extends JPanel implements ExceptionsPanelDisplay {

	private static final long serialVersionUID = 2225947043959574746L;
	private JTable exceptionsTable;
	private ExceptionsTableModel exceptionsTableModel;

	public ExceptionsPanelView(List<String> exceptions) {
		setLayout(new MigLayout("fill,insets 0"));
		add(new JScrollPane(createExceptionsTable(exceptions)), "grow");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JTable createExceptionsTable(List<String> exceptions) {

		exceptionsTableModel = new ExceptionsTableModel();
		exceptionsTableModel.setData(exceptions);

		exceptionsTable = (JTable) new ZebraJTable(exceptionsTableModel);
		exceptionsTable.getTableHeader().setFont(CommonFonts.getTableHeaderFont());
		exceptionsTable.setFont(CommonFonts.getTableContentFont());	
		
		exceptionsTable.setFillsViewportHeight(true);

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
	public JTable getTable() {
		return exceptionsTable;

	};
	
	@Override
	public void setData(List<String> exceptions) {
		exceptionsTableModel.setData(exceptions);	
		exceptionsTableModel.fireTableDataChanged();
	}
}
