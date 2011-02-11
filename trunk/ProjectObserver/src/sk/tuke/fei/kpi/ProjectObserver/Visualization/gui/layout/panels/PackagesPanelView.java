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

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.panels.common.SelectionListener;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.java.PackagesTableModel;

public class PackagesPanelView extends JPanel implements PackagesPanelDisplay {

	private static final long serialVersionUID = -7778417156272491486L;

	public PackagesPanelView(List<Package> packages) {
		setLayout(new MigLayout("fillx"));

		add(new JScrollPane(createPackagesTable(packages)), "growx");
	}

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JXTable createPackagesTable(List<Package> packages) {

		PackagesTableModel packagesTableModel = new PackagesTableModel();
		packagesTableModel.setData(packages);

		JXTable packagesTable = new JXTable(packagesTableModel);

	//	packagesTable.setShowGrid(true, true);

		packagesTable.getTableHeader().setFont(MyFonts.font3);
		packagesTable.setRolloverEnabled(true);
		packagesTable.setHorizontalScrollEnabled(true);
		packagesTable.setFillsViewportHeight(true);
		packagesTable.setEditable(true);

		packagesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		SelectionListener listener = new SelectionListener(packagesTable);
		packagesTable.getSelectionModel().addListSelectionListener(listener);
		packagesTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);

		packagesTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// documentTableMouseClicked(e, packagesTable);
			}
		});
		return packagesTable;
	}
}
