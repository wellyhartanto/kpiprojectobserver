package sk.tuke.fei.kpi.ProjectObserver.Integration.alignment;

import org.apache.log4j.Logger;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;

public class Aligner {
	private static Logger logger = Logger.getLogger(Aligner.class);

	public enum PrimaryModel {
		JAVA, UML
	}

	public enum AlignStrategy {
		EXACT, APPROXIMATION, MANUAL
	}

	private MappingHolder mappingHolder;
	private PrimaryModel primaryModel;
	private AlignStrategy alignStrategy;
	private ClassDiagram classDiagram;
	private Application application;
	private JavaFinder javaFinder;
	private ClassDiagramFinder classDiagramFinder;

	public Aligner(PrimaryModel primaryModel, AlignStrategy alignStrategy) {
		this.primaryModel = primaryModel;
		this.alignStrategy = alignStrategy;
		mappingHolder = new MappingHolder();
	}

	public MappingHolder alignModels(ClassDiagram cd, Application app) {
		this.classDiagram = cd;
		this.application = app;
		classDiagramFinder = new ClassDiagramFinder(cd);
		javaFinder = new JavaFinder(app);
		if (primaryModel == PrimaryModel.JAVA) {
			alignFromJavaModel();
		} else {
			alignFromUmlModel();
		}
		return mappingHolder;
	}

	private void alignFromJavaModel() {
		JavaAligner javaAligner = new JavaAligner(mappingHolder,classDiagram);
		javaAligner.alignJavaPackages(application.getPackages());
		javaAligner.alignJavaClass(application.getClasses());
		javaAligner.alignJavaClasses(application.getPackages());
		javaAligner.alignJavaInterfaces(application.getInterfaces());
		javaAligner.alignJavaInterface(application.getPackages());
	}	

	private void alignFromUmlModel() {

	}
}
