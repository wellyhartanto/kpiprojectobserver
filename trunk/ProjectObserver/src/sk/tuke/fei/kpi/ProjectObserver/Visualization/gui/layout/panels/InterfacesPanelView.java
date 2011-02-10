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

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.InterfacesTableModel;

public class InterfacesPanelView extends JPanel implements InterfacesPanelDisplay {

	private static final long serialVersionUID = -4309355923133943804L;

	public InterfacesPanelView(List<Interface> interfaces) {
		setLayout(new MigLayout("fillx"));
		add(new JScrollPane(createInterfacesTable(interfaces)), "growx");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JXTable createInterfacesTable(List<Interface> interfaces) {

		InterfacesTableModel interfacesTableModel = new InterfacesTableModel();
		interfacesTableModel.setData(interfaces);

		JXTable interfacesTable = new JXTable(interfacesTableModel);
		interfacesTable.getTableHeader().setFont(MyFonts.font3);
		interfacesTable.setRolloverEnabled(true);
		interfacesTable.setHorizontalScrollEnabled(true);
		interfacesTable.setFillsViewportHeight(true);
		interfacesTable.setEditable(true);

		interfacesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SelectionListener listener = new SelectionListener(interfacesTable);
		interfacesTable.getSelectionModel().addListSelectionListener(listener);
		interfacesTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

		interfacesTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// documentTableMouseClicked(e, interfacesTable);
			}
		});

		return interfacesTable;
	}
}
