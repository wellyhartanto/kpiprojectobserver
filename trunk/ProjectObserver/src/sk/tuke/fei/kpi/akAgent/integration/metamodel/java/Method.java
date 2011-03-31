package sk.tuke.fei.kpi.akAgent.integration.metamodel.java;

import sk.tuke.fei.kpi.akAgent.integration.alignment.Aligner.AlignStrategy;

/**
 * Method of {@link Class} or {@link Interface}. 
 */
public class Method extends BehavioralElement {
	private static final long serialVersionUID = 2015355208730487925L;
	private String returnType;

	/**
	 * Constructor
	 */
	public Method() {
		super();
	}

	/**
	 * Gets method's return type.
	 * @return name of data type.
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * Sets method's return type.
	 * @param returnType name of data type
	 */
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	@Override
	public boolean matches(Object object, AlignStrategy alignStrategy) {
		if (object == null) {
			return false;
		}

		if (object instanceof sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Method) {
			sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Method method = (sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Method) object;
			switch (alignStrategy) {
			default:
				boolean value = /*getVisibility().toString().equalsIgnoreCase(method.getVisibility().toString()) &&*/ getName().equals(method.getName()) && returnType.endsWith(method.getReturnType())
						&& getParams().size() == method.getParams().size();
				if (value && !getParams().isEmpty()) {
					return testParams(method);
				} else {
					return value;
				}
			}
		}
		return false;
	}

	private boolean testParams(sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Method method) {
		for (int i = 0; i < getParams().size(); i++) {
			if (!getParams().get(i).getType().endsWith(method.getParams().get(i).getType())) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getVisibility()+" " +returnType+" "+getName()+getParams().toString();
	}
}
