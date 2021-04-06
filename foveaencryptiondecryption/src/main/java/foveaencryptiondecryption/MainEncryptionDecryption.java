package foveaencryptiondecryption;



import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class MainEncryptionDecryption {
	
		
	
	public static List<String> encrypt(List<String> strToEncrypt, byte[] key, byte[] ivs) {
	    try {
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
	        byte[] finalIvs = new byte[16];
	        int len = ivs.length > 16 ? 16 : ivs.length;
	        System.arraycopy(ivs, 0, finalIvs, 0, len);
	        IvParameterSpec ivps = new IvParameterSpec(finalIvs);
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivps);
//	        String s= Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
	        List<String> encstrings= new ArrayList<String>();
            for(int i=0;i<strToEncrypt.size();i++) {
            	String s= Base64.getEncoder().encodeToString(cipher.doFinal((strToEncrypt.get(i)).getBytes("UTF-8")));
            	encstrings.add(s.trim());
            }
            
            return encstrings;
//	        return s;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}

	public static List<String> decrypt(List<String> strToDecrypt, byte[] key, byte[] ivs) {
	    try {
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
	        byte[] finalIvs = new byte[16];
	        int len = ivs.length > 16 ? 16 : ivs.length;
	        System.arraycopy(ivs, 0, finalIvs, 0, len);
	        IvParameterSpec ivps = new IvParameterSpec(finalIvs);
	        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivps);
//	        String s= new String(cipher.doFinal(Base64.getDecoder().decode(data)));
	        List<String> decstrings= new ArrayList<String>();
            for(int i=0;i<strToDecrypt.size();i++) {
            	String s= new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt.get(i))));
            	decstrings.add(s);
            }
            
            return decstrings;
//	        return s;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}

}
