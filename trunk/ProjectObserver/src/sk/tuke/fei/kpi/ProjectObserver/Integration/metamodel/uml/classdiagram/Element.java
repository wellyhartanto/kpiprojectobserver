package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.io.Serializable;
import java.util.List;

public abstract class Element implements Serializable {
	private static final long serialVersionUID = 2140263151359000290L;

	public enum Visibility {
		PRIVATE, DEFAULT, PROTECTED, PUBLIC;

		public static Visibility fromString(String value) {
			if ("private".equalsIgnoreCase(value)) {
				return PRIVATE;
			} else if ("public".equalsIgnoreCase(value)) {
				return PUBLIC;
			} else if ("protected".equalsIgnoreCase(value)) {
				return PROTECTED;
			} else {
				return DEFAULT;
			}
		}
		
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	};

	public enum Modifiers {
		FINAL, ABSTRACT, STATIC, NONE;

		public static Modifiers fromString(String value) {
			if ("".equals(value)) {
				return FINAL;
			} else if ("".equals(value)) {
				return STATIC;
			} else if ("".equals(value)) {
				return ABSTRACT;
			} else {
				return NONE;
			}
		}
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
		return name;
	}

	public void setParent(Element parent) {
		this.parent = parent;
	}

	public Element getParent() {
		return parent;
	}

	public String getFullyQualifiedName() {
		return parent == null?name : parent.getFullyQualifiedName()+"."+name;
	}
}
