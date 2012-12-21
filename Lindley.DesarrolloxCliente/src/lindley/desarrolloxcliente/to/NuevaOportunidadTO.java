package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NuevaOportunidadTO {

	@Expose()
	@SerializedName("CPRO")
	public String codigoProducto;

	@Expose()
	@SerializedName("DPRO")
	public String descripcionProducto;
	
	@Expose()
	@SerializedName("LEG")
	public String codigoLegacy;
	
	public boolean seleccionado;

}
