package com.cooltrickshome;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFolder {
	public static void main(String[] a) throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the file/folder to be zipped");
		String zipFile = s.nextLine();
		File f = new File(zipFile);
		String zipFileName = f.getName() + ".zip";
		if (f.isDirectory()) {
			zipFolder(zipFile, zipFileName);
		} else {
			zipFile(zipFile, zipFileName);
		}
		System.out.println(zipFileName + " has been generated at "
				+ new File("").getAbsolutePath());
		s.close();
	}

	static public void zipFile(String srcFile, String destZipFile)
			throws Exception {
		ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(destZipFile));
		File folder = new File(srcFile);
		byte[] buf = new byte[1024];
		int len;
		FileInputStream in = new FileInputStream(srcFile);
		zip.putNextEntry(new ZipEntry(folder.getName()));
		while ((len = in.read(buf)) > 0) {
			zip.write(buf, 0, len);
		}
		zip.close();
	}

	static public void zipFolder(String srcFolder, String destZipFile)
			throws Exception {
		ZipOutputStream zip = null;
		FileOutputStream fileWriter = null;

		fileWriter = new FileOutputStream(destZipFile);
		zip = new ZipOutputStream(fileWriter);

		addFolderToZip("", srcFolder, zip);
		zip.flush();
		zip.close();
	}

	static private void addFileToZip(String path, String srcFile,
			ZipOutputStream zip) throws Exception {

		File folder = new File(srcFile);
		if (folder.isDirectory()) {
			addFolderToZip(path, srcFile, zip);
		} else {
			byte[] buf = new byte[1024];
			int len;
			FileInputStream in = new FileInputStream(srcFile);
			zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
			while ((len = in.read(buf)) > 0) {
				zip.write(buf, 0, len);
			}
		}
	}

	static private void addFolderToZip(String path, String srcFolder,
			ZipOutputStream zip) throws Exception {
		File folder = new File(srcFolder);

		for (String fileName : folder.list()) {
			if (path.equals("")) {
				addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
			} else {
				addFileToZip(path + "/" + folder.getName(), srcFolder + "/"
						+ fileName, zip);
			}
		}
	}
}
