package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

public abstract class Element {
	public enum Visibility {
		PRIVATE, DEFAULT, PROTECTED, PUBLIC
	};

	public enum Modifiers {
		FINAL, ABSTRACT, STATIC, NATIVE, TRANSIENT, VOLATILE, SYNCHRONIZED, STRICTFP
	};

	private String name;
	private Visibility visibility = Visibility.DEFAULT;
	private Modifiers[] modifiers;
	private Annotation[] anotations;

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

	public Annotation[] getAnotations() {
		return anotations;
	}

	public void setAnotations(Annotation[] anotations) {
		this.anotations = anotations;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}

}
