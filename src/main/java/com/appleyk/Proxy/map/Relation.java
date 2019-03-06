package com.appleyk.Proxy.map;

import com.appleyk.Proxy.Proxy.ProxyUtils;
import com.appleyk.Proxy.virtualObejct.AcReduceT;
import com.appleyk.Proxy.device.Airconditioner;
import com.appleyk.Proxy.device.Gree;
import com.appleyk.Proxy.device.Panasonic;
import com.appleyk.Proxy.device.Philips;
import com.appleyk.Proxy.device.Midea;
import com.appleyk.Proxy.device.Opple;
import com.appleyk.Proxy.runtime.AirCleaner;
import com.appleyk.Proxy.runtime.AirCleanerImpl;
import com.appleyk.Proxy.runtime.AirCondition;
import com.appleyk.Proxy.runtime.AirConditionImpl;
import com.appleyk.Proxy.runtime.AirConditioners;
import com.appleyk.Proxy.runtime.Light;
import com.appleyk.Proxy.runtime.LightImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appleyk.Proxy.runtime.AirCleanersImpl;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

/**
 * @author chenshihong02
 * @version 1.0
 * @created 2018/12/23 下午6:50
 **/
public class Relation {

	// 类之间的映射 即 k - 底层设备类 v - 运行时设备类
	public static Map<String, String> classMaps = new HashMap<>();

	// 方法之间的映射 即 k - 运行时api v - 底层设备api
	public static Map<String, List<String>> apiMaps = new HashMap<>();

	// 底层设备对象与运行时对象之间的映射 k - 运行时对象 v - 底层设备对象
	public static Map<Object, Object> objMaps = new HashMap<>();

	// 运行时对象标识与运行时对象的映射
	public static Map<String, Object> idObjmaps = new HashMap<>();

	// 运行时对象标识与底层设备id的映射
	public static Map<String, String> idmaps = new HashMap<>();

	// 服务id与设备id的映射
	public static Map<String, String> SerDevMaps = new HashMap<>();

	/**
	 * 这边应该是读取配置文件得到映射关系 ，但我这边直接初始化映射关系
	 */
	public static void config() throws Exception {

		// 类之间的映射关系
		// 模拟配置
		String packageUnderDevice = "com.appleyk.Proxy.device";
		String configUnderDevice = "Gree";
		String UDString = packageUnderDevice + "." + configUnderDevice;

		String packageRuntimeDevice = "com.appleyk.Proxy.runtime";
		String configRutimeDevice = "AirConditionImpl";
		String RTString = packageRuntimeDevice + "." + configRutimeDevice;

		// 空调
		Class<?> underDevice = Class.forName(UDString);
		Class<?> runtimeDevice = Class.forName(RTString);
		classMaps.put(underDevice.getName(), runtimeDevice.getName());
		classMaps.put(Panasonic.class.getName(), AirConditionImpl.class.getName());
		// 电灯
		classMaps.put(Midea.class.getName(), LightImpl.class.getName());
		classMaps.put(Opple.class.getName(), LightImpl.class.getName());
		// 空气净化器
		classMaps.put(Philips.class.getName(), AirCleanerImpl.class.getName());

		// 方法之间的映射关系
		// 1.1空调的降温方法
		apiMaps.put(AirCondition.class.getName() + "." + AirCondition.class.getMethod("cool").getName(),
				Arrays.asList(new String[] { Gree.class.getName() + "." + Gree.class.getMethod("cool").getName(),
						Panasonic.class.getName() + "." + Panasonic.class.getMethod("down").getName() }));
		apiMaps.put(AirCondition.class.getName() + "." + AirCondition.class.getMethod("setT", double.class).getName(),
				Arrays.asList(new String[] {
						Gree.class.getName() + "." + Gree.class.getMethod("setTemperature", double.class).getName(),
						Panasonic.class.getName() + "."
								+ Panasonic.class.getMethod("setTemperature", double.class).getName() }));

		// 1.2空调的设置、获取id方法
		apiMaps.put(AirCondition.class.getName() + "." + AirCondition.class.getMethod("setID", String.class).getName(),
				Arrays.asList(new String[] {
						Gree.class.getName() + "." + Gree.class.getMethod("setId", String.class).getName(),
						Panasonic.class.getName() + "."
								+ Panasonic.class.getMethod("setId", String.class).getName() }));
		apiMaps.put(AirCondition.class.getName() + "." + AirCondition.class.getMethod("getID").getName(),
				Arrays.asList(new String[] { Gree.class.getName() + "." + Gree.class.getMethod("getId").getName(),
						Panasonic.class.getName() + "." + Panasonic.class.getMethod("getId").getName() }));
		// 1.3空调的设置获取地点方法
		apiMaps.put(
				AirCondition.class.getName() + "." + AirCondition.class.getMethod("setLName", String.class).getName(),
				Arrays.asList(new String[] {
						Gree.class.getName() + "." + Gree.class.getMethod("setLocation", String.class).getName(),
						Panasonic.class.getName() + "."
								+ Panasonic.class.getMethod("setLocation", String.class).getName() }));
		apiMaps.put(AirCondition.class.getName() + "." + AirCondition.class.getMethod("getLName").getName(),
				Arrays.asList(new String[] { Gree.class.getName() + "." + Gree.class.getMethod("getLocation").getName(),
						Panasonic.class.getName() + "." + Panasonic.class.getMethod("getLocation").getName() }));

	}

//	public static <T> 

