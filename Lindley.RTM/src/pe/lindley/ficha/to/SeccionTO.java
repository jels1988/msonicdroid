package pe.lindley.ficha.to;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class SeccionTO {
	
	public SeccionTO(){
		detalleSubseccion = new ArrayList<SubSeccionTO>();
	}
	
	@SerializedName("CSEC")
	private String codigoSeccion;

	@SerializedName("SEC")
	private String descripcionSeccion;

	@SerializedName("SUBS")
	private ArrayList<SubSeccionTO> detalleSubseccion;

	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	public String getDescripcionSeccion() {
		return descripcionSeccion;
	}

	public void setDescripcionSeccion(String descripcionSeccion) {
		this.descripcionSeccion = descripcionSeccion;
	}

	public ArrayList<SubSeccionTO> getDetalleSubseccion() {
		return detalleSubseccion;
	}

	public void setDetalleSubseccion(ArrayList<SubSeccionTO> detalleSubseccion) {
		this.detalleSubseccion = detalleSubseccion;
	}


}
