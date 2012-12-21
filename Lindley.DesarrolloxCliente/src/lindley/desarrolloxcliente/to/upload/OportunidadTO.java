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
		
		@Expose()
			@SerializedName("EID")
		    public long evaluacionId;
	
		@Expose()
			@SerializedName("ANI")
	        public int anio;

		@Expose()
	        @SerializedName("MES")
	        public int mes;

		@Expose()
	        @SerializedName("ART")
	        public String codigoArticulo;
	        
		@Expose()
	        @SerializedName("DART")
	        public String articulo;

		@Expose()
	        @SerializedName("CNR")
	        public String concrecion;

		@Expose()
	        @SerializedName("CNRA")
	        public String concrecionActual;

		@Expose()
	        @SerializedName("CNRC")
	        public String concrecionCumple;

		@Expose()
	        @SerializedName("SOV")
	        public String sovi;

		@Expose()
	        @SerializedName("SOVA")
	        public String soviActual;

		@Expose()
	        @SerializedName("SOVC")
	        public String soviCumple;

		@Expose()
	        @SerializedName("RPR")
	        public String respetoPrecio;

		@Expose()
	        @SerializedName("RPRA")
	        public String respetoPrecioActual;

		@Expose()
	        @SerializedName("RPRC")
	        public String respetoPrecioCumple;

		@Expose()
	        @SerializedName("NSB")
	        public String numeroSabores;

		@Expose()
	        @SerializedName("NSBA")
	        public String numeroSaboresActual;

		@Expose()
	        @SerializedName("NSBC")
	        public String numeroSaboresCumple;

		@Expose()
	        @SerializedName("CTR")
	        public String codigoAccionTrade;

		@Expose()
	        @SerializedName("TRA")
	        public String accionTrade;

		@Expose()
	        @SerializedName("PSG")
	        public String puntosSugeridos;

		@Expose()
	        @SerializedName("PBN")
	        public String puntosBonus;

		@Expose()
	        @SerializedName("PGN")
	        public String puntosGanados;

		@Expose()
	        @SerializedName("FCO")
	        public String fechaCompromiso;

		@Expose()
	        @SerializedName("CON")
	        public String confirmacion;

		@Expose()
	        @SerializedName("ORG")
	        public String origen;

		@Expose()
	         @SerializedName("EST")
	        public String estado;

		@Expose()
	         @SerializedName("LEG")
	        public String legacy;
	         
		@Expose()
	         @SerializedName("FLP")
		     public int proceso;
	         
		@Expose()
	         public List<AccionTradeTO> listaAccionesTrade;
}
