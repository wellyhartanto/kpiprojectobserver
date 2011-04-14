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

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.ZebraJTable;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.MyTableCellRenderer;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.EnumsTableModel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Enum;

public class EnumsPanelView extends JPanel implements EnumsPanelDisplay {

	private static final long serialVersionUID = 6922425165656739899L;

	private JTable enumsTable;
	private EnumsTableModel enumsTableModel;

	public EnumsPanelView(List<Enum> enums) {
		setLayout(new MigLayout("fill,insets 0"));
		add(new JScrollPane(createEnumsTable(enums)), "grow");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JTable createEnumsTable(List<Enum> enums) {

		enumsTableModel = new EnumsTableModel();
		enumsTableModel.setData(enums);

		enumsTable =  (JTable)new ZebraJTable(enumsTableModel);
		enumsTable.getTableHeader().setFont(CommonFonts.getTableHeaderFont());
		enumsTable.setFont(CommonFonts.getTableContentFont());	
	
		enumsTable.setFillsViewportHeight(true);

		enumsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SelectionListener listener = new SelectionListener(enumsTable);
		enumsTable.getSelectionModel().addListSelectionListener(listener);
		enumsTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

		enumsTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());

		enumsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// documentTableMouseClicked(e, enumsTable);
			}
		});

		return enumsTable;
	}

	@Override
	public void setMouseListener(MouseListener l) {
		enumsTable.addMouseListener(l);
	}

	@Override
	public JTable getTable() {
		return enumsTable;

	};

	@Override
	public void setData(List<Enum> enums) {
		enumsTableModel.setData(enums);
		enumsTableModel.fireTableDataChanged();
	}

}
