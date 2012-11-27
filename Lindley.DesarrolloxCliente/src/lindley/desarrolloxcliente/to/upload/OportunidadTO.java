package lindley.desarrolloxcliente.to.upload;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OportunidadTO {
	
		public OportunidadTO(){
			listaAccionesTrade = new ArrayList<AccionTradeTO>();
			
			concrecion = ConstantesApp.RESPUESTA_NO;
			concrecionActual = ConstantesApp.RESPUESTA_NO;
			concrecionCumple = ConstantesApp.RESPUESTA_NO;
			
			respetoPrecio = ConstantesApp.RESPUESTA_NO;
			respetoPrecioActual = ConstantesApp.RESPUESTA_NO;
			respetoPrecioCumple = ConstantesApp.RESPUESTA_NO;
			
			numeroSabores = "2";
			numeroSaboresActual = "2";
			numeroSaboresCumple = ConstantesApp.RESPUESTA_NO;
			
			sovi = "0";
			soviActual = "0";
			soviCumple = ConstantesApp.RESPUESTA_NO;
			
			puntosSugeridos="0";
			puntosGanados="0";
			puntosBonus="0";
			
			codigoAccionTrade="0";
			accionTrade="";
			
			confirmacion =  ConstantesApp.RESPUESTA_NO;
			origen =  ConstantesApp.OPORTUNIDAD_SISTEMA;
			estado =  ConstantesApp.OPORTUNIDAD_ABIERTA;
		}
		
		@Expose()
	    public long id;
		
		/*@Expose()
	    public long productoId;
*/
		@Expose()
		public boolean seleccionado;
		
			@SerializedName("EID")
		    public long evaluacionId;
	
			@SerializedName("ANI")
	        public int anio;

	        @SerializedName("MES")
	        public int mes;

	        @SerializedName("ART")
	        public String codigoArticulo;
	        
	        @SerializedName("DART")
	        public String articulo;

	        @SerializedName("CNR")
	        public String concrecion;

	        @SerializedName("CNRA")
	        public String concrecionActual;

	        @SerializedName("CNRC")
	        public String concrecionCumple;

	        @SerializedName("SOV")
	        public String sovi;

	        @SerializedName("SOVA")
	        public String soviActual;

	        @SerializedName("SOVC")
	        public String soviCumple;

	        @SerializedName("RPR")
	        public String respetoPrecio;

	        @SerializedName("RPRA")
	        public String respetoPrecioActual;

	        @SerializedName("RPRC")
	        public String respetoPrecioCumple;

	        @SerializedName("NSB")
	        public String numeroSabores;

	        @SerializedName("NSBA")
	        public String numeroSaboresActual;

	        @SerializedName("NSBC")
	        public String numeroSaboresCumple;

	        @SerializedName("CTR")
	        public String codigoAccionTrade;

	        @SerializedName("TRA")
	        public String accionTrade;

	        @SerializedName("PSG")
	        public String puntosSugeridos;

	        @SerializedName("PBN")
	        public String puntosBonus;

	        @SerializedName("PGN")
	        public String puntosGanados;

	        @SerializedName("FCO")
	        public String fechaCompromiso;

	        @SerializedName("CON")
	        public String confirmacion;

	        @SerializedName("ORG")
	        public String origen;

	         @SerializedName("EST")
	        public String estado;

	         @SerializedName("LEG")
	        public String legacy;
	         
	         @SerializedName("FLP")
		     public int proceso;
	         
	         
	         public List<AccionTradeTO> listaAccionesTrade;
}
