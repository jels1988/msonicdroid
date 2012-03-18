package pe.pae.encuesta.to;

import com.google.gson.annotations.SerializedName;

public class ProductoTO {

	
	@SerializedName("Id")
	public int productoId;
	
	@SerializedName("Des")
	public String descripcion;
	
	@SerializedName("EnId")
	public int encuestaId;
	

}
