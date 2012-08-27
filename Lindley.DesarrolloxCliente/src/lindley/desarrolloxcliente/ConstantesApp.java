package lindley.desarrolloxcliente;

import java.util.Calendar;

import android.text.format.DateFormat;

public final class ConstantesApp {

	
	public static final String RESPUESTA_SI = "S";
	public static final String RESPUESTA_NO = "N";
	public static final String RESPUESTA_NO_TIENE = "T";
	
	
	public static final String RESPUESTA_NO_LARGA = "N0";
	public static final String RESPUESTA_SI_LARGA = "SI";
	
	/*
	public static final String SKU_RESPUESTA_SI = "S";
	public static final String SKU_RESPUESTA_NO = "N";
	public static final String SKU_RESPUESTA_NO_TIENE = "T";
	*/
	
	
	public final static String OPORTUNIDAD_SISTEMA = "1";
	
	
	public final static String OPORTUNIDAD_DESARROLLADOR_ABIERTO = "A";
	
	
	
	
	public final static String getFechaSistema(){
		return DateFormat.format("dd/MM/yyyy", new java.util.Date()).toString();
	}
	
	public final static String formatPorcentaje(double number,int decimales){
		
		return String.format("%s", number);
		
	}
	public static String LPad(String str, int length, char car) {
		if(str.length()>=length)
			return str;
		
		
		  return str + String.format("%" + (length - str.length()) + "s", "").replace(" ", String.valueOf(car));
		}

		public static String RPad(String str, int length, char car) {
			if(str.length()>=length)
				return str;
			
		  return String.format("%" + (length - str.length()) + "s", "").replace(" ", String.valueOf(car)) +str;
		}
	
	
		/*
	public final static String getFechaFormatAS400(String fecha){

		if(null!=fecha){
			if (fecha.length() >= 7) {
				
				int anio = Integer.parseInt(fecha.substring(0, 4));
				int mes = Integer.parseInt(fecha.substring(4, 6)) - 1;
				int dia = Integer.parseInt(fecha.substring(6));
				
				final Calendar calendar = Calendar.getInstance();
				calendar.set(anio, mes, dia);
				
				return DateFormat.format("dd/MM/yyyy", calendar).toString();
			}else{
				return "";
			}
		}else{
			return "";
		}
	}*/
	
	public final static int[] getFechaFactores(String fecha){
		
		int[] factores = new int[3];
	
		final Calendar calendar = Calendar.getInstance();
		factores[0] = calendar.get(Calendar.YEAR);        
		factores[1] = calendar.get(Calendar.MONTH);        
		factores[2] = calendar.get(Calendar.DAY_OF_MONTH);
		
		if(null==fecha){
			return factores;
		}
		
		//01/01/2012
		if (fecha.length() >= 7) {
			factores[0]= Integer.parseInt(fecha.substring(6));
			factores[1] = Integer.parseInt(fecha.substring(3, 5	))-1;
			factores[2] = Integer.parseInt(fecha.substring(0,2));
			
		}
		
		return factores;
	}
}
