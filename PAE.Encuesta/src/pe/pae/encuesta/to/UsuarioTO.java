package pe.pae.encuesta.to;

import com.google.gson.annotations.SerializedName;

public class UsuarioTO {
	
	@SerializedName("Id")
	public int usuarioId; 
	
	@SerializedName("Usr")
	public String usuario;
	
	@SerializedName("Nom")
	public String nombres;
	
	@SerializedName("Pat")
	public String paterno;
	
	@SerializedName("Mat")
	public String materno;
	
}
