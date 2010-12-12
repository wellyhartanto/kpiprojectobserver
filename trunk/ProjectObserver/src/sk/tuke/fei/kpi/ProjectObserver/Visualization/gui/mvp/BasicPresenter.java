package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.mvp;


public abstract class BasicPresenter<D extends Display> implements Presenter<D> {

	/**
	 * Associated display
	 */
	protected D display;

	@Override
	public void bind() {
		onBind();
	}

	@Override
	public D getDisplay() {
		return display;
	}

	/**
	 * Called where is binding performed.
	 */
	protected void onBind() {
	}

	/**
	 * Called where is binding performed.
	 */
	protected void onUnbind() {
	}


	/**
	 * Sets the display.
	 * 
	 * @param display
	 *            the new display
	 */
	public void setDisplay(D display) {
		this.display = display;
	}

	@Override
	public void unbind() {
		onUnbind();
	}
}
