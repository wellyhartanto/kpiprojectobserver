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

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.EnumValuesTableModel;

public class EnumValuesView extends JPanel implements EnumValuesDisplay {

	private static final long serialVersionUID = -1081591320587590117L;

	public EnumValuesView(String[] values) {
		setLayout(new MigLayout("fillx"));
		add(new JScrollPane(createEnumValuesTable(values)), "growx");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JXTable createEnumValuesTable(String[] values) {

		List<String> valuestmp = Arrays.asList(values);

		EnumValuesTableModel enumValuesTableModel = new EnumValuesTableModel();
		enumValuesTableModel.setData(valuestmp);

		JXTable enumValuesTable = new JXTable(enumValuesTableModel);
		enumValuesTable.getTableHeader().setFont(MyFonts.font3);
		enumValuesTable.setRolloverEnabled(true);
		enumValuesTable.setHorizontalScrollEnabled(true);
		enumValuesTable.setFillsViewportHeight(true);
		enumValuesTable.setEditable(true);

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
}
