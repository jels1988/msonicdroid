package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.FotoExitoTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarFotoExitoResponse extends ResponseBase {

	@Expose()
	@SerializedName("FEX")
	public List<FotoExitoTO> listFotoExito;
}
