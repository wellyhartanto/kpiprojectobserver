/*
 * $Id: JTableRenderer.java,v 1.4 2010-05-28 13:57:32 gaudenz Exp $
 * Copyright (c) 2001-2005, Gaudenz Alder
 * 
 * All rights reserved.
 * 
 * See LICENSE file for license details. If you are unable to locate
 * this file please contact info (at) jgraph (dot) com.
 */
package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.sourcecode;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Field;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Method;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Element.Visibility;
 

/**
 * @author MV
 * 
 */
public class JPanelRenderer extends JPanel {

	private static final long serialVersionUID = 2106746763664760745L;

	public JPanelRenderer(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.java.Class sourceClass) {

		setLayout(new MigLayout("insets 5", "", "[]0[]0[]"));
		setOpaque(false);

		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setBackground(Color.BLACK);
		separator.setOpaque(true);
		JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
		separator1.setBackground(Color.BLACK);
		separator1.setOpaque(true);

		JLabel classNameLbl = new JLabel("Class");
		add(classNameLbl, "wrap,span,center");

		add(separator, "wrap,span,growx");

		Field m = new Field();
		m.setName("testdieldname");
		m.setVisibility(Visibility.PUBLIC);
		m.setType("String");

		Method m1 = new Method();
		m1.setName("testmethodname");
		m1.setVisibility(Visibility.PUBLIC);
		m1.setReturnType("String");

		List<Field> fields = new ArrayList<Field>();
		fields.add(m);
		sourceClass.setFields(fields);

		List<Method> methods = new ArrayList<Method>();
		methods.add(m1);
		sourceClass.setMethods(methods);

		for (Field field : sourceClass.getFields()) {

			JLabel fieldLbl = new JLabel();
			fieldLbl.setText(field.getVisibility() + " " + field.getType() + " " + field.getName());
			add(fieldLbl, "wrap");
		}
		add(separator1, "wrap,span,growx");

		for (Method method : sourceClass.getMethods()) {

			JLabel methodLbl = new JLabel();
			methodLbl.setText(method.getVisibility() + " " + method.getReturnType() + " " + method.getName());

			add(methodLbl, "wrap");

		}

	}

	public static JPanelRenderer getVertex(Component component) {
		while (component != null) {
			if (component instanceof JPanelRenderer) {
				return (JPanelRenderer) component;
			}
			component = component.getParent();
		}

		return null;
	}

}
