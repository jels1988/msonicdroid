package pe.lindley.mmil.to;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class CdaTO {
	
	public CdaTO(){
		coordenada = new ArrayList<CoordenadaTO>();
	}

	@SerializedName("COD")
	private String codigo;

	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@SerializedName("NAM")
	private String nombre;

	@SerializedName("DES")
	private String descripcion;

	@SerializedName("COO")
	private ArrayList<CoordenadaTO> coordenada;

	@SerializedName("COLR")
	private int r;
	
	@SerializedName("COLG")
	private int g;
	
	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	@SerializedName("COLB")
	private int b;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<CoordenadaTO> getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(ArrayList<CoordenadaTO> coordenada) {
		this.coordenada = coordenada;
	}

	

}
