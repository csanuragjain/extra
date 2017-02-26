package com.cooltrickshome;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ReflectionReadApi {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) {
		ClassLoader cl;
		Class c;
		try {
			File file = new File(".");
			URL url = file.toURL();
			URL[] urls = new URL[] { url };
			cl = new URLClassLoader(urls);
			c = cl.loadClass("com.cooltrickshome.completed.RunExternalProgram");
			System.out.println("\nName of class is " + c.getName());
			ReflectionReadApi ra = new ReflectionReadApi();
			ra.printMethods(c);
			ra.printConstructor(c);
			ra.printFields(c);
		} catch (ClassNotFoundException e) {
			System.out.println("Requested class was not found "
					+ e.getMessage());
		} catch (MalformedURLException e) {
			System.out.println("Given class file url was not found "
					+ e.getMessage());
		}
	}

	public void printMethods(Class c) {
		// Getting all the methods
		System.out.println("\nMethods of this class");
		Method methlist[] = c.getDeclaredMethods();
		for (int i = 0; i < methlist.length; i++) {
			Method m = methlist[i];
			System.out.println(m.toString());
			System.out.println("Method Name: " + m.getName());
			System.out.println("Declaring Class: " + m.getDeclaringClass());
			Class param[] = m.getParameterTypes();
			for (int j = 0; j < param.length; j++)
				System.out.println("Param #" + j + ": " + param[j]);
			Class exec[] = m.getExceptionTypes();
			for (int j = 0; j < exec.length; j++)
				System.out.println("Exception thrown by method #" + j + ": "
						+ exec[j]);
			System.out.println("Method Return type: " + m.getReturnType());
			System.out
					.println("--------------------------------------------------\n");
		}
	}

	public void printConstructor(Class c) {
		// Getting all the constructor
		System.out.println("Constructor of this class");
		Constructor[] constlist = c.getDeclaredConstructors();
		for (int i = 0; i < constlist.length; i++) {
			Constructor m = constlist[i];
			System.out.println(m.toString());
			System.out.println("Method Name: " + m.getName());
			System.out.println("Declaring Class: " + m.getDeclaringClass());
			Class param[] = m.getParameterTypes();
			for (int j = 0; j < param.length; j++)
				System.out.println("Param #" + j + ": " + param[j]);
			Class exec[] = m.getExceptionTypes();
			for (int j = 0; j < exec.length; j++)
				System.out.println("Exception thrown by method #" + j + ": "
						+ exec[j]);
			System.out
					.println("--------------------------------------------------\n");
		}
	}
	
	public void printFields(Class c)
	{
		System.out.println("Variables of this class");
		Field fieldlist[] 
	              = c.getDeclaredFields();
	            for (int i 
	              = 0; i < fieldlist.length; i++) {
	               Field fld = fieldlist[i];
	               System.out.println("Variable name: " + fld.getName());
	               System.out.println("Declaring class: " +fld.getDeclaringClass());
	               System.out.println("Variable type: " + fld.getType());
	               int mod = fld.getModifiers();
	               System.out.println("Modifiers = " +Modifier.toString(mod));
	               System.out.println("--------------------------------------------------\n");
	            }
	}

}
