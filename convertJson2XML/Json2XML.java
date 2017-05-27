package com.cooltrickshome;

import org.json.JSONObject;
import org.json.XML;

public class Json2XML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jsonString="{\"name\":\"Virat\",\"sport\":\"cricket\",\"age\":25,\"id\":121,\"lastScores\":[77,72,23,57,54,36,74,17]}";
		System.out.println(new Json2XML().json2XML(jsonString));
	}
	
	public String json2XML(String jsonString){
		JSONObject json = new JSONObject(jsonString);
		String xml = XML.toString(json);
		return xml;
	}

}
