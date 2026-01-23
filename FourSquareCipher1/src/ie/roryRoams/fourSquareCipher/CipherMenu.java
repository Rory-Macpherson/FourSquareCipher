package ie.roryRoams.fourSquareCipher;

import static java.lang.System.out;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

//this is the name of the class and its public so that
//any class can call it.
//this is the base of operations for my program.
//scanner is private so only this class can use it and its 
//basically a keyboard reader. if you type a number not a letter it knows!
//i made all these vars private as they are only needed in this class. no need for 
//another class to make my keeprunning false.. 

public class CipherMenu {
	private Scanner s = new Scanner(System.in);
	private String decryptedText;
	private StringBuilder all;
	private boolean keepRunning = true;
	private FourSquareCipher cipher;
	private LoadNSave lNs = new LoadNSave();
	private String message;

//this is a method called start, and while(the keep running boolean) is true
	// this method will keep running.
	// the show options is loading up a different method that just shows a menu to
	// the user
	// then when the user makes a choice and presses a button. lets say 1
	// int choice saved the ouput, but thats the easy bit, integer is a class
	// so integer.parseint is a method in that class, it it litrally reads
	// the input and converts it into an int.
	// then we have the s.nextline. so method from the scanner class.
	// it grabs the whole line the user types before they press enter and
	// converts it to a string. i had nextInt before but do not recommend..
	// i changed it to message so i can feed the user back their own input if it
	// wasnt a number
	// then the switch. it used what ever the int is from choice.
	// basicaly its an if the press 1 do this
	// if - else do that
	// if-else do that. but its written much neater. yay for switch() not a class or
	// a method
	// just a handy built in java keyword.
	// default is what happens if the user gives an int thats not an option like 42.
	// then if the user gives an option of "apples" java throws a hissy not a number
	// fit
	// which the catch statement catches and calmly tells the user to cop on.
	// exception is a java class, basically java makes an object when there is an
	// exception.
	// i name it e and i have it. i do nothing with it ... but i have it
	// continue basically just lets the user go back to the menu. gluck breaking it
	// the out is short for sysetemout. and it only runs after the try and while
	// loop finish
	// which means hopefully only when the user hits 3.
	public void start() {
		while (keepRunning) {
			try {
				showOptions();
				message = s.nextLine();
				int choice = Integer.parseInt(message);
				switch (choice) {
				case 1 -> begin();
				case 2 -> findOutMore();
				case 3 -> keepRunning = false;
				default -> out.println("[Error] Invalid Selection");

				}
			} catch (Exception e) {
				out.println("please enter a valid number " + message + " is not a valid number.");
				continue;
			}

		}

		out.println("[Info] Exiting Rory's Four Square Cipher");
		System.out.println(ConsoleColour.RESET);
	}
	
	// Standardized Header Method to keep things clean and consistent
	private void printHeader() {
		System.out.println(ConsoleColour.CYAN_BRIGHT);
		out.println("***************************************");
		out.println("** Rory's Four Square Cipher      **");
		out.println("***************************************");
	}

	// show options method
	private void showOptions() {
		printHeader();
		out.println("(1) Start");
		out.println("(2) Learn how to use the Four Square Cipher ");
		out.println("(3) Quit");
		out.println(); // spacer
		out.print("Select an option [1-3]>");
	}

