package pe.lindley.om.to;

import com.google.gson.annotations.SerializedName;

public class ParametroTO {
	
	@SerializedName("ctb")
	private String codigoTabla;
	
	@SerializedName("id")
	private String id;
	
	@SerializedName("des")
	private String descripcion;
	private int orden;
	
	public String getCodigoTabla() {
		return codigoTabla;
	}
	public void setCodigoTabla(String codigoTabla) {
		this.codigoTabla = codigoTabla;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
}
