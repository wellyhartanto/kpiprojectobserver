package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import java.util.ArrayList;
import java.util.List;

public class Interface extends Element {
    private List<Method> methods = new ArrayList<Method>();
    private List<Field> fields = new ArrayList<Field>();

    public Interface() {
	// TODO Auto-generated constructor stub
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
}
