package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.SerializedName;

public class ProfitTO {

	public long id;
	
	@SerializedName("ANI")
    public String anio;

    @SerializedName("MES")
    public String mes;

    @SerializedName("CLI")
    public String codigoCliente;

    @SerializedName("ART")
    public String codigoArticulo;

    @SerializedName("IND")
    public String indicador;

    @SerializedName("CJF")
    public double cajasFisica;

    @SerializedName("MAC")
    public double margenActual;

    @SerializedName("CFF")
    public double cajasFisicasFaltante;

    @SerializedName("MGF")
    public double margenFaltante;
    
}