	// findout more method
	// usefull for people to get a basic understanding of what the program is and
	// how to use it
	private void findOutMore() {
		out.println("--- HOW THE FOUR-SQUARE CIPHER WORKS ---");
		out.println("this is a key cipher that splits text into pairs.");
		out.println("It uses four 5x5 grids: two standard alphabets and two filled with keys.");
		out.println("These key squares use keys provided to fill them so they are random.");
		out.println("then it finds the intersection of the pair of letters");
		out.println("matches them onto the other 2 squares and feeds it back.");
		out.println("this creates a code that is very hard to break.");
		out.println("--- THE FOUR SQUARE LAYOUT EXAMPLE ---");
		out.println("   Top Left (Standard)          Top Right (Key 1)");
		out.println("   -------------------          -----------------");
		out.println("   A B C D E                    W P T U E");
		out.println("   F G H I K                    K V Z O M");
		out.println("   L M N O P                    Y N Q X C");
		out.println("   Q R S T U                    D G R H A");
		out.println("   V W X Y Z                    L F S B I");
		out.println("   Bottom Left (Key 2)          Bottom Right (Standard)");
		out.println("   -------------------          -----------------------");
		out.println("   D O C V L                    A B C D E");
		out.println("   I Q H X M                    F G H I K");
		out.println("   G N S P R                    L M N O P");
		out.println("   W U B K Z                    Q R S T U");
		out.println("   Y T F A E                    V W X Y Z");

		out.println("--- USER INSTRUCTIONS ---");
		out.println("1. KEYS: Choose to enter your own keywords or generate random ones.");
		out.println("2. URLS: Enter the full address, e.g., https://www.google.com");
		out.println("   Note: URLs MUST start with 'https' to be reached.");
		out.println("3. FILES: Provide the exact name, e.g., example.txt");
		out.println("   The file must be located in the main project folder.");
		out.println("4. Text: enter the text you want to encrypt. only letters will be encrypted");
		out.println("   All other charcaters will stay the same.");
		out.println("5. SAVING: Always include an extension like '.txt' when naming your output.");
		out.println("6. RESTART: Use option 'Start Again' at any menu to clear memory and start fresh.\n");
		out.println("");
		out.println("");
		out.println("");
		out.println("Press Enter to go back to menu");
		s.nextLine();

	}

	// ok if the user hit start then they arrive here and and get shown key choice
	// which is another menu. this is basically just the same as the first menu
	// the new and cool bit is the 3 3 does 2 things. it calls a reset method and
	// returns
	// the user back to the start menu. the reset is pointless here actually tbh but
	// usefull
	// later i prommise. the if cipher == null is a usefull trick. i will explain in
	// reset.
	private void begin() {
		while (keepRunning) {
			keyChoice();
			try {
				int choice = Integer.parseInt(s.nextLine());
				switch (choice) {
				case 1 -> setKeys();
				case 2 -> generatekeys();
				case 3 -> {
					reset();
					return; // This exits the Key menu and lands you back in start
				}
				default -> out.println("[Error] Invalid Selection");

				}
				if (cipher == null)
					return;

			} catch (Exception e) {
				out.println("please enter a valid number.");
				continue;
			}
		}
	}

	// method keychoice. just more printing menus

	private void keyChoice() {
		printHeader();
		out.println("Would you like to add your own keys?");
		out.println("Or have them generated by the program? **");
		out.println("-------------------------------------------");
		out.println("(1) Enter your own keys");
		out.println("(2) Generate keys");
		out.println("(3) Start Again");
		out.println();
		out.print("Select an option [1-3]>");

	}

	// important method for setting keys. basic but critical, this will take
	// anything but it does warn the user it wants at least one letter per key
	// otherwise the program prints a message thats kinda hidden for the user.
	// again note the if cipher is void.
	// this method calls the ciphef class and feeds it two keys. making the cipher
	// not void

	private void setKeys() {
		printHeader();
		System.out.println("Enter key 1 and make sure it contains at least one letter.");
		System.out.print("Enter Key 1 -> ");
		String key1 = s.nextLine();
		
		System.out.println("Enter key 2 and please make sure it contains at least one letter. ");
		System.out.print("Enter Key 2 -> ");
		String key2 = s.nextLine();
		cipher = new FourSquareCipher(key1, key2);
		pickEncrypt();
		if (cipher == null)
			return;

	}

	// this is a method that calls another method called generate keys
	// and then feeds these keys into the cipher. making the cipher != null

	private void generatekeys() {

		String key1 = generatekey();
		String key2 = generatekey();

		cipher = new FourSquareCipher(key1, key2);
		pickEncrypt();

		if (cipher == null)
			return;
	}

