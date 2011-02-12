package sk.tuke.fei.kpi.ProjectObserver.Integration;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
import sk.tuke.fei.kpi.ProjectObserver.utils.Disposable;

/**
 * Citaj komentar v triede project service Po precitani tento komentar mozes zmazat :)
 * 
 * @author Maroš Tyrpák
 * 
 */
public class Project implements Serializable, Disposable {
	private static final long serialVersionUID = -8116395605534346529L;

	private File umlFile;
	private File javaFile;

	private ClassDiagram classDiagram;

	private Application javaModel;

	private Date creationDate;
	private String name;
	private String description;

	private Project() {
		creationDate = new Date();
	}

	public Project(String umlFile, String javaFile) {
		this(new File(umlFile), new File(javaFile));
	}

	public Project(File umlFile, File javaFile) {
		this.umlFile = umlFile;
		this.javaFile = javaFile;
		creationDate = new Date();
	}

	public boolean createModel() throws AlignmentException {
		// TODO
		return false;
	}

	public void save(String pathname) {
		save(new File(pathname));
	}

	public void save(File file) {
		// TODO
	}

	public static Project load(String pathname) {
		return load(new File(pathname));
	}

	public static Project load(File file) {
		// TODO
		return new Project();
	}

	public ClassDiagram getClassDiagram() {
		return classDiagram;
	}

	public Application getJavaModel() {
		return javaModel;
	}

	public Date getCreationDate() {
		return creationDate;
	}

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

	@Override
	public void dispose() {
		classDiagram = null;
		javaModel = null;
		umlFile = null;
		javaFile = null;
	}
}
