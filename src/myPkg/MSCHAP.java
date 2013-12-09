package myPkg;

import java.util.Random;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
//import org.apache.avro.io.BinaryData;


import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKeyFactory;

import java.security.*;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.*;
import javax.crypto.spec.*;

import org.bouncycastle.crypto.digests.*; //import za MD4 hash


public class MSCHAP {
	public static byte[] ClientChallenge = new byte[16];
	public static String CC;
	public static byte[] ServerChallenge = new byte[16];
	public static String SC;
	public static String SHA1Hash;
	public static String NThash;
	public static byte[] DES1 = new byte[8];
	public static String D1;
	public static byte[] DES2 = new byte[8];
	public static String D2;
	public static byte[] DES3 = new byte[8];
	public static String D3;
	public static String ChallengeRsp;
	public static String NTHashHash;
	public static String Digest;
	public static String Authrsp;
	static byte[] thirdDesKey = new byte[8];
	public static String uname;
	
	private static byte[] Generate16ByteServerChallenge(){
		byte[] ServerCh = new byte[16];
		new Random().nextBytes(ServerCh);
		return ServerCh;
	}
	
	private static byte[] Generate16ByteClientChallenge (){
		byte[] ClientCh = new byte[16];
		new Random().nextBytes(ClientCh);
		return ClientCh;
	}
	
