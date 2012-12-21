package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticuloTO {

	public long id;
	
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
