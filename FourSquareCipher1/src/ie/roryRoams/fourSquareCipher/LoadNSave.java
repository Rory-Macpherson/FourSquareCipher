package ie.roryRoams.fourSquareCipher;

//imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//public class so that other classes can see it
public class LoadNSave {

	// a public method save, its public so that other classes can call it.
	// when its called you must provide a string that we call fileName
	// it throws an exception away.
	// a stringbuilder that is a string that can be changed.
	// the stringbuilder is called record
	public String load(String fileName) throws Exception {
		StringBuilder record = new StringBuilder();

		// inside the the method we have a try -catch. so the things in the brackets
		// this means as soon as the try method is finished the buffered reader
		// and the rest are closed.
		// i got this from
		// https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
		// br is a bufferedreader. this is a bucked and it filles up intil it has a
		// sentence
		// new file opens a file
		// new fileinputstream gets a stream of bytes.
		// inputstream reader converts them to chars.
		// then br into sentences.

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))))) {

			// new string called line
			// br.readline is a method from the buffered reader class than
			// reads a line until the line is done. so while line = br.readline and is not
			// empty
			// append the line onto record which is a stringbuilder, then it appens /n which
			// is
			// next line
			String line;
			while ((line = br.readLine()) != null) {
				record.append(line).append("\n");
			}
		} catch (IOException e) {
			// Rethrow with your custom message for the CipherMenu to catch
			throw new Exception("[ERROR] Could not read " + fileName);
		}

		return record.toString();
	}

	// a method called save which needs two things when you call it, a stringbuilder
	// and a string
	// we have a try method that writes the content to a file that the user says.
	// if the file exists it will be over written, if it does not exist it will
	// be created.
	// so new file creates a new file, file output stream makes a connection to fill
	// that
	// file.
	// string converts all to a string. and then to bytes and then the ouput stream
	// sends them to the file.
	// then close the output stream. important
	// then an io exception from the exceptions class

	public void save(StringBuilder all, String fileName, boolean append) throws Exception {
		try {
			FileOutputStream out = new FileOutputStream(new File(fileName), append);
			String string = all.toString();
			out.write(string.getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("[ERROR] Cannot save file to " + fileName + ". " + e.getMessage());
		}
	}

}
