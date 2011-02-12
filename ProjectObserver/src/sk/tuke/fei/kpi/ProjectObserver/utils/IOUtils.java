package sk.tuke.fei.kpi.ProjectObserver.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class IOUtils {
	/**
	 * Writes specified text to file
	 * 
	 * @param string
	 *            text
	 * @param filename
	 *            filename
	 */
	public static boolean writeToFile(String string, String filename) {
		try {
			File f = prepareFileForWrite(filename);
			final OutputStream os = new FileOutputStream(f);
			final PrintStream printStream = new PrintStream(os);
			printStream.print(string);
			printStream.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Writes specified bytes to file
	 * 
	 * @param string
	 *            text
	 * @param filename
	 *            filename
	 */
	public static boolean writeToFile(byte[] bytes, String filename) {
		try {
			final OutputStream os = new FileOutputStream(prepareFileForWrite(filename));
			os.write(bytes);
			os.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Reads the file
	 * 
	 * @param filename
	 * @return Text content of file
	 */
	public static String readFile(String filename) {
		try {
			FileReader input = new FileReader(getFile(filename));
			BufferedReader bufRead = new BufferedReader(input);
			StringBuffer sb = new StringBuffer();
			while (bufRead.ready()) {
				sb.append(bufRead.readLine());
			}
			bufRead.close();
			return sb.toString();
		} catch (ArrayIndexOutOfBoundsException e) {
			return "";

		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static byte[] getBytesFromFile(String fileName) {
		try {
			File file = new File(fileName);
			InputStream is = new FileInputStream(file);
			long length = file.length();
			if (length > Integer.MAX_VALUE) {
				System.err.println("File is too large");
			}
			byte[] bytes = new byte[(int) length];

			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}

			// Ensure all the bytes have been read in
			if (offset < bytes.length) {
				throw new IOException("Could not completely read file " + fileName);
			}
			is.close();
			return bytes;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean fileExist(String filename) {
		return new File(HOME_DIR, filename).exists();
	}

	public static File prepareFileForWrite(String filename) {
		try {
			createHomeDirectory();
			File file = getFile(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			return file;
		} catch (Exception ex) {
			return null;
		}
	}

	private static final String HOME_DIR = System.getenv("APPDATA") + File.separator + "ProjectObserver";

	private static void createHomeDirectory() {
		File f = new File(System.getenv("APPDATA"), "ProjectObserver");
		if (!f.exists())
			f.mkdirs();
	}

	public static File getFile(String filename) {
		return new File(HOME_DIR, filename);
	}
}
