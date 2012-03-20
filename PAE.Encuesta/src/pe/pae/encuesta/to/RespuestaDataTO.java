package pe.pae.encuesta.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class RespuestaDataTO {

	
	
	@SerializedName("Id")
	public int respuestaId;
	
	@SerializedName("TId")
	public int tiendadId;
	
	
	@SerializedName("PId")
	public int productoId;
	

	@SerializedName("EId")
	public int encuestaId;
	
	@SerializedName("FRE")
	public String fechaRegistro;
	
	@SerializedName("HRE")
	public String horaRegistro;
	
	@SerializedName("UId")
	public int usuarioId;
	
	@SerializedName("PRE")
	public ArrayList<RespuestaPreguntaDataTO> preguntas;
	
	
}
