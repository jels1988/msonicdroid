package pe.lindley.red.ws.bean;

import com.google.gson.annotations.SerializedName;

import pe.lindley.red.to.DatosFichaTO;
import pe.lindley.util.ResponseBase;

public class ConsultarFichaResponse extends ResponseBase {

	@SerializedName("DTC")
	private DatosFichaTO fichaCliente;

	public DatosFichaTO getFichaCliente() {
		return fichaCliente;
	}

	public void setFichaCliente(DatosFichaTO fichaCliente) {
		this.fichaCliente = fichaCliente;
	}
}
