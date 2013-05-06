package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TipoActivoTO {
	
	@Expose()
	@SerializedName("COD")
	public String codigo;
	
	@Expose()
	@SerializedName("DES")
	public String descripcion;
	
	@Expose()
	@SerializedName("TIP")
	public String tipo;
	
	public boolean seleccionado;
}
