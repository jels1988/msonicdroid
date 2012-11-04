package lindley.desarrolloxcliente.to.upload;


import com.google.gson.annotations.SerializedName;

public class PosicionTO {
	
			@SerializedName("ANI")
	        public int anio;

	        @SerializedName("MES")
	        public int mes;


	        @SerializedName("SOV")
	        public String sovir;

	        @SerializedName("SOVM")
	        public String sovirMaximo;

	        @SerializedName("SOVD")
	        public String sovirDiferencia;

	        @SerializedName("PSG")
	        public String puntosSugeridos;

	        @SerializedName("PBN")
	        public String puntosBonus;

	        @SerializedName("PGN")
	        public String puntosGanados;

	        @SerializedName("FIN")
	        public String fotoInicial;

	        @SerializedName("FFN")
	        public String fotoFinal;

	        @SerializedName("CVR")
	        public String codigoVariable;

	        @SerializedName("FEN")
	        public String fechaEncuesta;

	        @SerializedName("OBS")
	        public String observacion;

	        @SerializedName("FCO")
	        public String fechaCompromiso;

	        @SerializedName("CON")
	        public String confirmacion;

	        @SerializedName("ORG")
	        public String origen;

	        @SerializedName("TPA")
	        public String tipoAgrupacion;

	        @SerializedName("CNA")
	        public String activosLindley;
	        
	        @SerializedName("EST")
	        public String estado;
	        
    
}
