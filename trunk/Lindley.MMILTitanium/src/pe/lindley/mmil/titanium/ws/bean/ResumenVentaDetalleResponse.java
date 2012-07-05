package pe.lindley.mmil.titanium.ws.bean;

import com.google.gson.annotations.SerializedName;

import pe.lindley.mmil.titanium.to.ResumenTO;
import net.msonic.lib.ResponseBase;

public class ResumenVentaDetalleResponse extends ResponseBase {

	@SerializedName("DET")
	public ResumenTO detalle;
	
	
}
