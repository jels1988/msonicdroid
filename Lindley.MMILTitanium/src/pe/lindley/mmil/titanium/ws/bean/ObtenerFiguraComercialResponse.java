package pe.lindley.mmil.titanium.ws.bean;

import java.util.List;

import pe.lindley.mmil.titanium.FiguraComercialTO;



import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ObtenerFiguraComercialResponse extends ResponseBase {

	
	@SerializedName("FIG")
	public List<FiguraComercialTO> figuracomercial;
	
}
