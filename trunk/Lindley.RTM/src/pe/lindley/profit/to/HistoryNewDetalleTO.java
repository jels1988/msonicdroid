package pe.lindley.profit.to;

import com.google.gson.annotations.SerializedName;


//codigo
//descripcion
//lista:
	// anio
	// mes
	// 1
	// 2
	// 3
	// 4	


public class HistoryNewDetalleTO {
	
	@SerializedName("ANIO")
	private int anio;
	
	@SerializedName("MES")
	private int mes;
	
	@SerializedName("CACFA")
	private double cajasFacturadas;
	
	@SerializedName("CAUFA")
	private double cajasUnitarias;
	
	@SerializedName("IMFAC")
	private double importeFacturado;
	
	@SerializedName("IMUTI")
	private double utilidad;

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public double getCajasFacturadas() {
		return cajasFacturadas;
	}

	public void setCajasFacturadas(double cajasFacturadas) {
		this.cajasFacturadas = cajasFacturadas;
	}

	public double getCajasUnitarias() {
		return cajasUnitarias;
	}

	public void setCajasUnitarias(double cajasUnitarias) {
		this.cajasUnitarias = cajasUnitarias;
	}

	public double getImporteFacturado() {
		return importeFacturado;
	}

	public void setImporteFacturado(double importeFacturado) {
		this.importeFacturado = importeFacturado;
	}

	public double getUtilidad() {
		return utilidad;
	}

	public void setUtilidad(double utilidad) {
		this.utilidad = utilidad;
	}
}