package pe.pae.encuesta.to;

import com.google.gson.annotations.SerializedName;

public class TiendaTO {
	
	@SerializedName("Id")
	public int tiendaId;
	
	@SerializedName("Nom")
	public String nombre;
	
	@SerializedName("Dir")
	public String direccion;

}
