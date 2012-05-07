package pe.com.agrobanco.sms.service;

import com.google.gson.annotations.SerializedName;

public class SMSData {

	@SerializedName("id")
	public long _id;
	
	@SerializedName("pho")
	public String phone;
	
	@SerializedName("sms")
	public String sms;
	
	@SerializedName("fec")
	public String fecha;
	
}
