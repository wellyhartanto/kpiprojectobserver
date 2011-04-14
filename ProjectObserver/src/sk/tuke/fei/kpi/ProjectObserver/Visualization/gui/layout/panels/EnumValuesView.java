package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;


import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.EnumValuesTableModel;

public class EnumValuesView extends JPanel implements EnumValuesDisplay {

	private static final long serialVersionUID = -1081591320587590117L;
	private JTable enumValuesTable;
	private EnumValuesTableModel enumValuesTableModel;

	public EnumValuesView(String[] values) {
		setLayout(new MigLayout("fill,insets 0"));
		JScrollPane sp =new JScrollPane(createEnumValuesTable(values));
		sp.setBorder(BorderFactory.createEmptyBorder());
		add(sp, "grow");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JTable createEnumValuesTable(String[] values) {

		List<String> valuestmp = Arrays.asList(values);

		enumValuesTableModel = new EnumValuesTableModel();
		enumValuesTableModel.setData(valuestmp);

		enumValuesTable = new JTable(enumValuesTableModel);
		enumValuesTable.getTableHeader().setFont(CommonFonts.getTableHeaderFont());
		enumValuesTable.setFont(CommonFonts.getTableContentFont());	
		
		
		enumValuesTable.setFillsViewportHeight(true);

		enumValuesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SelectionListener listener = new SelectionListener(enumValuesTable);
		enumValuesTable.getSelectionModel().addListSelectionListener(listener);
		enumValuesTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

		enumValuesTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// documentTableMouseClicked(e, enumValuesTable);
			}
		});

		return enumValuesTable;

	}

	@Override
	public void setMouseListener(MouseListener l) {
		enumValuesTable.addMouseListener(l);
	}

	@Override
	public JTable getTable() {
		return enumValuesTable;

	};

	@Override
	public void setData(String[] values) {
		List<String> valuestmp = Arrays.asList(values);
		enumValuesTableModel.setData(valuestmp);

		enumValuesTableModel.fireTableDataChanged();
	}
}
