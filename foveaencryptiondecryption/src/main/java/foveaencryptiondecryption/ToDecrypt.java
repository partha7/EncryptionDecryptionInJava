package foveaencryptiondecryption;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ToDecrypt {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		String line;
		List<String> lstrings= new ArrayList<String>();
        List<String> encryptedString = new ArrayList<String>();
        List<String> decryptedString = new ArrayList<String>();
        
        Object jo = new JSONParser().parse(new FileReader("KeysToEncrypt.json")); 
        JSONObject res = new JSONObject();
        JSONArray jsonArray = new JSONArray();
         
        JSONObject obj = (JSONObject) jo;
        
        String encryptionDecryptionKey = (String) obj.get("ENCKEY");
	    String ivs = (String) obj.get("ENCIVS");
        
		
		BufferedReader br = new BufferedReader(new FileReader("encryptedValues.txt"));

	    try {
			while((line = br.readLine()) != null) {
			    String[] details = line.split(" ");
			    encryptedString.add(details[1]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
//	    System.out.println(encryptedString);
	    
	    decryptedString = MainEncryptionDecryption.decrypt(encryptedString, encryptionDecryptionKey.getBytes(),
	            ivs.getBytes());
	    
	    
//	    System.out.println(decryptedString);
	    PrintWriter pw = new PrintWriter("decryptedValues.txt"); 
        
        pw.write("KEY0: "+ decryptedString.get(0)+"\n");
        pw.write("KEY1: "+ decryptedString.get(1)+"\n");
        pw.write("KEY2: "+ decryptedString.get(2)+"\n");
        pw.write("KEY3: "+ decryptedString.get(3)+"\n");
        pw.write("KEY4: "+ decryptedString.get(4)+"\n");
          
        System.out.println("Decryption Success");
        
        
        pw.flush(); 
        pw.close(); 
    
	    
	    
	    

	}

}
