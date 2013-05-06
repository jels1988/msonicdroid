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
	        tipoMarcado=ConstantesApp.TIPO_MARCADO_NORMAL;
	        confirmacion =  ConstantesApp.RESPUESTA_NO;
			origen =  ConstantesApp.OPORTUNIDAD_SISTEMA;
			estado =  ConstantesApp.OPORTUNIDAD_ABIERTA;
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
    @SerializedName("TPA")
    public String tipoAgrupacion;

	@Expose()
    @SerializedName("CVR")
    public String codigoVariable;
    
	@Expose()
    @SerializedName("FDE")
    public String codigoFDE;
    
	@Expose()
    @SerializedName("FEN")
    public String fechaEncuesta;
    
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
    @SerializedName("FLP")
    public int proceso;


	@Expose()
    @SerializedName("TMR")
    public String tipoMarcado;
}
