package endecryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Decrypter {
	public static String AESDecrypt(byte[] input, SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		String transformation = "AES/" + "ECB" + "/PKCS5Padding";	
		Cipher cipher = Cipher.getInstance(transformation);		
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decrypted = cipher.doFinal(input);
		return new String(decrypted);
	}
	
	public static String DESDecrypt(byte[] input, SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		String transformation = "DES/" + "ECB" + "/PKCS5Padding";
		Cipher cipher = Cipher.getInstance(transformation);	//
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decrypted = cipher.doFinal(input);
		return new String(decrypted);
	}
	//
	public static void decryptFile(String fileName, SecretKey key, String alg) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
		Cipher ciph = Cipher.getInstance(alg + "/ECB/PKCS5Padding");
		ciph.init(Cipher.DECRYPT_MODE,  key);
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

        SecretKey key2 = new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm);	
        fileInputStream.close();
        return key2;
	}
}
