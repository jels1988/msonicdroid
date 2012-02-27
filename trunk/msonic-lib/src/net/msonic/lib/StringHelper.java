package net.msonic.lib;

public final  class StringHelper {
	
	public static CharSequence null2CharSequence(CharSequence value){
		if(value==null){
			return "";
		}else{
			return value;
		}
	}
	
	public static String null2String(CharSequence value){
		if(value==null){
			return "";
		}else{
			return value.toString();
		}
	}
	
}
