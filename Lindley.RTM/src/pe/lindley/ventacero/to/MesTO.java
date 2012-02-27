package pe.lindley.ventacero.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class MesTO {
	
	@SerializedName("MES")
	private String mes;

	@SerializedName("SEM")
	private ArrayList<String> semana;

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public ArrayList<String> getSemana() {
		return semana;
	}

	public void setSemana(ArrayList<String> semana) {
		this.semana = semana;
	}

}
