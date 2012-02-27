package pe.lindley.ventacero.to;

import com.google.gson.annotations.SerializedName;

public class ParametroTO {

	@SerializedName("CTBL")
	private String codigoTabla;
	 
	@SerializedName("COD")
	private String codigo;

	@SerializedName("LVL1")
	private String level1;
	
	@SerializedName("LVL2")
	private String level2;
	
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

	public String getLevel1() {
		return level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	public String getLevel2() {
		return level2;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}
	
}
