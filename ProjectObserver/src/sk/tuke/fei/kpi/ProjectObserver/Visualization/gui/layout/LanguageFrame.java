package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.CommonConstants;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.MyFonts;

public class LanguageFrame extends JFrame {

	private JPanel languages;
	private final JRadioButton sklanguage;
	private final JRadioButton enlanguage;

	public LanguageFrame() {

		ImageIcon kpilogo = new ImageIcon(getClass().getResource(
				CommonConstants.IMAGES_FOLDER_PATH + "icon.png"));

		setIconImage(kpilogo.getImage());

		setName("myFirstframe");
		setBounds(500, 200, 120, 120);
		// setLocale(new Locale("en"));
		Locale.setDefault(new Locale("sk"));

		languages = new JPanel(new MigLayout());
		sklanguage = new JRadioButton("Slovenský");
		sklanguage.setSelected(true);
		enlanguage = new JRadioButton("English");
		ButtonGroup lang = new ButtonGroup();
		lang.add(sklanguage);
		lang.add(enlanguage);
		JLabel languageLbl = new JLabel("Jazyk / Language");
		languageLbl.setFont(MyFonts.font4);
		languages.add(languageLbl, "wrap");
		languages.add(sklanguage, "wrap");
		languages.add(enlanguage, "wrap");
		JButton okBttn = new JButton("OK");
		okBttn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (enlanguage.isSelected()) {
					Locale.setDefault(new Locale("en"));
				}
			}
		});
		languages.add(okBttn, "center,wrap");

		add(languages);

		pack();
		setVisible(true);

	}

}
