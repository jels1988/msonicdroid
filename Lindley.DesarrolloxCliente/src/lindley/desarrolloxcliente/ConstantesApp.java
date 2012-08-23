package lindley.desarrolloxcliente;

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
}
