package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class MercaderistaTO {

	
	@SerializedName("COD")
	public String codigo;

	@SerializedName("DES")
	public String descripcion;
	
	@SerializedName("PRO")
	public String programados;
	
	@SerializedName("VIS")
	public String visitados;
	
	@SerializedName("ATE")
	public String atendidos;
	
	@SerializedName("NAT")
	public String noAtendidos;
	
	@SerializedName("HIN")
	public String horaInicio;
	
	@SerializedName("HFI")
	public String horaFin;
	
	@SerializedName("TEJ")
	public String tiempoEjecutado;
	
	@SerializedName("PVI")
	public String planVisita;
	
	@SerializedName("IND")
	public String indicador;
	

	
}
