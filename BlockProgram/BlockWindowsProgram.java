package com.cooltrickshome;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class BlockWindowsProgram {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	
	static int snapshotCounter=0;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		String[] monitorRunningProcess=new String[]{"tasklist.exe"};
		String processToBlock="";
		System.out.println("Enter the process to block");
		Scanner s=new Scanner(System.in);
		processToBlock = s.nextLine();
		s.close();
		String[] killProgram=new String[]{"taskkill","/F","/IM",processToBlock};
		System.out.println("Initiated blocking "+processToBlock);
		while(true)
		{
			boolean isProgramRunning=monitorProgram(monitorRunningProcess, processToBlock);
			if(isProgramRunning)
			{
				snapshotCounter++;
				System.out.println("Someone ran "+processToBlock);
				Webcam webcam = Webcam.getDefault();
				webcam.setViewSize(new Dimension(640, 480));
				webcam.open();
				ImageIO.write(webcam.getImage(), "PNG", new File("User"+snapshotCounter+".png"));
				webcam.close();
				System.out.println("Picture of the Person who ran blocked program is stored at "+new File("").getAbsolutePath()+File.separator+"User"+snapshotCounter+".png");
				System.out.println("Killing "+processToBlock);
				runProgram(killProgram);
			}
			Thread.sleep(30000);
		}
	}
	
	public static boolean monitorProgram(String[] program, String processToMonitor) throws InterruptedException, IOException
	{
		boolean isprocessRunning=false;
		Process proc = Runtime.getRuntime().exec (program);
		InputStream progOutput = proc.getInputStream ();
		InputStreamReader inputReader=new InputStreamReader(progOutput);
		BufferedReader reader = new BufferedReader(inputReader);		
		String line;
		while ((line = reader.readLine()) != null)
		    {
				if(line.contains(processToMonitor))
				{
					isprocessRunning=true;
				}
		    }
		proc.waitFor ();
		return isprocessRunning;
	}
	
	public static void runProgram(String[] program) throws InterruptedException, IOException
	{
		Process proc = Runtime.getRuntime().exec (program);
		InputStream progOutput = proc.getInputStream ();
		InputStreamReader inputReader=new InputStreamReader(progOutput);
		BufferedReader reader = new BufferedReader(inputReader);		
		String line;
		while ((line = reader.readLine()) != null)
		    {
				System.out.println(line);
		    }
		
		if (0 == proc.waitFor ()) {
		    System.out.println("Process completed successfully");
		}
		else
		{
			System.out.println("Their was some issue while running the program");
		}
	}

}
