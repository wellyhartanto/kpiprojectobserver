package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;


public class Enum extends Element {
	private static final long serialVersionUID = 1184099714623574598L;
	private String[] values = {};

	public Enum() {
		super();
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	public String[] getValues() {
		return values;
	}
}
