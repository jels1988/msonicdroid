package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class MercaderistaDetalleTO {

	
	@SerializedName("COD")
	public String codigo;

	@SerializedName("DES")
	public String descripcion;
	
	@SerializedName("DIR")
	public String direccion;
	


	@SerializedName("HIN")
	public String horaInicio;

	@SerializedName("HFI")
	public String horaFin;

	@SerializedName("TEJ")
	public String tiempoEjecutado;
	
}
