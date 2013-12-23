package endecryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	//mora postojati public static void main
	
	public static String napraviHash(String input, String hash) throws NoSuchAlgorithmException {
		String rezultat = null;
		switch(hash) {
		case "MD5" : 
			MessageDigest digest2 = MessageDigest.getInstance("MD5");
			digest2.update(input.getBytes());
			rezultat = bytArrayToHex(digest2.digest());
			break;
		case "SHA-1" :
			MessageDigest digest3 = MessageDigest.getInstance("SHA-1");
			digest3.update(input.getBytes());
			rezultat = bytArrayToHex(digest3.digest());
			break;
		}
		return rezultat;
	}
	
	public static String bytArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder();
		for(byte b: a) sb.append(String.format("%02x", b&0xff));
		return sb.toString();
	}
}
