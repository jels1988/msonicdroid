package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class FileTO {

	public int id;
	
	@SerializedName("nom")
	public String nombre;


	@SerializedName("fil")
	public String file;
	
}