	/**
	 * 通过配置文件生成底层设备，这边我就手动写设备
	 */
	public static void generateDeviceAndRuntime() throws Exception {

		List<Object> oList = new ArrayList<Object>();
//		List<HashMap<String, Object>> idObjList = new ArrayList<HashMap<String,Object>>();

		// 底层设备生成 返回一个运行时对象
		AirCondition gree = (AirCondition) generate(Gree.class.getName());
		AirCondition panasonic = (AirCondition) generate(Panasonic.class.getName());

		// 运行时对象调用
		Object dObject = findUnderDByRuntimeD(objMaps.get(gree));

		AirCondition ndAirCondition = (AirCondition) dObject;
		ndAirCondition.setID("A0");
		ndAirCondition.getID();
		ndAirCondition.setT(100);
		ndAirCondition.cool();
		ndAirCondition.setLName("bedroom");
		System.out.println(ndAirCondition.getLName());
		idObjmaps.put(String.valueOf(gree.hashCode()), objMaps.get(gree));
		idmaps.put(gree.getID(), String.valueOf(gree.hashCode()));

		oList.add(objMaps.get(gree));
		panasonic.cool();
		panasonic.setID("A2");
		idObjmaps.put(String.valueOf(panasonic.hashCode()), objMaps.get(panasonic));
		idmaps.put(panasonic.getID(), String.valueOf(panasonic.hashCode()));
		oList.add(objMaps.get(panasonic));
//		
		System.out.println(idmaps);
		System.out.println(idObjmaps);
		System.out.println(oList);

		// 运行时空调对象集合，有添加空调的方法addlist和列出运行时空调的方法list
		AirConditioners acs = new AirConditioners();
		// 遍历运行时对象标识与底层设备id的映射，添加运行时设备对应的底层设备id
		for (Map.Entry<String, String> mEntry : idmaps.entrySet()) {
			acs.addlist(mEntry.getKey());
		}
		// 列出运行时的空调对应的底层空调
		acs.list();
//		String ServiceId, String DeviceId, String RutimeDeviceId, String DName,
//		String LName, String CType, String Effect, String Status, double SValue, Object obj
		String ServiceId = "S11";
		String DeviceId = findUnderid(gree.hashCode());
		String RutimeDeviceId = String.valueOf(gree.hashCode());
		String DName = classMaps.get(objMaps.get(gree).getClass().getName());
		String LName = "bedroom";
//		Field[] fields = objMaps.get(gree).getClass().getDeclaredFields();
		String CType="Temperature";
		String Effect = "Reduce";
		String Status = "Off";
		double SValue =22.0;
		AcReduceT coolService = new AcReduceT();
		AcReduceT coolS = (AcReduceT) initService(ServiceId, DeviceId, RutimeDeviceId, DName, LName, CType,
				Effect, Status,SValue, coolService);

		coolS.doService();
		
		System.out.println(coolS.getSValue());
		SerDevMaps.put(coolS.getServiceId(), coolS.getRutimeDeviceId());
		System.out.println(SerDevMaps);

	}

