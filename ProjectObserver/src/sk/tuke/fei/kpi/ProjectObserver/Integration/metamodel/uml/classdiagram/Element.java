package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.io.Serializable;

public abstract class Element implements Serializable {
	private static final long serialVersionUID = 2140263151359000290L;

	public enum Visibility {
		PRIVATE, DEFAULT, PROTECTED, PUBLIC
	};

	public enum Modifiers {
		FINAL, ABSTRACT, STATIC
	};

	private String name;
	private Visibility visibility = Visibility.DEFAULT;
	private Modifiers[] modifiers;

	public Element() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public Modifiers[] getModifiers() {
		return modifiers;
	}

	public void setModifiers(Modifiers[] modifiers) {
		this.modifiers = modifiers;
	}
}
