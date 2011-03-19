package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.util.ArrayList;
import java.util.List;

/**
 * Class from class diagram
 */
public class Class extends TypeElement {
	private static final long serialVersionUID = 5698993126415179350L;
	private List<Constructor> constructors;
	private Class superClass;
	
	/**
	 * Constructor.
	 */
	public Class() {
		super();
		constructors = new ArrayList<Constructor>();
	}

	/**
	 * Gets list of constructors.
	 * @return constructors
	 */
	public List<Constructor> getConstructors() {
		return constructors;
	}
	/**
	 * Sets list of constructors.
	 * @param constructors list to set
	 */
	public void setConstructors(List<Constructor> constructors) {
		this.constructors = constructors;
	}
	/**
	 * Sets super class
	 * @param superClass super class object
	 */
	public void setSuperClass(Class superClass) {
		this.superClass = superClass;
	}
	/**
	 * Gets super class.
	 * @return super class object
	 */
	public Class getSuperClass() {
		return superClass;
	}	
}
