package lindley.desarrolloxcliente.to.upload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkuTO {
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
    
    @SerializedName("CSKU")
    public String codigoSKU;
	
    @SerializedName("DSKU")
    public String descripcionSKU;
    
    @SerializedName("MAC")
    public String marcaActual;
    
    @SerializedName("MCO")
    public String marcaCompromiso;
    
    @SerializedName("MCU")
    public String marcaCumplio;
    
    @SerializedName("EST")
    public String estado;
	
    @SerializedName("FLP")
    public int proceso;
	
}
