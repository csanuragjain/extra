package com.cooltrickshome;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class AccessClipboard {

	/**
	 * @param args
	 * @throws Exception 
	 */
	
	static int counter=0;
	
	public static void main(String[] args) throws Exception {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		
		if (clipboard.isDataFlavorAvailable(DataFlavor.javaFileListFlavor)) 
		{
		getCopiedFile(clipboard);
		}
		
		if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) 
		{
		getText(clipboard);
		}
		
		if (clipboard.isDataFlavorAvailable(DataFlavor.imageFlavor)) 
		{
		getImageFromClipboard(clipboard);
		}
	}

	public static Object getCopiedFile(Clipboard clipboard) throws UnsupportedFlavorException, IOException
	{
		Object result = clipboard.getData(DataFlavor.javaFileListFlavor);
        List files = (List) result;
        for (int i = 0; i < files.size(); i++) {
            File file = (File) files.get(i);
            File destFile=new File(file.getName());
            copyFileUsingStream(file, destFile);
            System.out.println("Copied file "+file.getName()+" to "+destFile.getAbsolutePath());
        }
		return result;
	}
	
	public static Image getImageFromClipboard(Clipboard clipboard)
			throws Exception
			{
			  Transferable transferable = clipboard.getContents(null);
			  Image img=(Image) transferable.getTransferData(DataFlavor.imageFlavor);
				if(img!=null)
				{
					counter++;
					BufferedImage buffered = (BufferedImage) img;
					ImageIO.write(buffered, "jpg", new File("copiedFile"+counter+".jpg"));
					System.out.println("Saved the clipboard image as copiedFile"+counter+".jpg");
				}
				return img;
			}
	
	public static String getText(Clipboard clipboard) throws UnsupportedFlavorException, IOException
	{
		String result = (String) clipboard.getData(DataFlavor.stringFlavor);
		System.out.println("String from Clipboard:" + result);
		return result;
	}
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
}
