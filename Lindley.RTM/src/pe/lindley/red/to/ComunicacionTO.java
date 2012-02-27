package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class ComunicacionTO {

	@SerializedName("EST")
	private String estado;

	@SerializedName("UBI")
	private String ubicacion;

	@SerializedName("MAT")
	private String material;

	@SerializedName("INI")
	private String inicio;
	
	@SerializedName("FIN")
	private String fin;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}
	        
}