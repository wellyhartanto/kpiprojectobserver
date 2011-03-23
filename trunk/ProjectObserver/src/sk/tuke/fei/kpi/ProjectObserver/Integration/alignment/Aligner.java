package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
/**
 * Aligner.
 * Creates connections between classes, interfaces and packages in {@link Application} and {@link ClassDiagram}
 */
public class Aligner {

	/**
	 * Primary model for alignment..
	 */
	public enum PrimaryModel {
		JAVA, UML
	}

	/**
	 * Align strategy
	 */
	public enum AlignStrategy {
		EXACT, HEURISTIC, IGNORE_PACKAGE, APPROXIMATION, MANUAL
	}

	private MappingHolder mappingHolder;
	private PrimaryModel primaryModel;
	private AlignStrategy alignStrategy;
	private ClassDiagram classDiagram;
	private Application application;

	/**
	 * Aligner constructor
	 * @param primaryModel primary model
	 * @param alignStrategy align strategy
	 */
	public Aligner(PrimaryModel primaryModel, AlignStrategy alignStrategy) {
		this.primaryModel = primaryModel;
		this.alignStrategy = alignStrategy;
		mappingHolder = new MappingHolder();
	}

	/**
	 * Aligns classes, interfaces and packages of {@link Application} with elements in {@link ClassDiagram} 
	 * @param cd class diagram
	 * @param app application
	 * @return mapping holder, which contains relation between models
	 */
	public MappingHolder alignModels(ClassDiagram cd, Application app) {
		this.classDiagram = cd;
		this.application = app;
		if (primaryModel == PrimaryModel.JAVA) {
			alignFromJavaModel();
		} else {
			alignFromUmlModel();
		}
		return mappingHolder;
	}

	/**
	 * Aligns model. Java model is used as primary model.
	 */
	private void alignFromJavaModel() {
		JavaAligner javaAligner = new JavaAligner(mappingHolder,classDiagram,alignStrategy);
		javaAligner.alignPackages(application.getPackages());
		javaAligner.alignClasses(application.getClasses());
		javaAligner.alignClassesInPackage(application.getPackages());
		javaAligner.alignInterfaces(application.getInterfaces());
		javaAligner.alignInterfaceInPackage(application.getPackages());
	}	

	/**
	 * Aligns models. Class diagram is used as primary model.
	 * Not implemented yet.
	 */
	private void alignFromUmlModel() {

	}
}
