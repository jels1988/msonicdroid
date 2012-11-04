package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.SerializedName;

public class PosicionTO {

	public long id;
	
	@SerializedName("CLI")
	public String codigoCliente;
	

	@SerializedName("ACT")
	public String activo;
	
	@SerializedName("TAG")
	public String tipoAgrupacion;
	
	
	@SerializedName("VRD")
	public String variableRed;
	

}
