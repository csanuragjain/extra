package com.cooltrickshome;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class ThumbnailGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s =new Scanner(System.in);
		System.out.println("Enter the path of image whose thumbnail need to be generated");
		String imgPath=s.nextLine();
		File thumnailImg=createThumbnail(new File(imgPath), 400, 400);
		System.out.println("Thumbnail generated at "+thumnailImg.getAbsolutePath());
	}

	/**
	 * Creates a thumnail of provided image
	 * @param inputImgFile The input image file
	 * @param thumnail_width Desired width of the output thumbnail
	 * @param thumbnail_height Desired height of thr output thumnail
	 */
	public static File createThumbnail(File inputImgFile, int thumnail_width, int thumbnail_height){
		File outputFile=null;
		try {
		BufferedImage img = new BufferedImage(thumnail_width, thumbnail_height, BufferedImage.TYPE_INT_RGB);
		img.createGraphics().drawImage(ImageIO.read(inputImgFile).getScaledInstance(thumnail_width, thumbnail_height, Image.SCALE_SMOOTH),0,0,null);
		outputFile=new File(inputImgFile.getParentFile()+File.separator+"thumnail_"+inputImgFile.getName());
			ImageIO.write(img, "jpg", outputFile);
			return outputFile;
		} catch (IOException e) {
			System.out.println("Exception while generating thumbnail "+e.getMessage());
			return null;
		}
	}
}
