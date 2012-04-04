package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class InformacionAdicionalTO {

	@SerializedName("CSS")
	private String comboSS;
	
	@SerializedName("OBSSS")
	private String observacionSS;
	
	@SerializedName("CMS")
	private String comboMS;
	
	@SerializedName("OBS")
	private String observacion;
	
	@SerializedName("USR")
	private String codigoUsuario;
	
	@SerializedName("CLI")
	private String codigoCliente;
	
	@SerializedName("AGR")
	private String tipoAgrupacion;

	public String getComboSS() {
		return comboSS;
	}

	public void setComboSS(String comboSS) {
		this.comboSS = comboSS;
	}

	public String getComboMS() {
		return comboMS;
	}

	public void setComboMS(String comboMS) {
		this.comboMS = comboMS;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getTipoAgrupacion() {
		return tipoAgrupacion;
	}

	public void setTipoAgrupacion(String tipoAgrupacion) {
		this.tipoAgrupacion = tipoAgrupacion;
	}

	public String getObservacionSS() {
		return observacionSS;
	}

	public void setObservacionSS(String observacionSS) {
		this.observacionSS = observacionSS;
	}
}
