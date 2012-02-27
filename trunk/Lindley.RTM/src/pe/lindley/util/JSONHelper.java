package pe.lindley.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;



public class JSONHelper {
	
	public static <T> String serializar(T obj){
		//String text = gson.fromJson(jsonDemo,T);
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	public static <T> T desSerializar(String obj,Type clazz){
		Gson gson = new Gson();
		return gson.fromJson(obj,clazz);
	}
	
}
