package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class NuevaOportunidadTO {

	@SerializedName("CPRO")
	public String codigoProducto;

	@SerializedName("DPRO")
	public String descripcionProducto;
	
	public boolean seleccionado;

}
