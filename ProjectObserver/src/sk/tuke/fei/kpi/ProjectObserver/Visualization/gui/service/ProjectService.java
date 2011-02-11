package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.model.Project;

public class ProjectService {

	private static String userHome = System.getProperty("user.home");

	private static String projectsFolderName = "projectObserver";

	public static void saveProject(Project project) {

		File projectDir = new File(userHome, projectsFolderName);
		if (!projectDir.isDirectory()) {
			projectDir.mkdir();
		}

		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(projectDir + File.separator + project.getName() + project.getCreationDate().getTime() + ".txt");
			out = new ObjectOutputStream(fos);
			out.writeObject(project);
			out.close();
			System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static List<Project> loadProjects() {

		List<Project> projects = new ArrayList<Project>();

		File projectDir = new File(userHome, projectsFolderName);
		if (projectDir.exists() && projectDir.isDirectory()) {

			List<File> files = Arrays.asList(projectDir.listFiles());

			for (File file : files) {

				Project project;

				FileInputStream fis = null;
				ObjectInputStream in = null;
				try {
					fis = new FileInputStream(file);
					in = new ObjectInputStream(fis);
					project = (Project) in.readObject();
					in.close();

					projects.add(project);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}

		return projects;
	}

	public static void deleteProject(Project project) {

		File projectDir = new File(userHome, projectsFolderName);
		if (projectDir.exists() && projectDir.isDirectory()) {

			File f = new File(projectDir, project.getName() + project.getCreationDate().getTime() + ".txt");

			f.delete();

		}
	}

}
