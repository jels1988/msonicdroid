package pe.lindley.preliquidacion.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class AvanceTO {
	
	@SerializedName("ESP")
	private double avanceEsperado;
	
	@SerializedName("ACT")
	private double avanceActual;
	
	@SerializedName("PRO")
	private double cajasFisicasProgramadas;
	
	@SerializedName("ENT")
	private double cajasFisicasEntregadas;
	
	@SerializedName("REC")
	private String cajasFisicasRechazadas;
	
	@SerializedName("SPR")
	private String solesProgramados;
	
	@SerializedName("SEN")
	private String solesEntregados;
	
	@SerializedName("CPR")
	private String clientesProgramados;
	
	@SerializedName("CEN")
	private String clientesEntregados;
	
	
	@SerializedName("MOT")
	private ArrayList<ProductoTO> productos;


	public double getAvanceEsperado() {
		return avanceEsperado;
	}


	public void setAvanceEsperado(double avanceEsperado) {
		this.avanceEsperado = avanceEsperado;
	}


	public double getAvanceActual() {
		return avanceActual;
	}


	public void setAvanceActual(double avanceActual) {
		this.avanceActual = avanceActual;
	}


	public double getCajasFisicasProgramadas() {
		return cajasFisicasProgramadas;
	}


	public void setCajasFisicasProgramadas(double cajasFisicasProgramadas) {
		this.cajasFisicasProgramadas = cajasFisicasProgramadas;
	}


	public double getCajasFisicasEntregadas() {
		return cajasFisicasEntregadas;
	}


	public void setCajasFisicasEntregadas(double cajasFisicasEntregadas) {
		this.cajasFisicasEntregadas = cajasFisicasEntregadas;
	}


	public String getCajasFisicasRechazadas() {
		return cajasFisicasRechazadas;
	}


	public void setCajasFisicasRechazadas(String cajasFisicasRechazadas) {
		this.cajasFisicasRechazadas = cajasFisicasRechazadas;
	}


	public String getSolesProgramados() {
		return solesProgramados;
	}


	public void setSolesProgramados(String solesProgramados) {
		this.solesProgramados = solesProgramados;
	}


	public String getSolesEntregados() {
		return solesEntregados;
	}


	public void setSolesEntregados(String solesEntregados) {
		this.solesEntregados = solesEntregados;
	}


	public String getClientesProgramados() {
		return clientesProgramados;
	}


	public void setClientesProgramados(String clientesProgramados) {
		this.clientesProgramados = clientesProgramados;
	}


	public String getClientesEntregados() {
		return clientesEntregados;
	}


	public void setClientesEntregados(String clientesEntregados) {
		this.clientesEntregados = clientesEntregados;
	}


	public ArrayList<ProductoTO> getProductos() {
		return productos;
	}


	public void setProductos(ArrayList<ProductoTO> productos) {
		this.productos = productos;
	}

}
