package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccionTradeTO {
	public long id;
	
	@Expose()
	@SerializedName("COD")
	public String codigo;
	
	@Expose()
	@SerializedName("DES")
	public String descripcion;
}
