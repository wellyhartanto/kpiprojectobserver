package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import java.util.List;

import org.apache.log4j.Logger;

import sk.tuke.fei.kpi.ProjectObserver.Integration.alignment.Aligner.AlignStrategy;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Package;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
import sk.tuke.fei.kpi.ProjectObserver.Integration.search.ClassDiagramFinder;
/**
 * Align
 */
public class JavaAligner {
	private static Logger logger = Logger.getLogger(JavaAligner.class);
	
	private MappingHolder mappingHolder;

	private ClassDiagramFinder classDiagramFinder;
	
	/**
	 * Constructor
	 * @param mappingHolder mapping holder where align result are stored.
	 * @param cd class diagram finder
	 * @param alignStrategy align strategy
	 */
	public JavaAligner(MappingHolder mappingHolder,ClassDiagram cd,AlignStrategy alignStrategy) {
		this.mappingHolder = mappingHolder;
		this.classDiagramFinder = new ClassDiagramFinder(cd,alignStrategy);
	}
	
	/**
	 * 
	 * @param packages
	 */
	public void alignPackages(List<Package> packages) {
		for (Package p : packages) {
			logger.info("Aligning package " + p.getFullyQualifiedName());
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package pair = classDiagramFinder.findPackage(p);
			if (pair != null) {
				mappingHolder.getJava2UmlMapping().addPackagePair(p.getFullyQualifiedName(), pair);
				mappingHolder.getUml2JavaMapping().addPackagePair(pair.getFullyQualifiedName(), p);
				logger.info("Paired with " + pair.getFullyQualifiedName());
			}
			if (!p.getPackages().isEmpty()) {
				alignPackages(p.getPackages());
			}
		}
	}
	
	public void alignClassesInPackage(List<Package> packages) {
		for (Package p : packages) {
			if(!p.getClasses().isEmpty()){
				alignClasses(p.getClasses());
			}
			if (!p.getPackages().isEmpty()) {
				alignClassesInPackage(p.getPackages());
			}
		}
	}
	
	public void alignClasses(List<Class> classes) {
		for (Class c : classes) {
			logger.info("Aligning classs " + c.getFullyQualifiedName());
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class pair = classDiagramFinder.findClass(c);
			if (pair != null) {
				mappingHolder.addClassPair(pair,c);
				logger.info("Paired with " + pair.getFullyQualifiedName());
			}
			if (!c.getClasses().isEmpty()) {
				alignClasses(c.getClasses());
			}
		}
	}
	
	public void alignInterfaceInPackage(List<Package> packages) {
		for (Package p : packages) {
			if(!p.getInterfaces().isEmpty()){
				alignInterfaces(p.getInterfaces());
			}
			if (!p.getPackages().isEmpty()) {
				alignInterfaceInPackage(p.getPackages());
			}
		}
	}
	
	public void alignInterfaces(List<Interface> classes) {
		for (Interface c : classes) {
			logger.info("Aligning interface " + c.getFullyQualifiedName());
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Interface pair = classDiagramFinder.findInterface(c);
			if (pair != null) {
				mappingHolder.addInterfacePair(pair, c);
				logger.info("Paired with " + pair.getFullyQualifiedName());
			}
		}
	}
}
