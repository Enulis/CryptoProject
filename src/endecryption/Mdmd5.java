package endecryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Mdmd5 {

	/** Sazimanje poruka pomocu MD5 i SHA-1
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		System.out.println("Unesite poruku od koje zelite napraviti sazetak!");
		Scanner cs = new Scanner(System.in);
		String input = cs.nextLine();
		
		System.out.println("Unesite izbor MD5/SHA-1/SHA-256");	//razlika SHA-1 i SHA-256?
		String hash = cs.nextLine();
		
		switch(hash) {
		case "MD5" : 
			MessageDigest digest2 = MessageDigest.getInstance("MD5");
			digest2.update(input.getBytes());
			System.out.println("heksadekadski zapis MD5 sazetka:  " + bytArrayToHex(digest2.digest()));
			break;
		case "SHA-1" :
			MessageDigest digest3 = MessageDigest.getInstance("SHA-1");
			digest3.update(input.getBytes());
			System.out.println("heksadekadski zapis SHA-1 sazetka:  " + bytArrayToHex(digest3.digest()));
			break;
		case "SHA-256" : 
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(input.getBytes());
			System.out.println("heksadekadski zapis SHA-256 sazetka:  " + bytArrayToHex(digest.digest()));
		}
		cs.close();
	}
	
	public static String bytArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder();
		for(byte b: a) sb.append(String.format("%02x", b&0xff));
		return sb.toString();
	}
}
