package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Constructor of {@link Class}.
 */
public class Constructor extends Element implements Serializable {
	private static final long serialVersionUID = 5391577937504578761L;
	private List<Param> params;
	
	/**
	 * Constructor.
	 */
	public Constructor() {
		params = new ArrayList<Param>();
	}	

	/**
	 * Gets parameters of constructor.
	 * @return List of parameters.
	 */
	public List<Param> getParams() {
		return params;
	}

	/**
	 * Sets parameters of constructor.
	 * @param params list to set.
	 */
	public void setParams(List<Param> params) {
		this.params = params;
	}
	
	@Override
	public String toString() {
		return visibility + " " + name + "(" + params + ")";
	}
}
