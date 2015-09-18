package weibo4j.wang;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Tool for file handling, such as delete, read, etc.
 * 
 * @author Carl
 * @6:36:15 PM @Sep 9, 2009
 * @GGLL
 */
public class FileHandler {

	/**
	 * Read the content from a txt file.
	 * 
	 * @param file
	 * @return the content of txt file
	 * @throws Exception
	 */
	public static String readTXT(File file, String encoding) throws IOException {
		// ...checks on file are elided
		StringBuilder contents = new StringBuilder();
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader in = new InputStreamReader(fis, encoding);
		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(in);
			try {
				String line = null; // not declared within while loop
				/*
				 * readLine is a bit quirky : it returns the content of a line
				 * MINUS the newline. it returns null only for the END of the
				 * stream. it returns an empty String if two newlines appear in
				 * a row.
				 */
				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			P.pr(ex.getMessage());
		}
		fis.close();
		in.close();

		return contents.toString();
	}
	
	public static String readHeadTXT(File file, String encoding, int lines) throws IOException {
		// ...checks on file are elided
		StringBuilder contents = new StringBuilder();
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader in = new InputStreamReader(fis, encoding);
		int lines_read = 0;
		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(in);
			try {
				String line = null; // not declared within while loop
				/*
				 * readLine is a bit quirky : it returns the content of a line
				 * MINUS the newline. it returns null only for the END of the
				 * stream. it returns an empty String if two newlines appear in
				 * a row.
				 */
				while ((line = input.readLine()) != null && lines_read++ < lines) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			P.pr(ex.getMessage());
		}
		fis.close();
		in.close();

		return contents.toString();
	}
	
	public static String readHeadTXT(String fileName, int lines) throws IOException {
		File file2Read = checkFile(fileName, false);
		return readHeadTXT(file2Read, Constants.ENCODE_DEFAULT, lines);
	}

	/**
	 * Read a file with default enconding.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String readTXT(String fileName) throws IOException {
		return readTXT(fileName, Constants.ENCODE_DEFAULT);
	}

	/**
	 * Read a file by the name
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String readTXT(String fileName, String encoding)
			throws IOException {
		File file2Read = checkFile(fileName, false);
		String content = readTXT(file2Read, encoding);
		return content;
	}

	/**
	 * Save some text to a txt file.
	 * 
	 * @param text
	 * @param fileName
	 * @throws IOException
	 */
	public static void saveText2Txt(String text, String fileName, boolean renew)
			throws IOException {
		File outputFile = checkFile(fileName, renew);
		FileWriter fstream = new FileWriter(outputFile);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(text);
		// Close the output stream
		out.flush();
		out.close();
		fstream.close();
	}

	public static void saveList2Txt(List<String> text, String fileName, boolean renew)
			throws IOException {
		File outputFile = checkFile(fileName, renew);
		FileWriter fstream = new FileWriter(outputFile);
		BufferedWriter out = new BufferedWriter(fstream);
		for (String line: text) {
			out.write(line);
		}
		// Close the output stream
		out.flush();
		out.close();
		fstream.close();
	}

