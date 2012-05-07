package pe.lindley.om.to;

import com.google.gson.annotations.SerializedName;

public class ClienteTO {

	
	@SerializedName("cod")
     private String codigo;

     @SerializedName("rzs")
     private String razonSocial;

     @SerializedName("drc")
     private String direccion;

     @SerializedName("cdrut")
     private String rutaVentas;

     @SerializedName("cdrum")
     private String rutaMecaderista;
     
     

     public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRutaVentas() {
		return rutaVentas;
	}

	public void setRutaVentas(String rutaVentas) {
		this.rutaVentas = rutaVentas;
	}

	public String getRutaMecaderista() {
		return rutaMecaderista;
	}

	public void setRutaMecaderista(String rutaMecaderista) {
		this.rutaMecaderista = rutaMecaderista;
	}

	public String getRutaDesarrollador() {
		return rutaDesarrollador;
	}

	public void setRutaDesarrollador(String rutaDesarrollador) {
		this.rutaDesarrollador = rutaDesarrollador;
	}

	public String getRutaTecnicoMan() {
		return rutaTecnicoMan;
	}

	public void setRutaTecnicoMan(String rutaTecnicoMan) {
		this.rutaTecnicoMan = rutaTecnicoMan;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getSubcanal() {
		return subcanal;
	}

	public void setSubcanal(String subcanal) {
		this.subcanal = subcanal;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public String getPotencial() {
		return potencial;
	}

	public void setPotencial(String potencial) {
		this.potencial = potencial;
	}

	public String getModoservicio() {
		return modoservicio;
	}

	public void setModoservicio(String modoservicio) {
		this.modoservicio = modoservicio;
	}

	@SerializedName("cdrud")
     private String rutaDesarrollador;

     @SerializedName("cdrue")
     private String rutaTecnicoMan;

     @SerializedName("latit")
     private String latitud;

     @SerializedName("longi")
     private String longitud;

     @SerializedName("canal")
     private String canal;

     @SerializedName("subcanal")
     private String subcanal;

     @SerializedName("tamanio")
     private String tamanio;

     @SerializedName("potencial")
     private String potencial;

     @SerializedName("modo")
     private String modoservicio;
     
     @SerializedName("cex")
     private String clubExito;



	public String getClubExito() {
		return clubExito;
	}

	public void setClubExito(String clubExito) {
		this.clubExito = clubExito;
	}
     
}
