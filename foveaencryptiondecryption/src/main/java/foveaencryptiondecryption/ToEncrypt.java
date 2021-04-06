package foveaencryptiondecryption;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

import org.json.*;
import org.json.simple.parser.JSONParser;
//import org.json.Pa
import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;


public class ToEncrypt {
	
	public static void main(String[] args) throws Exception  
    { 
        
	    List<String> lstrings= new ArrayList<String>();
        List<String> encryptedString = new ArrayList<String>();
        List<String> decryptedString = new ArrayList<String>();
	    
	    
        Object jo = new JSONParser().parse(new FileReader("KeysToEncrypt.json")); 
        JSONObject res = new JSONObject();
        JSONArray jsonArray = new JSONArray();
         
        JSONObject obj = (JSONObject) jo;
        
        String encryptionDecryptionKey = (String) obj.get("ENCKEY");
	    String ivs = (String) obj.get("ENCIVS");
          
        String accessKey = (String) obj.get("KEY0");
        lstrings.add(accessKey);
        String secretKey = (String) obj.get("KEY1");
        lstrings.add(secretKey);
        String host = (String) obj.get("KEY2");
        lstrings.add(host);
        String uri = (String) obj.get("KEY3");
        lstrings.add(uri);
        String liburl = (String) obj.get("KEY4");
        lstrings.add(liburl);

        encryptedString = MainEncryptionDecryption.encrypt(lstrings, encryptionDecryptionKey.getBytes(),
	            ivs.getBytes());
        
        
        PrintWriter pw = new PrintWriter("encryptedValues.txt"); 
        
        pw.write("KEY0: "+ encryptedString.get(0)+"\n");
        pw.write("KEY1: "+ encryptedString.get(1)+"\n");
        pw.write("KEY2: "+ encryptedString.get(2)+"\n");
        pw.write("KEY3: "+ encryptedString.get(3)+"\n");
        pw.write("KEY4: "+ encryptedString.get(4)+"\n");
          
//        System.out.println(encryptedString);
        System.out.println("Encryption Success");
        
        
        pw.flush(); 
        pw.close(); 
    


}
}

