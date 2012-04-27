package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class VendedorTO {
	@SerializedName("CDV")
	public String codigo;
	
	@SerializedName("RUT")
	public String ruta;

	@SerializedName("NMV")
	public String nombre;

	@SerializedName("EST")
	public String estado;

	@SerializedName("CET")
	public String colorEstado;

	@SerializedName("PRG")
	public String primerRegistro;

	@SerializedName("URG")
	public String ultimoRegistro;

	@SerializedName("CUO")
	public String cuota;

	@SerializedName("CCU")
	public String cuotaColor;

	@SerializedName("EGL")
	public String eficGlobal;

	@SerializedName("CEG")
	public String eficGlobalColor;

	@SerializedName("PVS")
	public String planVisita;

	@SerializedName("CPV")
	public String planVisitaColor;

	@SerializedName("EPV")
	public String eficPreventa;

	@SerializedName("CEP")
	public String eficPreventaColor;
		        
	@SerializedName("CCL")
	public String cantidadClientes;

	@SerializedName("VIS")
	public String visitas;

	@SerializedName("CPD")
	public String conPedido;

	@SerializedName("SPD")
	public String sinPedido;

	@SerializedName("IMP")
	public String importe;

	@SerializedName("TME")
	public String tiempoEfec;

	@SerializedName("CJF")
	public String cajaFisica;

	@SerializedName("CJU")
	public String cajaUni;
}
