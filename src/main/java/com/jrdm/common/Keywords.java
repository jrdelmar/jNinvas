package com.jrdm.common;

import java.util.ArrayList;
import java.util.List;

public final class Keywords {
	
	
	public static List<String> getBannerDangerWords(){
			
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("default");
		keywords.add("password");
		
		return keywords;
	
	}
	
	public static List<String> searchFilterWordsForExploit(){
		
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("default");
		keywords.add("password");
		
		return keywords;
	
	}
}