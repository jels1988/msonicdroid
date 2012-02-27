package pe.lindley.ventacero.ws.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import pe.lindley.util.ResponseBase;
import pe.lindley.ventacero.to.VentaCeroTO;

public class ObtenerVentaCeroResponse extends ResponseBase{

	@SerializedName("VTA")
	private List<VentaCeroTO> listaVentaCero;

	public List<VentaCeroTO> getListaVentaCero() {
		return listaVentaCero;
	}

	public void setListaVentaCero(List<VentaCeroTO> listaVentaCero) {
		this.listaVentaCero = listaVentaCero;
	}
	
}
