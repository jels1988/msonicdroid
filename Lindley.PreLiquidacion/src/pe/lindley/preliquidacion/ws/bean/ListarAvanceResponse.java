package pe.lindley.preliquidacion.ws.bean;

import pe.lindley.preliquidacion.to.AvanceTO;

import com.google.gson.annotations.SerializedName;
import net.msonic.lib.ResponseBase;

public class ListarAvanceResponse extends ResponseBase {
	
	@SerializedName("DAT")
	private AvanceTO avance;

	public AvanceTO getAvance() {
		return avance;
	}

	public void setAvance(AvanceTO avance) {
		this.avance = avance;
	}
}