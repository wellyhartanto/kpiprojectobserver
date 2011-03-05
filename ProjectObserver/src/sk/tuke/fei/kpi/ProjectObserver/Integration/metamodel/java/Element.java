package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Element implements Serializable {
	private static final long serialVersionUID = -6146256352210642770L;

	public enum Visibility {
		PRIVATE, DEFAULT, PROTECTED, PUBLIC;

		public static Visibility fromString(String string) {
			if ("public".equals(string)) {
				return PUBLIC;
			} else if ("protected".equals(string)) {
				return PROTECTED;
			} else if ("private".equals(string)) {
				return PRIVATE;
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
		FINAL, ABSTRACT, STATIC, NATIVE, TRANSIENT, VOLATILE, SYNCHRONIZED, STRICTFP, NONE;

		public static Modifiers fromString(String value) {
			if ("final".equals(value)) {
				return FINAL;
			} else if ("static".equals(value)) {
				return STATIC;
			} else if ("abstract".equals(value)) {
				return ABSTRACT;
			} else if ("native".equals(value)) {
				return NATIVE;
			} else if ("volatile".equals(value)) {
				return VOLATILE;
			} else if ("synchronized".equals(value)) {
				return SYNCHRONIZED;
			} else if ("strictfp".equals(value)) {
				return STRICTFP;
			} else {
				return NONE;
			}
		}
	};

	private String name;
	private String fullName;
	private Visibility visibility = Visibility.DEFAULT;
	private List<Modifiers> modifiers;
	private List<Annotation> anotations;
	private Element parent;

	public Element() {
		modifiers = new ArrayList<Modifiers>();
		anotations = new ArrayList<Annotation>();
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

	public List<Annotation> getAnotations() {
		return anotations;
	}

	public void setAnotations(List<Annotation> anotations) {
		this.anotations = anotations;
	}

	public Element getParent() {
		return parent;
	}

	public void setParent(Element parent) {
		this.parent = parent;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getFullyQualifiedName() {
		return parent == null ? name : parent.getFullyQualifiedName() + "." + name;
	}
}
