package RSFiltersEncryptionDecryption;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Decryption{
	public static float[] decryptconvCoefficient(String strToDecrypt, byte[] key, byte[] ivs) {
        try {
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
          SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
          byte[] finalIvs = new byte[16];
          int len = Math.min(ivs.length, 16);
          System.arraycopy(ivs, 0, finalIvs, 0, len);
          IvParameterSpec ivps = new IvParameterSpec(finalIvs);
          cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivps);
//                String s= new String(cipher.doFinal(Base64.getDecoder().decode(data)));

 

          String s = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
          byte[] byteVal = Base64.getDecoder().decode(s);

 


          return bytesToFloats(byteVal);

 

        } catch (Exception e) {
          e.printStackTrace();
        }

 

        return null;
      }

 

      public static byte[] decryptrenderFilter(String strToDecrypt, byte[] key, byte[] ivs) {
        try {
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
          SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
          byte[] finalIvs = new byte[16];
          int len = Math.min(ivs.length, 16);
          System.arraycopy(ivs, 0, finalIvs, 0, len);
          IvParameterSpec ivps = new IvParameterSpec(finalIvs);
          cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivps);
//                String s= new String(cipher.doFinal(Base64.getDecoder().decode(data)));

 

          String s = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));

 

          return Base64.getDecoder().decode(s);

 

        } catch (Exception e) {
          e.printStackTrace();
        }

 

        return null;
      }

 

      protected static float[] bytesToFloats(byte[] bytes) {
        if (bytes.length % 4 != 0) {
          throw new RuntimeException("Illegal length");
        }
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);
        int n_floats = bytes.length / 4;
        float[] floats = new float[n_floats];
        for (int i=0; i<n_floats; i++) {
          floats[i] =  buffer.getFloat(i * 4);
        }
        return floats;
      }

 

    
    public static void main(String[] args) {
        
        String key = "x9mB0Kt993aGcup!";
        String ives = "5kst9eu91f";
        
        String encryptedSmartBlurFilter = "+jLxlvysJIq3uNUvBk4KJresdLHyn+dm9AIRIR1bJRQ2jqGOBzlseTuElZ6c+x3Y";
        String encryptedConvFilter = "75QpfBf6sMvIp3oCK0wXMlyf+GVlDueZzeu0sgwcVscczUvLN7Uj5pyfZ7tD781bUknA6nQ6EDp+59a1+vheGA==";
        
        byte[] smartBlurFilter = decryptrenderFilter(encryptedSmartBlurFilter, key.getBytes(), ives.getBytes());
        float[] convConef = decryptconvCoefficient(encryptedConvFilter, key.getBytes(), ives.getBytes());
        
        System.out.println("Smart blur filter decoded is:\n" + Arrays.toString(smartBlurFilter));
        System.out.println("Conv Coeff filter encoded string is:\n" + Arrays.toString(convConef));
        
        
    }
}