package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.models.GenericTableModel;

public class InterfacesTableModel extends GenericTableModel<Interface> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1629050875612660093L;

	public InterfacesTableModel(Locale locale) {
		super(locale);
		data = new ArrayList<Interface>();

		columnNames = new String[] { bundle
				.getString("table.interfacestable.column.name") };

	}

}
