package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.JXTable;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonConstants;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.ComponentsBuilder;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.JErrorPanel;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.JTextFieldLimit;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Languages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.tablemodels.ProjectsTableModel;
import sk.tuke.fei.kpi.akAgent.integration.Project;

public class LoginPanelView extends JPanel implements LoginPanelDisplay {

	private static final long serialVersionUID = -6427921875113787927L;

	@Override
	public JComponent asComponent() {
		return this;
	}

	private JComboBox languages;

	private JXTable projectsTable;
	private JTextPane infoProjectDescription;

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

	private JErrorPanel namePanel;
	private JErrorPanel descriptionPanel;
	private JErrorPanel umlFilePanel;
	private JErrorPanel sourceCodeFilePanel;

	private JXHyperlink copyrightHyperlink;

	String descriptionBackgroundText;
	String nameBackgroundText;
	Color backgroundTextColor = Color.GRAY;
	Color forgroundTextColor = Color.BLACK;

	public LoginPanelView() {

		initComponents();

	}

	private void initComponents() {
		// String[] language = { LoginPanelPresenter.SK_LANGUAGE,
		// LoginPanelPresenter.EN_LANGUAGE };

//		setBackground(CommonColors.LOGIN_BACKGROUND_COLOR);

		languages = new JComboBox(Languages.getlanguages());
		Preferences p = Preferences
				.userNodeForPackage(LoginPanelPresenter.class);
		int defaultLanguage = p.getInt(CommonConstants.DEFAULT_LANGUAGE,
				Languages.SK.ordinal());
		languages.setSelectedIndex(defaultLanguage);

		projectsTable = new JXTable(new ProjectsTableModel());
		projectsTable.getTableHeader().setFont(CommonFonts.tahoma14);
		projectsTable.setRolloverEnabled(true);
		projectsTable.setHorizontalScrollEnabled(true);
		projectsTable.setFillsViewportHeight(true);
		projectsTable.setEditable(true);
		projectsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		projectsTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {

						Project p = getSelectedProject();
						if (p != null) {
							infoProjectDescription.setText(p.getDescription());
							repaint();
						}

					}
				});

		openProject = ComponentsBuilder.createLoginPanelButton(Messages
				.getMessage("loginpanel.buttons.open"));
		deleteProject = ComponentsBuilder.createLoginPanelButton(Messages
				.getMessage("loginpanel.buttons.delete"));
		importProject = ComponentsBuilder.createLoginPanelButton(Messages
				.getMessage("loginpanel.buttons.import"));
		exportProject = ComponentsBuilder.createLoginPanelButton(Messages
				.getMessage("loginpanel.buttons.export"));

		createProject = ComponentsBuilder.createLoginPanelButton(Messages
				.getMessage("loginpanel.buttons.create"));
		loadSourceCode = ComponentsBuilder.createLoginPanelButton(Messages
				.getMessage("loginpanel.buttons.loadsourcefile"));
		loadSourceCode.setToolTipText(Messages
				.getMessage("tooltip.chooseowlfile"));
		loadUmlModel = ComponentsBuilder.createLoginPanelButton(Messages
				.getMessage("loginpanel.buttons.loadumlfile"));
		loadUmlModel.setToolTipText(Messages
				.getMessage("tooltip.choosexmlfile"));

		sourceCodeFileLbl = new JLabel();
		umlFileLbl = new JLabel();

		projectName = new JTextField(50);
		projectName.setDocument(new JTextFieldLimit(20));
		nameBackgroundText = Messages.getMessage("loginpanel.newproject.name");
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
		descriptionBackgroundText = Messages
				.getMessage("loginpanel.newproject.description");
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
				if (projectDescription.getText().equals(
						descriptionBackgroundText)) {
					projectDescription.setText("");
					projectDescription.setForeground(forgroundTextColor);
				}
			}
		});

		// infoProjectDescription = new JTextArea(4, 50);

		infoProjectDescription = new JTextPane();
		infoProjectDescription.setMaximumSize(projectDescription
				.getPreferredSize());
		infoProjectDescription.setMinimumSize(new Dimension(100, 80));
		// infoProjectDescription.setLineWrap(true);
		// infoProjectDescription.setAutoscrolls(true);
		// infoProjectDescription.setDocument(new JTextFieldLimit(300));
		infoProjectDescription.setOpaque(true);
		infoProjectDescription.setBackground(new Color(255, 255, 255, 0));
		infoProjectDescription.setEditable(false);
		infoProjectDescription.setDisabledTextColor(Color.BLACK);
		infoProjectDescription.setEnabled(false);
		
		infoProjectDescription.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		

		namePanel = new JErrorPanel(projectName, Messages
				.getMessage("message.error.fillproject"));
		descriptionPanel = new JErrorPanel(projectDescription, Messages
				.getMessage("message.error.fillproject"));
		umlFilePanel = new JErrorPanel(loadUmlModel, Messages
				.getMessage("message.error.selectfile"));
		sourceCodeFilePanel = new JErrorPanel(loadSourceCode, Messages
				.getMessage("message.error.selectfile"));

		Dimension scBtnSize = loadSourceCode.getPreferredSize();
		Dimension umlBtnSize = loadUmlModel.getPreferredSize();

		loadSourceCode.setMinimumSize(umlBtnSize.getWidth() > scBtnSize
				.getWidth() ? umlBtnSize : scBtnSize);
		loadUmlModel.setMinimumSize(umlBtnSize.getWidth() > scBtnSize
				.getWidth() ? umlBtnSize : scBtnSize);

		copyrightHyperlink = ComponentsBuilder
				.createLoginAboutHyperlink(Messages
						.getMessage("loginpanel.copyright"));

		setComponentsPosition();
	}

	private void setComponentsPosition() {

		JPanel loginPanel = new JPanel();

		loginPanel.setLayout(new MigLayout("", "20[growprio 50]20[]20",
				"20[][]10[][][][][]"));

		JScrollPane scroll = new JScrollPane(projectsTable);
		scroll.setMaximumSize(new Dimension(1000, 200));

		JPanel buttonsPanel = new JPanel(new MigLayout("insets 0"));
		buttonsPanel.setOpaque(false);
		buttonsPanel.add(openProject, "wrap");
		buttonsPanel.add(deleteProject, "wrap");
		buttonsPanel.add(importProject, "wrap");
		buttonsPanel.add(exportProject, "wrap");

		loginPanel.add(scroll, "growx");
		loginPanel.add(buttonsPanel, "wrap");

		loginPanel.add(infoProjectDescription, "grow,wrap");

		loginPanel.add(namePanel);
		loginPanel.add(createProject, "wrap,top");
		loginPanel.add(descriptionPanel, "wrap");
		loginPanel.add(sourceCodeFilePanel, "split 2");
		loginPanel.add(sourceCodeFileLbl, "span,gaptop 7,top,wrap");
		loginPanel.add(umlFilePanel, "split 2");
		loginPanel.add(umlFileLbl, "growx,gaptop 7,top");

		loginPanel.add(languages, "align right,wrap");
//		loginPanel.setBackground(CommonColors.LOGINPANEL_COLOR);

		JPanel aboutPanel = new JPanel(new MigLayout("insets 5"));
		//aboutPanel.setBackground(CommonColors.LOGIN_ABOUT_PANEL_COLOR);
		aboutPanel.add(copyrightHyperlink,"growx,align right");

		JPanel centerPanel = new JPanel(new MigLayout("insets 0", "", "[]0[]"));

		centerPanel.add(loginPanel, "wrap");
		centerPanel.add(aboutPanel, "growx,wrap");

		add(centerPanel, BorderLayout.CENTER);

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

			int index = projectsTable.convertRowIndexToModel(projectsTable
					.getSelectedRow());

			ProjectsTableModel tableModel = (ProjectsTableModel) projectsTable
					.getModel();
			return tableModel.getData().get(index);
		}
		return null;
	}

	@Override
	public void removeProjectFromList(Project project) {
		ProjectsTableModel tableModel = (ProjectsTableModel) projectsTable
				.getModel();
		tableModel.getData().remove(project);
		tableModel.fireTableDataChanged();
		infoProjectDescription.setText(null);

		repaint();
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

	@Override
	public boolean isNewProjectCorrect(File umlFile, File sourceCodeFile) {

		boolean isCorrect = true;

		if (projectName.getText().isEmpty()
				|| projectName.getForeground() == backgroundTextColor) {
			isCorrect = false;
			namePanel.showErrorMessage(true);
		} else {
			namePanel.showErrorMessage(false);
		}

		if (projectDescription.getText().isEmpty()
				|| projectDescription.getForeground() == backgroundTextColor) {
			isCorrect = false;
			descriptionPanel.showErrorMessage(true);
		} else {
			descriptionPanel.showErrorMessage(false);
		}

		if (umlFile == null) {
			isCorrect = false;
			umlFilePanel.showErrorMessage(true);
		} else {
			umlFilePanel.showErrorMessage(false);
		}
		if (sourceCodeFile == null) {
			isCorrect = false;
			sourceCodeFilePanel.showErrorMessage(true);
		} else {
			sourceCodeFilePanel.showErrorMessage(false);
		}

		return isCorrect;
	}

	@Override
	public void setLanguageChangeAction(ActionListener actionListener) {
		languages.addActionListener(actionListener);
	}

	@Override
	public Object getSelectedLanguage() {

		return languages.getSelectedItem();
	}

	@Override
	public void setKpiHyperlinkAction(ActionListener actionListener) {
		copyrightHyperlink.addActionListener(actionListener);
	}

}
