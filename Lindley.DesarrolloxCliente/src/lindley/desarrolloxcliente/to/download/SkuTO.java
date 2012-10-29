package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.SerializedName;

public class SkuTO {

	
	public long id;
	
	@SerializedName("COD")
	public String codigo;
	
	@SerializedName("DES")
	public String descripcion;
	
	@SerializedName("CLU")
	public String cluster;
}
