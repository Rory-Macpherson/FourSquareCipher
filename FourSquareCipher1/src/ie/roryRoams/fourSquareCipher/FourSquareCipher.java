package ie.roryRoams.fourSquareCipher;

public class FourSquareCipher {

	// 4 small double arrays
	private char[][] TLeft = new char[5][5], TRight = new char[5][5];
	private char[][] BLeft = new char[5][5], BRight = new char[5][5];

	private boolean paddingAdded = false;
	private boolean badKey = false;

	// CONSTRUCTOR - runs when you create a new FourSquareCipher object
	public FourSquareCipher(String key1, String key2) {
		// Build the keys
		key1 = keyBuilder(key1);
		key2 = keyBuilder(key2);

		// Fill TRight and BLeft with the keys
		for (int i = 0; i < 25; i++) {
			TRight[i / 5][i % 5] = key1.charAt(i);
			BLeft[i / 5][i % 5] = key2.charAt(i);
		}

		// Fill TLeft and BRight with standard alphabet
		int num = 'A';
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (num == 'J') {
					num++;
				}
				TLeft[i][j] = (char) num;
				BRight[i][j] = (char) num;
				num++;
			}
		}

		if(badKey) {
		// Print the grids for testing remove later
		printSquare(TLeft, "Top Left (Standard)");
		printSquare(TRight, "Top Right (Key 1)");
        printSquare(BLeft, "Bottom Left (Key 2)");
        printSquare(BRight, "Bottom Right (Standard)");
	}
	}

	// cipherrunner calls these, and sends them text, then they in turn call and
	// return process
	public String encrypt(String text) {
		return process(text, true);
	}

	public String decrypt(String text) {
		return process(text, false);

	}

	// PRIVATE METHODS - internal logic only

	// This method takes a key word and creates a 25-letter string from it
	// First it puts the key letters (no repeats), then fills in the rest of the
	// alphabet
	// Returns a String that's exactly 25 letters long, A to Z minus J
	private String keyBuilder(String key) {
		// Make the key uppercase, remove any non-letters, and replace J with I
		key = key.toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I');
		StringBuilder result = new StringBuilder();// declares a string builder
		if (key == null || key == "") {
			key = "IDIOTS";//if this happens it means they entered a key with no letters in it.
		badKey = true;
		}
		// add key letters (no duplicates)
		// This loop goes through each letter in the key
		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);
			if (result.toString().indexOf(c) == -1) {// this is just searching the string for 'c'. if there was 1 in
														// there it would come back as 1 and therefore not enter it
														// again
				result.append(c); // then it adds c to the sting. unless its already in there then it does not.
			}
		}

		// add rest of alphabet
		// After adding all the key letters, fill in the missing alphabet letters
		for (char c = 'A'; c <= 'Z'; c++) {// its a simple loop statement
			if (c == 'J')
				continue; // if j is in there, just skip the rest of the for loop
			if (result.indexOf(String.valueOf(c)) == -1) {// searches for the letter. no duplicates
				result.append(c); // adds the results
			}
		}

		// At this point, result has all 25 letters (A-Z minus J) with key letters first
		return result.toString();
	}

	// The main processing method - handles both encrypt and decrypt
	private String process(String text, boolean encryptMode) {
		// Clean letters for processing
		String cleanST = text.toUpperCase().replace('J', 'I').replaceAll("[^A-Z]", "");
		StringBuilder clean = new StringBuilder(cleanST);

		if (encryptMode) {
			paddingAdded = false;
		} // this resets the padding added. its a little messy this removing the x but i
			// hate the extra x

		// If the cleaned text has an odd number of letters, add an X to make it even
		if (clean.length() % 2 != 0) {
			clean.append("X");
			paddingAdded = true; // this means that the cipher runner will remove the extra x if all works well.
		}

		// System.out.println("\nProcessing text: " + clean + " (" + clean.length() + "
		// letters)");

		// This will hold the encrypted/decrypted text
		StringBuilder midText = new StringBuilder();

		// Process the text in pairs of letters
		for (int i = 0; i < clean.length(); i += 2) {
			// Take two letters at a time and transform them using the transform method
			midText.append(transform(clean.charAt(i), clean.charAt(i + 1), encryptMode));
		}

		// If we are decrypting AND the switch is ON
		if (!encryptMode && this.paddingAdded) {
			if (midText.length() > 0) {
				midText.setLength(midText.length() - 1); // Remove the padding
			}
			this.paddingAdded = false; // FLIP THE SWITCH OFF IMMEDIATELY
		}

		// Merge with original punctuation/spaces
		// This part puts the spaces and punctuation back where they were in the
		// original text
		StringBuilder finalText = new StringBuilder();
		int letterIndex = 0; // Keeps track of the midtext

		// Go through the original text character by character
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i); // grabs every char in the original text
			// If it's a letter in the original, replace it with a transformed letter
			if (Character.isLetter(c)) { // if character c is a letter
				if (letterIndex < midText.length()) {
					finalText.append(midText.charAt(letterIndex++));
				}
			} else {
				// If it's not a letter just add it to the end.
				finalText.append(c);
			}
		}

		// Append the last letter if there is one
		while (letterIndex < midText.length()) {
			finalText.append(midText.charAt(letterIndex++));
		}

		return finalText.toString();
	}

	// Parameters: a and b are the two letters to transform, its a lot and a bit
	// messy, but works so far
	// encryptMode: true = encrypt, false = decrypt, when you call it you have to
	// also feed in a boolean
	private String transform(char a, char b, boolean encryptMode) {
		if (encryptMode) {
			// Encrypt
			// We need to find where letters 'a' and 'b' are in their respective grids
			int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
			// Search through all 5 rows and 5 columns
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					// Find where 'a' is located in the TLeft grid
					if (TLeft[i][j] == a) {
						row1 = i;
						col1 = j;
					}
					// Find where 'b' is located in the BRight grid
					if (BRight[i][j] == b) {
						row2 = i;
						col2 = j;
					}
				}
			}
			// The encrypted pair is formed by:
			// Taking the letter from TRight at (rowA, colB)
			// Taking the letter from BLeft at (rowB, colA)
			return "" + TRight[row1][col2] + BLeft[row2][col1];// you need to add the "" otherwise the thing crashes, it
																// forces it to be a string.
			// when we return it we mix the rows and cols. confusing but once you keep the
			// same pattern it works out
		} else {
			// Decrypt
			// Same idea as encrypt, but we look in different grids
			int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					// Find where a is in the TRight grid
					if (TRight[i][j] == a) {
						row1 = i;
						col1 = j;
					}
					// Find where b is in the BLeft grid
					if (BLeft[i][j] == b) {
						row2 = i;
						col2 = j;
					}
				}
			}
			// The decrypted pair is formed by reversing the encryption process:
			// Taking the letter from TLeft, the normal filled one with no key
			// Taking the letter from BRight
			return "" + TLeft[row1][col2] + BRight[row2][col1];
		}
	}

	// This method prints out a 5x5 grid to the console so you can see what's in it
	// Parameters: square = the grid to print, name = what to call it when printing
   private void printSquare(char[][] square, String name) {
 System.out.println("\n" + name);// \n means pressing the enter button or new
 System.out.println("-----------------");
// Loop through each row 
	 for (int i = 0; i < 5; i++) {
	// // Loop through each column
           for (int j = 0; j < 5; j++) {
              //  Print each character followed by a space
               System.out.print(square[i][j] + " ");
           }
            System.out.println(); 
       }
    } 

}
