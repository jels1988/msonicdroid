package pe.lindley.ventacero.ws.bean;

import com.google.gson.annotations.SerializedName;

public class GuardarVtaCeroMotivoRequest {

	@SerializedName("ANO")
	private String Anio;

	@SerializedName("MES")
	private String Mes;

	@SerializedName("SEM")
	private String Semana;

	@SerializedName("DEP")
	private String codDeposito;

	@SerializedName("CLI")
	private String codCliente;

	@SerializedName("MOT")
	private String Motivo;

	@SerializedName("OBS")
	private String observacion;

	@SerializedName("URG")
	private String Usuario;

	public String getAnio() {
		return Anio;
	}

	public void setAnio(String anio) {
		Anio = anio;
	}

	public String getMes() {
		return Mes;
	}

	public void setMes(String mes) {
		Mes = mes;
	}

	public String getSemana() {
		return Semana;
	}

	public void setSemana(String semana) {
		Semana = semana;
	}

	public String getCodDeposito() {
		return codDeposito;
	}

	public void setCodDeposito(String codDeposito) {
		this.codDeposito = codDeposito;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getMotivo() {
		return Motivo;
	}

	public void setMotivo(String motivo) {
		Motivo = motivo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	
}
