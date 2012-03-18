package pe.pae.encuesta.to;

import com.google.gson.annotations.SerializedName;

public class OpcionTO {

	@SerializedName("Id")
	public int opcionId;
	
	@SerializedName("Des")
	public String descripcion;
	
	public boolean seleccionado;

}
