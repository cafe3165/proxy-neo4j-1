package com.appleyk.Proxy.virtualObejct;

import java.util.ArrayList;
import java.util.List;

public class Services {
public List<String> sl=new ArrayList<String>();
	
	public void addlist(String o) {
		sl.add(o);
	}
	
	public void list(){
		System.out.println(sl);
	
			
	}
	
	public List<String> getList(){
		return sl;
	}

}
