package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.LoginPanelPresenter;

public class Main {

	private static final long serialVersionUID = -1960464005712732926L;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		// new MainFrame();

	

		MainFrame.getMainFrame().setPanel(new LoginPanelPresenter().getDisplay().asComponent());

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
