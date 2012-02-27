package pe.lindley.mmil.to;

import com.google.gson.annotations.SerializedName;

public class DashBoardDetalleTO {
	
	@SerializedName("HOR")
	private String hora;
	
	@SerializedName("AVN")
	private double avance;

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public double getAvance() {
		return avance;
	}

	public void setAvance(double avance) {
		this.avance = avance;
	}

	

}
