package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class GuardarDesarrolloResponse extends ResponseBase{

	@SerializedName("CAB")
	private String codCabecera;

	public String getCodCabecera() {
		return codCabecera;
	}

	public void setCodCabecera(String codCabecera) {
		this.codCabecera = codCabecera;
	}
}
