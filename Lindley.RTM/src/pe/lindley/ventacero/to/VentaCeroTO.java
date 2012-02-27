package pe.lindley.ventacero.to;

import com.google.gson.annotations.SerializedName;

public class VentaCeroTO {
	
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

	@SerializedName("PRO")
	private String codPro;

	@SerializedName("DSP")
	private String Descpro;

	@SerializedName("SUP")
	private String codSup;

	@SerializedName("NSU")
	private String NombreSup;

	@SerializedName("RUT")
	private String codRuta;

	@SerializedName("GSM")
	private String codSegmento;

	@SerializedName("CLI")
	private String codCliente;

	@SerializedName("NMC")
	private String NombreCliente;

	@SerializedName("DRC")
	private String Direccion;

	@SerializedName("FLQ")
	private String Flequ;

	@SerializedName("CAQ")
	private String caeQu;

	@SerializedName("STS")
	private String Status;

	@SerializedName("FLP")
	private String FlPro;

	@SerializedName("MOT")
	private String codMotivo;

	@SerializedName("DES")
	private String descMotv;

	@SerializedName("OBS")
	private String observacion;

	@SerializedName("MTA")
	private String codMotvAnt;

	@SerializedName("DMA")
	private String descMotvAnt;

	@SerializedName("OMA")
	private String observacionAnt;

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

	public String getCodPro() {
		return codPro;
	}

	public void setCodPro(String codPro) {
		this.codPro = codPro;
	}

	public String getDescpro() {
		return Descpro;
	}

	public void setDescpro(String descpro) {
		Descpro = descpro;
	}

	public String getCodSup() {
		return codSup;
	}

	public void setCodSup(String codSup) {
		this.codSup = codSup;
	}

	public String getNombreSup() {
		return NombreSup;
	}

	public void setNombreSup(String nombreSup) {
		NombreSup = nombreSup;
	}

	public String getCodRuta() {
		return codRuta;
	}

	public void setCodRuta(String codRuta) {
		this.codRuta = codRuta;
	}

	public String getCodSegmento() {
		return codSegmento;
	}

	public void setCodSegmento(String codSegmento) {
		this.codSegmento = codSegmento;
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

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getFlequ() {
		return Flequ;
	}

	public void setFlequ(String flequ) {
		Flequ = flequ;
	}

	public String getCaeQu() {
		return caeQu;
	}

	public void setCaeQu(String caeQu) {
		this.caeQu = caeQu;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getFlPro() {
		return FlPro;
	}

	public void setFlPro(String flPro) {
		FlPro = flPro;
	}

	public String getCodMotivo() {
		return codMotivo;
	}

	public void setCodMotivo(String codMotivo) {
		this.codMotivo = codMotivo;
	}

	public String getDescMotv() {
		return descMotv;
	}

	public void setDescMotv(String descMotv) {
		this.descMotv = descMotv;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getCodMotvAnt() {
		return codMotvAnt;
	}

	public void setCodMotvAnt(String codMotvAnt) {
		this.codMotvAnt = codMotvAnt;
	}

	public String getDescMotvAnt() {
		return descMotvAnt;
	}

	public void setDescMotvAnt(String descMotvAnt) {
		this.descMotvAnt = descMotvAnt;
	}

	public String getObservacionAnt() {
		return observacionAnt;
	}

	public void setObservacionAnt(String observacionAnt) {
		this.observacionAnt = observacionAnt;
	}

}
