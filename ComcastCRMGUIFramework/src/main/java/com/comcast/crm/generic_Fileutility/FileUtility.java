package com.comcast.crm.generic_Fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertiesFile(String key) throws Throwable {
		FileInputStream pfis = new FileInputStream("./configAppData/commondata.properties");
		Properties prop = new Properties();
		prop.load(pfis);
		String data = prop.getProperty(key);
		return data;

	}

}
