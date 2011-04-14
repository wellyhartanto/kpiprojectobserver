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
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.ParamsTableModel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Param;

public class MethodParamsView extends JPanel implements MethodParamsDisplay {

	private static final long serialVersionUID = 3168521799892831140L;

	private JTable paramsTable;
	private ParamsTableModel paramsTableModel ;
	public MethodParamsView(List<Param> parameters) {
		setLayout(new MigLayout("fill,insets 0"));
		add(new JScrollPane(createParamsTable(parameters)), "grow");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JTable createParamsTable(List<Param> parameters) {
		// List<Param> params = null;
		 paramsTableModel = new ParamsTableModel();
		paramsTableModel.setData(parameters);

		paramsTable = new ZebraJTable(paramsTableModel);
		paramsTable.getTableHeader().setFont(CommonFonts.getTableHeaderFont());
		paramsTable.setFont(CommonFonts.getTableContentFont());	
		
		
		paramsTable.setFillsViewportHeight(true);

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
	public JTable getTable() {
		return paramsTable;

	};

	@Override
	public void setData(List<Param> params) {
		paramsTableModel.setData(params);
		paramsTableModel.fireTableDataChanged();
	}
}
