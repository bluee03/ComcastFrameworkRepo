package com.comcast.crm.generic_Fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

public class JsonUtility {

	public String getDataFromjsonfile(String key) throws IOException, ParseException {
		FileReader file= new FileReader("./configAppData/appCommonData.json");
		JSONParser parser= new JSONParser();
		Object obj = parser.parse(file);
		JSONObject jb=(JSONObject)obj;
		String data = (String) jb.get(key);
		return data;
		
	}
  
}
