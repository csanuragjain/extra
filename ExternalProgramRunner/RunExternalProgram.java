package com.cooltrickshome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunExternalProgram {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		//open and list process from task manager
		String[] commands = {"tasklist.exe"};
		new RunExternalProgram().runProgram(commands);
		
		//Play song which is at c:\\Program Files\\1.mp3
		String[] commands2 = {"\"c:\\Program Files\\Windows Media Player\\wmplayer.exe\"","\"c:\\Program Files\\1.mp3\""};
		new RunExternalProgram().runProgram(commands2);
		
		//Run program with hidden console
		String[] commands3 = {"cmd","/c","start","/B","notepad"};
		new RunExternalProgram().runProgram(commands3);
	}

	public void runProgram(String[] program) throws InterruptedException, IOException
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
