package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Param;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.ParamsTableModel;

public class MethodParamsView extends JPanel implements MethodParamsDisplay {

	private static final long serialVersionUID = 3168521799892831140L;

	public MethodParamsView(Param[] parameters) {
		setLayout(new MigLayout("fillx"));

		add(new JScrollPane(createParamsTable(parameters)), "growx");

	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JXTable createParamsTable(Param[] parameters) {

		List<Param> params = Arrays.asList(parameters);
		// List<Param> params = null;
		ParamsTableModel paramsTableModel = new ParamsTableModel();
		paramsTableModel.setData(params);

		JXTable paramsTable = new JXTable(paramsTableModel);
		paramsTable.getTableHeader().setFont(MyFonts.font3);
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
}
