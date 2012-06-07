package pe.lindley.mmil.titanium.ws.bean;

import java.util.ArrayList;

import pe.lindley.mmil.titanium.to.PedidoTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class PedidosServiceResponse extends ResponseBase {

	
	@SerializedName("PED")
	public ArrayList<PedidoTO> pedidos;
}
