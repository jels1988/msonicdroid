package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class CompromisoPosicionTO {

	@SerializedName("DES")
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
