package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;

public class Method extends BehavioralElement {
	private static final long serialVersionUID = 2015355208730487925L;
	private String returnType;

	public Method() {
		super();
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	@Override
	public boolean matches(Object object, AlignStrategy alignStrategy) {
		if (object == null) {
			return false;
		}

		if (object instanceof sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method) {
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method method = (sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method) object;
			switch (alignStrategy) {
			default:
				boolean value = getVisibility().toString().equalsIgnoreCase(method.getVisibility().toString()) && getName().equals(method.getName()) && returnType.endsWith(method.getReturnType())
						&& getParams().size() == method.getParams().size();
				if (value && getParams().size() != 0) {
					return testParams(method);
				} else {
					return value;
				}
			}
		}
		return false;
	}

	private boolean testParams(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method method) {
		for (int i = 0; i < getParams().size(); i++) {
			if (!getParams().get(i).getType().endsWith(method.getParams().get(i).getType())) {
				return false;
			}
		}
		return true;
	}
}
