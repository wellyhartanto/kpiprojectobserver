package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.io.Serializable;

public class Param implements Serializable {
	private static final long serialVersionUID = -6610602685289634372L;
	private String name;
	private String type;

	public Param() {
	}

	public Param(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