	// so random is a class in java and i make a new random called random
	// i make a char array that is 25 chars long and i hard code them in
	// all letters less j. then i use a for loop and go down from the
	// last letter in the array to the first.
	// so i starts at 24 and moves down.
	// int j = a number between 24 and 0.
	// then it swaps the letter thats in position i (z) with the random number.
	// using the shuffle yates from https://www.youtube.com/watch?v=-JZVfi4u8PA
	// and
	// https://www.geeksforgeeks.org/dsa/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
	private String generatekey() {
		char[] arr = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z' };
		Random random = new Random();

		for (int i = arr.length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			char temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}

		return new String(arr);

	}

	// another method that calls a method thats just a menu
	// nothing new
	private void pickEncrypt() {
		while (keepRunning) {
			URLorFILE();
			try {
				int choice = Integer.parseInt(s.nextLine());

				switch (choice) {
				case 1 -> urlEncrypt();
				case 2 -> fileEncrypt();
				case 3 -> textEncrypt();
				case 4 -> {
					reset(); // resets all the variables in the menu
					return; // this goes back to menu
				}
				default -> out.println("[Error] Invalid Selection");

				}
				if (cipher == null)
					return;

			} catch (Exception e) {
				out.println("please enter a valid number.");
				continue;
			}
		}
	}

	private void textEncrypt() {
		String pt;
		printHeader();
		System.out.println("Please type in the text you want to encrypt and decrypt. ");
		System.out.println("Then press enter to start the process. ");
		System.out.println(ConsoleColour.BLUE_BOLD_BRIGHT);
		System.out.println("NOTE: When the program starts compiling please do not press any button"); 
		System.out.println(ConsoleColour.CYAN_BRIGHT);
		System.out.print("Please enter your text here >");
		pt = s.nextLine();
		
		if(pt.isBlank() || pt == null) {
			pt = "you did not enter any text yet the program stays on!!!";
		}
		append(pt);
	}

	// method that is just a menu
	private void URLorFILE() {
		printHeader();
		out.println("(1) To chose a url to encrypt ");
		out.println("(2) To chose a text file to encrypt ");
		out.println("(3) To enter some text to encrypt ");
		out.println("(4) Start Again");
		out.println();
		out.print("Select an option [1-4] ->");

	}

	// ok so we reset all which is a stringbuilder, we do this by calling a new
	// string builder
	// that is overwriting the all we have at the top. if we say all = null. it
	// crashes.....
	// then declare a string called pt. its only in this method.
	// we call my class url parser and name it search
	// print out a message and read the user input
	// we use the search.fetch url method in the url parser class to search a
	// webpage
	// using the input from the user
	// then we call cipher which is our four square cipher class
	// and we ask it to encrypt the txt from the url class
	// then we decrypt it
	// then we append both encrypted and decrypted to a sb called all
	// then we call the save method
	// we have a catch it there is an issue. its normally a bad url tbh
	private void urlEncrypt() {
		try {
			String pt;
			URLParser search = new URLParser();
			
			printHeader();
			System.out.println("Please enter a full URL (must start with HTTPS).");
			System.out.println(ConsoleColour.BLUE_BOLD_BRIGHT);
			System.out.println("when the program starts compiling please do not press any button"); 
			System.out.println(ConsoleColour.CYAN_BRIGHT);
			System.out.print("Please enter a URL here >");
			pt = s.nextLine();
			
			String midtext = search.fetchURL(pt);
			append(midtext);
		} catch (Exception e) {
			System.out.println(ConsoleColour.RED_BOLD);
			System.out.println("");
			System.out.println("Could not load URL");
			System.out.println(ConsoleColour.CYAN_BRIGHT);
			return; // go back to menu
		}

	}

