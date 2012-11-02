package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.SerializedName;

public class PresentacionTO {
	public long id;
	
	@SerializedName("CLI")
	public String codigoCliente;
	


	
	@SerializedName("TAG")
	public String tipoAgrupacion;
	
	
	@SerializedName("VRD")
	public String variableRed;
	
	@SerializedName("FDE")
	public String fde;
	
	@SerializedName("FRD")
	public int fechaRed;
}
