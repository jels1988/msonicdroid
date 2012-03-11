package pe.lindley.prospector.to;

import com.google.gson.annotations.SerializedName;

public class FileTO {
	
	
	@SerializedName("nom")
    public String nombre;


	@SerializedName("fil")
    public String file;

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getFile() {
		return file;
	}



	public void setFile(String file) {
		this.file = file;
	}



	
}
