package sk.tuke.fei.kpi.ProjectObserver.Integration;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.ParserException;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.ClassDiagramParser;
import sk.tuke.fei.kpi.ProjectObserver.utils.Disposable;

public class Project implements Serializable, Disposable {
	private static final long serialVersionUID = -8116395605534346529L;
	private Logger logger = Logger.getLogger(Project.class);
	private File umlFile;
	private File javaFile;

	private ClassDiagram classDiagram;

	private Application javaModel;

	private Date creationDate;
	private String name;
	private String description;

	private Project() {
		creationDate = new Date();
		logger.info("info");
		logger.debug("debug");
		logger.warn("warn");
	}

	public Project(String umlFile, String javaFile) {
		this(new File(umlFile), new File(javaFile));
	}

	public Project(File umlFile, File javaFile) {
		this();
		this.umlFile = umlFile;
		this.javaFile = javaFile;
	}

	public boolean createModel() throws AlignmentException, ParserException {
		classDiagram = new ClassDiagramParser().parse(umlFile);
		// System.out.println(classDiagram);
		// javaModel = new JavaParser().parse(javaFile);
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

	public static void main(String[] args) {
		URL configFileResource = Project.class.getClassLoader().getResource("sk/tuke/fei/kpi/ProjectObserver/log4j.xml");
		DOMConfigurator.configure(configFileResource.getFile());
		Project project = new Project("exp1.1.xml", "full.owl");
		try {
			project.createModel();
		} catch (AlignmentException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
}
