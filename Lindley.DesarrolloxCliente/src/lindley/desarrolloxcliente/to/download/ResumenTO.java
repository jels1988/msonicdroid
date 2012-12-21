package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResumenTO {

	@Expose()
	@SerializedName("des")
	public String descripcion;
	
	@Expose()
	@SerializedName("val")
	public String valor;
}
