package com.cooltrickshome;

import org.json.*;

public class JSONParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String json = "{\"student\":{\"rNo\":\"1\",\"Name\":\"Anurag\",\"Website\":\"https://cooltrickshome.blogspot.com\"},\"posts\":[{\"post\":\"Create Keylogger with Java\",\"url\":\"http://cooltrickshome.blogspot.com/2016/11/creating-your-personal-keylogger-from.html\"}]}";
		JSONObject obj = new JSONObject(json);
		String name = obj.getJSONObject("student").getString("Name");
		String website = obj.getJSONObject("student").getString("Website");
		System.out.println("Name: "+name);
		System.out.println("Website: "+website);
		
		JSONArray arr = obj.getJSONArray("posts");
		System.out.println("Printing posts by this author");
		for (int i = 0; i < arr.length(); i++)
		{
		    String post = arr.getJSONObject(i).getString("post");
		    String url = arr.getJSONObject(i).getString("url");
		    System.out.println(post+" at "+url);
		}

	}

}
