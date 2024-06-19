package practice.TestNG;

import java.util.Date;

public class CaptureTimeStamp {
public static void main(String[] args) {
	
	String Time = new Date().toString().replace(" ", "_").replace(":", "_");
	System.out.println(Time);
	
	
	
}
	
	
}
