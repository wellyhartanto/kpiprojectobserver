package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.JXLabel;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonConstants;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonFonts;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.ComponentsBuilder;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;

public class AboutDialogView extends JDialog {
	private ImageIcon iconLogo;

	private JXHyperlink copyrightHyperlink;
	
	
	public AboutDialogView() {
		setTitle(Messages.getMessage("dialog.about.title"));
		setResizable(false);
		setLocationByPlatform(true);
		
		iconLogo = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "logo.png"));
		JLabel logoLabel = new JLabel(iconLogo);
		logoLabel.setOpaque(true);
		logoLabel.setBackground(Color.WHITE);
		
		JPanel aboutPanel = new JPanel(new MigLayout());
		aboutPanel.setOpaque(true);
		aboutPanel.setBackground(Color.WHITE);
		aboutPanel.setMaximumSize(new Dimension(240, 700));

		copyrightHyperlink = ComponentsBuilder.createLoginAboutHyperlink(Messages.getMessage("loginpanel.copyright"));
		copyrightHyperlink.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String url = "http://kpi.fei.tuke.sk";
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel title = new JLabel("Project Observer");
		title.setFont(CommonFonts.getInfoLabelFont());
		title.setForeground(Color.DARK_GRAY);
		
		JXLabel text = new JXLabel(Messages.getMessage("dialog.about.text"));
		text.setLineWrap(true);
		text.setFont(CommonFonts.getAboutTextFont());
		text.setForeground(Color.GRAY);
		
		JXLabel grant = new JXLabel(Messages.getMessage("dialog.about.grant"));
		grant.setLineWrap(true);
		grant.setFont(CommonFonts.getToolTipFont());
		grant.setForeground(Color.GRAY);

		
		aboutPanel.add(title,"growx,gaptop 20,wrap,gapright 20");
		aboutPanel.add(text, "gaptop 7,gapright 20,wrap");
		aboutPanel.add(grant,"gaptop 4,gapright 20,wrap");
		aboutPanel.add(copyrightHyperlink,"gaptop 7,gapright 20,wrap");
		
		
		setLayout(new MigLayout("fill,insets 0","[]0[]",""));
		add(logoLabel,"grow");
		add(aboutPanel,"grow");
		pack();
	}
}
