package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.GuardarOportunidadTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GuardarNuevoDesarrolloRequest {

	@Expose()
	@SerializedName("CSIS")
	public List<GuardarOportunidadTO> oportunidadSistema;
	
	@Expose()
	@SerializedName("SKU")
	public List<SKUPresentacionTO> listSKUPresentacion;
	
	@Expose()
	@SerializedName("ACTL")
	public String activosLindley;
	
	@Expose()
	@SerializedName("CLI")
	public String codigoCliente;
	
	@Expose()
	@SerializedName("USR")
	public String codigoUsuario;
	
	@Expose()
	@SerializedName("FDE")
	public String codigoFDE;
}
