package sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Association;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.ClassDiagram;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Constructor;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Param;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.TypeElement;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Association.AssociationType;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Association.Cardinality;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element.Modifiers;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Element.Visibility;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.Parser;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.ParserException;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.entities.Comment;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.entities.DataType;
import sk.tuke.fei.kpi.ProjectObserver.Integration.parser.classDiagram.entities.XmiElement;
import sk.tuke.fei.kpi.ProjectObserver.utils.Disposable;
import sk.tuke.fei.kpi.ProjectObserver.utils.XMLUtils;

public class ClassDiagramParser implements Parser<ClassDiagram>, Disposable {
	private Logger logger = Logger.getLogger(ClassDiagramParser.class);
	private static final String OWNED_ELEMENT = "Namespace.ownedElement";
	private static final String CLASSIFIER_FEATURE = "Classifier.feature";
	private static final String STRUCTURAL_FEATURE = "StructuralFeature.type";
	private static final String ATTRIBUTE_VALUE = "Attribute.initialValue";
	private static final String PREFIX = "UML:";
	private static final String ENUM = "EnumConstant";
	private Document document;
	private ClassDiagram classDiagram;
	private Map<String, DataType> datatypes;
	private List<Comment> comments;
	private List<Package> packages;
	private List<TypeElement> elements;

	public ClassDiagramParser() {
		classDiagram = new ClassDiagram();
		datatypes = new HashMap<String, DataType>();
		comments = new ArrayList<Comment>();
		elements = new ArrayList<TypeElement>();
		packages = new ArrayList<Package>();
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
		List<Node> packages = getNodeList(OWNED_ELEMENT, "Package", nodes.item(0));
		for (Node node : packages) {
			Package pack = processPackage(node);
			classDiagram.getPackages().add(pack);
			this.packages.add(pack);
		}
		processGeneralizations();
		processAssociations();

		// for (TypeElement element : elements) {
		// logger.info(element.toString());
		// logger.info(element.getFields());
		// logger.info(element.getMethods());
		// logger.info(element.getAssociations());
		// }
		logger.info(elements.size() + " classes and interfaces were loaded from repository.");

		dispose();
	}

	private void processGeneralizations() {
		NodeList generalizations = document.getElementsByTagName(PREFIX + "Generalization");
		for (int i = 0; i < generalizations.getLength(); i++) {
			Node childNode = getNode("Generalization.child", "GeneralizableElement", generalizations.item(i));
			if (childNode != null) {
				Node parentNode = getNode("Generalization.parent", "GeneralizableElement", generalizations.item(i));
				XmiElement child = getXmiElement(childNode);
				XmiElement parent = getXmiElement(parentNode);
				TypeElement superClass = findByXmiId(parent.getXmiId());
				TypeElement subClass = findByXmiId(child.getXmiId());
				subClass.getSuperClasses().add(superClass);
			}
		}
	}

	private void processAssociations() {
		NodeList associations = document.getElementsByTagName(PREFIX + "Association");
		for (int i = 0; i < associations.getLength(); i++) {
			XmiElement name = getXmiElement(associations.item(i), "name");
			List<Node> taggedValue = getNodeList("ModelElement.taggedValue", "TaggedValue", associations.item(i));
			XmiElement aContainer = getElement(taggedValue.get(0), "tag", "value");
			XmiElement aImpl = getElement(taggedValue.get(1), "tag", "value");
			XmiElement bContainer = getElement(taggedValue.get(2), "tag", "value");
			XmiElement bImpl = getElement(taggedValue.get(3), "tag", "value");

			List<Node> ends = getNodeList("Association.connection", "AssociationEnd", associations.item(i));
			XmiElement a = getXmiElement(ends.get(0), "visibility");
			XmiElement b = getXmiElement(ends.get(1), "visibility");

			XmiElement aAgregation = getElement("xmi.value", XMLUtils.getNodeByName(PREFIX + "AssociationEnd.aggregation", ends.get(0)));
			XmiElement bAgregation = getElement("xmi.value", XMLUtils.getNodeByName(PREFIX + "AssociationEnd.aggregation", ends.get(1)));

			XmiElement aType = getXmiElement(getNode("AssociationEnd.type", "Classifier", ends.get(0)));
			XmiElement bType = getXmiElement(getNode("AssociationEnd.type", "Classifier", ends.get(1)));

			Node multiplicityA = getNode("AssociationEnd.multiplicity", "Multiplicity", ends.get(0));
			Integer aLower = null;
			Integer aUpper = null;
			if (multiplicityA != null) {
				Node range = getNode("Multiplicity.range", "MultiplicityRange", multiplicityA);
				aLower = Integer.parseInt(range.getChildNodes().item(1).getTextContent());
				aUpper = Integer.parseInt(range.getChildNodes().item(3).getTextContent());
			}
			Integer bLower = null;
			Integer bUpper = null;
			Node multiplicityB = getNode("AssociationEnd.multiplicity", "Multiplicity", ends.get(1));
			if (multiplicityB != null) {
				Node range = getNode("Multiplicity.range", "MultiplicityRange", multiplicityB);
				bLower = Integer.parseInt(range.getChildNodes().item(1).getTextContent());
				bUpper = Integer.parseInt(range.getChildNodes().item(3).getTextContent());
			}
			Association association = new Association();
			association.setName(name.getName());
			association.setFrom(findByXmiId(aType.getXmiId()));
			association.setTo(findByXmiId(bType.getXmiId()));
			if (!aAgregation.getXmiId().equals("none")) {
				association.setType(AssociationType.fromString(aAgregation.getXmiId()));
			} else {
				association.setType(AssociationType.fromString(bAgregation.getXmiId()));
			}
			association.setCardinalityFrom(Cardinality.get(aLower, aUpper));
			association.setCardinalityTo(Cardinality.get(bLower, bUpper));
			association.getFrom().getAssociations().add(association);

			association.getTo().getAssociations().add(association);

		}
	}

