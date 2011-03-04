package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.util.ArrayList;
import java.util.List;

public abstract class TypeElement extends Element {
	private static final long serialVersionUID = -4697238786269383432L;
	List<Method> methods;
	List<Field> fields;
	private List<TypeElement> superClasses;
	private List<String> superclassesIds;
	private List<Association> associations;
	private List<TypeElement> innerClasses;
	private boolean enumClass;

	public TypeElement() {
		super();
		methods = new ArrayList<Method>();
		fields = new ArrayList<Field>();
		superClasses = new ArrayList<TypeElement>();
		superclassesIds = new ArrayList<String>();
		associations = new ArrayList<Association>();
		innerClasses = new ArrayList<TypeElement>();
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

	public void setSuperClasses(List<TypeElement> superClasses) {
		this.superClasses = superClasses;
	}

	public List<TypeElement> getSuperClasses() {
		return superClasses;
	}

	public List<String> getSuperclassesIds() {
		return superclassesIds;
	}

	public void setSuperclassesIds(List<String> superclassesIds) {
		this.superclassesIds = superclassesIds;
	}

	@Override
	public String toString() {
		return parent == null ? name : parent + "." + name;
	}

	public void setAssociations(List<Association> associations) {
		this.associations = associations;
	}

	public List<Association> getAssociations() {
		return associations;
	}

	public boolean isEnumClass() {
		return enumClass;
	}

	public void setEnumClass(boolean enumClass) {
		this.enumClass = enumClass;
	}

	public List<TypeElement> getInnerClasses() {
		return innerClasses;
	}

	public void setInnerClasses(List<TypeElement> innerClasses) {
		this.innerClasses = innerClasses;
	}

	@Override
	public String getFullyQualifiedName() {
		return parent == null ? name : parent.getFullyQualifiedName() + "." + name;
	}
}
