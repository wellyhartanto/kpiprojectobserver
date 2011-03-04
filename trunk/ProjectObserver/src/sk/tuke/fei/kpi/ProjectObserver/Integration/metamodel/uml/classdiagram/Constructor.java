package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Constructor extends Element implements Serializable {
	private static final long serialVersionUID = 5391577937504578761L;
	private List<Param> params;
	
	public Constructor() {
		params = new ArrayList<Param>();
	}	

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}
	
	@Override
	public String toString() {
		return visibility + " " + name + "(" + params + ")";
	}
}
