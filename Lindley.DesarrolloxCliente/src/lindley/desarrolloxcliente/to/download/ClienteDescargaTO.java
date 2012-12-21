package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClienteDescargaTO {

	@Expose(serialize = false)
	public long id;
	
	@Expose()
	@SerializedName("COD")
	public String codigo;
	
	@Expose()
	@SerializedName("FEC")
	public String fecha;	
	
	@Expose()
	@SerializedName("NOM")
	public String nombre;
	
	@Expose()
	@SerializedName("FRE") 
	public String frecuencia;
	
	@Expose()
	@SerializedName("ALC")
	public double alcance;
	
	@Expose()
	@SerializedName("FAL")
	public double falta;
	
	@Expose()
	@SerializedName("CLU")
	public String cluster;
	
	@Expose()
	@SerializedName("MC")
	public String mc;
	
	@Expose()
	@SerializedName("PUN")
	public int nroPuntos;
	
	@Expose()
	@SerializedName("SIG")
	public int nivelCanje;

	@Expose()
	@SerializedName("LAT")
	public double latitud;

	@Expose()
	@SerializedName("LNG")
	public double longitud;
	
	@Expose()
	@SerializedName("DIR")
	public String direccion;
	
	@Expose(serialize = false)
	public int evaluacionesAbiertas;
	
	@Expose()
	@SerializedName("TPR")
	public String TPPRO;
	
}
