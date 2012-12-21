package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PresentacionTO {
	
	@Expose()
	public long id;
	
	@Expose()
	@SerializedName("CLI")
	public String codigoCliente;
	


	@Expose()
	@SerializedName("TAG")
	public String tipoAgrupacion;
	
	@Expose()
	@SerializedName("VRD")
	public String variableRed;
	
	@Expose()
	@SerializedName("FDE")
	public String fde;
	
	@Expose()
	@SerializedName("FRD")
	public int fechaRed;
}
