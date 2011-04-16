package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXLabel;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;

public class HelpDialogView extends JDialog{
	
	public HelpDialogView() {
		setTitle(Messages.getMessage("dialog.help.title"));
		setResizable(false);
		setLocationByPlatform(true);
		setLayout(new MigLayout("insets 0,fill"));
		JPanel helpPanel = new JPanel(new MigLayout("insets 0"));
		helpPanel.setBackground(Color.WHITE);
		
		
		JLabel newProject = new JLabel(Messages.getMessage("dialog.help.text.newproject.title"));
		newProject.setFont(CommonFonts.getInfoLabelFont());
		newProject.setForeground(Color.DARK_GRAY);
		JXLabel newProject1 = new JXLabel(Messages.getMessage("dialog.help.text.newproject.1"));
		newProject1.setFont(CommonFonts.getAboutTextFont());
		newProject1.setLineWrap(true);
		newProject1.setForeground(Color.GRAY);

		JXLabel newProject2 = new JXLabel(Messages.getMessage("dialog.help.text.newproject.2"));
		newProject2.setFont(CommonFonts.getAboutTextFont());
		newProject2.setLineWrap(true);
		newProject2.setForeground(Color.GRAY);

		JXLabel newProject3 = new JXLabel(Messages.getMessage("dialog.help.text.newproject.3"));
		newProject3.setFont(CommonFonts.getAboutTextFont());
		newProject3.setLineWrap(true);
		newProject3.setForeground(Color.GRAY);

		JXLabel newProject4 = new JXLabel(Messages.getMessage("dialog.help.text.newproject.4"));
		newProject4.setFont(CommonFonts.getAboutTextFont());
		newProject4.setLineWrap(true);
		newProject4.setForeground(Color.GRAY);

		JLabel importProject = new JLabel(Messages.getMessage("dialog.help.text.importproject.title"));
		importProject.setFont(CommonFonts.getInfoLabelFont());
		importProject.setForeground(Color.DARK_GRAY);

		JXLabel importProject1 = new JXLabel(Messages.getMessage("dialog.help.text.importproject.1"));
		importProject1.setFont(CommonFonts.getAboutTextFont());
		importProject1.setLineWrap(true);
		importProject1.setForeground(Color.GRAY);

		JXLabel importProject2 = new JXLabel(Messages.getMessage("dialog.help.text.importproject.2"));
		importProject2.setFont(CommonFonts.getAboutTextFont());
		importProject2.setLineWrap(true);
		importProject2.setForeground(Color.GRAY);
		JLabel exportProject = new JLabel(Messages.getMessage("dialog.help.text.exportproject.title"));
		exportProject.setFont(CommonFonts.getInfoLabelFont());
		exportProject.setForeground(Color.DARK_GRAY);

		JXLabel exportProject1 = new JXLabel(Messages.getMessage("dialog.help.text.exportproject.1"));
		exportProject1.setFont(CommonFonts.getAboutTextFont());
		exportProject1.setLineWrap(true);
		exportProject1.setForeground(Color.GRAY);

		JXLabel exportProject2 = new JXLabel(Messages.getMessage("dialog.help.text.exportproject.2"));
		exportProject2.setFont(CommonFonts.getAboutTextFont());
		exportProject2.setLineWrap(true);
		exportProject2.setForeground(Color.GRAY);

		helpPanel.add(newProject,"wrap,gaptop 2");
		helpPanel.add(newProject1,"wrap,gaptop 5");
		helpPanel.add(newProject2,"wrap");
		helpPanel.add(newProject3,"wrap");
		helpPanel.add(newProject4,"wrap");
		helpPanel.add(importProject,"wrap,gaptop 20");
		helpPanel.add(importProject1,"wrap,gaptop 5");
		helpPanel.add(importProject2,"wrap");
		helpPanel.add(exportProject,"wrap,gaptop 20");
		helpPanel.add(exportProject1,"wrap,gaptop 5");
		helpPanel.add(exportProject2,"wrap");
		
		helpPanel.setMaximumSize(new Dimension(300, 700));
		helpPanel.setMinimumSize(new Dimension(50, 350));

		JPanel m = new JPanel(new MigLayout("insets 20"));
		m.setBackground(Color.white);
		m.add(helpPanel,"");
		
		add(m);
		
	//	setMinimumSize(new Dimension(300, 700));
		
		pack();

	}
	
}
