package endecryption;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ModeCBCEncrypt {

	/** Kriptiranje i dektriptiranje nizova znakova i datoteka algoritmima AES i DES te CBC nacinom kriptiranja
	 * @param args
	 * @throws IOException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeySpecException 
	 */
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
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
		}
		cs.close();
	}
	
	public static void doIt (String input, String alg) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException {
		switch (alg) {
		case "AES" : {
			SecretKey key = KeyGenerator.GenerateKey(Algorithm.AES);
			saveSecretKeyToFile("secretKey_cbcAES.txt", key);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec ivSpec = getIv(16);	
			byte[] kriptirano = encryptOrDecrypt('E', input.getBytes(), key, cipher, ivSpec);
			System.out.println("heksadekadski ispis kriptirane poruke: ");
			System.out.println(bytArrayToHex(kriptirano));
			System.out.println("dekriptirano : " + new String(encryptOrDecrypt('D', kriptirano, key, cipher, ivSpec)));
			System.out.println("dekriptirano s parametrima iz datoteke: " + new String(encryptOrDecrypt('D', kriptirano, getKeyFromFile("secretKey_cbcAES.txt","AES"), cipher,  makeIvFromFile("paramFile.txt"))));
		}
			break;
		case "DES" : {
			SecretKey key = KeyGenerator.GenerateKey(Algorithm.DES);
			saveSecretKeyToFile("secretKey_cbcDES.txt", key);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec ivSpec = getIv(8);
			byte[] kriptirano = encryptOrDecrypt('E', input.getBytes(), key, cipher, ivSpec);	
			System.out.println("heksadekadski ispis kriptirane poruke: ");
			System.out.println(bytArrayToHex(kriptirano));
			System.out.println("dekriptirano : " + new String(encryptOrDecrypt('D', kriptirano, key, cipher, ivSpec)));
			System.out.println("dekriptirano s parametrima iz datoteke: " + new String(encryptOrDecrypt('D', kriptirano, getKeyFromFile("secretKey_cbcDES.txt","DES"), cipher,  makeIvFromFile("paramFile.txt"))));
		}
					break;
		}
	}

	public static IvParameterSpec getIv (int blockSize) throws IOException {
		byte[] iv = new byte[blockSize];
		new SecureRandom().nextBytes(iv);
		saveIvToFile(iv);
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		return ivSpec;
	}
	
	//zapravo se sprema materijal od kojeg se radi ivSpec
	public static void saveIvToFile(byte[] iv) throws IOException {
		FileOutputStream fs = new FileOutputStream(new File("paramFile.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(fs);
        bos.write(iv);
        bos.close();
	}
	
	public static IvParameterSpec makeIvFromFile(String paramFile) throws IOException {
		File file = new File(paramFile);
        byte[] ivBytes = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(ivBytes);
		IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
		fileInputStream.close();
		return ivSpec;
	}
	
	public static void saveSecretKeyToFile(String fileName, SecretKey key) throws IOException {
		byte[] data = key.getEncoded();
		FileOutputStream izlaz = new FileOutputStream(fileName);
		int i;
		for (i=0; i < data.length; i++) {
			izlaz.write(data[i]);
		}
		izlaz.close();
	}
	
	public static String bytArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder();
		for(byte b: a) sb.append(String.format("%02x", b&0xff));
		return sb.toString();
	}
	
	public static byte[] encryptOrDecrypt(char opmode ,byte[] input, SecretKey key, Cipher cipher, IvParameterSpec ivSpec) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		switch (opmode){
		case 'E' : 
			cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
			break;
		case 'D' :
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
			break;
		}
		byte [] result = cipher.doFinal(input);
		return result;
	}
	
	public static void FileEncrypt(String alg) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, InvalidAlgorithmParameterException, InvalidKeySpecException {
		String transform = alg + "/CBC" + "/PKCS5Padding";
		Cipher cipher = Cipher.getInstance(transform);
		switch (alg) {
		case "AES" :
			SecretKey key = KeyGenerator.GenerateKey(Algorithm.AES);
			saveSecretKeyToFile("secretKey_cbcAES.txt", key);
			IvParameterSpec ivSpec = getIv(cipher.getBlockSize());
			cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
			FileInputStream fis = new FileInputStream("cleartext.txt");
			FileOutputStream fos = new FileOutputStream("ciphertextcbc.txt");
			CipherOutputStream cos = new CipherOutputStream(fos, cipher);
			
			byte[] block = new byte[32];
			int i;
			while ((i = fis.read(block)) != -1) {
			cos.write(block,0,i);	
			}
			cos.close();
			fis.close();
			
			System.out.println("Heksadekadski ispis kriptirane datoteke: ");
			readFileHex();
			System.out.println("dekriptirano i s parametrima spremljenim u datoteke.");
			decryptFile("ciphertextcbc.txt", getKeyFromFile("secretKey_cbcAES.txt","AES"), "paramFile.txt", "AES");
			break;			
			
		case "DES" :
			SecretKey key2 = KeyGenerator.GenerateKey(Algorithm.DES);
			saveSecretKeyToFile("secretKey_cbcDES.txt", key2);
			IvParameterSpec ivSpec2 = getIv(cipher.getBlockSize());
			cipher.init(Cipher.ENCRYPT_MODE, key2, ivSpec2);
			FileInputStream fiss = new FileInputStream("cleartext.txt");
			FileOutputStream foss = new FileOutputStream("ciphertextcbc.txt");
			CipherOutputStream coss = new CipherOutputStream(foss, cipher);
			
			byte[] blockk = new byte[32];
			@SuppressWarnings("unused")
			int ii;
			while ((i = fiss.read(blockk)) != -1) {
			coss.write(blockk,0,i);	
			}
			coss.close();
			fiss.close();
			
			System.out.println("Heksadekadski ispis kriptirane datoteke: ");
			readFileHex();
			System.out.println("dekriptirano i s parametrima spremljenim u datoteke.");
			decryptFile("ciphertextcbc.txt", getKeyFromFile("secretKey_cbcDES.txt","DES"), "paramFile.txt", "DES");
			break;
		}
	}
	
	public static void decryptFile(String fileName, SecretKey key, String paramFile, String alg) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
		Cipher ciph = Cipher.getInstance(alg + "/CBC/PKCS5Padding");
		IvParameterSpec ivSpec = makeIvFromFile(paramFile);
		ciph.init(Cipher.DECRYPT_MODE, key, ivSpec);
		FileInputStream fis5 = new FileInputStream(fileName);
		CipherInputStream cis5 = new CipherInputStream(fis5, ciph);
		FileOutputStream fos5 = new FileOutputStream("cleartextAgainFileKeyFromFileAESDES.txt");
		
		byte[] block5 = new byte[32];
		int n;
		
		while ((n = cis5.read(block5)) != -1) {
		fos5.write(block5, 0, n);
		}
		cis5.close();
		fos5.close();
	}
	
	public static SecretKey getKeyFromFile(String fileName, String algorithm) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

		File file = new File(fileName);
        byte[] keyBytes = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(keyBytes);

        SecretKey key2 = new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm);	//nebi trebalo pisati AES
        fileInputStream.close();
        return key2;
	}
	
	public static void readFileHex() throws IOException{
		FileInputStream fis = new FileInputStream("ciphertextcbc.txt");
		byte[] blok = new byte[64];
     	@SuppressWarnings("unused")
		int brojac=0;
     	while((brojac = fis.read(blok)) != -1){
     		System.out.println(bytArrayToHex(blok));
     	}
     	fis.close();
	}
}
