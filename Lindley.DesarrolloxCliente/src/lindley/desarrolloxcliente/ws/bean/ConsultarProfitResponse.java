package lindley.desarrolloxcliente.ws.bean;

import java.util.List;

import lindley.desarrolloxcliente.to.ProfitTO;

import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarProfitResponse extends ResponseBase {

	@SerializedName("PRO")
    public List<ProfitTO> ListaProfit;
}
