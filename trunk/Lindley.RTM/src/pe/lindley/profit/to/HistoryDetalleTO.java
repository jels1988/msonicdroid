package pe.lindley.profit.to;

import com.google.gson.annotations.SerializedName;

public class HistoryDetalleTO {
	@SerializedName("nuanio")
	private int anio;
	
	@SerializedName("numes")
	private int mes;
	
	@SerializedName("sem")
	private int semana;
	
	@SerializedName("cacfa")
	private double cajasFacturadas;
	
	@SerializedName("caufa")
	private double cajasUnitarias;
	
	@SerializedName("imfac")
	private double importeFacturado;
	
	@SerializedName("imuti")
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

	public int getSemana() {
		return semana;
	}

	public void setSemana(int semana) {
		this.semana = semana;
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
