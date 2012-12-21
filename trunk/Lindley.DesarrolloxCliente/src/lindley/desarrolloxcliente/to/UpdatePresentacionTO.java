package lindley.desarrolloxcliente.to;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePresentacionTO {

	public UpdatePresentacionTO()
    {
		listaSKU = new ArrayList<UpdateSKUPresentacionTO>();
    }
	
	@Expose()
	@SerializedName("CVAR")
	public String codigoVariable;       

	@Expose()
	@SerializedName("LSKU")
	public List<UpdateSKUPresentacionTO> listaSKU;

	@Expose()
	@SerializedName("FCM")
	public String fechaCompromiso;
}
