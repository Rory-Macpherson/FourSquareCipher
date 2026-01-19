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
		
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(fileName))))) {
			
			String line;
			while ((line = br.readLine()) != null) {
				record.append(line).append("\n");
			}	
			}catch (Exception e) {
				e.printStackTrace();
				throw new Exception("[ERROR] Cannot load file from " + fileName + ". " + e.getMessage());
				
			}
			
			
			

		
return record.toString();
		
			}
	
	
	
	public void save(String text, String fileName) throws Exception{
		 try {
			FileOutputStream out = new FileOutputStream(new File(fileName));
			   out.write(text.getBytes());
			   out.close();
		 } catch (IOException e) {
			 e.printStackTrace();
			 throw new Exception("[ERROR] Cannot save file to " + fileName + ". " + e.getMessage());
		 } 	   
    }
	
			
		}
	

	

