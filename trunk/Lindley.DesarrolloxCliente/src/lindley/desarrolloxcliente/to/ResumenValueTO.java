package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class ResumenValueTO {
	
	public long id;
	
	@SerializedName("des")
	public String descripcion;
	
	@SerializedName("val")
	public String valor;
	
	@SerializedName("val2")
	public String valor2;
	
	
}
