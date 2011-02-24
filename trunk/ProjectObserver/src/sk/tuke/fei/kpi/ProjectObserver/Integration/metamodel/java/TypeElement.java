package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import java.util.ArrayList;
import java.util.List;

public class TypeElement extends Element {
	private static final long serialVersionUID = 7982624404245180466L;
	private List<Method> methods = new ArrayList<Method>();
	private List<Field> fields = new ArrayList<Field>();
	private List<Enum> enums = new ArrayList<Enum>();
	private List<Class> classes = new ArrayList<Class>();
	private boolean external;
	private String fullName;
	
	public TypeElement() {
		super();
		methods = new ArrayList<Method>();
		fields = new ArrayList<Field>();
		enums = new ArrayList<Enum>();
		classes = new ArrayList<Class>();
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
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
