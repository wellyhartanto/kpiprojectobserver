package sk.tuke.fei.kpi.ProjectObserver.Integration;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.AlignmentException;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.MappingHolder;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;
import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.PrimaryModel;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.ParserException;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.ClassDiagramParser;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.java.JavaParser;
import sk.tuke.fei.kpi.ProjectObserver.utils.Disposable;

/**
 * Main class of integration framework.
 * Central point of architectural knowledge of software project.
 * Contains Class diagram model and model application extracted from source files. 
 *
 */
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
		DOMConfigurator.configure(Project.class.getClassLoader().getResource("sk/tuke/fei/kpi/ProjectObserver/log4j.xml").getFile());
	}

	/**
	 * Constructor.
	 */
	private Project() {
		creationDate = new Date();
	}

	/**
	 * Constructor.
	 * @param umlFile pathname of file which contains class diagram in XMI 1.1 format
	 * @param javaFile pathname of owl ontology file 
	 */
	public Project(String umlFile, String javaFile) {
		this(new File(umlFile), new File(javaFile));
	}

	/**
	 * Constructor.
	 * @param umlFile file which contains class diagram in XMI 1.1 format
	 * @param javaFile owl ontology file 
	 */
	public Project(File umlFile, File javaFile) {
		this();
		this.umlFile = umlFile;
		this.javaFile = javaFile;
	}

	/**
	 * Creates project model.
	 * This method parses class diagram XMI file and OWL ontology with model of application implementation.
	 * @return true if parsing was successful and model were created	
	 * @throws ParserException
	 */
	public boolean createModel() throws ParserException {
		classDiagram = new ClassDiagramParser().parse(umlFile);
		javaModel = new JavaParser().parse(javaFile);
		return true;
	}

	/**
	 * Executes alignment of model.
	 * Joins element of java model with elements in class diagram.
	 * @return true if alignment was successful without errors.
	 * @throws AlignmentException
	 */
	public boolean alignModels() throws AlignmentException {
		Aligner aligner = new Aligner(PrimaryModel.JAVA, AlignStrategy.EXACT);
		mappingHolder = aligner.alignModels(classDiagram, javaModel);
		return true;
	}

	/**
	 * Saves project to specified file in binary format.
	 * @param pathname pathname
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save(String pathname) throws FileNotFoundException, IOException {
		save(new File(pathname));
	}

	/**
	 * Saves project to specified file in binary format.
	 * @param file file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save(File file) throws FileNotFoundException, IOException {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(this);

		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	@Deprecated
	public void saveToXml(String pathname,Object object) throws FileNotFoundException, IOException {
		saveToXml(new File(pathname),object);
	}
	
	/**
	 * Load project from xml file.
	 * @param file xml file
	 * @param object
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Deprecated
	public void saveToXml(File file,Object object) throws FileNotFoundException, IOException {
		XMLEncoder xmlEncoder = null;
		try {
			xmlEncoder = new XMLEncoder(new FileOutputStream(file));
			xmlEncoder.writeObject(object);
		} finally {
			if (xmlEncoder != null) {
				xmlEncoder.close();
			}
		}
	}
	
	/**
	 * Loads project from binary file.
	 * @param pathname pathname
	 * @return Project
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Project load(String pathname) throws IOException, ClassNotFoundException {
		return load(new File(pathname));
	}

	/**
	 * Loads project from binary file.
	 * @param file file
	 * @return Project
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Project load(File file) throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			return (Project) in.readObject();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	/**
	 * Loads project from XML file.
	 * @param pathname pathname
	 * @return Project
	 * @throws FileNotFoundException Thrown when file does not exist
	 */
	@Deprecated
	public static Project loadFromXml(String pathname) throws FileNotFoundException {
		return loadFromXml(new File(pathname));
	}

	/**
	 * Loads project object from XML file.
	 * @param file XML file
	 * @return Project
	 * @throws FileNotFoundException Thrown when file does not exist
	 */
	@Deprecated
	public static Project loadFromXml(File file) throws FileNotFoundException {
		XMLDecoder xmlDecoder = null;
		try {
			xmlDecoder = new XMLDecoder(new FileInputStream(file));
			return (Project) xmlDecoder.readObject();
		} finally {
			if (xmlDecoder != null) {
				xmlDecoder.close();
			}
		}
	}

	/**
	 * Gets class diagram of project.
	 * @return class diagram
	 */
	public ClassDiagram getClassDiagram() {
		return classDiagram;
	}

	/**
	 * Gets java model of project.
	 * @return java model
	 */
	public Application getJavaModel() {
		return javaModel;
	}

	/**
	 * Gets date when project was created.
	 * @return creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Gets project name.
	 * @return name of project
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets project name.
	 * @param name name of project
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets project description.
	 * @return description of project
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets project description.
	 * @param description description of project
	 */
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

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Date start = new Date();
		Project project = new Project("test.xml", "full.owl");
		try {
			project.createModel();
			project.alignModels();
			System.out.println(project.mappingHolder.getDifference("de.softproject.elos.model.web.Dienstleister"));
			System.out.println(project.mappingHolder.getJava2UmlMapping().getClass("de.softproject.elos.model.web.Pannenursachen"));
			System.out.println(project.getJavaModel().searchClasses("PA"));
			Logger.getLogger(project.getClass()).info(new Date().getTime() - start.getTime());
		} catch (AlignmentException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Return file which contents model of application implementation as OWL ontology
	 * @return OWL file
	 */
	public File getJavaFile() {
		return javaFile;
	}

	/**
	 * Gets File which contains UML diagram in XMI format
	 * @return UML diagram file
	 */
	public File getUmlFile() {
		return umlFile;
	}

	/**
	 * Gets mapping holder for this project
	 * @return mapping holder
	 */
	public MappingHolder getMappingHolder() {
		return mappingHolder;
	}
}