	/**
	 * device 底层设备类名
	 * 
	 * @param device
	 */
	private static Object generate(String device) throws Exception {
		// 生成底层设备对象
		Object deviceObj = Class.forName(device).newInstance();
//		System.out.println("deviceObj:" + deviceObj.hashCode());
		// 通过类映射关系 获取到底层设备 对应的 运行时类
		for (String deviceType : classMaps.keySet()) {

			if (deviceType.equals(device)) {
				String runtimeType = classMaps.get(deviceType);

				Class<?> runtimeClass = Class.forName(runtimeType);
				// 生成运行时对象
				Object runtimeObj = runtimeClass.newInstance();

				// 将运行时对象的类型设置成底层设备的类型
				Field type = runtimeClass.getDeclaredField("type");

				// 获得各个设备的方法
				Method[] methods = runtimeClass.getDeclaredMethods();

				String functionType = "";

				for (Method m : methods) {
					String temp = m.toString();
					String[] ff = temp.split("\\.");
					String[] ff2 = ff[2].split("\\(");
					functionType = ff2[0];
//				Method method = runtimeClass.getDeclaredMethod(functionType);

				}

				type.setAccessible(true);
				type.set(runtimeObj, deviceType);

//				System.out.println(runtimeObj);
//				System.out.println("runtimeObj:" + runtimeObj.hashCode());
				// 生成运行时对象的代理对象
				Object proxyObj = ProxyUtils.getProxy(runtimeObj);

				// 将运行时对象代理与底层设备对象放入objMaps
				objMaps.put(proxyObj, deviceObj);
//				System.out.println(proxyObj);
				return proxyObj;
			}
		}
		return null;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */


	public static void main(String[] args) throws Exception {
//		System.out.println("hello");
		config();
		generateDeviceAndRuntime();
	}

//根据运行时对象找底层设备对象
	public static Object findUnderDByRuntimeD(Object p) {

		Object dObject = null;
		for (Object o : objMaps.keySet()) {
			if (objMaps.get(o).hashCode() == p.hashCode()) {
				dObject = o;
			}
		}
		return dObject;
	}

	// 根据运行时对象id找底层设备对象id
	public static String findUnderid(int p) {
		String iString = String.valueOf(p);
		String d = "";
		for (String o : idmaps.keySet()) {

			if (idmaps.get(o).equals(iString)) {
				d = o;
			}

		}
		return d;

	}

	public static Object initService(String ServiceId, String DeviceId, String RutimeDeviceId, String DName,
			String LName, String CType, String Effect, String Status, double SValue, Object obj) {
		AcReduceT objAcReduceT = new AcReduceT();
		objAcReduceT.setServiceId(ServiceId);
		objAcReduceT.setDeviceId(DeviceId);
		objAcReduceT.setRutimeDeviceId(RutimeDeviceId);
		objAcReduceT.setDName(DName);
		objAcReduceT.setLName(LName);
		objAcReduceT.setCType(CType);
		objAcReduceT.setEffect(Effect);
		objAcReduceT.setStatus(Status);
		objAcReduceT.setSValue(SValue);

		obj = objAcReduceT;
		return obj;

	}
	
	public static String StrPackName(String s) {
		String rString="";
		
		
		
		return s;
		
	}
	
}
