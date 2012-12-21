package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResumenValueTO {
	
	public long id;
	
	@Expose()
	@SerializedName("des")
	public String descripcion;
	
	@Expose()
	@SerializedName("val")
	public String valor;
	
	@Expose()
	@SerializedName("val2")
	public String valor2;
	
	
}
