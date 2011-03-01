package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import java.util.ArrayList;
import java.util.List;

public class TypeElement extends Element {
	private static final long serialVersionUID = 7982624404245180466L;
	private List<Method> methods = new ArrayList<Method>();
	private List<Field> fields = new ArrayList<Field>();
	private List<Enum> enums = new ArrayList<Enum>();
	private List<Class> classes = new ArrayList<Class>();
	private List<TypeElement> implemented = new ArrayList<TypeElement>();
	private List<Constructor> constructors = new ArrayList<Constructor>();
	private transient List<String> implementedNames = new ArrayList<String>();
	private TypeElement superClass;
	private transient String superClassName;
	
	private boolean external;
	
	public TypeElement() {
		super();
	}
	
	public List<Field> getFields() {
		return fields;
	}
	
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	
	public List<Class> getClasses() {
		return classes;
	}
	
	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}
	
	public List<Enum> getEnums() {
		return enums;
	}
	
	public void setEnums(List<Enum> enums) {
		this.enums = enums;
	}
	
	public boolean isExternal() {
		return external;
	}
	
	public void setExternal(boolean external) {
		this.external = external;
	}
	
	public TypeElement getSuperClass() {
		return superClass;
	}
	
	public void setSuperClass(TypeElement superClass) {
		this.superClass = superClass;
	}
	
	public List<TypeElement> getImplemented() {
		return implemented;
	}
	
	public void setImplemented(List<TypeElement> implemented) {
		this.implemented = implemented;
	}
	
	public String getSuperClassName() {
		return superClassName;
	}
	
	public void setSuperClassName(String superClassName) {
		this.superClassName = superClassName;
	}
	
	public List<String> getImplementedNames() {
		return implementedNames;
	}
	
	public void setImplementedNames(List<String> implementedNames) {
		this.implementedNames = implementedNames;
	}
	
	public List<Constructor> getConstructors() {
		return constructors;
	}
	
	public void setConstructors(List<Constructor> constructors) {
		this.constructors = constructors;
	}
}
