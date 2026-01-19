package ie.roryRoams.fourSquareCipher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class URLParser {

	// when someone calls this method they need to feed it a string
	public String fetchURL(String urlPath) throws IOException {

		// new stringbulder
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;

		// i had to use a new method to work with java 20
		URL url = URI.create(urlPath).toURL();

		// 2. new method to try and see if i can get into https pages
		// https://stackoverflow.com/questions/3155488/how-to-read-the-https-page-content-using-java
		// using info from this site along with the notes
		java.net.URLConnection connection = url.openConnection();

		// this line lets the code access https so the cipher can encrypt and decrpt url
		// pages
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");

		// connection.getInputStream gets bytes
		// new InputStreamReader Translates those bytes into letters or chars
		// new BufferedReader loads the chars into sentences

		try {
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line;// just makes a string called line
			while ((line = br.readLine()) != null) {// gets a line from the buffere reader
				sb.append(line).append("\n");// appends that line to string builder sb
			}

		} finally {
			if (br != null) { // if br is not empty
				br.close();// close br
			}

		}
		return sb.toString();
	}
}


