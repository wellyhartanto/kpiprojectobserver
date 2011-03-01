package sk.tuke.fei.kpi.ProjectObserver.Integration.parser.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Param;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class OwlUtils {
	private static String defaultNamespace = "http://www.jscc.sk/ontology/OOMOntology.owl#";
	private Logger logger = Logger.getLogger(OwlUtils.class);
	private Model model;

	public OwlUtils(Model model) {
		this.model = model;
	}

	private Query createQuery(String queryRequest) {
		StringBuffer queryStr = new StringBuffer();
		// Establish Prefixs
		queryStr.append("PREFIX jscc" + ": <" + defaultNamespace + "> ");
		// Now add query
		queryStr.append(queryRequest);
		logger.info(queryStr.toString());
		return QueryFactory.create(queryStr.toString());
	}

	public List<Param> runParamQuery(String entityName) {
		StringBuilder query = new StringBuilder("SELECT * WHERE {?param a jscc:Parameter; ");
		query.append("jscc:hasName ?name;");
		// query.append("jscc:hasFullName ?fullname; ");
		query.append("jscc:hasType ?type; ");
		// query.append("jscc:hasModifier ?modifier;");
		query.append("jscc:isParameterOf <").append(entityName).append(">");
		// query.append("?")
		query.append(".}");
		Query q = createQuery(query.toString());
		QueryExecution qexec = QueryExecutionFactory.create(q, model);
		// Run Select
		List<Param> result = new ArrayList<Param>();
		try {
			ResultSet response = qexec.execSelect();
			while (response.hasNext()) {
				QuerySolution soln = response.nextSolution();
				Param param = new Param();
				param.setName(soln.get("name").toString());
				param.setType(getValue(soln.get("type").toString(), '#'));
				param.setOrder(Integer.parseInt(getValue(soln.get("param").toString(), '$')));
				result.add(param);
			}
		} finally {
			qexec.close();
		}
		return result;
	}

//	StringBuilder query = new StringBuilder("SELECT * WHERE {?method a jscc:Method; ");
//	query.append("jscc:hasName 'setValue';");
//	query.append("jscc:hasFullName ?fullname; ");
//	query.append("jscc:hasReturnType ?type; ");
//	query.append("jscc:hasModifier ?modifier; jscc:isDefiningBehaviorOf  ?class");
	public void runMethodQuery(String queryRequest, Method method) {
		Query query = createQuery(queryRequest);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		// Run Select
		try {
			ResultSet response = qexec.execSelect();
			while (response.hasNext()) {
				QuerySolution soln = response.nextSolution();
				Iterator<String> iterato = soln.varNames();
				while (iterato.hasNext()) {
					String key = iterato.next();
					RDFNode name = soln.get(key);
					if (name != null) {
						logger.info(key + " = " + name.toString());
					}
				}
			}
		} finally {
			qexec.close();
		}
	}
	public void runAttributeQuery(String queryRequest,Field field) {
		Query query = createQuery(queryRequest);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		// Run Select
		try {
			ResultSet response = qexec.execSelect();
			while (response.hasNext()) {
				QuerySolution soln = response.nextSolution();
				Iterator<String> iterato = soln.varNames();
				while (iterato.hasNext()) {
					String key = iterato.next();
					RDFNode name = soln.get(key);
					if (name != null) {
						logger.info(key + " = " + name.toString());
					}
				}
			}
		} finally {
			qexec.close();
		}
	}
	
	public static String getValue(String value, char separator) {
		return value.substring(value.lastIndexOf(separator) + 1, value.length());
	}
}
