package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClienteDescargaTO {

	@Expose(serialize = false)
	public long id;
	
	@SerializedName("COD")
	public String codigo;
	
	@SerializedName("FEC")
	public String fecha;	
	
	@SerializedName("NOM")
	public String nombre;
	
	@SerializedName("FRE") 
	public String frecuencia;
	
	@SerializedName("ALC")
	public double alcance;
	
	@SerializedName("FAL")
	public double falta;
	
	@SerializedName("CLU")
	public String cluster;
	
	@SerializedName("MC")
	public String mc;
	
	@SerializedName("PUN")
	public int nroPuntos;
	
	@SerializedName("SIG")
	public int nivelCanje;

	@SerializedName("LAT")
	public double latitud;

	@SerializedName("LNG")
	public double longitud;
	
	@SerializedName("DIR")
	public String direccion;
	
	
	@Expose(serialize = false)
	public int evaluacionesAbiertas;
	
}