	@Override
	public void dispose() {
		document = null;
		datatypes.clear();
		datatypes = null;
		comments.clear();
		comments = null;
	}

	private Package processPackage(Node node, String parentName) {
		Package pack = getElement(Package.class, node);
		if (parentName != null) {
			pack.setFullName(parentName + "." + pack.getName());
		} else {
			pack.setFullName(pack.getName());
		}
		packages.add(pack);
		processClasses(node, pack);
		processInterfaces(node, pack);
		List<Node> packages = getNodeList(OWNED_ELEMENT, "Package", node);
		for (Node n : packages) {
			Package subPackage = processPackage(n, pack.getFullName());
			subPackage.setParent(pack);
			pack.getPackages().add(subPackage);
		}
		return pack;
	}

	private void processClasses(Node node, Package pack) {
		List<Node> classes = getNodeList(OWNED_ELEMENT, "Class", node);
		for (Node n : classes) {
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class clazz = getElement(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class.class, n);
			clazz.setParent(pack);
			logger.info(clazz.getFullyQualifiedName());
			pack.getClasses().add(clazz);
			elements.add(clazz);
			processFields(n, clazz);
			processMethods(n, clazz);
			processSuperClasses(n, clazz);
			processClasses(n, clazz);
		}
	}

	private void processClasses(Node node, TypeElement ownerClass) {
		List<Node> classes = getNodeList(OWNED_ELEMENT, "Class", node);
		for (Node n : classes) {
			sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class clazz = getElement(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class.class, n);
			//logger.info(ownerClass);
			clazz.setParent(ownerClass);
			logger.info(clazz.getFullyQualifiedName());
			ownerClass.getInnerClasses().add(clazz);
			elements.add(clazz);
			processFields(n, clazz);
			processMethods(n, clazz);
			processSuperClasses(n, clazz);
		}
	}

	private void processMethods(Node node, TypeElement element) {
		List<Node> methods = getNodeList(CLASSIFIER_FEATURE, "Operation", node);
		for (Node n : methods) {
			Method method = getElement(Method.class, n);

			List<Node> params = getNodeList("BehavioralFeature.parameter", "Parameter", n);
			for (Node param : params) {
				Param p = extract(param);
				if (p.getName() == null) {
					method.setReturnType(p.getType());
				} else {
					method.getParams().add(p);
				}
			}
			if (element.getName().equals(method.getName())) {
				if (element instanceof Class) {
					Constructor c = new Constructor();
					c.setName(method.getName());
					c.setParams(method.getParams());
					c.setVisibility(method.getVisibility());
					((Class) element).getConstructors().add(c);
					c.setParent(element);
					//logger.info(c.getFullyQualifiedName());
				}
			} else {
				method.setParent(element);
				element.getMethods().add(method);
			}
			//logger.info(method.getFullyQualifiedName());
		}
	}

	private Param extract(Node node) {
		Param param = new Param();
		if (node.getAttributes().getNamedItem("name") != null) {
			param.setName(node.getAttributes().getNamedItem("name").getNodeValue());
		}
		Node type = getNode("Parameter.type", "Classifier", node);
		if (type != null) {
			XmiElement xe = getXmiElement(type);
			param.setType(datatypes.get(xe.getXmiId()).getName());
		}
		return param;
	}

