package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FotoExitoTO {

	@Expose()
	@SerializedName("TIT")
	public String titulo;

	@Expose()
	@SerializedName("NOM")
	public String nombre;
}
