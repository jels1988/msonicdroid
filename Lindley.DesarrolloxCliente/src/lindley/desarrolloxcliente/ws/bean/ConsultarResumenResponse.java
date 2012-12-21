package lindley.desarrolloxcliente.ws.bean;

import java.util.ArrayList;

import lindley.desarrolloxcliente.to.ResumenValueTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.msonic.lib.ResponseBase;

public class ConsultarResumenResponse extends ResponseBase {

	@Expose()
	@SerializedName("DAT")
	public ArrayList<ResumenValueTO> datos;

}
