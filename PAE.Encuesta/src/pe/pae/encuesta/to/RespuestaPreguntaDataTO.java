package pe.pae.encuesta.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class RespuestaPreguntaDataTO {

	
	@SerializedName("Id")
	public int respuestaPreguntaDataId;
	
	
	@SerializedName("RId")
	public int respuestaId;
	
	@SerializedName("PId")
	public int preguntaId;
	
	@SerializedName("Obs")
	public String observacion;
	
	@SerializedName("R1")
	public String respuesta;
	
	@SerializedName("FOT")
	public String foto;
	
	@SerializedName("OPC")
	public ArrayList<RespuestaPreguntaOpcionDataTO> opciones;
	
}
