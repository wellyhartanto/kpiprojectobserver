package sk.tuke.fei.kpi.akAgent.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
/**
 * Contains method for reading and writing to files.
 *
 */
public class IOUtils {
	/**
	 * Writes specified text to file. If file doesn't exist it will be created.
	 * @param string text to write
	 * @param filename name of file (without path)
	 * @return flag which signalizes whether operation was successful or not
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
	 * Writes bytes in array to specified file
	 * @param bytes file content in byte format
	 * @param filename name of file (without path)
	 * @return flag which signalizes whether operation was successful or not
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
	 * Read text content from file
	 * @param filename name of file (without path)
	 * @return content of file as string
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
	
	/**
	 * Read content of file as byte array
	 * @param pathname pathname
	 * @return content of file
	 */
	public static byte[] getBytesFromFile(String pathname) {
		try {
			File file = new File(pathname);
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
				throw new IOException("Could not completely read file " + pathname);
			}
			is.close();
			return bytes;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Check whether specified file exists in home directory
	 * @param filename name of file (without path)
	 * @return true if file exist
	 */
	public static boolean fileExist(String filename) {
		return new File(HOME_DIR, filename).exists();
	}

	/**
	 * Prepares file to write. If file does not exist the new one is created.
	 * @param filename name of file (without path)
	 * @return file
	 */
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
	
	/**
	 * Application directory in UserName/Aplication Data folder
	 */
	public static final String HOME_DIR = System.getenv("APPDATA") + File.separator + "ProjectObserver";

	/**
	 * Creates application directory
	 */
	private static void createHomeDirectory() {
		File f = new File(System.getenv("APPDATA"), "ProjectObserver");
		if (!f.exists())
			f.mkdirs();
	}

	/**
	 * Gets file in Home directory of application
	 * @param filename name of file (without path)
	 * @return file
	 */
	public static File getFile(String filename) {
		return new File(HOME_DIR, filename);
	}
}
