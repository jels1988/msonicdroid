package pe.lindley.profit.to;

import com.google.gson.annotations.SerializedName;

public class ParametroTO {
	
	@SerializedName("CTBL")
	private String codigoTabla;
	
	@SerializedName("COD")
	private String codigo;

	@SerializedName("CREL")
	private String codigoRelacion;

	@SerializedName("DES")
	private String descripcion;

	public String getCodigoTabla() {
		return codigoTabla;
	}

	public void setCodigoTabla(String codigoTabla) {
		this.codigoTabla = codigoTabla;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoRelacion() {
		return codigoRelacion;
	}

	public void setCodigoRelacion(String codigoRelacion) {
		this.codigoRelacion = codigoRelacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
