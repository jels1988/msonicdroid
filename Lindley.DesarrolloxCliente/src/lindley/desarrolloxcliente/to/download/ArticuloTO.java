package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.SerializedName;

public class ArticuloTO {

	
	public long id;
	
	@SerializedName("COD")
	public String codigoArticulo;

	@SerializedName("DSC")
	public String descripcionArticulo;

	@SerializedName("PTO")
	public String puntosCanje;
}
