package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import java.util.List;

public class Class extends Element {
	private List<Method> methods;
	private List<Field> fields;
	private List<Enum> enums;
	private List<Class> classes;
	private Class superClass;

	public Class() {
		super();
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public void setEnums(List<Enum> enums) {
		this.enums = enums;
	}

	public List<Enum> getEnums() {
		return enums;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	public List<Class> getClasses() {
		return classes;
	}

	public void setSuperClass(Class superClass) {
		this.superClass = superClass;
	}

	public Class getSuperClass() {
		return superClass;
	}

}
