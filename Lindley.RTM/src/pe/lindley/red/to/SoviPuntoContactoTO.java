package pe.lindley.red.to;

import com.google.gson.annotations.SerializedName;

public class SoviPuntoContactoTO {

	@SerializedName("CVI")
	private int carasVisibles;
	
	@SerializedName("COM")
	private String compania;
	

	@SerializedName("TPC")
	private String tipoPuntoContacto;


	public int getCarasVisibles() {
		return carasVisibles;
	}


	public void setCarasVisibles(int carasVisibles) {
		this.carasVisibles = carasVisibles;
	}


	public String getCompania() {
		return compania;
	}


	public void setCompania(String compania) {
		this.compania = compania;
	}


	public String getTipoPuntoContacto() {
		return tipoPuntoContacto;
	}


	public void setTipoPuntoContacto(String tipoPuntoContacto) {
		this.tipoPuntoContacto = tipoPuntoContacto;
	}

	
	
}
