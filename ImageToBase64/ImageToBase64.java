package com.cooltrickshome;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class ImageToBase64 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner s=new Scanner(System.in);
		System.out.println("Enter image path to be converted to base64 (eg. c:\\abc.jpg)");
		String imgPath=s.nextLine();
		File imgFile=new File(imgPath);
		String base64Val=convertTobase64(imgFile);
		System.out.println("Converted value ");
		System.out.println(base64Val);
	}
	
	public static String convertTobase64(File imgFile) throws IOException
	{
		return Base64.encodeBase64String(FileUtils.readFileToByteArray(imgFile));
	}

}
