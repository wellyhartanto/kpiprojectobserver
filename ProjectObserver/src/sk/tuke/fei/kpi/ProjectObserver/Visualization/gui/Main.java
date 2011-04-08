package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui;

import java.net.URL;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.LoginPanelPresenter;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

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
				
				UIManager.setLookAndFeel( new com.nilo.plaf.nimrod.NimRODLookAndFeel());
				
				
				NimRODTheme nt = new NimRODTheme( new URL( "http://personales.ya.com/nimrod/data/DarkGrey.theme"));

				NimRODLookAndFeel nf = new NimRODLookAndFeel();
				nf.setCurrentTheme( nt);
				UIManager.setLookAndFeel( nf);
				
				
				
				
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
