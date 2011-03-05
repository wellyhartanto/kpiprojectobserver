package sk.tuke.fei.kpi.ProjectObserver.Integration;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.MappingHolder;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.PrimaryModel;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.ParserException;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.ClassDiagramParser;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.java.JavaParser;
import sk.tuke.fei.kpi.ProjectObserver.utils.Disposable;

public class Project implements Serializable, Disposable {
	private static final long serialVersionUID = -8116395605534346529L;
	private transient Logger logger = Logger.getLogger(Project.class);
	private File umlFile;
	private File javaFile;

	private ClassDiagram classDiagram;

	private Application javaModel;
	
	private MappingHolder mappingHolder;

	private Date creationDate;
	private String name;
	private String description;
	static {
		URL configFileResource = Project.class.getClassLoader().getResource("sk/tuke/fei/kpi/ProjectObserver/log4j.xml");
		DOMConfigurator.configure(configFileResource.getFile());
	}

	private Project() {
		creationDate = new Date();
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
		javaModel = new JavaParser().parse(javaFile);
		mappingHolder = new MappingHolder();
		return true;
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
		Date start = new Date();
		Project project = new Project("test.xml", "full2.owl");
		try {
			project.createModel();
			Aligner aligner = new Aligner(PrimaryModel.JAVA, AlignStrategy.EXACT);
			aligner.alignModels(project.classDiagram, project.javaModel);
			Logger.getLogger(project.getClass()).info(new Date().getTime() - start.getTime());
		} catch (AlignmentException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	public File getJavaFile() {
		return javaFile;
	}

	public File getUmlFile() {
		return umlFile;
	}
	
	public MappingHolder getMappingHolder() {
		return mappingHolder;
	}
	
	public void setMappingHolder(MappingHolder mappingHolder) {
		this.mappingHolder = mappingHolder;
	}
}
