package lindley.desarrolloxcliente.to.upload;


import lindley.desarrolloxcliente.ConstantesApp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PresentacionTO {

	public PresentacionTO(){
		 	//listaSKU = new ArrayList<SKUPresentacionCompromisoTO>();
	        puntosSugeridos = "0";
	        puntosBonus = "0";
	        puntosGanados="0";
	        
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

    @SerializedName("TPA")
    public String tipoAgrupacion;

    @SerializedName("CVR")
    public String codigoVariable;
    
    @SerializedName("FDE")
    public String codigoFDE;
    
    @SerializedName("FEN")
    public String fechaEncuesta;
    
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

}
