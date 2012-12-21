package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsultarInformacionComboRequest {

	@Expose()
	@SerializedName("COD")
	public String codigoRegistro;
	
}
