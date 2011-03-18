package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.difference;

import java.util.ArrayList;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method;

public class ClassDifference {

	private ArrayList<sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method> missingMethods;
	private ArrayList<sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method> extraMethods;

	public ClassDifference(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.TypeElement java, sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.TypeElement uml) {
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
	
	public boolean differs(){
		return hasExtraMethods() && hasMissingMethods();
	}
	
	public boolean hasMissingMethods(){
		return missingMethods.size()!=0;
	}
	
	public boolean hasExtraMethods(){
		return extraMethods.size()!=0;
	}
	
	@Override
	public String toString() {
		return missingMethods.toString() + extraMethods.toString();
	}
}
