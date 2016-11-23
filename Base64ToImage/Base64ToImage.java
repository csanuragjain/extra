package com.cooltrickshome;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;

public class Base64ToImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner s=new Scanner(System.in);
		System.out.println("Enter base64 string to be converted to image");
		String base64=s.nextLine();
		byte[] base64Val=convertToImg(base64);
		writeByteToImageFile(base64Val, "image.png");
		System.out.println("Saved the base64 as image in current directory with name image.png");
	}
	
	public static byte[] convertToImg(String base64) throws IOException
	{
		return Base64.decodeBase64(base64);
	}
	
	public static void writeByteToImageFile(byte[] imgBytes, String imgFileName) throws IOException
	{
		File imgFile = new File(imgFileName);
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgBytes));
		ImageIO.write(img, "png", imgFile);
	}

}
