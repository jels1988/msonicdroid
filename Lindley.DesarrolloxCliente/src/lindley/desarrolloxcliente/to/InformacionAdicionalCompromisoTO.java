package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InformacionAdicionalCompromisoTO {

	@Expose()
	@SerializedName("CSS")
    public String comboSS;

	@Expose()
	@SerializedName("OBSSS")
	public String observacionSS;

	@Expose()
	@SerializedName("CMS")
	public String comboMS;

	@Expose()
	@SerializedName("OBS")
	public String observacion;
}
