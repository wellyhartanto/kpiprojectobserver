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
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.renderers.MyTableCellRenderer;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.InterfacesTableModel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Interface;

public class InterfacesPanelView extends JPanel implements InterfacesPanelDisplay {

	private static final long serialVersionUID = -4309355923133943804L;
	private JTable interfacesTable;
	private InterfacesTableModel interfacesTableModel;

	public InterfacesPanelView(List<Interface> interfaces) {
		setLayout(new MigLayout("fill,insets 0"));
		add(new JScrollPane(createInterfacesTable(interfaces)), "grow");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JTable createInterfacesTable(List<Interface> interfaces) {

		interfacesTableModel = new InterfacesTableModel();
		interfacesTableModel.setData(interfaces);

		interfacesTable =  (JTable)new ZebraJTable(interfacesTableModel);
		interfacesTable.getTableHeader().setFont(CommonFonts.tahoma14);
//		interfacesTable.setRolloverEnabled(true);
//		interfacesTable.setHorizontalScrollEnabled(true);
		interfacesTable.setFillsViewportHeight(true);
//		interfacesTable.setEditable(true);

		interfacesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SelectionListener listener = new SelectionListener(interfacesTable);
		interfacesTable.getSelectionModel().addListSelectionListener(listener);
		interfacesTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);
		interfacesTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());

		interfacesTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// documentTableMouseClicked(e, interfacesTable);
			}
		});

		return interfacesTable;
	}

	@Override
	public void setMouseListener(MouseListener l) {
		interfacesTable.addMouseListener(l);
	}

	@Override
	public JTable getTable() {
		return interfacesTable;

	};

	@Override
	public void setData(List<Interface> interfaces) {
		interfacesTableModel.setData(interfaces);
		interfacesTableModel.fireTableDataChanged();
	}

}
