package com.book.test;

import java.util.UUID;

import org.junit.Test;

public class UUIDTest {
	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		String roleid = uuid.toString();
		System.err.println(roleid);
		System.out.println(roleid.length());
	}
	
	@Test
	public void test(){
		String str = "ee28a8f4-18fb-4708-838f-590bf4c519dc,ee28a8f4-18fb-4708-838f-590bf4c519dc";
		String[] s = str.split(",");
		System.out.println(str.split(","));
		System.out.println(s[0]);
//		System.err.println(str.substring(0,37));
	}
}