	// same as method above apart from you grab a file not a url.
	// we have a if statement that basically says that if the user enters nothing
	// then return to the last menu.
	// here we have to call our laodnsave class. we called it lNs.
	// we call thelaod method in that class and feed it the user input
	// it does its magic and we call the file in string form encrypted text.
	// then we call the cipher and encypt and decrypt and then call the save
	// function
	private void fileEncrypt() {
		String pt;
		String loadedText;
		printHeader();
		System.out.println("Please enter a text file from the files in your project, ");
		System.out.println("make sure it is the full text file name with the .txt ");
		System.out.println("or what ever extention is on it ");
		System.out.println(ConsoleColour.BLUE_BOLD_BRIGHT);
		System.out.println("when the program starts compiling please do not press any button"); 
		System.out.println(ConsoleColour.CYAN_BRIGHT);
		System.out.print("Enter file Name here >");
		pt = s.nextLine();

		if (pt.trim().isEmpty()) {
			out.println("[Error] No filename entered.");
			return;
		}

		try {

			loadedText = lNs.load(pt);
			append(loadedText);
			

		} catch (Exception e) {
			out.println("");
			System.out.println(ConsoleColour.RED_BOLD);
			out.println("[Error]: Could not find or read '" + pt + "'.");
			out.println("Make sure the file is in the directory folder and the name is correct.");
			out.println("make sure to include the extension example: .txt");
			System.out.println(ConsoleColour.CYAN_BRIGHT);
		}

	}

	// simple method that calls the save method from the loadnsave class
	// if the user enters nothing it sends them back to the last menu.
	// if it all worked the user get a message saying it worked and
	// a prompt to press enter to go back to the start
	// added in a new try catch block to make warn the user if they are overwriting
	// a file.
	// it checks if the file exists, if it is, it warns the user and they can choose
	// to overwrite
	// the file or go back.
	// https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java
	
private void append(String text) {
	all = new StringBuilder();
	String encrypted = cipher.encrypt(text);
	all.append("\n--- ENCRYPTED ---\n");
	all.append(encrypted);
	decryptedText = cipher.decrypt(encrypted);
	all.append("\n\n"); // Add two enters for spacing
	all.append("\n--- DECRYPTED ---\n");
	all.append(decryptedText);
	all.append("\n\n");
	save();

	}

	private void save() {
		String pt;

		printHeader();
		System.out.println("Please enter a filename to save your file (e.g. testing.txt).");
		System.out.println("Do not enter a full system path (absolute path).");
		System.out.println("To return, leave it blank and press enter!.");
		System.out.print("Please enter file name here ->");
		
		pt = s.nextLine();

		if (pt.trim().isEmpty() || decryptedText == null) {
			System.out.println(ConsoleColour.RED_BOLD);
			System.out.println("[Error] Save cancelled or no text to save.");
			System.out.println(ConsoleColour.CYAN_BRIGHT);
			return;
		}
		System.out.println();
		File f = new File(pt);
		boolean appendMode = false;
		if (f.exists() && !f.isDirectory()) {
			System.out.println(ConsoleColour.RED_BOLD);
			System.out.println("Warning: File '" + pt + "' already exists!");
			System.out.println("(1) Overwrite the file");
			System.out.println("(2) Append to the file"); // NEW OPTION
			System.out.println("(3) Start Again");
			out.println("Select an option [1-3]>");

			try {
				int choice = Integer.parseInt(s.nextLine());
				if (choice == 3) {
					System.out.println("Save cancelled.");
					System.out.println(ConsoleColour.CYAN_BRIGHT);
					return;
				} else if (choice == 2) {
		            appendMode = true; // Set to true if they pick append
				} else if (choice != 1) {
					System.out.println("[Error] Invalid selection. Save cancelled.");
					return;
				}

			} catch (Exception e) {
				System.out.println("[Error] Invalid input. Save cancelled.");
				return;
			}
		}
		System.out.println(ConsoleColour.CYAN_BRIGHT);

		try {
			lNs.save(all, pt, appendMode);
			printHeader();
			System.out.println("File " + pt + " saved");
			System.out.println(ConsoleColour.BLUE_BOLD_BRIGHT);
			System.out.println("Saved file to: " + System.getProperty("user.dir"));
			System.out.println(ConsoleColour.CYAN_BRIGHT);
			System.out.print("Please press enter to go back to menu ->");
			s.nextLine();
			reset();
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	// this is what sends the user back to the start menu if they start again from
	// any
	// menu.
	// it just resets this.cipher. so no keys. other menus see that and also return.
	private void reset() {
		this.decryptedText = "";
		this.cipher = null;
		this.all = null;
		out.println();
		out.println("Returning to Main Menu.....");
		out.println();

	}

}
