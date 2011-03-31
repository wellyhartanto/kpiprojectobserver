package sk.tuke.fei.kpi.akAgent.integration.alignment;

import java.util.List;

import org.apache.log4j.Logger;

import sk.tuke.fei.kpi.akAgent.integration.alignment.Aligner.AlignStrategy;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Application;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Class;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Interface;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Package;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.ClassDiagram;
import sk.tuke.fei.kpi.akAgent.integration.search.ClassDiagramFinder;
/**
 * Aligns elements from {@link Application} to their models from {@link ClassDiagram}
 */
public class JavaAligner {
	private static Logger logger = Logger.getLogger(JavaAligner.class);
	
	private MappingHolder mappingHolder;

	private ClassDiagramFinder classDiagramFinder;
	
	/**
	 * Constructor
	 * @param mappingHolder mapping holder where align results are stored.
	 * @param cd class diagram finder
	 * @param alignStrategy align strategy
	 */
	public JavaAligner(MappingHolder mappingHolder,ClassDiagram cd,AlignStrategy alignStrategy) {
		this.mappingHolder = mappingHolder;
		this.classDiagramFinder = new ClassDiagramFinder(cd,alignStrategy);
	}
	
	/**
	 * Aligns packages.
	 * @param packages packages from {@link Application} to align
	 */
	public void alignPackages(List<Package> packages) {
		for (Package p : packages) {
			logger.info("Aligning package " + p.getFullyQualifiedName());
			sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Package pair = classDiagramFinder.findPackage(p);
			if (pair != null) {
				mappingHolder.getJava2UmlMapping().setPackagePair(p.getFullyQualifiedName(), pair);
				mappingHolder.getUml2JavaMapping().setPackagePair(pair.getFullyQualifiedName(), p);
				logger.info("Paired with " + pair.getFullyQualifiedName());
			}
			if (!p.getPackages().isEmpty()) {
				alignPackages(p.getPackages());
			}
		}
	}
	
	/**
	 * Aligns classes in passed list of packages.
	 * @param packages list of packages to process
	 */
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
	
	/**
	 * Aligns classes in the list.
	 * @param classes list of classes to align.
	 */
	public void alignClasses(List<Class> classes) {
		for (Class c : classes) {
			logger.info("Aligning classs " + c.getFullyQualifiedName());
			sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Class pair = classDiagramFinder.findClass(c,AlignStrategy.EXACT);
			if (pair == null) {
				pair = classDiagramFinder.findClass(c,AlignStrategy.IGNORE_PACKAGE);
			}
			if (pair == null) {
				pair = classDiagramFinder.findClass(c,AlignStrategy.HEURISTIC);
			}
			if (pair == null) {
				pair = classDiagramFinder.findClass(c,AlignStrategy.APPROXIMATION);
			}
			if (pair != null) {
				mappingHolder.addClassPair(pair,c);
				logger.info("Paired with " + pair.getFullyQualifiedName());
			}
			if (!c.getClasses().isEmpty()) {
				alignClasses(c.getClasses());
			}
		}
	}
	
	/**
	 * Aligns interfaces in passed list of packages.
	 * @param packages list of packages to process
	 */
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
	
	/**
	 * Aligns interfaces in the list.
	 * @param interfaces list of interfaces to align.
	 */
	public void alignInterfaces(List<Interface> interfaces) {
		for (Interface c : interfaces) {
			logger.info("Aligning interface " + c.getFullyQualifiedName());
			sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Interface pair = classDiagramFinder.findInterface(c,AlignStrategy.EXACT);
			if (pair == null) {
				pair = classDiagramFinder.findInterface(c,AlignStrategy.IGNORE_PACKAGE);
			}			
			if (pair == null) {
				pair = classDiagramFinder.findInterface(c,AlignStrategy.HEURISTIC);
			}
			if (pair == null) {
				pair = classDiagramFinder.findInterface(c,AlignStrategy.APPROXIMATION);
			}
			if (pair != null) {
				mappingHolder.addInterfacePair(pair,c);
				logger.info("Paired with " + pair.getFullyQualifiedName());
			}
		}
	}
}
