package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Param;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.ParamsTableModel;

public class MethodParamsView extends JPanel implements MethodParamsDisplay {

	private static final long serialVersionUID = 3168521799892831140L;

	private JXTable paramsTable;
	private ParamsTableModel paramsTableModel ;
	public MethodParamsView(List<Param> parameters) {
		setLayout(new MigLayout("fill"));
		add(new JScrollPane(createParamsTable(parameters)), "grow");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JXTable createParamsTable(List<Param> parameters) {
		// List<Param> params = null;
		 paramsTableModel = new ParamsTableModel();
		paramsTableModel.setData(parameters);

		paramsTable = new JXTable(paramsTableModel);
		paramsTable.getTableHeader().setFont(CommonFonts.tahoma14);
		paramsTable.setRolloverEnabled(true);
		paramsTable.setHorizontalScrollEnabled(true);
		paramsTable.setFillsViewportHeight(true);
		paramsTable.setEditable(true);

		paramsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SelectionListener listener = new SelectionListener(paramsTable);
		paramsTable.getSelectionModel().addListSelectionListener(listener);
		paramsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

		paramsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// documentTableMouseClicked(e, paramsTable);
			}
		});
		return paramsTable;

	}

	@Override
	public void setMouseListener(MouseListener l) {
		paramsTable.addMouseListener(l);
	}

	@Override
	public JXTable getTable() {
		return paramsTable;

	};

	@Override
	public void setData(List<Param> params) {
		paramsTableModel.setData(params);
		paramsTableModel.fireTableDataChanged();
	}
}
