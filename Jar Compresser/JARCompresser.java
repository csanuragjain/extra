package com.cooltrickshome;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class JARCompresser {


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
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		System.out.println("Press 1 to compress file and 2 to decompress");
		int choice=s.nextInt();
		if(choice==1)
		{
			Scanner s2=new Scanner(System.in);
			System.out.println("Enter file to compress");
			String file=s2.nextLine();
			long origlength= new File(file).length();
			runProgram(new String[]{"pack200","output.pack.gz",file});
			long newlength= new File("output.pack.gz").length();
			System.out.println("File compressed and named output.pack.gz, which saved "+((origlength-newlength)/1000)+" KB");
			s2.close();
		}
		else
		{
			Scanner s2=new Scanner(System.in);
			System.out.println("Enter file to decompress");
			String file=s2.nextLine();
			runProgram(new String[]{"unpack200",file,"output.jar"});
			System.out.println("File uncompressed and named output.jar");
			s2.close();
		}
		s.close();
	}

}
