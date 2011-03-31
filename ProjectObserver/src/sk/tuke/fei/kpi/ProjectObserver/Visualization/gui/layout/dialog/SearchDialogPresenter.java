package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;
import sk.tuke.fei.kpi.akAgent.integration.Project;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.TypeElement;
 
public class SearchDialogPresenter extends BasicPresenter<SearchDialogDisplay> {

	public SearchDialogPresenter(Project project) {

		display = new SearchDialogView(project);

		bind();
	}

	@Override
	protected void onBind() {
		super.onBind();

		display.setEntryKeyAction(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					display.getList().requestFocus();
				}
			}
		});

		display.setListSelectionAction(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				TypeElement element = (TypeElement) display.getList().getSelectedValue();
				if (element != null) {
					display.setStatus(element.getFullName());
				}

			}
		});

		display.setCancelAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				display.showDialog(false);
			}
		});
	}

}
