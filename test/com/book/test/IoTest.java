package com.book.test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

public class IoTest {
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public static void main(String[] args) throws IOException{
		IoTest reade = new IoTest();
		reade.readeMethod();
	}
	
	public void readeMethod() throws IOException{
		FileReader fr = new FileReader("1.txt");
		int ch = 0;
		while((ch=fr.read())!=-1){
			System.out.print((char)ch);
		}
		fr.close();
	}
	
	public void addMethod() throws IOException{
		FileWriter fw = new FileWriter("1.txt",true);
		fw.write("[section3]"+LINE_SEPARATOR);
		fw.write("name = 1"+LINE_SEPARATOR);
		fw.write("passwd = 3");
		fw.flush();
		fw.close();
		System.out.println("成功");
	}
	
	@Test
	public void deleMethod() throws IOException{
		FileReader fr = new FileReader("1.txt");
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter("1.txt");
		PrintWriter pw = new PrintWriter(fw);
		
		int count = 0;
		String str = null;
		str = br.readLine();
		while(str!=null){
			count++;
			if(count == 10){
				str = "[section4]";
				pw.println(str);
				pw.flush();
			}else{
				pw.println(str);
				pw.flush();
			}
		}
		pw.close();
		fw.close();
		br.close();
		fr.close();
		System.out.println("成功");
	}
}
