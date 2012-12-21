package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccionTradeProductoTO {

	@Expose()
	@SerializedName("ACC")
	public String codigoAccion;
	
	@Expose()
	@SerializedName("PRO")
	public String codigoProducto;
}
