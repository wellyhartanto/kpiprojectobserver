package sk.tuke.fei.kpi.ProjectObserver.Integration.parser.java;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class OwlUtils {
	private static String defaultNamespace = "http://www.jscc.sk/ontology/OOMOntology";

	public OwlUtils() {
		// TODO Auto-generated constructor stub
	}

	public void runQuery(String queryRequest, Model model) {
		StringBuffer queryStr = new StringBuffer();
		// Establish Prefixs
		queryStr.append("PREFIX swp2" + ": <" + defaultNamespace + "> ");
		queryStr.append("PREFIX foaf" + ": <" + "http://xmlns.com/foaf/0.1/" + "> ");
		// Now add query
		queryStr.append(queryRequest);
		Query query = QueryFactory.create(queryStr.toString());
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		// Run Select
		try {
			ResultSet response = qexec.execSelect();
			while (response.hasNext()) {
				QuerySolution soln = response.nextSolution();
				RDFNode name = soln.get("?name");
				if (name != null) {
					System.out.println("Hello to " + name.toString());
				} else
					System.out.println("No Friends found!");
			}
		} finally {
			qexec.close();
		}
	}
}
