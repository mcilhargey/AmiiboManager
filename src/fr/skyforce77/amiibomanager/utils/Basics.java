package fr.skyforce77.amiibomanager.utils;

public class Basics {

	public static byte[] toValidString(byte[] bytes) {
		byte[] nbytes = new byte[bytes.length];
		int wrote = 0;
		for(int i = 0; i < bytes.length; i++) {
			if(bytes[i] != 0) {
				nbytes[wrote] = bytes[i];
				wrote++;
			}
		}
		return nbytes;
	}
	
	public static String toHexadecimalString(byte[] bytes) {
	    StringBuilder builder = new StringBuilder();
	    for(byte b : bytes) {
	        builder.append(String.format("%02x", b));
	    }
	    return builder.toString();
	}
	
}
