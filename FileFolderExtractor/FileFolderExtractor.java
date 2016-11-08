package com.cooltrickshome;

import java.io.File;

public class FileFolderExtractor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String path=System.getProperty("user.home") + "/Desktop";
		File f= new File(path);
		File[] desktopProjects=f.listFiles();
		
		for(File file:desktopProjects)
		{
			if(file.isDirectory())
			{
				System.out.println("Directory: "+file.getName()+":"+(folderSize(file)/1000)+"KB");
			}
			else
			{
				System.out.println("File: "+file.getName()+":"+(file.length()/1000)+"KB");
			}
		}
	}
	
	public static long folderSize(File directory) {
	    long length = 0;
	    for (File file : directory.listFiles()) {
	        if (file.isFile())
	            length += file.length();
	        else
	            length += folderSize(file);
	    }
	    return length;
	}
}
