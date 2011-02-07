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

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Enum;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java.EnumsTableModel;

public class EnumsPanelView extends JPanel implements EnumsPanelDisplay {

	private static final long serialVersionUID = 6922425165656739899L;

	public EnumsPanelView(List<Enum> enums) {
		setLayout(new MigLayout("fillx"));
		add(new JScrollPane(createEnumsTable(enums)), "growx");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JXTable createEnumsTable(List<Enum> enums) {

		EnumsTableModel enumsTableModel = new EnumsTableModel();
		enumsTableModel.setData(enums);

		JXTable enumsTable = new JXTable(enumsTableModel);
		enumsTable.getTableHeader().setFont(MyFonts.font3);
		enumsTable.setRolloverEnabled(true);
		enumsTable.setHorizontalScrollEnabled(true);
		enumsTable.setFillsViewportHeight(true);
		enumsTable.setEditable(true);

		enumsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SelectionListener listener = new SelectionListener(enumsTable);
		enumsTable.getSelectionModel().addListSelectionListener(listener);
		enumsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

		enumsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// documentTableMouseClicked(e, enumsTable);
			}
		});

		return enumsTable;
	}
}