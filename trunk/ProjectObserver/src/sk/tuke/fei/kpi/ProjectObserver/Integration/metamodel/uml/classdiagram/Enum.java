package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

public class Enum extends Element {
	private static final long serialVersionUID = -7656030684090007523L;
	private String[] values;

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
