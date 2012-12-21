package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import net.msonic.lib.ResponseBase;

import lindley.desarrolloxcliente.to.download.PuntoTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DescargarPuntoResponse extends ResponseBase {

	@Expose()
	@SerializedName("PTO")
	public List<PuntoTO> puntos;
}
