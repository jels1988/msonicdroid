package lindley.desarrolloxcliente.lazanadorapp.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClienteTO {
	
	@Expose()
	@SerializedName("NATOUTNUM")
	public String ruc;
	
	@Expose()
	@SerializedName("OUTNUM")
	public String codigoCliente;
	
	@Expose()
	 @SerializedName("NUDNI")
     public String dni;
	
	@Expose()
	@SerializedName("OUTLOC")
	public String codigoCda;
	
	@Expose()
	@SerializedName("ADRLIN1")
	public String razonSocial;
	
	@Expose()
	@SerializedName("ADRLIN2")
	public String direccion;
	
	@Expose()
	@SerializedName("ADRLIN3")
	public String nombreCliente;
	
	@Expose()
	@SerializedName("IsSuspe")
	public boolean suspendido;
	
	@Expose()
	@SerializedName("ltd")
	public double latitud;
	
	@Expose()
	@SerializedName("lng")
	public double longitud;
	
	@Expose()
	@SerializedName("efrio")
    public boolean equiposFrio;
	
	
	@Expose()
	@SerializedName("TRDCHN")
	public String canal;
	
	@Expose()
	@SerializedName("SUBTRDCHN")
	public String subCanal;
	
	@Expose()
	@SerializedName("SUBTRDCHNDES")
	public String subCanalDes;
	
	@Expose()
	@SerializedName("SALRTE")
	public String ruta;
	
	@Expose()
	@SerializedName("SPRDAT")
	public String fechaSuspencion;
	
	@Expose()
	@SerializedName("CREDAT")
	public String fechaCreacion;
	
	@Expose()
	@SerializedName("UPDDAT")
	public String fechaActualizacion;    
	
	@Expose()
    @SerializedName("cdrut")
	public String rutaVentas;

	@Expose()
    @SerializedName("cdrum")
	public String rutaMecaderista;
    
	@Expose()
	@SerializedName("cdrud")
	public String rutaDesarrollador;

	@Expose()
   @SerializedName("cdrue")
	public String rutaTecnicoMan;
	
	@Expose()
	 @SerializedName("tamanio")
	public String tamanio;
	
	@Expose()
    @SerializedName("potencial")
	public String potencial;
	
	@Expose()
	@SerializedName("modo")
	public String modoservicio;
	
	@Expose()
    @SerializedName("cex")
    public String clubExito;
}
