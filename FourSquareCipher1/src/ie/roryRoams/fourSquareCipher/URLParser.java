package ie.roryRoams.fourSquareCipher;

//lots of imports to access the internet 
//and to be a buffered reader

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class URLParser {

	// when someone calls this method they need to feed it a string
	// it throws an exception away
	public String fetchURL(String urlPath) throws IOException {

		// new stringbuilder, and initialise
		StringBuilder sb = new StringBuilder();

		// new buffered reader but its not initilised its just out here so
		// i can access it out of loops. also i need it to be nulled otherwise it would
		// keep
		// adding and adding.
		BufferedReader br = null;

		// 2. new method to try and see if i can get into https pages
		// https://stackoverflow.com/questions/3155488/how-to-read-the-https-page-content-using-java
		// using info from this site along with the notes
		// i had to use a new method to work with java 20
		// as the "newURL(string)" has been depreceated.
		// so it takes the string that is fed from the menu class from the user
		// and converts it into a uri which basically makes sure its a valid url
		// then the url converts that uri to a url.
		// so now uri is an onject that we can use to connect to the internet but we
		// have no connection now
		URI uri = URI.create(urlPath);
		URL url = uri.toURL();

		// this line makes the connection ready to be used, open connection is a method
		// in the URL class
		// so connection is now a object of the url connection class and it calls
		// url to openconnection which is a method of the url class.
		java.net.URLConnection connection = url.openConnection();

		// this line lets the code access https so the cipher can encrypt and decrpt url
		// pages. setrequestproperty is a method of the urlconnection class.
		// it needs a key and a value and with this here we can visit way more pages
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");

		// we have a try - finaly block.
		// so the br is a buffered reader
		// connection has the value and the key and the address
		// get input stream opens the connection and reads it by the bytes
		// input stream reader gets it by the chars.
		// this makes it much easier to read as apposed to byte by byte.
		try {
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line;// just makes a string called line
			while ((line = br.readLine()) != null) {// gets a line from the buffere reader
				sb.append(line).append("\n");// appends that line to string builder sb
			}

		} finally {
			if (br != null) { // if br is not empty, so after the while loop is finished,
				// we close the buffer reader so the connections close.
				br.close();// close br
			}

		}
		return sb.toString();// returns a huge string
	}
}
