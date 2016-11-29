package com.cooltrickshome;

import java.io.File;
import java.util.Scanner;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class UnzipFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		System.out.println("Please enter the zip file to be unzipped");
		String zipFile=s.nextLine();
		System.out.println("Please enter the password for zip file (type none if no password)");
		String password=s.nextLine();
		File f=new File(zipFile);
		//Your password if any
		unzipFile(zipFile, f.getParent(),password);
		System.out.println("Extracted zip content at "+f.getParent());
		s.close();
	}
	
	public static void unzipFile(String sourceZip, String destination, String password)
	{

		try {
		    ZipFile zipFile = new ZipFile(sourceZip);
		    if (zipFile.isEncrypted()) {
		        zipFile.setPassword(password);
		    }
		    zipFile.extractAll(destination);
		} catch (ZipException e) {
		    e.printStackTrace();
		}
	}

}
