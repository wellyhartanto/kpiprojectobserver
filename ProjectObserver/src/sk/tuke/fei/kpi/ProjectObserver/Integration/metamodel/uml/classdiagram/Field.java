package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

public class Field extends Element {
	private static final long serialVersionUID = -8910247458912757575L;
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
