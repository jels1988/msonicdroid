package pe.lindley.ficha.to;

import com.google.gson.annotations.SerializedName;

public class VisualizacionTO {

	@SerializedName("CLI")
	private String codigo;

	@SerializedName("IMG")
	private String codigoImagen;

	@SerializedName("FCT")
	private String fechaToma;

	@SerializedName("URL")
	private String url;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoImagen() {
		return codigoImagen;
	}

	public void setCodigoImagen(String codigoImagen) {
		this.codigoImagen = codigoImagen;
	}

	public String getFechaToma() {
		return fechaToma;
	}

	public void setFechaToma(String fechaToma) {
		this.fechaToma = fechaToma;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
