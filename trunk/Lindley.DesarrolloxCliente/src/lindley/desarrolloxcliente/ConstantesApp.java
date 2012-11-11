package lindley.desarrolloxcliente;

import java.io.File;
import java.util.Calendar;

import android.content.Context;
import android.os.Environment;
import android.text.format.DateFormat;

public final class ConstantesApp {

	public static final int EVALUACION_TIENE_CAMBIOS=1;
	public static final int EVALUACION_NO_TIENE_CAMBIOS=0;
	
	public static final String RESPUESTA_SI = "S";
	public static final String RESPUESTA_NO = "N";
	public static final String RESPUESTA_NO_TIENE = "T";
	
	
	public static final String RESPUESTA_NO_LARGA = "N0";
	public static final String RESPUESTA_SI_LARGA = "SI";
	public static final String RESPUESTA_NO_TIENE_LARGA = "NV";
	
	/*
	public static final String SKU_RESPUESTA_SI = "S";
	public static final String SKU_RESPUESTA_NO = "N";
	public static final String SKU_RESPUESTA_NO_TIENE = "T";
	*/
	
	
	public final static String OPORTUNIDAD_SISTEMA = "1";
	public final static String OPORTUNIDAD_DESARROLLADOR_ABIERTO = "A";
	public final static String OPORTUNIDAD_ABIERTA = "A";
	public final static String OPORTUNIDAD_ABIERTA_LARGA = "Abierta";
	public final static String OPORTUNIDAD_CERRADA = "C";
	public final static String OPORTUNIDAD_CERRADA_LARGA = "Cerrada";
	
	public final static String TIPO_AGRUPRACION_INVENTARIO="1";
	public final static String TIPO_AGRUPRACION_POSICION="2";
	public final static String TIPO_AGRUPRACION_PRESENTACION="3";
	

	public static final String VARIABLE_RED_SKU_PRIORITARIOS = "01";
	public static final String VARIABLE_RED_ESTANDAR_EXHIBICION = "02";
	public static final String VARIABLE_RED_ESTANDAR_ANAQUEL = "03";
	public static final String VARIABLE_RED_POSICION_DOMINANTE = "04";
	public static final String VARIABLE_RED_PRECIO_MERCADO = "05";
	public static final String VARIABLE_RED_POP = "06";
	
	public final static boolean isSI(String estado){
		if((estado==null) || (estado.trim().equalsIgnoreCase("")) || (estado.equalsIgnoreCase(RESPUESTA_NO))){
			return false;
		}else{
			return true;
		}
	}
	public final static String getFechaSistema(){
		return DateFormat.format("dd/MM/yyyy", new java.util.Date()).toString();
	}
	
	public final static String getFormatFecha(String fecha){
		if(fecha==null) return "";
		int[] factores = getFechaFactores(fecha);
		String fechaFormat = String.format("%s/%s/%s", factores[2],factores[1], factores[0]);
		return fechaFormat;
	}
	
	public final static String formatFecha(String fecha){
		if(fecha==null || fecha.equals("") || fecha.equals("0")) return "";
		String[] factores = getFechaFactoresAS400(fecha);
		String fechaFormat = String.format("%s/%s/%s", factores[2],factores[1], factores[0]);
		return fechaFormat;
	}
	
	public final static String formatHora(String hora){
		if(hora==null || hora.equals("0") || hora.equals("0"))   return "";
		String[] factores = getHoraFactoresAS400(hora);
		String horaFormat = String.format("%s:%s", factores[0],factores[1]);
		return horaFormat;
	}
	
	public final static String getFechaSistemaAS400(){
		return DateFormat.format("yyyyMMdd", new java.util.Date()).toString();
	}
	
	public final static String getHoraSistemaAS400(){
		return DateFormat.format("kkmm", new java.util.Date()).toString();
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
	
	public final static String[] getFechaFactoresAS400(String fecha){
		
		String[] factores = new String[3];
		
		final Calendar calendar = Calendar.getInstance();
		factores[0] = String.valueOf(calendar.get(Calendar.YEAR));        
		factores[1] = String.valueOf(calendar.get(Calendar.MONTH)+1);        
		factores[2] = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		
		if(null==fecha){
			return factores;
		}
		

		if (fecha.length() >= 8) {
			factores[0]= fecha.substring(0,4);
			factores[1] = fecha.substring(4,6);
			factores[2] = fecha.substring(6,8);
		}
		
		return factores;
		
	}
	public final static String[] getHoraFactoresAS400(String hora){
		
	String[] factores = new String[2];
	
		final Calendar calendar = Calendar.getInstance();
		factores[0] = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) ;        
		factores[1] = String.valueOf(calendar.get(Calendar.MINUTE));        
	
		
		if(null==hora){
			return factores;
		}
		
		
		if (hora.length() >= 4) {
			factores[0]= hora.substring(0,2);
			factores[1] = hora.substring(2,4);
			
		}
		
		return factores;
	}
	
	 public static File getTempFile(Context context,String file_name){
		    
		   final File path = new File( Environment.getExternalStorageDirectory(), context.getPackageName() );
		   
		    if(!path.exists()){
		    	path.mkdir();
		    }
		    return new File(path, file_name); 
		    }
}
