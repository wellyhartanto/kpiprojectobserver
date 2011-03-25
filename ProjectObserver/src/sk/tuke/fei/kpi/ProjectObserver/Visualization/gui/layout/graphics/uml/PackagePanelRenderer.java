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
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Interface;
import sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.ComponentsBuilder;
import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.common.Messages;
 
/**
 * @author MV
 * 
 */
public class PackagePanelRenderer extends JPanel {

	private static final long serialVersionUID = 2106746763664760745L;

	public PackagePanelRenderer(sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Package umlPackage) {

		setLayout(new MigLayout("insets 3", "", "[]0[]0[]"));
		setOpaque(false);

		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setBackground(Color.BLACK);
		separator.setOpaque(true);
		JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
		separator1.setBackground(Color.BLACK);
		separator1.setOpaque(true);
		JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
		separator2.setBackground(Color.BLACK);
		separator2.setOpaque(true);

		JLabel classNameLbl = new JLabel(umlPackage.getName());
		add(classNameLbl, "wrap,span,center");

		add(separator, "wrap,span,growx");
		add(ComponentsBuilder.createDiagramLabel(Messages.getMessage("title.packages")), "center,span");

		for (Package packagee : umlPackage.getPackages()) {

			JLabel packageLbl = new JLabel();
			packageLbl.setText(packagee.getName());
			add(packageLbl, "wrap");

		}

		add(separator1, "wrap,span,growx");
		add(ComponentsBuilder.createDiagramLabel(Messages.getMessage("title.interfaces")), "center,span");
		for (Interface interfacee : umlPackage.getInterfaces()) {

			JLabel interfaceLbl = new JLabel();
			interfaceLbl.setText(interfacee.getVisibility() + " " + interfacee.getName());

			add(interfaceLbl, "wrap");

		}

		add(separator2, "wrap,span,growx");
		add(ComponentsBuilder.createDiagramLabel(Messages.getMessage("title.classes")), "center,span");
		for (sk.tuke.fei.kpi.ProjectObserver.Integration.metamodel.uml.classdiagram.Class classs : umlPackage.getClasses()) {

			JLabel classLbl = new JLabel();
			classLbl.setText(classs.getVisibility() + " " + classs.getName());

			add(classLbl, "wrap");

		}
	}

	public static PackagePanelRenderer getVertex(Component component) {
		while (component != null) {
			if (component instanceof PackagePanelRenderer) {
				return (PackagePanelRenderer) component;
			}
			component = component.getParent();
		}

		return null;
	}

}
