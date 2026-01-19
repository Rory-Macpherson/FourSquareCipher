package ie.roryRoams.fourSquareCipher;

import java.util.Scanner;


public class CipherRunner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        LoadNSave rw = new LoadNSave();
        
        //System.out.println("Enter plaintext: ");
       // String pt = scan.nextLine();
        
       // System.out.println("Enter key 1: ");
       // String key1 = scan.nextLine();
        
       // System.out.println("Enter key 2: ");
      //  String key2 = scan.nextLine();
        
        // Create cipher with keys
        //FourSquareCipher cipher = new FourSquareCipher(key1, key2);
        FourSquareCipher cipher = new FourSquareCipher("low", "high");
        URLParser search = new URLParser();
        
        // Encrypt
        //String encrypted = cipher.encrypt(pt);
        String all = new String();
        String pt;
        pt = "https://en.wikipedia.org/wiki/Cryptography";
		//try {
		//	pt = rw.load("1984Orwell.txt");
	//	} catch (Exception e) {
			
			//e.printStackTrace();
			//scan.close();
		//	return;
		//}
       // String encrypted = cipher.encrypt(pt);
		String midtext = search.fetchURL(pt);
		String encrypted = cipher.encrypt(midtext);
        all += encrypted + "\n--FIND ME SO YOU CAN CHECK--\n";
      //  System.out.println("\nEncrypted text: " + encrypted);
        
        // Decrypt
      String decrypted = cipher.decrypt(encrypted);
      all += decrypted;
        System.out.println("Decrypted text: " + decrypted);
        
        scan.close();
        
        
try {
	rw.save(all, "testing.txt");
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
        
    }
}