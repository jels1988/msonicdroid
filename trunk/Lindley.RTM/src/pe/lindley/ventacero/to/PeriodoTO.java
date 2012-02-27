package pe.lindley.ventacero.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class PeriodoTO {
	
	@SerializedName("ANO")
	private String anio;

	@SerializedName("MSS")
	private ArrayList<MesTO> mes;

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public ArrayList<MesTO> getMes() {
		return mes;
	}

	public void setMes(ArrayList<MesTO> mes) {
		this.mes = mes;
	}

}
