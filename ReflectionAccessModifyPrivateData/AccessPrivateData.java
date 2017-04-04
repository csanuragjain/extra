package com.cooltrickshome;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AccessPrivateData {

	public static void main(String[] args) throws Exception {
		Class c=Class.forName("com.cooltrickshome.Unknown2");
		//Class<Unknown2> c=Unknown2.class;
		Constructor<Unknown2> constr=c.getDeclaredConstructor();
		constr.setAccessible(true);
		Unknown2 s=(Unknown2)constr.newInstance();  
		System.out.println("Calling method from class with private constructor:");
		s.showMessage(); 
		System.out.println();
		
		Field field = c.getDeclaredField("msg");
		field.setAccessible(true);
		System.out.println("Calling private field:");
		System.out.println(field.get(s));
		System.out.println();
		System.out.println("Changing a final variable value:");
		field.set(s, "I have change a private final variable");
		System.out.println(field.get(s));
		System.out.println();
		
		Method m= c.getDeclaredMethod("privateWelcome", String.class);
		m.setAccessible(true);
		Object o=m.invoke(s, "Calling private method");
		System.out.println("Calling private method:");
		System.out.println(o);
		
	}

}