	private void processFields(Node node, TypeElement element) {
		List<Node> attributes = getNodeList(CLASSIFIER_FEATURE, "Attribute", node);
		for (Node n : attributes) {
			Field field = getElement(Field.class, n);
			field.setParent(element);
			element.getFields().add(field);
			Node type = getNode(STRUCTURAL_FEATURE, "Classifier", n);
			XmiElement el = getXmiElement(type);
			field.setType(datatypes.get(el.getXmiId()).getName());
			Node value = getNode(ATTRIBUTE_VALUE, "Expression", n);
			if (value != null) {
				XmiElement e = getXmiElement(value, "body");
				field.setDefaultValue(e.getName());
			}
			if(ENUM.equals(field.getType())){
				element.setEnumClass(true);
			}
			setMultiplicity(n, field);
		}
	}

	private void setMultiplicity(Node node, Field field) {
		Node n = getNode("StructuralFeature.multiplicity", "Multiplicity", node);
		if (n != null) {
			Node range = getNode("Multiplicity.range", "MultiplicityRange", n);
			if (range != null) {
				field.setArray(true);
				field.setSize(Integer.parseInt(range.getChildNodes().item(3).getTextContent()));
			}
		}
	}

	private void processInterfaces(Node node, Package pack) {
		List<Node> interfaces = getNodeList(OWNED_ELEMENT, "Interface", node);
		for (Node n : interfaces) {
			Interface inter = getElement(Interface.class, n);
			inter.setParent(pack);
			pack.getInterfaces().add(inter);
			elements.add(inter);
			logger.info(inter.getFullyQualifiedName());
			processFields(n, inter);
			processMethods(n, inter);
			processClasses(n, inter);
			processSuperClasses(n, inter);
		}
	}

	private void processSuperClasses(Node n, TypeElement typeElement) {
		List<Node> nodes = getNodeList("GeneralizableElement.generalization", "Generalization", n);
		for (Node node : nodes) {
			XmiElement element = getXmiElement(node);
			typeElement.getSuperclassesIds().add(element.getXmiId());
		}
	}

	private TypeElement findByXmiId(String xmiId) {
		for (TypeElement el : elements) {
			if (el.getXmiId().equals(xmiId))
				return el;
		}
		return null;
	}

	private Package processPackage(Node node) {
		return processPackage(node, null);
	}

	private void loadComments() {
		NodeList nodes = document.getElementsByTagName(PREFIX + "Comment");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			XmiElement element = getXmiElement(node, "name");
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
			XmiElement element = getXmiElement(node, "name");
			datatypes.put(element.getXmiId(), new DataType(element.getName(), element.getXmiId()));
		}
	}

	private XmiElement getElement(Node node, String attribute1, String attribute2) {
		NamedNodeMap attributes = node.getAttributes();
		return new XmiElement(attributes.getNamedItem(attribute1).getNodeValue(), attributes.getNamedItem(attribute2).getNodeValue());
	}

	private XmiElement getElement(String attribute, Node node) {
		NamedNodeMap attributes = node.getAttributes();
		return new XmiElement(attributes.getNamedItem(attribute).getNodeValue());
	}

	private XmiElement getXmiElement(Node node, String attribute) {
		NamedNodeMap attributes = node.getAttributes();
		return new XmiElement(attributes.getNamedItem(attribute).getNodeValue(), attributes.getNamedItem("xmi.id").getNodeValue());
	}

	private XmiElement getXmiElement(Node node) {
		NamedNodeMap attributes = node.getAttributes();
		return new XmiElement(attributes.getNamedItem("xmi.idref").getNodeValue());
	}

	private <T extends Element> T getElement(java.lang.Class<T> clazz, Node node) {
		T object = null;
		try {
			object = clazz.newInstance();
			NamedNodeMap attributes = node.getAttributes();
			object.setName(attributes.getNamedItem("name").getNodeValue());
			object.setXmiId(attributes.getNamedItem("xmi.id").getNodeValue());
			if (attributes.getNamedItem("visibility") != null) {
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

	private List<Node> getNodeList(String parentName, String name, Node parent) {
		Node element = XMLUtils.getNodeByName(PREFIX + parentName, parent.getChildNodes());
		if (element != null) {
			return XMLUtils.getNodesByName(PREFIX + name, element.getChildNodes());
		} else {
			return new ArrayList<Node>();
		}
	}

	private Node getNode(String parentName, String name, Node parent) {
		Node element = XMLUtils.getNodeByName(PREFIX + parentName, parent.getChildNodes());
		if (element != null) {
			return XMLUtils.getNodeByName(PREFIX + name, element.getChildNodes());
		} else {
			return null;
		}
	}
	
	public List<TypeElement> getElements() {
		return elements;
	}
	
	public List<Package> getPackages() {
		return packages;
	}
}
