package lindley.desarrolloxcliente.to.upload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkuTO {
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
    @SerializedName("CSKU")
    public String codigoSKU;
	
	@Expose()
    @SerializedName("DSKU")
    public String descripcionSKU;
    
	@Expose()
    @SerializedName("MAC")
    public String marcaActual;
    
	@Expose()
    @SerializedName("MCO")
    public String marcaCompromiso;
    
	@Expose()
    @SerializedName("MCU")
    public String marcaCumplio;
    
	@Expose()
    @SerializedName("EST")
    public String estado;
	
	@Expose()
    @SerializedName("FLP")
    public int proceso;
	
}
