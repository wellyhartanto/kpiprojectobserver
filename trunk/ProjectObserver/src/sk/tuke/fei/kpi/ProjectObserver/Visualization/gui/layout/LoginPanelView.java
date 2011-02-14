package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Integration.Project;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.JTextFieldLimit;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyResourceBundle;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.ProjectsTableModel;

import net.miginfocom.swing.MigLayout;

public class LoginPanelView extends JPanel implements LoginPanelDisplay {

	private static final long serialVersionUID = -6427921875113787927L;

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JXTable projectsTable;

	private JButton openProject;
	private JButton deleteProject;

	private JButton importProject;
	private JButton exportProject;

	private JButton createProject;
	private JButton loadSourceCode;
	private JButton loadUmlModel;

	private JLabel sourceCodeFileLbl;
	private JLabel umlFileLbl;

	private JTextField projectName;
	private JTextArea projectDescription;

	String descriptionBackgroundText;
	String nameBackgroundText;
	Color backgroundTextColor = Color.GRAY;
	Color forgroundTextColor = Color.BLACK;

	public LoginPanelView() {

		initComponents();

	}

	private void initComponents() {

		projectsTable = new JXTable(new ProjectsTableModel());
		projectsTable.getTableHeader().setFont(MyFonts.font3);
		projectsTable.setRolloverEnabled(true);
		projectsTable.setHorizontalScrollEnabled(true);
		projectsTable.setFillsViewportHeight(true);
		projectsTable.setEditable(true);
		projectsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		Font buttonsFont = MyFonts.font2;
		Dimension buttonsSize = new Dimension(120, 30);

		openProject = new JButton(MyResourceBundle.getMessage("loginpanel.buttons.open"));
		deleteProject = new JButton(MyResourceBundle.getMessage("loginpanel.buttons.delete"));
		importProject = new JButton(MyResourceBundle.getMessage("loginpanel.buttons.import"));
		exportProject = new JButton(MyResourceBundle.getMessage("loginpanel.buttons.export"));

		createProject = new JButton(MyResourceBundle.getMessage("loginpanel.buttons.create"));
		loadSourceCode = new JButton(MyResourceBundle.getMessage("loginpanel.buttons.loadsourcefile"));
		loadUmlModel = new JButton(MyResourceBundle.getMessage("loginpanel.buttons.loadumlfile"));

		openProject.setFont(buttonsFont);
		deleteProject.setFont(buttonsFont);
		importProject.setFont(buttonsFont);
		exportProject.setFont(buttonsFont);
		createProject.setFont(buttonsFont);
		loadSourceCode.setFont(buttonsFont);
		loadUmlModel.setFont(buttonsFont);

		sourceCodeFileLbl = new JLabel();
		umlFileLbl = new JLabel();

		openProject.setMinimumSize(buttonsSize);
		deleteProject.setMinimumSize(buttonsSize);
		importProject.setMinimumSize(buttonsSize);
		exportProject.setMinimumSize(buttonsSize);
		createProject.setMinimumSize(buttonsSize);
		loadSourceCode.setMinimumSize(buttonsSize);
		loadUmlModel.setMinimumSize(buttonsSize);

		projectName = new JTextField(50);
		projectName.setDocument(new JTextFieldLimit(20));
		nameBackgroundText = MyResourceBundle.getMessage("loginpanel.newproject.name");
		projectName.setText(nameBackgroundText);
		projectName.setForeground(backgroundTextColor);
		projectName.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (projectName.getText().isEmpty()) {
					projectName.setText(nameBackgroundText);
					projectName.setForeground(backgroundTextColor);

				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (projectName.getText().equals(nameBackgroundText)) {
					projectName.setText("");
					projectName.setForeground(forgroundTextColor);
				}
			}
		});

		projectDescription = new JTextArea(4, 50);
		projectDescription.setMaximumSize(new Dimension(1000, 70));
		projectDescription.setMinimumSize(new Dimension(100, 70));
		projectDescription.setLineWrap(true);
		projectDescription.setAutoscrolls(true);
		projectDescription.setDocument(new JTextFieldLimit(300));
		descriptionBackgroundText = MyResourceBundle.getMessage("loginpanel.newproject.description");
		projectDescription.setText(descriptionBackgroundText);
		projectDescription.setForeground(backgroundTextColor);

		projectDescription.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (projectDescription.getText().isEmpty()) {
					projectDescription.setText(descriptionBackgroundText);
					projectDescription.setForeground(backgroundTextColor);

				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (projectDescription.getText().equals(descriptionBackgroundText)) {
					projectDescription.setText("");
					projectDescription.setForeground(forgroundTextColor);
				}
			}
		});

		setComponentsPosition();
	}

	private void setComponentsPosition() {
		setLayout(new MigLayout("", "50[]50[]", "60[]150[][][][]"));

		JScrollPane scroll = new JScrollPane(projectsTable);
		scroll.setMaximumSize(new Dimension(1000, 200));

		JPanel buttonsPanel = new JPanel(new MigLayout("insets 0"));
		buttonsPanel.add(openProject, "wrap");
		buttonsPanel.add(deleteProject, "wrap");
		buttonsPanel.add(importProject, "wrap");
		buttonsPanel.add(exportProject, "wrap");

		add(scroll, "growx");
		add(buttonsPanel, "wrap");

		add(projectName);
		add(createProject, "wrap");
		add(projectDescription, "wrap");
		add(loadSourceCode, "split 2");
		add(sourceCodeFileLbl, "wrap");
		add(loadUmlModel, "split 2");
		add(umlFileLbl, "wrap");

	}

	@Override
	public void setOpenAction(ActionListener actionListener) {
		openProject.addActionListener(actionListener);
	}

	@Override
	public void setCreateAction(ActionListener actionListener) {
		createProject.addActionListener(actionListener);

	}

	@Override
	public void setDeleteAction(ActionListener actionListener) {
		deleteProject.addActionListener(actionListener);
	}

	@Override
	public void setExportAction(ActionListener actionListener) {
		exportProject.addActionListener(actionListener);
	}

	@Override
	public void setImportAction(ActionListener actionListener) {
		importProject.addActionListener(actionListener);
	}

	@Override
	public Project getSelectedProject() {

		if (projectsTable.getSelectedRow() >= 0) {

			int index = projectsTable.convertRowIndexToModel(projectsTable.getSelectedRow());

			ProjectsTableModel tableModel = (ProjectsTableModel) projectsTable.getModel();
			return tableModel.getData().get(index);
		}
		return null;
	}

	@Override
	public void removeProjectFromList(Project project) {
		ProjectsTableModel tableModel = (ProjectsTableModel) projectsTable.getModel();
		tableModel.getData().remove(project);
		tableModel.fireTableDataChanged();

	}

	@Override
	public void setNameAndDescription(Project project) {

		project.setName(projectName.getText());
		project.setDescription(projectDescription.getText());

	}

	@Override
	public void setLoadSourceAction(ActionListener actionListener) {
		loadSourceCode.addActionListener(actionListener);
	}

	@Override
	public void setLoadUmlAction(ActionListener actionListener) {
		loadUmlModel.addActionListener(actionListener);
	}

	@Override
	public void setSourceCodeFileLabel(String filename) {
		sourceCodeFileLbl.setText(filename);
	}

	@Override
	public void setUmlFileLabel(String filename) {
		umlFileLbl.setText(filename);
	}

	@Override
	public void refreshTableModel() {
		projectsTable.setModel(new ProjectsTableModel());
	}

}
