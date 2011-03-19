package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.difference;

import java.io.Serializable;
import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method;

/**
 * Holds differences between class/interface from {@link Application}  and modeled class/interface from {@link ClassDiagram}.
 * Supported differences are missing methods and extra methods.
 * Missing methods are these that are in class model but they are not implemented.
 * Extra method are these that are implemented but wasn't modeled in class diagram.
 */
public class Difference implements Serializable {
	private static final long serialVersionUID = -632343355526225153L;
	
	private ArrayList<sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method> missingMethods;
	private ArrayList<sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method> extraMethods;

	/**
	 * Constructor.
	 * Creates difference object by comparing passed java and uml element.
	 * @param java
	 * @param uml
	 */
	public Difference(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.TypeElement java, sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.TypeElement uml) {
		missingMethods = new ArrayList<sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method>();
		extraMethods = new ArrayList<sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method>();
		for (sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method method : java.getMethods()) {
			if (!findMethod(method, uml)) {
				extraMethods.add(method);
			}
		}
		for (sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method method : uml.getMethods()) {
			if (!findMethod(method, java)) {
				missingMethods.add(method);
			}
		}
	}

	private boolean findMethod(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method method, sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.TypeElement uml) {
		for (Method m : uml.getMethods()) {
			if (method.matches(m, AlignStrategy.EXACT)) {
				return true;
			}
		}
		return false;
	}

	private boolean findMethod(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method method, sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.TypeElement java) {
		for (sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method m : java.getMethods()) {
			if (method.matches(m, AlignStrategy.EXACT)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * States whether elements passed to constructors are different.
	 * @return true if elements don't match
	 */
	public boolean differs(){
		return hasExtraMethods() && hasMissingMethods();
	}
	
	/**
	 * States whether implemented element has some missing methods.
	 * @return true if elements doesn't implement every method from its model.
	 */
	public boolean hasMissingMethods(){
		return missingMethods.size()!=0;
	}
	
	/**
	 * States whether implemented element has some extra methods.
	 * @return true if elements implements some methods that are not in model.
	 */
	public boolean hasExtraMethods(){
		return extraMethods.size()!=0;
	}
	
	@Override
	public String toString() {
		return missingMethods.toString() + extraMethods.toString();
	}
}
