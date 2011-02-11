package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model;

import java.io.Serializable;
import java.util.Date;

import sk.tuke.fei.kpi.ProjectObserver.Integration.Model;

public class Project implements Serializable{

	private static final long serialVersionUID = -2326937410048804108L;

	private String name;
	private String description;
	// model of the project source and uml;
//	private Model model;

	private Date creationDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Model getModel() {
//		return model;
//	}
//
//	public void setModel(Model model) {
//		this.model = model;
//	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