	private static String ChallengeHashSHA1(String username, int numBytes) throws NoSuchAlgorithmException{
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] SHA1 = new byte[numBytes];
		byte[] Uname = username.getBytes();
		mDigest.update(ClientChallenge,0,16);
		mDigest.update(ServerChallenge,0,16);
		mDigest.update(Uname,0,Uname.length);
		System.arraycopy(mDigest.digest(), 0, SHA1, 0, numBytes); //uzimamo samo prvih 16 hex znamenki iz sha1, tj prvih 8 byte-a
		return Hex.encodeHexString(SHA1);
	}
	
	private static String NTHash(String password){
		MD4Digest digest = new MD4Digest();
		byte[] pwdBytes = null;
		byte[] encPwd = null;
		pwdBytes = password.getBytes();
		digest.update(pwdBytes,0,pwdBytes.length);
		encPwd = new byte[16];
		digest.doFinal(encPwd, 0);
		return Hex.encodeHexString(encPwd);	
	}
	
	
	private static String ChallengeResponse(String username) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, ShortBufferException, DecoderException{
		byte[] ChallengeHash = new byte[8];
		ChallengeHash = Hex.decodeHex(ChallengeHashSHA1(username,8).toCharArray());
		byte[] keyMaterial = Hex.decodeHex(NThash.toCharArray());
		byte[] firstDesKey = new byte[8]; 
		System.arraycopy(keyMaterial, 0, firstDesKey, 0, 7); //za izradu des kljuca uzimamo 7 bajta ntHasha
		byte[] secondDesKey = new byte[8]; 
		System.arraycopy(keyMaterial, 7, secondDesKey, 0, 7); 
		System.arraycopy(keyMaterial, 14, thirdDesKey, 0, 2);
		byte[] zeroes = new byte[] {0,0,0,0,0,0};
		System.arraycopy(zeroes, 0, thirdDesKey, 2, 6); //za zadnji kljuc fali 5 bajta pa dodamo 0.
		DESKeySpec desKeySpec;
		SecretKeyFactory keyFactory;
		SecretKey secretKey;
		Cipher cipher;
		cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		byte[] encrypted;
		int enc_len;
		//Prvi DES
		desKeySpec = new DESKeySpec(firstDesKey);
		keyFactory = SecretKeyFactory.getInstance("DES");
		secretKey = keyFactory.generateSecret(desKeySpec);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		encrypted = new byte[cipher.getOutputSize(ChallengeHash.length)];
		enc_len = cipher.update(ChallengeHash, 0, ChallengeHash.length, encrypted, 0);
		enc_len += cipher.doFinal(encrypted,enc_len);
		System.arraycopy(encrypted, 0, DES1, 0, 8);
		
		//Drugi DES
		desKeySpec = new DESKeySpec(secondDesKey);
		keyFactory = SecretKeyFactory.getInstance("DES");
		secretKey = keyFactory.generateSecret(desKeySpec);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		encrypted = new byte[cipher.getOutputSize(ChallengeHash.length)];
		enc_len = cipher.update(ChallengeHash, 0, ChallengeHash.length, encrypted, 0);
		enc_len += cipher.doFinal(encrypted,enc_len);
		System.arraycopy(encrypted, 0, DES2, 0, 8);
		
		//Treci DES
		desKeySpec = new DESKeySpec(thirdDesKey);
		keyFactory = SecretKeyFactory.getInstance("DES");
		secretKey = keyFactory.generateSecret(desKeySpec);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		encrypted = new byte[cipher.getOutputSize(ChallengeHash.length)];
		enc_len = cipher.update(ChallengeHash, 0, ChallengeHash.length, encrypted, 0);
		enc_len += cipher.doFinal(encrypted,enc_len);
		System.arraycopy(encrypted, 0, DES3, 0, 8);
		return Hex.encodeHexString(DES1) + Hex.encodeHexString(DES2) + Hex.encodeHexString(DES3);
	}
	
	private static String SHA1ServerSide() throws NoSuchAlgorithmException{
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] NTHHBytes = NTHashHash.getBytes();
		int magic = 1132409;
		byte[] MagicNumber = {
			(byte)(magic >>> 24),
			(byte)(magic >>> 16),
			(byte)(magic >>> 8),
			(byte)magic
		};
		mDigest.update(NTHHBytes,0,16);
		mDigest.update(ChallengeRsp.getBytes(),0,16);
		mDigest.update(MagicNumber,0,4);
		//System.arraycopy(mDigest.digest(), 0, SHA1, 0, numBytes); //uzimamo samo prvih 16 hex znamenki iz sha1, tj prvih 8 byte-a
		String help = Hex.encodeHexString(mDigest.digest());
		return help;
	}
	
	private static String AuthResponse() throws NoSuchAlgorithmException{
		String str = "Pad to make it do more than one iteration";
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		mDigest.update(Digest.getBytes(),0,16);
		mDigest.update(SHA1Hash.getBytes(),0,16);
		mDigest.update(str.getBytes());
		String help = Hex.encodeHexString(mDigest.digest());
		return help;		
	}
	

	
	public static void MSCHAPv2 (String password, String username) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, ShortBufferException, DecoderException{
		ServerChallenge = Generate16ByteServerChallenge();
		ClientChallenge = Generate16ByteClientChallenge(); //rendom generiranih 16 byte-a client challenga
		SC = Hex.encodeHexString(ServerChallenge);
		CC = Hex.encodeHexString(ClientChallenge);
		SHA1Hash = ChallengeHashSHA1(username,16);
		NThash = NTHash(password);
		ChallengeRsp = ChallengeResponse(username);
		D1 = Hex.encodeHexString(DES1);
		D2 = Hex.encodeHexString(DES2);
		D3 = Hex.encodeHexString(DES3);
		NTHashHash = NTHash(NThash);
		Digest = SHA1ServerSide();
		Authrsp = AuthResponse();
		uname = username;
	}
	
	private static boolean CrackThirdDES() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, DecoderException{
		byte[] ChallengeHash = new byte[16];
		ChallengeHash = Hex.decodeHex(SHA1Hash.toCharArray());
		byte[] ChallengeHelp = new byte[8];
	    String CompareHash = ""; //zadnjih osam bajta ChallengeRsp - oni su enkriptirani trecim des kljucem koji pokusavamo otkriti
		CompareHash = ChallengeRsp.substring(32); 
		System.arraycopy(ChallengeHash, 0, ChallengeHelp, 0, 8); //Osam bajta challengeHasha koje treba enkriptirati i onda usporediti s gornjim compareHashom
		byte[] keyMaterial = new byte[] {0,0,0,0,0,0,0,0};
		byte[] DESGuess = new byte[8];
		boolean flag = false;
		byte[] zeroes = new byte[] {0,0,0,0,0,0};
		DESKeySpec desKeySpec;
		SecretKeyFactory keyFactory;
		SecretKey secretKey;
		keyFactory = SecretKeyFactory.getInstance("DES");
		Cipher cipher;
		cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		byte[] encrypted;
		int enc_len;
		byte[] help;
		for(int i=0; i<5; i++){
			//help = intToByteArray(i);
			//System.arraycopy(help, 2, keyMaterial, 0, 2);
			//System.out.println(Hex.encodeHexString(keyMaterial));
			//System.arraycopy(zeroes, 0, keyMaterial, 2, 6);
			//System.out.println(Hex.encodeHexString(keyMaterial));
			desKeySpec = new DESKeySpec(keyMaterial);  //pokušam napraviti DES ključ sa brojevima iz for petlji
			secretKey = keyFactory.generateSecret(desKeySpec);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encrypted = new byte[cipher.getOutputSize(ChallengeHelp.length)];
			enc_len = cipher.update(ChallengeHelp, 0, ChallengeHelp.length, encrypted, 0);
			enc_len += cipher.doFinal(encrypted,enc_len);
			System.arraycopy(encrypted, 0, DESGuess, 0, 8);
			System.out.println(Hex.encodeHexString(DESGuess));
			System.out.println(CompareHash);
			if(Hex.encodeHexString(DESGuess).compareTo(CompareHash) == 0){
				flag = true;
				break;
			}
		}
		return flag;
	}
	public static final byte[] intToByteArray(int value) {
	    return new byte[] {
	            (byte)(value >>> 24),
	            (byte)(value >>> 16),
	            (byte)(value >>> 8),
	            (byte)value};
	}
	
	/*public static void main (String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, ShortBufferException, DecoderException{
		String username, pass;
		username = "Nicole";
		pass = "Bilic";
		MSCHAPv2(pass,username);
		System.out.println(ServerChallenge);
		System.out.println(Hex.encodeHexString(ServerChallenge));
		System.out.println(ClientChallenge);
		System.out.println(Hex.encodeHexString(ClientChallenge));
		
	}*/
}