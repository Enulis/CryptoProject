package endecryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


@SuppressWarnings("unused")
public class RSAv2 {

	/** Kriptiranje i dektriptiranje nizova znakova i datoteka asimetricnim algoritmom RSA
	 * @param args
	 * @throws InvalidKeySpecException 
	 */
	
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException {
		System.out.println("Upisivanje poruke(1) / Ucitavanje datoteke(2)");
		Scanner cs = new Scanner(System.in);
		String choice = cs.nextLine();

		switch (choice) {
		case "1" :
			RSAStringEncrypt(cs);
			break;
			//RSAStringDecrypt(); koristeci 
		case "2" : 
			RSAFileEncrypt();
			System.out.println("Heksadekadski ispis kriptirane datoteke: ");
			readFileHex();			//RSAStringDecrypt(); koristeci 
			decryptFile("ciphertextRSA.txt", getKeyFromFile("privatnikljucRSA.txt"));
			break;
		}	
		cs.close();
	}
	
	public static void saveKeyToFile(PrivateKey privateKey) throws IOException {
		byte[] privateKeyBytes = privateKey.getEncoded();
		FileOutputStream izlaz = new FileOutputStream("privatnikljucRSA.txt");
		
		//String kljucHeksa = bytArrayToHex(privateKeyBytes);
				
		int i;
		for (i=0; i < privateKeyBytes.length; i++) {		//privateKeyBytes umjesto kljucHeksa
			izlaz.write(privateKeyBytes[i]);
		}
		izlaz.close();
		//return "privatnikljucRSA.txt"
	}
	
	public static PrivateKey getKeyFromFile(String fileName) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

		File file = new File(fileName);
        byte[] privateKeyBytes = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(privateKeyBytes);
//        for (int i = 0; i < privateKeyBytes.length; i++) {
//        	System.out.print((char)privateKeyBytes[i]);
//        }
        fileInputStream.close();
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
	    PrivateKey privateKey2 = keyFactory.generatePrivate(privateKeySpec);
	    return privateKey2;
	}
	
	public static void RSAStringEncrypt(Scanner cs) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException{
		String input = getString(cs);
		Cipher cipher = Cipher.getInstance("RSA");
		KeyPair kp = getKey();	
		
		saveKeyToFile(kp.getPrivate());
		System.out.println("spremio kljuc");
		getKeyFromFile("privatnikljucRSA.txt");		
		byte[] kriptirano = encryptString(input, cipher, kp);
		byte[] dekriptiraniString = decryptString(kriptirano, getKeyFromFile("privatnikljucRSA.txt"));
		System.out.println("kriptirano: " + new String(kriptirano));
		System.out.println("heksadekadski zapis kriptiranog :" + bytArrayToHex(encryptString(input, cipher, kp)));
		System.out.println("dekriptirano: " + new String(decryptString(kriptirano, kp.getPrivate())));
		System.out.println("dekriptirani string pomocu kljuca iz datoteke : " + new String(dekriptiraniString));
	}
	
	public static String getString(Scanner cs) throws NoSuchAlgorithmException, NoSuchPaddingException{
		System.out.println("Upisite string koji zelite kriptirati!");
		return cs.nextLine();
	}
	
	public static KeyPair getKey() throws NoSuchAlgorithmException{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);			//ili 2048
		return keyGen.genKeyPair();
	}
	
	public static byte[] encryptString(String input, Cipher cipher, KeyPair kp) throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		PublicKey publicKey = kp.getPublic();
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(input.getBytes());
	}
	
	public static byte[] decryptString(byte[] input, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher ciph = Cipher.getInstance("RSA");
		ciph.init(Cipher.DECRYPT_MODE, privateKey);
		return ciph.doFinal(input);
	}
	
	public static void RSAFileEncrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException{
		Cipher cipher = Cipher.getInstance("RSA");
		KeyPair kp = getKey();	
		encryptFile(cipher, kp);
	}
	
	public static void encryptFile(Cipher cipher, KeyPair kp) throws InvalidKeyException, IOException{
		cipher.init(Cipher.ENCRYPT_MODE, kp.getPublic());
		saveKeyToFile(kp.getPrivate());
		FileInputStream fis = new FileInputStream("cleartext.txt");
		FileOutputStream fos = new FileOutputStream("ciphertextRSA.txt");
		CipherOutputStream cos = new CipherOutputStream(fos, cipher);
		
		byte[] block = new byte[32];
		int i;
		while ((i = fis.read(block)) != -1) {
		cos.write(block,0,i);	
		}
		cos.close();
		fis.close();
	}
	
	public static void decryptFile(String fileName, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
		Cipher ciph = Cipher.getInstance("RSA");
		ciph.init(Cipher.DECRYPT_MODE,  privateKey);
		FileInputStream fis5 = new FileInputStream(fileName);
		CipherInputStream cis5 = new CipherInputStream(fis5, ciph);
		FileOutputStream fos5 = new FileOutputStream("cleartextAgainFileKeyFromFile.txt");
		
		byte[] block5 = new byte[32];
		int n;
		
		while ((n = cis5.read(block5)) != -1) {
		fos5.write(block5, 0, n);
		}
		cis5.close();
		fos5.close();
	}
	
	public static String bytArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder();
		for(byte b: a) sb.append(String.format("%02x", b&0xff));
		return sb.toString();
	}
	
	public static void readFileHex() throws IOException{
		FileInputStream fis = new FileInputStream("ciphertextRSA.txt");
		byte[] blok = new byte[64];
     	int brojac=0;
     	while((brojac = fis.read(blok)) != -1){
     		System.out.println(bytArrayToHex(blok));
     	}
     	fis.close();
	}
}
