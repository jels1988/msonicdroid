package lindley.desarrolloxcliente.to;

import java.util.ArrayList;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PresentacionTO {

	public PresentacionTO()
    {
        listaSKU = new ArrayList<SKUPresentacionTO>();
        seleccionado = true;
    }
    
	@Expose()
    @SerializedName("CVAR")
    private String codigoVariable;

	@Expose()
    @SerializedName("DVAR")
    private String descripcionVariable;

	@Expose()
    @SerializedName("LSKU")
    private ArrayList<SKUPresentacionTO> listaSKU;

	@Expose()
    @SerializedName("PTO")
    private String puntosSugeridos;

	@Expose()
    @SerializedName("FDE")
    private String codigoFDE;
    
    private boolean seleccionado;

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

	public ArrayList<SKUPresentacionTO> getListaSKU() {
		return listaSKU;
	}

	public void setListaSKU(ArrayList<SKUPresentacionTO> listaSKU) {
		this.listaSKU = listaSKU;
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

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
    
}
