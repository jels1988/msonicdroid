package pe.lindley.ficha.to;

import com.google.gson.annotations.SerializedName;

public class PreguntaTO {
	
	@SerializedName("ORD")
	private String orden;

	@SerializedName("CPRG")
	private String codigoPegunta;

	@SerializedName("DPRG")
	private String pregunta;

	@SerializedName("SEL")
	private boolean seleccionado;

	
	/*@SerializedName("REP1")
	private String respuesta1;

	@SerializedName("REP2")
	private String respuesta2;*/

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getCodigoPegunta() {
		return codigoPegunta;
	}

	public void setCodigoPegunta(String codigoPegunta) {
		this.codigoPegunta = codigoPegunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	/*
	public String getRespuesta1() {
		return respuesta1;
	}

	public void setRespuesta1(String respuesta1) {
		this.respuesta1 = respuesta1;
	}

	public String getRespuesta2() {
		return respuesta2;
	}

	public void setRespuesta2(String respuesta2) {
		this.respuesta2 = respuesta2;
	}*/

}
