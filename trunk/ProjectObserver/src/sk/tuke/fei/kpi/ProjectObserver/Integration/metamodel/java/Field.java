package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;


public class Field extends Element {
	private static final long serialVersionUID = 105827013816997623L;
	private String type;

	public Field() {
		super();
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
