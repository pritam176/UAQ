package com.pkm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;


@Service
public class ProductService {
	
	
	public Map<String , String> getProuducttypeList(){
		Map<String , String> prodictList =new HashMap<String , String>();
		prodictList.put("1", "TV");
		prodictList.put("2", "MOBILE");
		prodictList.put("3", "CAR");
		prodictList.put("4", "WASING MACHINE");
		
		return prodictList;
		
	}
	
	public Map<String , String> getProuductSubtypeList(String key){
		Map<String , String> tvList =new HashMap<String , String>();
		tvList.put("1", "LG");
		tvList.put("2", "SAMSUNG");
		tvList.put("3", "SONY");
		tvList.put("4", "PHILIPS");
		
		Map<String , String> mobileList =new HashMap<String , String>();
		mobileList.put("1", "NOKIA");
		mobileList.put("2", "Apple");
		mobileList.put("3", "SONY");
		mobileList.put("4", "LAVA");
		
		Map<String , String> carList =new HashMap<String , String>();
		carList.put("1", "BMW");
		carList.put("2", "SKODA");
		carList.put("3", "JAGUAR");
		carList.put("4", "Rolls-Royce");
		
		Map<String, Map<String, String>> dataMap =new HashMap<String, Map<String,String>>();
		dataMap.put("1", tvList);
		dataMap.put("2", mobileList);
		dataMap.put("3", carList);
		
		//System.out.println(dataMap);
		
		return dataMap.get(key);
		
	}

}
