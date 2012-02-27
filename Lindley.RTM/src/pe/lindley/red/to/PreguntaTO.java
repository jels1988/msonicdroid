package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class PreguntaTO {

	@SerializedName("PRG")
	private String pregunta;

	@SerializedName("RES")
	private String resultado;

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
		        
}
