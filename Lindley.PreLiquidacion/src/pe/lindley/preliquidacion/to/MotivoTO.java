package pe.lindley.preliquidacion.to;

import com.google.gson.annotations.SerializedName;

public class MotivoTO {
	@SerializedName("COD")
	private String codigo;
	
	@SerializedName("FUN")
	private String funcion;
	
	@SerializedName("DES")
	private String descripcion;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
