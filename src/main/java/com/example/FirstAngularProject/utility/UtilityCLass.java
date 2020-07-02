package com.example.FirstAngularProject.utility;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilityCLass {
	
	// Convert Object to JSONObject
	
	public JSONObject convertToJSON(Object javaObject) {
		JSONObject json = new JSONObject();
		ObjectMapper obj = new ObjectMapper();
		String jsonStr = "";
		try {
			jsonStr = obj.writeValueAsString(javaObject);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONParser parser = new JSONParser();
		try {
			json = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

}
