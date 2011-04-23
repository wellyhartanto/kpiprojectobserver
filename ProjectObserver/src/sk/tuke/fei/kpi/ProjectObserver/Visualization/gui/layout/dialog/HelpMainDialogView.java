package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXLabel;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonConstants;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;

public class HelpMainDialogView extends JDialog {

	public HelpMainDialogView() {
		setTitle(Messages.getMessage("dialog.help.title"));
		setResizable(false);
		setLocationByPlatform(true);
		setLayout(new MigLayout("insets 0,fill"));
		JPanel helpPanel = new JPanel(new MigLayout("insets 0,fill"));
		helpPanel.setBackground(Color.WHITE);

		JPanel left = new JPanel(new MigLayout("insets 5"));
		JPanel rightTop = new JPanel(new MigLayout("insets 5"));
		JPanel rightBottom = new JPanel(new MigLayout("insets 5"));
		left.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		rightTop.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		rightBottom.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		left.setOpaque(false);
		rightTop.setOpaque(false);
		rightBottom.setOpaque(false);

		ImageIcon iconPackageDiff = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "packagedif.gif"));
		ImageIcon iconClassDiff = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "classdif.gif"));
		ImageIcon iconInterfaceDiff = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "interfacedif.gif"));

		JXLabel packageexplan = new JXLabel(Messages.getMessage("info.packageexplanation"));
		packageexplan.setIcon(iconPackageDiff);
		packageexplan.setFont(CommonFonts.getAboutTextFont());
		packageexplan.setLineWrap(true);
		packageexplan.setForeground(Color.GRAY);

		JXLabel classexplan = new JXLabel(Messages.getMessage("info.classexplanation"));
		classexplan.setIcon(iconClassDiff);
		classexplan.setFont(CommonFonts.getAboutTextFont());
		classexplan.setLineWrap(true);
		classexplan.setForeground(Color.GRAY);

		JXLabel interfaceexplan = new JXLabel(Messages.getMessage("info.interfaceexplanation"));
		interfaceexplan.setIcon(iconInterfaceDiff);
		interfaceexplan.setFont(CommonFonts.getAboutTextFont());
		interfaceexplan.setLineWrap(true);
		interfaceexplan.setForeground(Color.GRAY);
		JXLabel mesterview = new JXLabel(Messages.getMessage("dialog.help.mainhelp.masterview"));
		mesterview.setFont(CommonFonts.getAboutTextFont());
		mesterview.setLineWrap(true);
		mesterview.setForeground(Color.GRAY);

		left.add(mesterview, "wrap,gaptop 10");
		left.add(packageexplan, "wrap,gaptop 10");
		left.add(classexplan, "wrap,gaptop 10");
		left.add(interfaceexplan, "wrap,gaptop 10");

		left.setMaximumSize(new Dimension(200, 400));

		// helpPanel.setMaximumSize(new Dimension(300, 700));
		helpPanel.setMinimumSize(new Dimension(700, 350));

		JLabel extra = new JLabel(Messages.getMessage("info.fieldmethod"));
		extra.setBackground(new Color(173, 255, 47));
		extra.setOpaque(true);
		
		JXLabel extrafield = new JXLabel(Messages.getMessage("info.extra"));
		extrafield.setForeground(Color.GRAY);
		extrafield.setFont(CommonFonts.getAboutTextFont());

		JXLabel detailview = new JXLabel(Messages.getMessage("dialog.help.mainhelp.detailview"));
		detailview.setFont(CommonFonts.getAboutTextFont());
		detailview.setLineWrap(true);
		detailview.setForeground(Color.GRAY);

		rightTop.add(detailview, "span,growx,wrap");
		rightTop.add(extra, "gapleft 1,growx");
		rightTop.add(extrafield, "growx,wrap");

		JLabel missing = new JLabel(Messages.getMessage("info.fieldmethod"));
		missing.setForeground(Color.RED);
		
		JXLabel missionfield = new JXLabel(Messages.getMessage("info.missing"));
		missionfield.setForeground(Color.GRAY);
		missionfield.setFont(CommonFonts.getAboutTextFont());
		JXLabel umlview = new JXLabel(Messages.getMessage("dialog.help.mainhelp.umlview"));
		umlview.setFont(CommonFonts.getAboutTextFont());
		umlview.setLineWrap(true);
		umlview.setForeground(Color.GRAY);

		rightBottom.add(umlview, "span,growx,wrap");
		rightBottom.add(missing, "gapleft 1,growx");
		rightBottom.add(missionfield, "growx,wrap");

		JPanel m = new JPanel(new MigLayout("insets 20"));
		m.setBackground(Color.white);
		m.add(helpPanel, "");

		helpPanel.add(left, "span 1 2,growy");
		helpPanel.add(rightTop, "wrap,grow");
		helpPanel.add(rightBottom, "grow");

		add(helpPanel, "grow");

		// setMinimumSize(new Dimension(300, 700));

		pack();

	}

}
