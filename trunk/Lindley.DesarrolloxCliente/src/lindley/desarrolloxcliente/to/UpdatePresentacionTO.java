package lindley.desarrolloxcliente.to;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class UpdatePresentacionTO {

	public UpdatePresentacionTO()
    {
		listaSKU = new ArrayList<UpdateSKUPresentacionTO>();
    }
	
	@SerializedName("REG")
	public String codigoRegistro;

	@SerializedName("TAG")
	public String tipoAgrupacion;

	@SerializedName("CVAR")
	public String codigoVariable;       

	@SerializedName("LSKU")
	public List<UpdateSKUPresentacionTO> listaSKU;

	@SerializedName("FCM")
	public String fechaCompromiso;

	@SerializedName("CNF")
	public String confirmacion;

}
