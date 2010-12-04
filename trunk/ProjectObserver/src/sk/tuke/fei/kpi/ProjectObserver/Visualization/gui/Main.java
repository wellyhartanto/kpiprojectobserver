package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui;

import javax.swing.UIManager;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.MainFrame;

public class Main {

    private static final long serialVersionUID = -1960464005712732926L;

    public static void main(String[] args) {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {

	    e.printStackTrace();
	}

	new MainFrame();

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
