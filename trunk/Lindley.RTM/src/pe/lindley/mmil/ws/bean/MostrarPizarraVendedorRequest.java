package pe.lindley.mmil.ws.bean;

import com.google.gson.annotations.SerializedName;

public class MostrarPizarraVendedorRequest {
	
	@SerializedName("COD")
	private String codDep;

	@SerializedName("FEC")
	private String Fecha;

	public String getCodDep() {
		return codDep;
	}

	public void setCodDep(String codDep) {
		this.codDep = codDep;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}
}
