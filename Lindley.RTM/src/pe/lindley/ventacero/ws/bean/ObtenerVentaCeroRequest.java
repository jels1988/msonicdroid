package pe.lindley.ventacero.ws.bean;

import com.google.gson.annotations.SerializedName;

public class ObtenerVentaCeroRequest {
	
	@SerializedName("DEP")
	private String codDeposito;

	@SerializedName("ANO")
	private String Anio;

	@SerializedName("MES")
	private String Mes;

	@SerializedName("SEM")
	private String Semana;
	
	@SerializedName("SAP")
	private String codSap;

	@SerializedName("RUT")
	private String codRuta;

	@SerializedName("SGM")
	private String Segmento;

	@SerializedName("CLI")
	private String codCliente;

	@SerializedName("NMC")
	private String NombreCliente;

	@SerializedName("RUC")
	private String Ruc;

	@SerializedName("DNI")
	private String Dni;

	public String getCodDeposito() {
		return codDeposito;
	}

	public void setCodDeposito(String codDeposito) {
		this.codDeposito = codDeposito;
	}

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

	public String getCodSap() {
		return codSap;
	}

	public void setCodSap(String codSap) {
		this.codSap = codSap;
	}

	public String getCodRuta() {
		return codRuta;
	}

	public void setCodRuta(String codRuta) {
		this.codRuta = codRuta;
	}

	public String getSegmento() {
		return Segmento;
	}

	public void setSegmento(String segmento) {
		Segmento = segmento;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getNombreCliente() {
		return NombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		NombreCliente = nombreCliente;
	}
	
	public String getRuc() {
		return Ruc;
	}

	public void setRuc(String ruc) {
		Ruc = ruc;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}
}
