package com.appleyk.Proxy.virtualObejct;

public interface Service {
	public String getServiceId() ;
	public void setServiceId(String serviceId) ;
	public String getDeviceId() ;
	public void setDeviceId(String deviceId) ;
	public String getRutimeDeviceId() ;
	public void setRutimeDeviceId(String rutimeDeviceId) ;
	public String getDName() ;
	public void setDName(String dName) ;
	public String getLName() ;
	public void setLName(String lName) ;
	public String getCType() ;
	public void setCType(String cType) ;
	public String getEffect() ;
	public void setEffect(String effect) ;
	public String getStatus() ;
	public void setStatus(String status) ;
	public double getSValue() ;
	public void setSValue(double sValue) ;
	
	public void doService() ;

}
