package lindley.desarrolloxcliente.to.upload;


import java.util.ArrayList;
import java.util.List;

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
		sovirDiferencia="0";
		sovir="0";
		
		activosLindley = ConstantesApp.RESPUESTA_NO;
		confirmacion =  ConstantesApp.RESPUESTA_NO;
		origen =  ConstantesApp.OPORTUNIDAD_SISTEMA;
		estado =  ConstantesApp.OPORTUNIDAD_ABIERTA;
		
		compromisos = new ArrayList<PosicionCompromisoTO>();
	}
	
			@Expose()
		    public long id;

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
	        @SerializedName("SOV")
	        public String sovir;

			@Expose()
	        @SerializedName("SOVM")
	        public String sovirMaximo;

			@Expose()
	        @SerializedName("SOVD")
	        public String sovirDiferencia;

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
	        @SerializedName("FIN")
	        public String fotoInicial;

			@Expose()
	        @SerializedName("FFN")
	        public String fotoFinal;

			@Expose()
	        @SerializedName("CVR")
	        public String codigoVariable;

			@Expose()
	        @SerializedName("FEN")
	        public String fechaEncuesta;

			@Expose()
	        @SerializedName("OBS")
	        public String observacion;

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
	        @SerializedName("TPA")
	        public String tipoAgrupacion;

			@Expose()
	        @SerializedName("CNA")
	        public String activosLindley;
	        
			@Expose()
	        @SerializedName("EST")
	        public String estado;
	        
			@Expose()
	        @SerializedName("COM")
	        public List<PosicionCompromisoTO> compromisos;
	        
			@Expose()
	        @SerializedName("FLP")
	        public int proceso;
    
}
