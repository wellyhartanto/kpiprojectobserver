package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import java.util.List;

import org.apache.log4j.Logger;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;

public class JavaAligner {
	private static Logger logger = Logger.getLogger(JavaAligner.class);
	
	private MappingHolder mappingHolder;

	private ClassDiagramFinder classDiagramFinder;
	public JavaAligner(MappingHolder mappingHolder,ClassDiagram cd) {
		this.mappingHolder = mappingHolder;
		this.classDiagramFinder = new ClassDiagramFinder(cd);
	}
	
	public void alignJavaPackages(List<Package> packages) {
		for (Package p : packages) {
			logger.info("Aligning package " + p.getFullyQualifiedName());
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package pair = classDiagramFinder.findPackage(p.getFullyQualifiedName());
			if (pair != null) {
				mappingHolder.getJava2UmlMapping().addPackagePair(p.getFullyQualifiedName(), pair);
				mappingHolder.getUml2JavaMapping().addPackagePair(pair.getFullyQualifiedName(), p);
				logger.info("Paired with " + pair.getFullyQualifiedName());
			}
			if (!p.getPackages().isEmpty()) {
				alignJavaPackages(p.getPackages());
			}
		}
	}
	
	public void alignJavaClasses(List<Package> packages) {
		for (Package p : packages) {
			if(!p.getClasses().isEmpty()){
				alignJavaClass(p.getClasses());
			}
			if (!p.getPackages().isEmpty()) {
				alignJavaClasses(p.getPackages());
			}
		}
	}
	
	public void alignJavaClass(List<Class> classes) {
		for (Class c : classes) {
			logger.info("Aligning classs " + c.getFullyQualifiedName());
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class pair = classDiagramFinder.findClass(c.getFullyQualifiedName());
			if (pair != null) {
				mappingHolder.getJava2UmlMapping().addClassPair(c.getFullyQualifiedName(), pair);
				mappingHolder.getUml2JavaMapping().addClassPair(pair.getFullyQualifiedName(), c);
				logger.info("Paired with " + pair.getFullyQualifiedName());
			}
			if (!c.getClasses().isEmpty()) {
				alignJavaClass(c.getClasses());
			}
		}
	}
	
	public void alignJavaInterface(List<Package> packages) {
		for (Package p : packages) {
			if(!p.getInterfaces().isEmpty()){
				alignJavaInterfaces(p.getInterfaces());
			}
			if (!p.getPackages().isEmpty()) {
				alignJavaInterface(p.getPackages());
			}
		}
	}
	
	public void alignJavaInterfaces(List<Interface> classes) {
		for (Interface c : classes) {
			logger.info("Aligning interface " + c.getFullyQualifiedName());
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Interface pair = classDiagramFinder.findInterface(c.getFullyQualifiedName());
			if (pair != null) {
				mappingHolder.getJava2UmlMapping().addInterfacePair(c.getFullyQualifiedName(), pair);
				mappingHolder.getUml2JavaMapping().addInterfacePair(pair.getFullyQualifiedName(), c);
				logger.info("Paired with " + pair.getFullyQualifiedName());
			}
		}
	}
}
