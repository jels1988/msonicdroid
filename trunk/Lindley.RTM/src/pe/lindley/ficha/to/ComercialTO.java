package pe.lindley.ficha.to;

import com.google.gson.annotations.SerializedName;

public class ComercialTO {
	
	@SerializedName("CLI")
	private String codigo;
	        
	@SerializedName("CAN")
	private String canal;

	@SerializedName("SCAN")
	private String subCanal;

	@SerializedName("CRTM")
	private String canalRTM;

	@SerializedName("SCRTM")
	private String subCanalRTM;

	@SerializedName("CGIR")
	private String codigoGiroSecundario;

	@SerializedName("DGIR")
	private String descripcionGiroSec;

	@SerializedName("CLU")
	private String cluster;
	        
	@SerializedName("MAT")
	private String matriz;

	@SerializedName("MRTM")
	private String modeloRTM;
	
	@SerializedName("URG")
	private String usuario;
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoGiroSecundario() {
		return codigoGiroSecundario;
	}

	public void setCodigoGiroSecundario(String codigoGiroSecundario) {
		this.codigoGiroSecundario = codigoGiroSecundario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCliente() {
		return codigo;
	}

	public void setCliente(String cliente) {
		this.codigo = cliente;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getSubCanal() {
		return subCanal;
	}

	public void setSubCanal(String subCanal) {
		this.subCanal = subCanal;
	}

	public String getCanalRTM() {
		return canalRTM;
	}

	public void setCanalRTM(String canalRTM) {
		this.canalRTM = canalRTM;
	}

	public String getSubCanalRTM() {
		return subCanalRTM;
	}

	public void setSubCanalRTM(String subCanalRTM) {
		this.subCanalRTM = subCanalRTM;
	}

	public String getCodGiroSecundario() {
		return codigoGiroSecundario;
	}

	public void setCodGiroSecundario(String codGiroSecundario) {
		this.codigoGiroSecundario = codGiroSecundario;
	}

	public String getDescripcionGiroSec() {
		return descripcionGiroSec;
	}

	public void setDescripcionGiroSec(String descripcionGiroSec) {
		this.descripcionGiroSec = descripcionGiroSec;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getMatriz() {
		return matriz;
	}

	public void setMatriz(String matriz) {
		this.matriz = matriz;
	}

	public String getModeloRTM() {
		return modeloRTM;
	}

	public void setModeloRTM(String modeloRTM) {
		this.modeloRTM = modeloRTM;
	}


}
