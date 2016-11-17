package com.cooltrickshome;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class ModifyClipboard {

	public static void main(String[] args) throws Exception {
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		
		Scanner s=new Scanner(System.in);
		System.out.println("Press 1 to copy text and 2 for copying image to clipboard");
		int ch=s.nextInt();
		if(ch==1)
		{
		setText(clipboard,"Text placed to clipboard by Java");
		System.out.println("If you press Ctrl+v then you will see the text \"Text placed to clipboard by Java\"");
		}
		else if(ch==2)
		{
			Robot robot = new Robot();
	        Dimension screenSize  = Toolkit.getDefaultToolkit().getScreenSize();
	        Rectangle screen = new Rectangle( screenSize );
	        BufferedImage image = robot.createScreenCapture( screen );
			setImageFromClipboard(clipboard, image);
			System.out.println("Copied your desktop screenshot to your clipboard.");
			System.out.println("Press Ctrl+v on paint and you will see your screenshot");
		}
		
	}
	
	public static void setImageFromClipboard(Clipboard clipboard, BufferedImage image)
			throws Exception
			{
        ImageSelection imgSel = new ImageSelection(image);
        clipboard.setContents(imgSel, null );
			}
	
	public static void setText(Clipboard clipboard, String text) 
	{
		StringSelection selection = new StringSelection(text);
		clipboard.setContents(selection, selection);
	}
}

// Thanks to http://alvinalexander.com/java/java-copy-image-to-clipboard-example
class ImageSelection implements Transferable
{
  private Image image;

  public ImageSelection(Image image)
  {
    this.image = image;
  }

  // Returns supported flavors
  public DataFlavor[] getTransferDataFlavors()
  {
    return new DataFlavor[] { DataFlavor.imageFlavor };
  }

  // Returns true if flavor is supported
  public boolean isDataFlavorSupported(DataFlavor flavor)
  {
    return DataFlavor.imageFlavor.equals(flavor);
  }

  // Returns image
  public Object getTransferData(DataFlavor flavor)
      throws UnsupportedFlavorException, IOException
  {
    if (!DataFlavor.imageFlavor.equals(flavor))
    {
      throw new UnsupportedFlavorException(flavor);
    }
    return image;
  }
}