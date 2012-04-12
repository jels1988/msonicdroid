package lindley.desarrolloxcliente.to;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class UpdatePosicionTO {

	public UpdatePosicionTO()
    {
		listCompromisos = new ArrayList<CompromisoPosicionTO>();
    }
	
	@SerializedName("CVAR")
	public String codigoVariable;

	@SerializedName("CMP")
	public List<CompromisoPosicionTO> listCompromisos;
	       
	@SerializedName("ACM")
	public String accionCompromiso;

	@SerializedName("FCM")
	public String fechaCompromiso;
	
	@SerializedName("FINI")
	public String fotoInicial;
	
}
