package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

public class Field extends Element {
	private static final long serialVersionUID = -8910247458912757575L;
	private String type;
	private String defaultValue;
	private boolean isArray;

	public boolean isArray() {
		return isArray;
	}

	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	private Integer size;

	public Field() {
		super();
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return visibility + " " + modifiers + " " + type + " " + name + " = " + defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
}
