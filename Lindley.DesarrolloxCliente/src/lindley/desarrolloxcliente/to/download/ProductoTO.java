package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductoTO {

	@Expose()
	public long id;
	
	@Expose()
	@SerializedName("C")
	public String codigo;
	
	@Expose()
	@SerializedName("D")
	public String descripcion;
	
	@Expose()
	@SerializedName("A")
	public String abreviatura;
}
