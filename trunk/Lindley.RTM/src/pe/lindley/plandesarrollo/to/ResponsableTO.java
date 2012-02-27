package pe.lindley.plandesarrollo.to;

import com.google.gson.annotations.SerializedName;

public class ResponsableTO {

	@SerializedName("CRES")
	private String codigo;
	        
	@SerializedName("RES")
	private String descripcion;
	
	@SerializedName("NCRT")
	private String nombreCorto;

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

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	        
}