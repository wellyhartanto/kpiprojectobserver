package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.dialog;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sk.tuke.fei.kpi.ProjectObserver.Integration.Project;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.TypeElement;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp.BasicPresenter;

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
	}

}
