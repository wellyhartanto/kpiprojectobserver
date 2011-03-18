package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Alignable;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;

public class Method extends Element implements Alignable {
	private static final long serialVersionUID = 7860060186187411059L;
	private String returnType;
	private String[] exceptions;
	private List<Param> params;

	public Method() {
		super();
		params = new ArrayList<Param>();
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String[] getExceptions() {
		return exceptions;
	}

	public void setExceptions(String[] exceptions) {
		this.exceptions = exceptions;
	}

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return parent + "." + name;
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

		if (object instanceof sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method) {
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method method = (sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method) object;
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

	private boolean testParams(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method method) {
		for(int i =0; i<getParams().size();i++){
			if(!method.getParams().get(i).getType().endsWith(getParams().get(i).getType())){
				return false;
			}
		}
		return true;
	}
}
