package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element.Visibility;

public class Constructor implements Serializable {
	private static final long serialVersionUID = 5391577937504578761L;
	private String name;
	private List<Param> params;
	private Visibility visibility;

	public Constructor() {
		params = new ArrayList<Param>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public Visibility getVisibility() {
		return visibility;
	};
}
