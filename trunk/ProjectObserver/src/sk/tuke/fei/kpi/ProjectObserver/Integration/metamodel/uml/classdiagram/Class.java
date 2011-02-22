package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.util.ArrayList;
import java.util.List;

public class Class extends TypeElement {
	private static final long serialVersionUID = 5698993126415179350L;
	private List<Constructor> constructors;
	private Class superClass;
	
	public Class() {
		super();
		constructors = new ArrayList<Constructor>();
	}

	public List<Constructor> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<Constructor> constructors) {
		this.constructors = constructors;
	}

	public void setSuperClass(Class superClass) {
		this.superClass = superClass;
	}

	public Class getSuperClass() {
		return superClass;
	}	
}
