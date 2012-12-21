package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticuloCanjeTO {

	@Expose()
	@SerializedName("COD")
	public String codigoArticulo;

	@Expose()
	@SerializedName("DSC")
	public String descripcionArticulo;

	@Expose()
	@SerializedName("PTO")
	public String puntosCanje;
}
