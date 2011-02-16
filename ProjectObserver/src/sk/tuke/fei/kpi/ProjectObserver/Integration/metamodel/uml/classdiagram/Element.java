package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.io.Serializable;
import java.util.List;

public abstract class Element implements Serializable {
	private static final long serialVersionUID = 2140263151359000290L;

	public enum Visibility {
		PRIVATE, DEFAULT, PROTECTED, PUBLIC;

		public static Visibility fromString(String name) {
			if (name.equalsIgnoreCase("private")) {
				return PRIVATE;
			} else if (name.equalsIgnoreCase("public")) {
				return PUBLIC;
			} else if (name.equalsIgnoreCase("protected")) {
				return PROTECTED;
			} else {
				return DEFAULT;
			}
		}
	};

	public enum Modifiers {
		FINAL, ABSTRACT, STATIC;
	};

	String name;
	String xmiId;
	Visibility visibility = Visibility.DEFAULT;
	List<Modifiers> modifiers;
	Element parent;

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

	public List<Modifiers> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}

	public void setXmiId(String xmiId) {
		this.xmiId = xmiId;
	}

	public String getXmiId() {
		return xmiId;
	}

	@Override
	public String toString() {
		return name + "," + visibility + modifiers;
	}

	public void setParent(Element parent) {
		this.parent = parent;
	}

	public Element getParent() {
		return parent;
	}
}
