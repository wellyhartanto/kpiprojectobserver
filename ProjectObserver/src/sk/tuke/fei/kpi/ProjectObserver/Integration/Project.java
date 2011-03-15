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
		DOMConfigurator.configure(Project.class.getClassLoader().getResource("sk/tuke/fei/kpi/ProjectObserver/log4j.xml").getFile());
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
		return true;
	}

	public boolean alignModels() {
		Aligner aligner = new Aligner(PrimaryModel.JAVA, AlignStrategy.EXACT);
		mappingHolder = aligner.alignModels(classDiagram, javaModel);
		return true;
	}

	public void save(String pathname) throws FileNotFoundException, IOException {
		save(new File(pathname));
	}

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
	
	public static Project load(String pathname) throws IOException, ClassNotFoundException {
		return load(new File(pathname));
	}
	
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

	@Deprecated
	public static Project loadFromXml(String pathname) throws FileNotFoundException {
		return loadFromXml(new File(pathname));
	}

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

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Date start = new Date();
		Project project = new Project("test2.xml", "full2.owl");
		try {
			project.createModel();
			project.alignModels();
			System.out.println(project.mappingHolder.getJava2UmlMapping().getClass("de.softproject.elos.model.web.Pannenursachen"));			
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
