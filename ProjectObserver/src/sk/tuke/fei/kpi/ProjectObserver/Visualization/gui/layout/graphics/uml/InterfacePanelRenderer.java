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
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.util.StringUtil;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Field;
import sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Method;

/**
 * @author MV
 * 
 */
public class InterfacePanelRenderer extends JPanel {

	private static final long serialVersionUID = 2106746763664760745L;

	public InterfacePanelRenderer(sk.tuke.fei.kpi.akAgent.integration.metamodel.uml.classDiagram.Interface umlInterface) {
		if (umlInterface == null) {
			return;
		}
		setLayout(new MigLayout("insets 3", "", "[]0[]0[]"));
		setOpaque(false);

		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setBackground(Color.BLACK);
		separator.setOpaque(true);
		JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
		separator1.setBackground(Color.BLACK);
		separator1.setOpaque(true);

		JLabel classNameLbl = new JLabel(umlInterface.getName());
		add(classNameLbl, "wrap,span,center");

		add(separator, "wrap,span,growx");

		add(ComponentsBuilder.createDiagramLabel(Messages.getMessage("title.fields")), "center,span");
		for (Field field : umlInterface.getFields()) {

			JLabel fieldLbl = new JLabel();
			fieldLbl.setText(field.getVisibility() + " " + field.getType() + " " + field.getName());
			add(fieldLbl, "wrap");
		}
		add(separator1, "wrap,span,growx");
		add(ComponentsBuilder.createDiagramLabel(Messages.getMessage("title.methods")), "center,span");

		for (Method method : umlInterface.getMethods()) {

			JLabel methodLbl = new JLabel();
			methodLbl.setText(StringUtil.convertUMLMethodToString(method));

			add(methodLbl, "wrap");

		}

	}

	public static InterfacePanelRenderer getVertex(Component component) {
		while (component != null) {
			if (component instanceof InterfacePanelRenderer) {
				return (InterfacePanelRenderer) component;
			}
			component = component.getParent();
		}

		return null;
	}

}
