package sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClassDiagram implements Serializable {
	private static final long serialVersionUID = -1965268218122677260L;
	private List<Class> classes;
	private List<Enum> enums;
	private List<Package> packages;
	private String filename;
	private String name;
	private List<Interface> interfaces;

	public ClassDiagram() {
		classes = new ArrayList<Class>();
		enums = new ArrayList<Enum>();
		packages = new ArrayList<Package>();
		interfaces = new ArrayList<Interface>();
	}	

	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	public List<Enum> getEnums() {
		return enums;
	}

	public void setEnums(List<Enum> enums) {
		this.enums = enums;
	}

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Interface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}
	@Override
	public String toString() {
		return name + packages.toString();
	}
}
