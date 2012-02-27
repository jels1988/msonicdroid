package pe.lindley.om.to;

import com.google.gson.annotations.SerializedName;

public class RolTO {
	
	private int asignacionId;
	
	public int getAsignacionId() {
		return asignacionId;
	}

	public void setAsignacionId(int asignacionId) {
		this.asignacionId = asignacionId;
	}

	@SerializedName("TPORD")
	private String tipoOrden;
	
	@SerializedName("CDRUT")
	private String codigoRuta;
	
	@SerializedName("TPROL")
	private String tipoRol;
	
	@SerializedName("TPSUP")
	private String tipoSub;
	
	@SerializedName("CDSUP")
	private String codigoSub;
	
	@SerializedName("ORVIS")
	private int orden;
	
	@SerializedName("CDSAP")
	private String codigoSap;
	
	@SerializedName("DSSUP")
	private String nombres;

	public String getTipoOrden() {
		return tipoOrden;
	}

	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
	}

	public String getCodigoRuta() {
		return codigoRuta;
	}

	public void setCodigoRuta(String codigoRuta) {
		this.codigoRuta = codigoRuta;
	}

	public String getTipoRol() {
		return tipoRol;
	}

	public void setTipoRol(String tipoRol) {
		this.tipoRol = tipoRol;
	}

	public String getTipoSub() {
		return tipoSub;
	}

	public void setTipoSub(String tipoSub) {
		this.tipoSub = tipoSub;
	}

	public String getCodigoSub() {
		return codigoSub;
	}

	public void setCodigoSub(String codigoSub) {
		this.codigoSub = codigoSub;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getCodigoSap() {
		return codigoSap;
	}

	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
}
