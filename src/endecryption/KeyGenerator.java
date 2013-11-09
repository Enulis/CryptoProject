package endecryption;

import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;


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
	}

	private static SecretKey GenerateAESKey() {
		// TODO Auto-generated method stub
		return null;
	}
}
