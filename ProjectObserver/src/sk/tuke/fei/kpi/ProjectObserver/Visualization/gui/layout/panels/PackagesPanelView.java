package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
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
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.PackagesTableModel;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package;

public class PackagesPanelView extends JPanel implements PackagesPanelDisplay {

	private static final long serialVersionUID = -7778417156272491486L;
	private JTable packagesTable;
	private PackagesTableModel packagesTableModel;

	public PackagesPanelView(List<Package> packages) {
		setLayout(new MigLayout("fill,insets 0"));
		JScrollPane sp =new JScrollPane(createPackagesTable(packages));
		sp.setBorder(BorderFactory.createEmptyBorder());
		add(sp, "grow");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JTable createPackagesTable(List<Package> packages) {

		packagesTableModel = new PackagesTableModel();
		packagesTableModel.setData(packages);

		packagesTable = new ZebraJTable(packagesTableModel);
		packagesTable.getTableHeader().setFont(CommonFonts.getTableHeaderFont());
		packagesTable.setFont(CommonFonts.getTableContentFont());
		packagesTable.setFillsViewportHeight(true);

		packagesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		SelectionListener listener = new SelectionListener(packagesTable);
		packagesTable.getSelectionModel().addListSelectionListener(listener);
		packagesTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);
		packagesTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
		
		packagesTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// documentTableMouseClicked(e, packagesTable);
			}
		});
		return packagesTable;
	}

	@Override
	public void setMouseListener(MouseListener l) {
		packagesTable.addMouseListener(l);
	}

	@Override
	public JTable getTable() {
		return packagesTable;

	};

	@Override
	public void setData(List<Package> packages) {
		packagesTableModel.setData(packages);
		packagesTableModel.fireTableDataChanged();
	}
}
