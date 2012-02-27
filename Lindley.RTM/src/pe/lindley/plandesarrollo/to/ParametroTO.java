package pe.lindley.plandesarrollo.to;

import com.google.gson.annotations.SerializedName;

public class ParametroTO {
	
	@SerializedName("CTBL")
	private String codigoTabla;
	 
	@SerializedName("COD")
	private String codigo;

	@SerializedName("DES")
	private String descripcion;
	
	@SerializedName("NCRT")
	private String nombreCorto;
	
	@SerializedName("NLRG")
	private String nombreLargo;

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

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public String getNombreLargo() {
		return nombreLargo;
	}

	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}

}
