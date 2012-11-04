package lindley.desarrolloxcliente.to.upload;

import com.google.gson.annotations.SerializedName;

public class OportunidadTO {

	
			@SerializedName("ANI")
	        public int anio;

	        @SerializedName("MES")
	        public int mes;

	        @SerializedName("ART")
	        public String codigoArticulo;

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

	        @SerializedName("CTR")
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
	         
}
