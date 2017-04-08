package com.jrdm.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fooock.shodan.model.banner.Banner;

public final class Utility {
	
	/**
	 * When shodan returns an array, when it is prefixed with an ! or - 
	 * then it means it is not included 
	 * ex. in opts.vuln !CVE-2014-0160 means it is not vulnerable
	 * returns an ArrayList
	 * @author joannarosedelmar
	 * @param strArray
	 * @return
	 */
	public static List<String> cleanArray(String[] strArray){
		
		ArrayList<String> cleanArr = new ArrayList<String>();
		if(strArray != null){
			for (String s : strArray){
				if(! s.trim().substring(0, 1).equals("-") &&
						! s.trim().substring(0, 1).equals("!")){
					cleanArr.add(s);
				}
			}
		}

		
		return cleanArr;
		
	}

	public static String[] cleanArray2(String[] strArray){

		String[] cleanArr = new String[strArray.length];

		int i = 0;
		for (String s : strArray){

			if(! s.trim().substring(0, 1).equals("-") &&
					! s.trim().substring(0, 1).equals("!")){
				cleanArr[i] = s.trim();
			}
			i++;
		}

		return cleanArr;

	}


	public static void clean(String[] strArray){

		String[] cleanArr = new String[strArray.length];

		int i = 0;
		for (String s : strArray){

			if(! s.trim().substring(0, 1).equals("-") &&
					! s.trim().substring(0, 1).equals("!")){
				cleanArr[i] = s.trim();
			}
			i++;
		}

	}
	
	public static List<String> cleanDistinct(List<String> strList){
		
		List<String> newList = new ArrayList<String>();
		for (String s : strList){
			if(!newList.contains(s) 
					&& !s.equalsIgnoreCase("unknown")){
				newList.add(s);
			}
		}
		return newList;
	}
	
	public static List<String> getProductList(List<Banner> bannerList){
		
		List<String> newList = new ArrayList<String>();
		for (Banner s : bannerList){
			if(!newList.contains(s.getProduct()) 
					&& !s.getProduct().equalsIgnoreCase("unknown")){
				newList.add(s.getProduct());
			}
		}
		return newList;
	}
	
	public static boolean isNotNullEmpty(Object obj){
		if (obj==null)
			return false;
		
		if(obj instanceof String){
			if(((String) obj).trim().equals("")) return false;
		}
		
		if(obj instanceof List){
			if(((List<?>) obj).size() == 0) return false;
		}
		
		return true;
	}

	public static String getCurrentDateForDb(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date);
    }




}
