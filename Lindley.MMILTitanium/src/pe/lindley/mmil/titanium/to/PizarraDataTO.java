package pe.lindley.mmil.titanium.to;

import java.util.ArrayList;


import com.google.gson.annotations.SerializedName;

public class PizarraDataTO {

	public PizarraDataTO(){
		detalleSupervisor = new ArrayList<PizarraDetalleTO>();
		detalleTeleventas = new ArrayList<PizarraDetalleTO>();
	}
	@SerializedName("COD")
	public String codigoDeposito;
	
	@SerializedName("CDA")
	public String cda;
	
	@SerializedName("NOMSP")
	public String nombreSupervisor;
	
	@SerializedName("NOMTV")
	public String nombreTeleventa;
	
	@SerializedName("CANSP")
	public int cantidadSupervisor;
	
	@SerializedName("CANTV")
	public int cantidadTeleventa;

	@SerializedName("DETSP")
	public ArrayList<PizarraDetalleTO> detalleSupervisor;
	
	@SerializedName("DETTV")
	public ArrayList<PizarraDetalleTO> detalleTeleventas;

}
