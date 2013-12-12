package endecryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Glavni {

	/** Kriptiranje poruke ili datoteke algoritmima AES i DES, nacin kriptiranja ECB
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 * @throws NoSuchPaddingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 * @throws IOException 
	 * @throws InvalidKeySpecException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException {
		
		Scanner cs = new Scanner(System.in);
		System.out.println("Upisivanje poruke(1) / Ucitavanje datoteke(2)");
		String choice = cs.nextLine();
		
		switch (choice) {
		case "1" :
			System.out.println("Unesite poruku koju zelite sifrirati!");
			String input = cs.nextLine();
			System.out.println("Odabir algoritma: AES/DES");
			String alg = cs.nextLine();
			doIt(input, alg);
			break;
			
		case "2" :
			System.out.println("Kriptiranje datoteke. Odabir algoritma: AES/DES");
			String alg2 = cs.nextLine();
			FileEncrypt(alg2);		
			System.out.println("napravio i dekripciju datoteke pomocu kljuca koji je spremljen u datoteci");
			if (alg2 == "AES") {
				Decrypter.decryptFile("ciphertext.txt", Decrypter.getKeyFromFile("kljucAES.txt", alg2), alg2);
			}
			else {
				Decrypter.decryptFile("ciphertext.txt", Decrypter.getKeyFromFile("kljucDES.txt", alg2), alg2);
			}
			
		}
		cs.close();
	}
	
	public static void doIt (String input, String alg) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException{
		switch (alg) {
		case "AES" : {
			SecretKey key = KeyGenerator.GenerateKey(Algorithm.AES);
			byte[] kriptirano = Encrypter.AESEncrypt(input,key);
			System.out.println("dekriptirano : " + new String(Decrypter.AESDecrypt(kriptirano, key)));
			System.out.println("dekriptirano pomocu kljuca iz datoteke : " + new String(Decrypter.AESDecrypt(kriptirano, Decrypter.getKeyFromFile("kljucAES.txt", "AES"))));
		}
			break;
			
		case "DES" : {
			SecretKey key = KeyGenerator.GenerateKey(Algorithm.DES);
			byte[] kriptirano = Encrypter.DESEncrypt(input, key);
			System.out.println("dekriptirano : " + new String(Decrypter.DESDecrypt(kriptirano, key)));
			System.out.println("dekriptirano pomocu kljuca iz datoteke : " + new String(Decrypter.DESDecrypt(kriptirano, Decrypter.getKeyFromFile("kljucDES.txt", "DES"))));
		}
			break;
		}
	}
	
	public static void FileEncrypt(String alg) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
		String transform = alg + "/ECB" + "/PKCS5Padding";
		Cipher cipher = Cipher.getInstance(transform);
		
		switch (alg) {
		case "AES" :
			SecretKey key = KeyGenerator.GenerateKey(Algorithm.AES);
			
			byte[] data = key.getEncoded();
			FileOutputStream izlaz = new FileOutputStream("kljucAES.txt");
			int i;
			for (i=0; i < data.length; i++) {
				izlaz.write(data[i]);
			}
			izlaz.close();
			
			cipher.init(Cipher.ENCRYPT_MODE, key);
			FileInputStream fis = new FileInputStream("cleartext.txt");
			FileOutputStream fos = new FileOutputStream("ciphertext.txt");
			CipherOutputStream cos = new CipherOutputStream(fos, cipher);
			
			byte[] block = new byte[32];
			i=0;
			while ((i = fis.read(block)) != -1) {
			cos.write(block,0,i);	
			}
			cos.close();
			fis.close();
			
			System.out.println("Heksadekadski ispis kriptirane datoteke: ");
			readFileHex();
			break;				
			
		case "DES" :
			SecretKey key2 = KeyGenerator.GenerateKey(Algorithm.DES);
			cipher.init(Cipher.ENCRYPT_MODE, key2);
			
			byte[] data2 = key2.getEncoded();
			FileOutputStream izlaz2 = new FileOutputStream("kljucDES.txt");
			int ii;
			for (ii=0; ii < data2.length; ii++) {
				izlaz2.write(data2[ii]);
			}
			izlaz2.close();
			
			FileInputStream fiss = new FileInputStream("cleartext.txt");
			FileOutputStream foss = new FileOutputStream("ciphertext.txt");
			CipherOutputStream coss = new CipherOutputStream(foss, cipher);
			
			byte[] blockk = new byte[32];
			ii=0;
			while ((ii = fiss.read(blockk)) != -1) {
			coss.write(blockk,0,ii);	
			}
			coss.close();
			fiss.close();
			
			System.out.println("Heksadekadski ispis kriptirane datoteke: ");
			readFileHex();
			break;
		}		
	}
	
	public static void readFileHex() throws IOException{
		FileInputStream fis = new FileInputStream("ciphertext.txt");
		byte[] blok = new byte[64];
     	@SuppressWarnings("unused")
		int brojac=0;
     	while((brojac = fis.read(blok)) != -1){
     		System.out.println(bytArrayToHex(blok));
     	}
     	fis.close();
	}
	
	public static String bytArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder();
		for(byte b: a) sb.append(String.format("%02x", b&0xff));
		return sb.toString();
		}
}
