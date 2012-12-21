package lindley.desarrolloxcliente.to;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePosicionTO {

	public UpdatePosicionTO()
    {
		listCompromisos = new ArrayList<CompromisoPosicionTO>();
    }
	
	@Expose()
	@SerializedName("CVAR")
	public String codigoVariable;

	@Expose()
	@SerializedName("CMP")
	public List<CompromisoPosicionTO> listCompromisos;
	       
	@Expose()
	@SerializedName("ACM")
	public String accionCompromiso;

	@Expose()
	@SerializedName("FCM")
	public String fechaCompromiso;
	
	@Expose()
	@SerializedName("FINI")
	public String fotoInicial;
	
}
