package pe.pae.encuesta.to;

import com.google.gson.annotations.SerializedName;

public class TiendaTO {
	
	@SerializedName("Id")
	public int tiendaId;
	
	@SerializedName("Nom")
	public String nombre;
	
	@SerializedName("Dir")
	public String direccion;

	@Override
    public String toString() {
		if(this.direccion==null){
			return this.nombre;
		}else{
			return this.nombre + "(" + this.direccion + ")";
		}
        
    }
	
}
