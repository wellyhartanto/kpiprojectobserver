package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.util.List;

public class Class extends AssociationElement {
	private static final long serialVersionUID = 5698993126415179350L;
	private List<Method> methods;
	private List<Field> fields;

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
}
