package com.cooltrickshome;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class ImageToPDF {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s =new Scanner(System.in);
		System.out.println("Please provide the path of image");
		String imgPath=s.nextLine();
		System.out.println("Please provide the path of pdf");
		String pdfPath=s.nextLine();
		boolean isValidURL=false;
		URL url;
		try {
		    url = new URL(imgPath);
		    isValidURL=true;
		} catch (MalformedURLException e) {

		}
		new ImageToPDF().imageToPdf(imgPath, pdfPath, isValidURL);
		s.close();
		System.out.println("PDF created...");
	}

	
	public void imageToPdf(String imgPath, String pdfPath, boolean isValidURL)
	{
		Document document= new Document(PageSize.A4);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File(pdfPath));
			PdfWriter writer = PdfWriter.getInstance(document, fos);
			writer.open();
		    document.open();
		    if(isValidURL)
		    {
		    	Image img=Image.getInstance(new java.net.URL(imgPath));
		    	float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
		                - document.rightMargin()) / img.getWidth()) * 100;
		    	img.scalePercent(scaler);
		    	document.add(img);
		    }
		    else
		    {
		    	Image img=Image.getInstance(imgPath);
		    	float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
		                - document.rightMargin()) / img.getWidth()) * 100;
		    	img.scalePercent(scaler);
		    	document.add(img);
		    }
		    document.close();
		    writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found "+e.getMessage());
		} catch (DocumentException e) {
			System.out.println("Document exception "+e.getMessage());
		} catch (MalformedURLException e) {
			System.out.println("Incorrect path given "+e.getMessage());
		} catch (IOException e) {
			System.out.println("Issue while accessing the input file "+e.getMessage());
		}
	}
}
