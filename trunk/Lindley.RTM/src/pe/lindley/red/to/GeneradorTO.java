package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class GeneradorTO {

	@SerializedName("NUM")
	private int numero;

	@SerializedName("PRG")
	private String pregunta;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
		        
}
