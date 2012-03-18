package pe.pae.encuesta.ws.bean;

import java.util.List;

import pe.pae.encuesta.to.ProductoTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class EncuestaResponse extends ResponseBase {

	@SerializedName("pro")
	public List<ProductoTO> productos;
}
