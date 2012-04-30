package pe.lindley.mmil.titanium.ws.bean;

import java.util.List;

import pe.lindley.mmil.titanium.to.MapVendedorTO;


import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ListarMapVendedorResponse extends ResponseBase {

	@SerializedName("MVD")
	public List<MapVendedorTO> listaMapVendedor;
}
