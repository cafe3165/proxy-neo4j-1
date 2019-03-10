package com.appleyk.Proxy.virtualObejct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appleyk.Proxy.runtime.AirCondition;
import com.appleyk.Proxy.virtualObejct.Service;

public class Services {
	public List<String> sl = new ArrayList<String>();
//	public HashMap<Object,List<String>> propertiesList=new HashMap<Object,List<String>>();
	public static Map<String, String> sdmapHashMap = new HashMap<>();

	public void addlist(Map<String, String> sd) {
		for (String s : sd.keySet()) {
			sl.add(s);
		}
	}

	public List<String> list() {
		System.out.println(sl);
		return sl;
	}

	public List<String> getList() {
		return sl;
	}

	public void ListProperties(String SerId, Map<String, Object> sMap) {
		System.out.println(SerId);
		System.out.println(sMap);
		Service s = new Service();
		for (String sid : sMap.keySet()) {
			if (SerId.equals(sid))
				s = (Service) sMap.get(sid);

		}
		System.out.println("ServiceId: " + s.getServiceId());
		System.out.println("DeviceId: " + s.getDeviceId());
		System.out.println("RutimeDeviceId: " + s.getRutimeDeviceId());
		System.out.println("DName: " + s.getDName());
		System.out.println("LName: " + s.getLName());
		System.out.println("CType: " + s.getCType());
		System.out.println("Effect: " + s.getEffect());
		System.out.println("Status: " + s.getStatus());
		System.out.println("SValue: " + s.getSValue());

	}

	public void SetDevProperties(String SerId, String Value, Map<String, String> SerDevMaps,
			Map<String, String> idmaps,	Map<String, Object> idObjmaps) {

		System.out.println(SerId + " " + Value);
		System.out.println(SerDevMaps);
		System.out.println(SerDevMaps.get(SerId));
		System.out.println(idmaps);
		System.out.println(idObjmaps);
		
		String p=SerDevMaps.get(SerId);
		
		Object acDevice=idObjmaps.get(SerDevMaps.get(SerId));
//		AirCondition ac=(AirCondition)acDevice;
		
		String d = "";
		for (String o : idmaps.keySet()) {

			if (idmaps.get(o).equals(p)) {
				d = o;
			}

		}
		System.out.println(d);
		
	}
}
