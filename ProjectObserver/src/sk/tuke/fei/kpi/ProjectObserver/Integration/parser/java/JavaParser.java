package sk.tuke.fei.kpi.ProjectObserver.Integration.parser.java;

import java.io.File;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Application;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.Parser;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.ParserException;
import sk.tuke.fei.kpi.ProjectObserver.utils.XMLUtils;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class JavaParser implements Parser<Application> {

	public static void main(String[] args) {
		OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);

		// we have a local copy of the wine ontology
		m.getDocumentManager().addAltEntry("http://www.w3.org/2001/sw/WebOnt/guide-src/wine", "file:wine.owl");
		m.getDocumentManager().addAltEntry("http://www.w3.org/2001/sw/WebOnt/guide-src/wine.owl", "file:wine.owl");
		m.getDocumentManager().addAltEntry("http://www.w3.org/2001/sw/WebOnt/guide-src/food", "file:food.owl");
		m.getDocumentManager().addAltEntry("http://www.w3.org/2001/sw/WebOnt/guide-src/food.owl", "file:food.owl");
		m.getDocumentManager().addAltEntry("http://www.jscc.sk/ontology/OOMOntology", "file:full.owl");
		m.getDocumentManager().addAltEntry("http://www.jscc.sk/ontology/OOMOntology.owl", "file:full.owl");

		m.read("http://www.jscc.sk/ontology/OOMOntology");

		 new ClassHierarchy().showHierarchy(System.out, m);
		// DescribeClass dc = new DescribeClass();
		//
		// if (args.length >= 2) {
		// // we have a named class to describe
		// OntClass c = m.getOntClass( args[1] );
		// dc.describeClass( System.out, c );
		// }
		// else {
		// for (Iterator<OntClass> i = m.listClasses(); i.hasNext(); ) {
		// // now list the classes
		// dc.describeClass( System.out, i.next() );
		// }
		// }
		
		
		m.close();
	}

	@Override
	public Application parse(String pathname) throws ParserException {
		return parse(new File(pathname));
	}

	@Override
	public Application parse(File file) throws ParserException {
		System.out.println(XMLUtils.readXmlDocument(file));
		return null;
	}
}
