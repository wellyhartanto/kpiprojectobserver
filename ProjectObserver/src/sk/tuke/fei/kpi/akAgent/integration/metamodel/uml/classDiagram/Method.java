package sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram;

import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.akAgent.integration.alignment.Alignable;
import sk.tuke.fei.kpi.akAgent.integration.alignment.Aligner.AlignStrategy;

/**
 * Method of {@link Class} or {@link Interface}. 
 */
public class Method extends Element implements Alignable {
	private static final long serialVersionUID = 7860060186187411059L;
	private String returnType;
	private List<Param> params;

	/**
	 * Constructor
	 */
	public Method() {
		super();
		params = new ArrayList<Param>();
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

	/**
	 * Gets {@link Param}s list.
	 * @return list of params.
	 */
	public List<Param> getParams() {
		return params;
	}

	/**
	 * Sets {@link Param}s list
	 * @param params list of param.
	 */
	public void setParams(List<Param> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getVisibility()+" " +returnType+" "+getName()+getParams().toString();
	}

	@Override
	public String getFullyQualifiedName() {
		return parent == null ? name : parent.getFullyQualifiedName() + "." + name;
	}
	
	@Override
	public boolean matches(Object object, AlignStrategy alignStrategy) {
		if (object == null) {
			return false;
		}

		if (object instanceof sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method) {
			sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method method = (sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method) object;
			switch (alignStrategy) {
			default:		
				boolean value = getVisibility().toString().equals(method.getVisibility().toString()) && method.getName().endsWith(getName()) && method.getReturnType().endsWith(returnType) && getParams().size() == method.getParams().size();
				if(value && params.size()!=0){
					return testParams(method);
				} else {
					return value;
				}
			}
		}
		return false;
	}

	private boolean testParams(sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Method method) {
		for(int i =0; i<getParams().size();i++){
			if(!method.getParams().get(i).getType().endsWith(getParams().get(i).getType())){
				return false;
			}
		}
		return true;
	}
}
