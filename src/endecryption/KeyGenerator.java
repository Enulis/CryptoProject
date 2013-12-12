package endecryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class KeyGenerator {
	public static SecretKey GenerateKey(Algorithm algorithm) throws NoSuchAlgorithmException {
		switch (algorithm) {
		case AES:
			return GenerateAESKey();
		case DES:
			return GenerateDESKey();
		default:
			throw new NoSuchAlgorithmException(); 
		}
	}
	
	private static SecretKey GenerateDESKey() throws NoSuchAlgorithmException {
		return javax.crypto.KeyGenerator.getInstance("DES").generateKey();
		/*KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
		SecretKey myDesKey = keygenerator.generateKey();
		return myDesKey;*/
	}
	//bilo je public iz nekog razloga kojeg se ne sjecam
	private static SecretKey GenerateAESKey() throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		String keyString = "23750ergoujnfv1093eusjxknmncdftwuz3erhwlei43784ioirek";
		digest.update(keyString.getBytes());
		
		byte[] key = new byte[16];
		System.arraycopy(digest.digest(), 0, key, 0, key.length);	
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		return keySpec;
	}
}
