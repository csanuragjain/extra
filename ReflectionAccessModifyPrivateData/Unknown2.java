package com.cooltrickshome;

public class Unknown2 {

	private final String msg="This is final string from private variable";
	
	private Unknown2(){
		
	}
	private String privateWelcome(String message)
	{
		message=message+" processed by private method";
		return message;
	}
	
	public void showMessage(){
		System.out.println("I should not be called since this class constructor is private");
	}

}
