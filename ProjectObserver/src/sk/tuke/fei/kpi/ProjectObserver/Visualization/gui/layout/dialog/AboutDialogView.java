package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXHyperlink;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonConstants;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.ComponentsBuilder;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;

import net.miginfocom.swing.MigLayout;

public class AboutDialogView extends JDialog {
	private ImageIcon iconLogo;

	private JXHyperlink copyrightHyperlink;
	
	
	public AboutDialogView() {
		setTitle("About");
		setResizable(false);
		setLocationByPlatform(true);
		
		iconLogo = new ImageIcon(getClass().getResource(CommonConstants.IMAGES_FOLDER_PATH + "logo.png"));
		JLabel logoLabel = new JLabel(iconLogo);
		logoLabel.setOpaque(true);
		logoLabel.setBackground(Color.WHITE);
		
		JPanel aboutPanel = new JPanel(new MigLayout());
		aboutPanel.setOpaque(true);
		aboutPanel.setBackground(Color.WHITE);

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
		
		
		
		aboutPanel.add(copyrightHyperlink,"wrap");
		
		
		setLayout(new MigLayout("fill,insets 0","[]0[]",""));
		add(logoLabel,"grow");
		add(aboutPanel,"grow");
		pack();
	}
}
