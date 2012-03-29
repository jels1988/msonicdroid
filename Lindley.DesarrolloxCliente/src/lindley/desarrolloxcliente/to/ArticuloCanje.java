package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class ArticuloCanje {

	@SerializedName("COD")
	public String codigoArticulo;

	@SerializedName("DSC")
	public String descripcionArticulo;

	@SerializedName("PTO")
	public String puntosCanje;
}
