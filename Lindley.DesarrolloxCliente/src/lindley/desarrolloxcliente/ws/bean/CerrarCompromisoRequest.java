package lindley.desarrolloxcliente.ws.bean;

import com.google.gson.annotations.SerializedName;

public class CerrarCompromisoRequest {

	@SerializedName("COD")
	private String codigoCabecera;

	public String getCodigoCabecera() {
		return codigoCabecera;
	}

	public void setCodigoCabecera(String codigoCabecera) {
		this.codigoCabecera = codigoCabecera;
	}
}
