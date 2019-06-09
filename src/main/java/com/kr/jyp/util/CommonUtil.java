package com.kr.jyp.util;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

	public static void encodingTest(String inputStr) {
		String [] charSet = {"utf-8","euc-kr","ksc5601","iso-8859-1","x-windows-949"};
		  
		for (int i=0; i<charSet.length; i++) {
			for (int j=0; j<charSet.length; j++) {
				try {
					System.out.println("[" + charSet[i] +"," + charSet[j] +"] = " + new String(inputStr.getBytes(charSet[i]), charSet[j]));
				}
				catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String transDateFormat(String inputDate, String turnType) {
		String resultData = "";
		
		if("".equals(turnType)) {
			resultData = inputDate.replace("-", "");
		}
		else if("-".equals(turnType)) {
			String tmpResultData = inputDate.replace("-", "");
			resultData = tmpResultData.substring(0,4)+ "-" + tmpResultData.substring(4,6)+ "-" + tmpResultData.substring(6,8);
		}
		
		return resultData;
	}
	
	
	
	public static void main(String[] args) {
		
		System.out.println(transDateFormat("2018-01-01","-"));
		
	}

}
