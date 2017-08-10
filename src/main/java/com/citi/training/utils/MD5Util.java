package com.citi.training.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class MD5Util {

	private static Md5PasswordEncoder encoder = new Md5PasswordEncoder();



	public static String md5(String str){
		return md5(str, null);
	}



	public static String md5(String str, String salt){
		return encoder.encodePassword(str,salt);
	}

	public static void main(String[] args) {
		System.out.println(md5("123456","Danol"));
		System.out.println(md5("123456","Shannae"));
		System.out.println(md5("123456","Sheryl"));
	}
}
