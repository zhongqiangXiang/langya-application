package com.ideacome.security.vo;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ideacome.base.utils.Md5Utils;

public class MD5PasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		
		return Md5Utils.MD5(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		return encodedPassword.equals(encode(rawPassword));
	}
	
	public static void main(String[] args) {
		String str = "123456";
		System.out.println(new MD5PasswordEncoder().encode(str));
	}
}
