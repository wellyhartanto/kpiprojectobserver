package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui;

import java.awt.Color;
import java.net.URL;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.LoginPanelPresenter;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;

public class Main {

	private static final long serialVersionUID = -1960464005712732926L;

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

			System.out.println(info.getName());

			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				
				System.out.println(UIManager.get("nimbusBase"));
				System.out.println(UIManager.get("nimbusBlueGrey"));
				System.out.println(UIManager.get("control"));
				

				
//				UIManager.put("nimbusBase", new Color(159,203,212));
//				UIManager.put("control", new Color(226,236,249));
//				UIManager.put("nimbusBlueGrey", new Color(179,223,242));
//				
//				UIManager.put("nimbusBase", new Color(159,203,242));
//				UIManager.put("control", new Color(226,236,249));
//				UIManager.put("info", new Color(218,218,158));
//				UIManager.put("nimbusAlertYellow", new Color(255,220,35));
//				UIManager.put("nimbusDisabledText", new Color(142,143,145));
//				UIManager.put("nimbusFocus", new Color(182,208,232));
//				UIManager.put("nimbusGreen", new Color(213,215,101));
//				UIManager.put("nimbusInfoBlue", new Color(112,145,213));
//				UIManager.put("nimbusLightBackground", new Color(255,255,255));
//				UIManager.put("nimbusOrange", new Color(191,98,4));
//				UIManager.put("nimbusRed", new Color(169,46,34));
//				UIManager.put("nimbusSelectedText", new Color(255,255,255));
//				UIManager.put("nimbusSelectionBackground", new Color(93,159,226));
//				UIManager.put("text", new Color(0,0,0));
//
//				
				
				
//				UIManager.setLookAndFeel(new com.jgoodies.looks.plastic.Plastic3DLookAndFeel());
//				UIManager.setLookAndFeel( new com.nilo.plaf.nimrod.NimRODLookAndFeel());
//				NimRODTheme nt = new NimRODTheme( new URL( "http://personales.ya.com/nimrod/data/Snow.theme"));
//				NimRODLookAndFeel nf = new NimRODLookAndFeel();
//				nf.setCurrentTheme( nt);
//				UIManager.setLookAndFeel( nf);
//				UIManager.put("nimbusBase", new Color(...));
//				UIManager.put("nimbusBlueGrey", new Color(...));
//				UIManager.put("control", new Color(...));
				
				break;
			}
		}

//		UIManager
//				.setLookAndFeel(new de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel());

		// new MainFrame();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame.getMainFrame().setPanel(
						new LoginPanelPresenter().getDisplay().asComponent());
			}
		});

		// Application.launch(Main.class, args);
	}

	/*
	 * @Override protected void startup() { // TODO Auto-generated method stub
	 * 
	 * MainFrame mf = new MainFrame();
	 * 
	 * setMainFrame(mf);
	 * 
	 * 
	 * // show(mf); }
	 * 
	 * @Override public void exit(EventObject arg0) { // TODO Auto-generated
	 * method stub super.exit(arg0); }
	 */

}
