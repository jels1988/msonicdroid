package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileTO {

	public int id;
	
	@Expose()
	@SerializedName("nom")
	public String nombre;


	@Expose()
	@SerializedName("fil")
	public String file;
	
}
