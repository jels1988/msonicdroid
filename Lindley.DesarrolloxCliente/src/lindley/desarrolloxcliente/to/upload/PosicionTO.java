package lindley.desarrolloxcliente.to.upload;


import lindley.desarrolloxcliente.ConstantesApp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PosicionTO {
		
	public PosicionTO(){
		
		confirmacion = ConstantesApp.RESPUESTA_NO;;
		fotoInicial = "";
		fotoFinal = " ";
		puntosGanados="0";
		puntosSugeridos="0";
		puntosBonus="0";
		sovirMaximo = "0";
		sovir="0";
		
		activosLindley = ConstantesApp.RESPUESTA_NO;
		confirmacion =  ConstantesApp.RESPUESTA_NO;
		origen =  ConstantesApp.OPORTUNIDAD_SISTEMA;
		estado =  ConstantesApp.OPORTUNIDAD_ABIERTA;
	}
	
	@Expose()
    public long id;

			@SerializedName("EID")
		    public long evaluacionId;
			
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
