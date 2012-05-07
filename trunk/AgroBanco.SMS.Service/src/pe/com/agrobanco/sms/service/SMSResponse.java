package pe.com.agrobanco.sms.service;

import com.google.gson.annotations.SerializedName;

public class SMSResponse {
	
	@SerializedName("sts")
	public int status;
	
	@SerializedName("sms")
	public String sms;
	
}
