package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class MapVendedorTO {

	
	@SerializedName("COD")
	public String codigoCliente;

	@SerializedName("NOM")
	public String nombre;

	@SerializedName("DIR")
	public String direccion;

	@SerializedName("FEC")
	public String fechaReg;

	@SerializedName("LTC")
	public String latitudCliente;

	@SerializedName("LNC")
	public String longitudCliente;

	@SerializedName("LTP")
	public String latitudPhone;

	@SerializedName("LNP")
	public String longitudPhone;

	@SerializedName("TPR")
	public String tipoRegistro;

	@SerializedName("MON")
	public double montoPedido;

	@SerializedName("CDP")
	public String codigoPedido;

	@SerializedName("PPD")
	public String primerPedido;

	@SerializedName("UPD")
	public String ultimoPedido;

	@SerializedName("NPD")
	public int cantidadPedido;

	@SerializedName("CJF")
	public String cajaFisicas;

	@SerializedName("CJU")
	public String cajaUnitaria;

	@SerializedName("DIS")
	public String distancia;

	@SerializedName("TIE")
	public String tiempo;

	@SerializedName("NOP")
	public String numeroPedido;
	
}
