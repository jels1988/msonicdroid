package pe.lindley.mmil.to;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class DashBoardTO {
	
	public DashBoardTO(){
		detalle = new ArrayList<DashBoardDetalleTO>();
	}
	
	@SerializedName("COD")
	private String codigo;
	
	@SerializedName("CUO")
	private double cuota;
	
	@SerializedName("AVN")
	private double avance;
	
	@SerializedName("TIP")
	private String tipo;
	
	@SerializedName("COL")
	private String color;
	
	@SerializedName("DET")
	private ArrayList<DashBoardDetalleTO> detalle;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getCuota() {
		return cuota;
	}

	public void setCuota(double cuota) {
		this.cuota = cuota;
	}

	public double getAvance() {
		return avance;
	}

	public void setAvance(double avance) {
		this.avance = avance;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ArrayList<DashBoardDetalleTO> getDetalle() {
		return detalle;
	}

	public void setDetalle(ArrayList<DashBoardDetalleTO> detalle) {
		this.detalle = detalle;
	}

	
}
