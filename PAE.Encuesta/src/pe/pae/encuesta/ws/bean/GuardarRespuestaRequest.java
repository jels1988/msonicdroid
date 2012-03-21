package pe.pae.encuesta.ws.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import pe.pae.encuesta.to.RespuestaDataTO;

public class GuardarRespuestaRequest {

	@SerializedName("rep")
	public ArrayList<RespuestaDataTO> respuestas;
}
