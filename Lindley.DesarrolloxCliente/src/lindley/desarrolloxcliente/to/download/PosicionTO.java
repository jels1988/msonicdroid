package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PosicionTO {

	@Expose()
	public long id;
	
	@Expose()
	@SerializedName("CLI")
	public String codigoCliente;
	

	@Expose()
	@SerializedName("ACT")
	public String activo;
	
	@Expose()
	@SerializedName("TAG")
	public String tipoAgrupacion;
	
	@Expose()
	@SerializedName("VRD")
	public String variableRed;
	

}
