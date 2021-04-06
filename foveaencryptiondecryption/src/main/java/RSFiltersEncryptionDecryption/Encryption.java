package RSFiltersEncryptionDecryption;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

 

public class Encryption {
final static int nbBytesInFloat = Float.SIZE / Byte.SIZE;
    
    public static String encrypt(String strToEncrypt, byte[] key, byte[] ivs) {
        try {
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
          SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
          byte[] finalIvs = new byte[16];
          int len = Math.min(ivs.length, 16);
          System.arraycopy(ivs, 0, finalIvs, 0, len);
          IvParameterSpec ivps = new IvParameterSpec(finalIvs);
          cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivps);
//                String s= Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
            String s= null;
    
            s = Base64.getEncoder().encodeToString(cipher.doFinal((strToEncrypt).getBytes(
                  StandardCharsets.UTF_8)));
          
            return s.trim();
        } catch (Exception e) {
          e.printStackTrace();
        }

 

        return null;
      }
    
    public static String toByteArray(float[] floatArray){
        byte[] result = new byte[floatArray.length * nbBytesInFloat];
        ByteBuffer wrappedBytes = ByteBuffer.wrap(result);
        for(int i=0;i<floatArray.length;i++) {
            wrappedBytes.putFloat(floatArray[i]);
        }
        String res = Base64.getEncoder().encodeToString(result);
        return res;
    }

 


    public static void main(String[] args) {
        
        String key = "x9mB0Kt993aGcup!";
        String ives = "5kst9eu91f";
        
        //Add the smart blur filter value here
        byte[] smartBlurFilter = {
                -28, 74, 93, 62,
                69, 74, 67, 62,
                -122, 56, 6, 62,
                -122, -81, -113,
                61, 16, 16, 16,
                16, 16, 16, 16, 16 
        };
        
        //Add the conv filter value here
        float[] convFilter = {
                -0.026897065f, 0.06701996f, -0.026242135f,
                0.065026864f, 0.8456815f, 0.062914886f,
                -0.027173325f, 0.0665018f, -0.025826963f
        };
        
        String smartBlurString = Base64.getEncoder().encodeToString(smartBlurFilter);
        String convFilterString = toByteArray(convFilter);
        
        String encryptedSmartBlur = encrypt(smartBlurString, key.getBytes(), ives.getBytes());
        System.out.println("Smart blur filter encoded string is:\n" + encryptedSmartBlur);
        
        
        String encryptedConvFilter = encrypt(convFilterString, key.getBytes(), ives.getBytes());
        System.out.println("Conv Coeff filter encoded string is:\n" + encryptedConvFilter);
        
    }
}