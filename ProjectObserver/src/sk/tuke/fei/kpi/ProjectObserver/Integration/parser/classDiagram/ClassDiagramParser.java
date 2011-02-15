package sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.Parser;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.ParserException;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.entities.Comment;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.entities.DataType;

public class ClassDiagramParser implements Parser<ClassDiagram> {

	private Document document;
	private ClassDiagram classDiagram;
	private Map<String, DataType> datatypes;
	private List<Comment> comments;

	public ClassDiagramParser() {
		classDiagram = new ClassDiagram();
		datatypes = new HashMap<String, DataType>();
		comments = new ArrayList<Comment>();
	}

	@Override
	public ClassDiagram parse(String pathname) throws ParserException {
		return parse(new File(pathname));
	}

	@Override
	public ClassDiagram parse(File file) throws ParserException {
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			loadDatatypes();
			loadComments();
			loadModel();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void loadModel() {
		NodeList nodes = document.getElementsByTagName("UML:Model");
		System.out.println(nodes.item(0).getAttributes().getNamedItem("name").getNodeValue());
	}

	private void loadComments() {
		NodeList nodes = document.getElementsByTagName("UML:Comment");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			NamedNodeMap attributes = node.getAttributes();
			Node name = attributes.getNamedItem("name");
			Node id = attributes.getNamedItem("xmi.id");
			Comment c = new Comment(name.getNodeValue(), id.getNodeValue());
			if (node.getChildNodes().item(1).getChildNodes().getLength() > 0) {
				c.setParentId(node.getChildNodes().item(1).getChildNodes().item(1).getAttributes().getNamedItem("xmi.idref").getNodeValue());
			}
			comments.add(c);
		}
	}

	private void loadDatatypes() {
		NodeList nodes = document.getElementsByTagName("UML:DataType");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			NamedNodeMap attributes = node.getAttributes();
			Node name = attributes.getNamedItem("name");
			Node id = attributes.getNamedItem("xmi.id");
			datatypes.put(id.getNodeValue(), new DataType(name.getNodeValue(), id.getNodeValue()));
		}
	}
}
