package endecryption;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Encrypter {
	public static byte[] AESEncrypt(String input, SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
		String transformation = "AES/" + "ECB" + "/PKCS5Padding";	
		Cipher cipher = Cipher.getInstance(transformation);	
		
		byte[] data = key.getEncoded();
		FileOutputStream izlaz = new FileOutputStream("kljucAES.txt");
		int i;
		for (i=0; i < data.length; i++) {
			izlaz.write(data[i]);
		}
		izlaz.close();
		
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(input.getBytes());
	}
	
	public static byte[] DESEncrypt(String input, SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
		String transformation = "DES/" + "ECB" + "/PKCS5Padding";
		Cipher cipher = Cipher.getInstance(transformation);		//
		
		byte[] data = key.getEncoded();
		FileOutputStream izlaz = new FileOutputStream("kljucDES.txt");
		int i;
		for (i=0; i < data.length; i++) {
			izlaz.write(data[i]);
		}
		izlaz.close();
		
		cipher.init(Cipher.ENCRYPT_MODE, key);		//
		return cipher.doFinal(input.getBytes());
	}
}
