package pe.lindley.ficha.to;

import com.google.gson.annotations.SerializedName;

public class OpcionMultipleTO {
	
	@SerializedName("CTBL")
	private String codigoTabla;
	 
	@SerializedName("COD")
	private String codigo;

	@SerializedName("DES")
	private String descripcion;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoTabla() {
		return codigoTabla;
	}

	public void setCodigoTabla(String codigoTabla) {
		this.codigoTabla = codigoTabla;
	}

}
