package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.GuardarOportunidadTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;

import com.google.gson.annotations.SerializedName;

public class GuardarNuevoDesarrolloRequest {

	@SerializedName("CSIS")
	public List<GuardarOportunidadTO> oportunidadSistema;
	
	@SerializedName("SKU")
	public List<SKUPresentacionTO> listSKUPresentacion;
	
	@SerializedName("ACTL")
	public String activosLindley;
	
	@SerializedName("CLI")
	public String codigoCliente;
	
	@SerializedName("USR")
	public String codigoUsuario;
	
	@SerializedName("FDE")
	public String codigoFDE;
}
