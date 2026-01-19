package ie.roryRoams.fourSquareCipher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoadNSave {

	public String load(String fileName) throws Exception {
		StringBuilder record = new StringBuilder();

		// The (parentheses) here ensure the file is closed automatically
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))))) {

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

	public void save(StringBuilder all, String fileName) throws Exception {
		try {
			FileOutputStream out = new FileOutputStream(new File(fileName));
			String string = all.toString();
			out.write(string.getBytes());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("[ERROR] Cannot save file to " + fileName + ". " + e.getMessage());
		}
	}

}

