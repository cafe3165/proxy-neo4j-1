package com.appleyk.Proxy.virtualObejct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Locations {
	public List<String> LocationIdList = new ArrayList<String>();

	public void addlist(String LId) {
		LocationIdList.add(LId);
	}

	public List<String> list() {
		System.out.println(LocationIdList);
		return LocationIdList;

	}

//	列出位置的所有属性
	public void ListProperties(String LocationId, Map<String, Object> locationMap) {
		Object location = null;
		for(String lId:locationMap.keySet()) {
			if(lId.equals(LocationId)) {
				location=locationMap.get(lId);
			}
		}
		
		Location l=(Location)location;
		System.out.println(l.getLId());
		System.out.println(l.getLName());
		System.out.println(l.getsList());
		System.out.println(l.getdList());


	}

}
