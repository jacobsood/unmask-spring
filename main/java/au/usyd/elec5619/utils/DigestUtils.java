package au.usyd.elec5619.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 or SHA encryption
 */

public class DigestUtils {
	
	public static final String MD5 = "MD5", SHA1 = "SHA-1", SHA256 = "SHA-256"; 

	public static String digest(String mess, String method) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(method);
			md.update(mess.getBytes());
			byte[] digest = md.digest();

			char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
	                'A', 'B', 'C', 'D', 'E', 'F' };
			char[] result = new char[digest.length * 2]; 
			int k = 0;  
            for (int i = 0; i < digest.length; i++) { 
                byte byte0 = digest[i];
                result[k++] = hexDigits[byte0 >>> 4 & 0xf];
                result[k++] = hexDigits[byte0 & 0xf];
            }  
			
            return new String(result);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static String sha256Digest(String mess) {
		return digest(mess, SHA256);
	}
}
