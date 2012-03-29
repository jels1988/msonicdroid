package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class ArticuloCanjeTO {

	@SerializedName("COD")
	public String codigoArticulo;

	@SerializedName("DSC")
	public String descripcionArticulo;

	@SerializedName("PTO")
	public String puntosCanje;
}
