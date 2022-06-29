package util;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptUtil {
	public static String hashPw(String origin) {
		String encrypted = BCrypt.hashpw(origin, BCrypt.gensalt());
		return encrypted;
	}
	public static boolean checkPw(String origin, String encrypted) {
		return BCrypt.checkpw(origin, encrypted);
	}
}
