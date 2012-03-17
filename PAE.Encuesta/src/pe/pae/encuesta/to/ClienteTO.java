package pe.pae.encuesta.to;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ClienteTO {

	

	@SerializedName("Id")
	public int clienteId;
	
	@SerializedName("Des")
	public String descripcion;
	
	@SerializedName("Tie")
	public List<TiendaTO> tiendas;
	
	
	
	 @Override
	    public String toString() {
	        return this.descripcion;
	    }
}
