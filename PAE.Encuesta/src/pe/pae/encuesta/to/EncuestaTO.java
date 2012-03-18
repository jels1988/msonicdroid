package pe.pae.encuesta.to;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class EncuestaTO {

	
	@SerializedName("Id")
	public int encuestaId;
	
	@SerializedName("Des")
	public String descripcion;
	
	@SerializedName("Prg")
	public List<PreguntaTO> preguntas;
}
