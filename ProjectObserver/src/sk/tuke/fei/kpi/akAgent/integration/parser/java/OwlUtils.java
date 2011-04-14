package sk.tuke.fei.kpi.akAgent.integration.parser.java;

import java.util.ArrayList;
import java.util.List;

import sk.tuke.fei.kpi.akAgent.integration.metamodel.java.Param;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

/**
 * OWL Utils.
 * Contains method for running and processing SPARQL queries on given Ontology model.
 */
public class OwlUtils {
	/**
	 * Default namespace of OWL ontology, which contains application model.
	 * It is used for executing SPARQL queries.
	 */
	public static String defaultNamespace = "http://www.jscc.sk/ontology/OOMOntology.owl#";
	private Model model;

	/**
	 * COntructor
	 * @param model ontology model
	 */
	public OwlUtils(Model model) {
		this.model = model;
	}

	/**
	 * Creates SPARQL query.
	 * @param queryRequest query string
	 * @return Query object
	 */
	private Query createQuery(String queryRequest) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("PREFIX jscc: <").append(defaultNamespace).append("> ");
		queryStr.append(queryRequest);
		return QueryFactory.create(queryStr.toString());
	}

	/**
	 * Runs query which selects method params.
	 * @param method method URI
	 * @return List of parameters
	 */
	public List<Param> runParamQuery(String method) {
		StringBuilder query = new StringBuilder("SELECT * WHERE {?param a jscc:Parameter; ");
		
		query.append("jscc:hasType ?type; ");
		query.append("jscc:isParameterOf <").append(method).append(">");
		query.append(".");
		query.append("OPTIONAL {?param jscc:hasName ?name}}");

		Query q = createQuery(query.toString());
		QueryExecution qexec = QueryExecutionFactory.create(q, model);
		// Run Select
		//if(method.contains("Question")){
		//}
		List<Param> result = new ArrayList<Param>();
		try {
			ResultSet response = qexec.execSelect();
			while (response.hasNext()) {
				QuerySolution soln = response.nextSolution();
				Param param = new Param();
				param.setName(soln.get("name")!=null ? soln.get("name").toString():"p");
				param.setType(getValue(JavaParser.filterType(soln.get("type").toString()),'#'));
				param.setOrder(Integer.parseInt(getValue(soln.get("param").toString(), '$')));
				result.add(param);
			}
		} finally {
			qexec.close();
		}
		return result;
	}

	/**
	 * Gets value from URI. 
	 * Finds last occurrence of separator and return string which starts after separator.
	 * @param value URI
	 * @param separator value separator
	 * @return value
	 */
	public static String getValue(String value, char separator) {
		if(value!=null &&value.contains(String.valueOf(separator))){
			return value.substring(value.lastIndexOf(separator) + 1, value.length());
		} else {
			return value;
		}
	}
}
