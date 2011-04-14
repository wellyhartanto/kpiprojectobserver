/*
 * $Id: JTableRenderer.java,v 1.4 2010-05-28 13:57:32 gaudenz Exp $
 * Copyright (c) 2001-2005, Gaudenz Alder
 * 
 * All rights reserved.
 * 
 * See LICENSE file for license details. If you are unable to locate
 * this file please contact info (at) jgraph (dot) com.
 */
package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.layout.graphics.uml;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.ComponentsBuilder;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
import sk.tuke.fei.kpi.akAgent.integration.alignment.difference.Difference;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Field;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Method;

/**
 * @author MV
 * 
 */
public class ClassPanelRenderer extends JPanel {

	private static final long serialVersionUID = 2106746763664760745L;

	public ClassPanelRenderer(sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Class umlClass, Difference difference) {
		if (umlClass == null) {
			return;
		}
		setLayout(new MigLayout("insets 5", "", "[]0[]0[]"));
		setOpaque(false);

		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setBackground(Color.BLACK);
		separator.setOpaque(true);
		JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
		separator1.setBackground(Color.BLACK);
		separator1.setOpaque(true);

		JLabel classNameLbl = new JLabel(umlClass.getName());
		add(classNameLbl, "wrap,span,center");
		add(separator, "wrap,span,growx");
		add(ComponentsBuilder.createDiagramLabel(Messages.getMessage("title.fields")), "center,span");

		for (Field field : umlClass.getFields()) {
			JLabel fieldLbl = new JLabel();
			fieldLbl.setText(field.getVisibility() + " " + field.getType() + " " + field.getName());

			if (difference.getMissingFields().contains(field)) {
				fieldLbl.setForeground(Color.RED);
			}

			add(fieldLbl, "wrap");
		}
		add(separator1, "wrap,span,growx");
		add(ComponentsBuilder.createDiagramLabel(Messages.getMessage("title.methods")), "center,span");
		for (Method method : umlClass.getMethods()) {
			JLabel methodLbl = new JLabel();
			methodLbl.setText(method.getVisibility() + " " + method.getReturnType() + " " + method.getName());
			if (difference.getMissingMethods().contains(method)) {
				methodLbl.setForeground(Color.RED);
			}

			add(methodLbl, "wrap");
		}

	}

	public static ClassPanelRenderer getVertex(Component component) {
		while (component != null) {
			if (component instanceof ClassPanelRenderer) {
				return (ClassPanelRenderer) component;
			}
			component = component.getParent();
		}

		return null;
	}

}
