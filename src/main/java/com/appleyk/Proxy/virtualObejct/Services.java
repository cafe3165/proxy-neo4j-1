package com.appleyk.Proxy.virtualObejct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Services {
	public List<String> sl = new ArrayList<String>();
//	public HashMap<Object,List<String>> propertiesList=new HashMap<Object,List<String>>();
	public void addlist(String o) {
		sl.add(o);
	}

	public void list() {
		System.out.println(sl);

	}

	public List<String> getList() {
		return sl;
	}

	public void ListProperties(List<Object> olist) {
		
		

		System.out.println();

	}

}
