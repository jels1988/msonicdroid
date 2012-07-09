package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class ResumenVentaDetalleTO {

	@SerializedName("COD")
	public String codigo;
	
	@SerializedName("DES")
	public String descripcion;
	
	
	@SerializedName("AVA")
	public String avance;
	

	@SerializedName("CUO")
	public String cuota;
	
	@SerializedName("VEN")
	public String vendido;
	
	@SerializedName("CPR")
	public String clientesProgramados;
	

	@SerializedName("CVS")
	public String clientesVisitados;
	
	@SerializedName("CCP")
	public String clientesPedido;
	
	@SerializedName("CSP")
	public String clientesSinPedido;
	
}
