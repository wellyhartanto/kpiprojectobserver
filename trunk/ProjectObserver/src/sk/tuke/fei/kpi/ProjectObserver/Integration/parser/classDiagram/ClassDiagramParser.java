package sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element.Modifiers;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element.Visibility;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.Parser;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.ParserException;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.entities.Comment;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.entities.DataType;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.entities.XmiElement;
import sk.tuke.fei.kpi.ProjectObserver.utils.XMLUtils;

public class ClassDiagramParser implements Parser<ClassDiagram> {
	private Logger logger = Logger.getLogger(ClassDiagramParser.class.getName());
	private static final String OWNED_ELEMENT = "Namespace.ownedElement";
	private static final String CLASSIFIER_FEATURE = "Classifier.feature";
	private static final String PREFIX = "UML:";
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
		return classDiagram;
	}

	private void loadModel() {
		NodeList nodes = document.getElementsByTagName(PREFIX + "Model");
		classDiagram.setName(nodes.item(0).getAttributes().getNamedItem("name").getNodeValue());
		List<Node> packages = getNodeList(OWNED_ELEMENT,"Package", nodes.item(0));
		for (Node node : packages) {
			Package pack = processPackage(node);
			classDiagram.getPackages().add(pack);
		}

	}

	private Package processPackage(Node node, String parentName) {
		Package pack = getElement(Package.class, node);
		if (parentName != null)
			pack.setName(parentName + "." + pack.getName());

		processClasses(node, pack);
		processInterfaces(node, pack);
		List<Node> packages = getNodeList(OWNED_ELEMENT,"Package", node);
		for (Node n : packages) {
			Package subPackage = processPackage(n, pack.getName());
			subPackage.setParent(pack);
			pack.getPackages().add(subPackage);
		}
		return pack;
	}

	private void processClasses(Node node, Package pack) {
		List<Node> classes = getNodeList(OWNED_ELEMENT,"Class", node);
		for (Node n : classes) {
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class clazz = getElement(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class.class, n);
			clazz.setParent(pack);
			pack.getClasses().add(clazz);
			processFields(n,clazz);
			processMethods(n,clazz);
			logger.info(clazz.toString());
			logger.info(clazz.getMethods().toString());
			logger.info(clazz.getFields().toString());
		}
	}

	private void processMethods(Node node, Class clazz) {
		List<Node> methods = getNodeList(CLASSIFIER_FEATURE,"Operation", node);
		for (Node n : methods) {
			Method method = getElement(Method.class, n);
			clazz.setParent(clazz);
			clazz.getMethods().add(method);
		}
		
	}

	private void processFields(Node node, Class clazz) {
		List<Node> attributes = getNodeList(CLASSIFIER_FEATURE,"Attribute", node);
		for (Node n : attributes) {
			Field field = getElement(Field.class, n);
			clazz.setParent(clazz);
			clazz.getFields().add(field);
		}
		
	}

	private void processEnums(Node node, Package pack) {
		List<Node> enums = getNodeList(OWNED_ELEMENT,"Package", node);
	}

	private void processInterfaces(Node node, Package pack) {
		List<Node> interfaces = getNodeList(OWNED_ELEMENT,"Interface", node);
		for (Node n : interfaces) {
			Interface inter = getElement(Interface.class, n);
			inter.setParent(pack);
			pack.getInterfaces().add(inter);

			logger.info(inter.toString() + inter.getParent());
		}
	}

	private Package processPackage(Node node) {
		return processPackage(node, null);
	}

	private void loadComments() {
		NodeList nodes = document.getElementsByTagName(PREFIX + "Comment");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			XmiElement element = getXmiElement(node);
			Comment c = new Comment(element.getName(), element.getXmiId());
			if (node.getChildNodes().item(1).getChildNodes().getLength() > 0) {
				c.setParentId(node.getChildNodes().item(1).getChildNodes().item(1).getAttributes().getNamedItem("xmi.idref").getNodeValue());
			}
			comments.add(c);
		}
	}

	private void loadDatatypes() {
		NodeList nodes = document.getElementsByTagName(PREFIX + "DataType");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			XmiElement element = getXmiElement(node);
			datatypes.put(element.getXmiId(), new DataType(element.getName(), element.getXmiId()));
		}
	}

	private XmiElement getXmiElement(Node node) {
		NamedNodeMap attributes = node.getAttributes();
		return new XmiElement(attributes.getNamedItem("name").getNodeValue(), attributes.getNamedItem("xmi.id").getNodeValue());
	}

	private <T extends Element> T getElement(java.lang.Class<T> clazz, Node node) {
		T object = null;
		try {
			object = clazz.newInstance();
			NamedNodeMap attributes = node.getAttributes();
			object.setName(attributes.getNamedItem("name").getNodeValue());
			object.setXmiId(attributes.getNamedItem("xmi.id").getNodeValue());
			if (attributes.getNamedItem("visibility") != null){
				object.setVisibility(Visibility.fromString(attributes.getNamedItem("visibility").getNodeValue()));
			}
			List<Modifiers> modifiers = new ArrayList<Modifiers>();
			if (attributes.getNamedItem("isLeaf") != null && attributes.getNamedItem("isLeaf").getNodeValue().equals("true")) {
				modifiers.add(Modifiers.FINAL);
			}
			if (attributes.getNamedItem("isAbstract") != null && attributes.getNamedItem("isAbstract").getNodeValue().equals("true")) {
				modifiers.add(Modifiers.ABSTRACT);
			}
			if (attributes.getNamedItem("ownerScope") != null && attributes.getNamedItem("ownerScope").getNodeValue().equals("classifier")) {
				modifiers.add(Modifiers.STATIC);
			}
			if (attributes.getNamedItem("changeability") != null && attributes.getNamedItem("changeability").getNodeValue().equals("frozen")) {
				modifiers.add(Modifiers.FINAL);
			}
			object.setModifiers(modifiers);

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return object;
	}

	private List<Node> getNodeList(String parentName,String name, Node parent) {
		Node element = XMLUtils.getNodeByName(PREFIX + parentName, parent.getChildNodes());
		return XMLUtils.getNodesByName(PREFIX + name, element.getChildNodes());
	}
}
