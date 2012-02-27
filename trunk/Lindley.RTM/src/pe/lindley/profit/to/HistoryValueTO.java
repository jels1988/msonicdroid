package pe.lindley.profit.to;

import com.google.gson.annotations.SerializedName;

public class HistoryValueTO 
{
	@SerializedName("des")
	private String descripcion;
	
	@SerializedName("val")
	private String valor;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
