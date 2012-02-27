package pe.lindley.om.ws.bean;

import com.google.gson.annotations.SerializedName;

public class DescargarParametrosRequest {
	@SerializedName("CodigoAlmancen")
	private String codigoAlmacen;

	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}

	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}
}
