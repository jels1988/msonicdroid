package pe.pae.encuesta.ws.bean;

import com.google.gson.annotations.SerializedName;

public class EncuestaRequest {

	
	@SerializedName("usr")
	public int usuarioId;
	
	@SerializedName("cli")
	public int clienteId;
	
	@SerializedName("tie")
	public int tiendaId;

}
