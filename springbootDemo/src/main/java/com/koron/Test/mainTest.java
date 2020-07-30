package com.koron.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class mainTest {
	public static void main(String[] args) {
		String aa="123";
		String bb="secret";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode(aa));
		System.out.println(encoder.encode(bb));
	}
}
