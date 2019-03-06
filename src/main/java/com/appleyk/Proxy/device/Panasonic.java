
package com.appleyk.Proxy.device;

/**
 * @author chenshihong02
 * @version 1.0
 * @created 2018/12/23 下午6:36
 **/
public class Panasonic {

	private double Temperature;
	private String id;
	private String location;
	private String deviceName;
    
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void down() {
    	Temperature-=0.5;
        System.out.println("松下空调降温");
    }
    
   
	public double getTemperature() {
		return Temperature;
	}

	public void setTemperature(double t) {
		Temperature = t;
	}
}