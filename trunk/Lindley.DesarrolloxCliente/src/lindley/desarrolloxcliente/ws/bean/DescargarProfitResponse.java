package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.download.ProfitTO;
import net.msonic.lib.ResponseBase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DescargarProfitResponse extends ResponseBase {

	@Expose()
	@SerializedName("PRO")
	public List<ProfitTO> profit;
}

