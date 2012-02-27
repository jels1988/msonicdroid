package pe.lindley.ficha.ws.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import pe.lindley.ficha.to.FiguraComercialTO;
import pe.lindley.util.ResponseBase;

public class ObtenerFiguraComercialResponse extends ResponseBase{
	
	@SerializedName("FIG")
	private List<FiguraComercialTO> figuracomercial;

	public List<FiguraComercialTO> getFiguracomercial() {
		return figuracomercial;
	}

	public void setFiguracomercial(List<FiguraComercialTO> figuracomercial) {
		this.figuracomercial = figuracomercial;
	}
}
