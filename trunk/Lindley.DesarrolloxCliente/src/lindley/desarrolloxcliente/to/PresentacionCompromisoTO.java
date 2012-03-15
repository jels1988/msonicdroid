package lindley.desarrolloxcliente.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class PresentacionCompromisoTO {

	public PresentacionCompromisoTO()
    {
        listaSKU = new ArrayList<SKUPresentacionCompromisoTO>();
    }
    	
    @SerializedName("CVAR")
    private String codigoVariable;

    @SerializedName("DVAR")
    private String descripcionVariable;

    @SerializedName("LSKU")
    private ArrayList<SKUPresentacionCompromisoTO> listaSKU;

    @SerializedName("FCM")
    private String fechaCompromiso;

    @SerializedName("CNF")
    private String confirmacion;

    @SerializedName("PTO")
    private String puntosSugeridos;

    @SerializedName("FDE")
    private String codigoFDE;

	public String getCodigoVariable() {
		return codigoVariable;
	}

	public void setCodigoVariable(String codigoVariable) {
		this.codigoVariable = codigoVariable;
	}

	public String getDescripcionVariable() {
		return descripcionVariable;
	}

	public void setDescripcionVariable(String descripcionVariable) {
		this.descripcionVariable = descripcionVariable;
	}

	public ArrayList<SKUPresentacionCompromisoTO> getListaSKU() {
		return listaSKU;
	}

	public void setListaSKU(ArrayList<SKUPresentacionCompromisoTO> listaSKU) {
		this.listaSKU = listaSKU;
	}

	public String getFechaCompromiso() {
		return fechaCompromiso;
	}

	public void setFechaCompromiso(String fechaCompromiso) {
		this.fechaCompromiso = fechaCompromiso;
	}

	public String getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(String confirmacion) {
		this.confirmacion = confirmacion;
	}

	public String getPuntosSugeridos() {
		return puntosSugeridos;
	}

	public void setPuntosSugeridos(String puntosSugeridos) {
		this.puntosSugeridos = puntosSugeridos;
	}

	public String getCodigoFDE() {
		return codigoFDE;
	}

	public void setCodigoFDE(String codigoFDE) {
		this.codigoFDE = codigoFDE;
	}
}
