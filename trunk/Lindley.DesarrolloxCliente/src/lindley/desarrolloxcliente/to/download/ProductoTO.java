package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.SerializedName;

public class ProductoTO {

	
	public long id;
	
	@SerializedName("C")
	public String codigo;
	
	@SerializedName("D")
	public String descripcion;
	
	@SerializedName("A")
	public String abreviatura;
}
