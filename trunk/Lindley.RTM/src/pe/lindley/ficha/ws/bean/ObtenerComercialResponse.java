package pe.lindley.ficha.ws.bean;

import com.google.gson.annotations.SerializedName;

import pe.lindley.ficha.to.ComercialTO;
import pe.lindley.util.ResponseBase;

public class ObtenerComercialResponse extends ResponseBase{

	@SerializedName("COM")
	private ComercialTO comercial;

	public ComercialTO getComercial() {
		return comercial;
	}

	public void setComercial(ComercialTO comercial) {
		this.comercial = comercial;
	}
}