	/**
	 * Append text to a text file.
	 * 
	 * @param fileName
	 * @param text
	 */
	public static void appText2File(String fileName, String text) {
		if (fileName.indexOf(System.getProperty("file.separator")) < 0) {
			fileName = Constants.TXTFILEPATH + fileName;
		}
		//P.pl(fileName);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fileName, true));
			bw.write(text);
			//bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ex) {
					P.pr("Appending text to file " + fileName + " fails!!"
							+ " - " + ex.getMessage());
				}
			}
		}
	}

	/**
	 * If want to renew the file.
	 * 
	 * @param fileName
	 * @param text
	 * @param reNew
	 */
	public static void appText2File(String fileName, String text, boolean reNew) {
		if (fileName.indexOf(System.getProperty("file.separator")) < 0) {
			fileName = Constants.TXTFILEPATH + fileName;
		}
		//P.pl(fileName);
		if (reNew) {
			File file = new File(fileName);
			if (file.exists()) {
				if (file.canWrite()) {
					file.delete();
				}
			}
			file = null;
		}
		appText2File(fileName, text);
	}

	/**
	 * Given file name, if exists, delete it and create a new one.
	 * 
	 * @param fileName
	 * @return the file
	 * @throws IOException
	 */
	public static File checkFile(String fileName, boolean renew)
			throws IOException {
		// If the input file name is not the whole path, set it to default
		// directory
		if (fileName.indexOf("\\") == -1 && fileName.indexOf("/") == -1) {
			fileName = Constants.TXTFILEPATH + fileName;
		}
		File file = new File(fileName);
		if (renew) {
			if (file.exists()) {
				if (file.canWrite()) {
					file.delete();
				} else {
					throw new IOException("file " + fileName
							+ " can not be written!");
				}
			} else {
				boolean success = file.createNewFile();
				if (success) {
					System.out.println("Created the file: " + file.getName());
				} else {
					System.err.println("Failed to create file: "
							+ file.getName());
				}
			}

		} else {
			if (file.exists()) {
				return file;
			} else {
				throw new IOException("No such file: " + fileName);
			}
		}
		return file;
	}

	public static boolean removeFile(String fileName) throws IOException {
		if (fileName.indexOf("\\") == -1 && fileName.indexOf("/") == -1) {
			fileName = Constants.TXTFILEPATH + fileName;
		}
		File file = new File(fileName);
		if (file.exists()) {
			if (file.canWrite()) {
				file.delete();
			} else {
				throw new IOException("file " + fileName
						+ " can not be written!");
			}
		} else {
			System.out.println("No file found: " + fileName);
		}
		return true;
	}

	/**
	 * Create a directory
	 * 
	 * @param path
	 */
	public static void createDir(String path) {
		File toDelete;
		if ((path == null) || path.equals("")) {
			return;
		} else {
			File file = new File(path);
			if (!(file.isDirectory())) {
				file.mkdir();
			}
			if (file.exists() && file.isDirectory()) {
				String[] files = file.list();
				// delete all files under the dir
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						toDelete = new File(files[i]);
						if (toDelete.canWrite()) {
							toDelete.delete();
						}
					}
				}
			}
		}
	}

	/**
	 * Get a file. If the file exists, delete it and recreate a new one.
	 * 
	 * @param fileName
	 * @return the file
	 * @throws IOException
	 */
	public static File getFile(String fileName) throws IOException {
		File outputFile = new File(fileName);

		if (outputFile.exists()) {
			if (outputFile.canWrite()) {
				outputFile.delete();
			} else {
				throw new IOException("file " + fileName
						+ " can not be written!");
			}
		}

		// Create the file
		boolean success = outputFile.createNewFile();

		if (success) {
			System.out.println("Created the file: " + outputFile.getName());
		} else {
			System.err
					.println("Failed to create file: " + outputFile.getName());
		}

		return outputFile;
	}

	/**
	 * Get all files in the given directory.
	 * 
	 * @param directory
	 * @return
	 */
	public static String[] getFilesInDir(String directory) {
		File dir = new File(directory);
		if (!(dir.isDirectory())) {
			dir.mkdir();
		}
		if (dir.exists() && dir.isDirectory()) {
			String[] files = dir.list();
			String[] filesPath = new String[files.length];
			for (int i = 0; i < files.length; i++) {
				filesPath[i] = directory + "/" + files[i];
			}
			return filesPath;
		} else {
			return null;
		}
	}

	/**
	 * Check whether the input path is a file or a directory.
	 * 
	 * @param path
	 * @return
	 */
	public static int isFileOrDir(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return -1;
		}
		if (file.isDirectory()) {
			return 0;
		} else {
			return 1;
		}
	}
}
