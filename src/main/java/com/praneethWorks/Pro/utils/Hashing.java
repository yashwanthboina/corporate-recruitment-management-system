package com.praneethWorks.Pro.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
	
	public static String charHashing(String data) {
		String str="";
		for(int i=0;i<data.length();i++) {
			char ch=(char)(data.charAt(i)+'?');
			str+=String.valueOf(ch);
		}
		
		return str;
	}
	
	
	public static String doRevHashing(String data) {
		String str="";
		for(int i=0;i<data.length();i++) {
			char ch=(char)(data.charAt(i)-'?');
			str+=String.valueOf(ch);
		}
		
		return str;
	}
	
    public static String getSHA256Hash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}