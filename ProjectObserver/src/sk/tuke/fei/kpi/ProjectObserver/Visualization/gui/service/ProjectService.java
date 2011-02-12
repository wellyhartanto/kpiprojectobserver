package sk.tuke.fei.kpi.ProjectObserver.Visualization.gui.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import sk.tuke.fei.kpi.ProjectObserver.Integration.Project;


public class ProjectService {

	private static String userHome = System.getProperty("user.home");

	private static String projectsFolderName = "projectObserver";

	private static String projectFileExtension = ".observer";

	public static void saveProject(Project project) {

		File projectDir = new File(userHome, projectsFolderName);
		if (!projectDir.isDirectory()) {
			projectDir.mkdir();
		}

		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(projectDir + File.separator
					+ project.getName() + project.getCreationDate().getTime()
					+ projectFileExtension);
			out = new ObjectOutputStream(fos);
			out.writeObject(project);
			out.close();
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

			File f = new File(projectDir, project.getName()
					+ project.getCreationDate().getTime() + projectFileExtension);

			f.delete();

		}
	}

	public static void exportProject(Project project,File file) {


		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(file + projectFileExtension);
			out = new ObjectOutputStream(fos);
			out.writeObject(project);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	
	public static void importProject(File file) {

		File projectDir = new File(userHome, projectsFolderName);
		if (!projectDir.isDirectory()) {
			projectDir.mkdir();
		}

		File f = new File(projectDir, file.getName());
		
		copyfile(file, f);
		

	}

	 private static  void copyfile(File srFile, File dtFile){
		    try{
		      InputStream in = new FileInputStream(srFile);
		      OutputStream out = new FileOutputStream(dtFile);

		      byte[] buf = new byte[1024];
		      int len;
		      while ((len = in.read(buf)) > 0){
		        out.write(buf, 0, len);
		      }
		      in.close();
		      out.close();
		    }
		    catch(Exception ex){
		    }
		  }
	
}
