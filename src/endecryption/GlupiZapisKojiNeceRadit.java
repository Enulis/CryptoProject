package endecryption;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;

@SuppressWarnings("unused")
public class GlupiZapisKojiNeceRadit {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		Cipher cipher = Cipher.getInstance("RSA");
		keyGen.initialize(1024);			//ili 2048
		KeyPair kp = keyGen.genKeyPair();
		
		PublicKey publicKey = kp.getPublic();
		PrivateKey privateKey = kp.getPrivate();
		
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		
		String cleartextFile = "cleartext.txt";
		String ciphertextFile = "ciphertextRSA.txt";
		
		FileInputStream fis = new FileInputStream(cleartextFile);
		FileOutputStream fos = new FileOutputStream(ciphertextFile);
		CipherOutputStream cos = new CipherOutputStream(fos, cipher);
		
		byte[] block = new byte[32];
		int i;
		while ((i = fis.read(block)) != -1) {
		cos.write(block, 0, i);
		}
		cos.close();
		
		String cleartextAgainFile = "cleartextAgainRSA.txt";
		//cipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		Cipher ciph = Cipher.getInstance("RSA");
		ciph.init(Cipher.DECRYPT_MODE,  privateKey);
		
		//neki warning
		fis = new FileInputStream(ciphertextFile);
		//CipherInputStream cis = new CipherInputStream(fis, cipher);
		CipherInputStream cis = new CipherInputStream(fis, ciph);
		fos = new FileOutputStream(cleartextAgainFile);
		
		while ((i = cis.read(block)) != -1) {
		fos.write(block, 0, i);
		}
		fos.close();
		
	}

}
