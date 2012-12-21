package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfitTO {

	public long id;
	
	@Expose()
	@SerializedName("ANI")
    public String anio;

	@Expose()
    @SerializedName("MES")
    public String mes;

	@Expose()
    @SerializedName("CLI")
    public String codigoCliente;

	@Expose()
    @SerializedName("ART")
    public String codigoArticulo;

	@Expose()
    @SerializedName("IND")
    public String indicador;

	@Expose()
    @SerializedName("CJF")
    public double cajasFisica;

	@Expose()
    @SerializedName("MAC")
    public double margenActual;

	@Expose()
    @SerializedName("CFF")
    public double cajasFisicasFaltante;

	@Expose()
    @SerializedName("MGF")
    public double margenFaltante;
    
}
