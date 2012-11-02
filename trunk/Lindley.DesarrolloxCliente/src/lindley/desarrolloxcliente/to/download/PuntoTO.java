package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.SerializedName;

public class PuntoTO {
	
	public long id;
	
	
	@SerializedName("TPR")
	public String tpPro;
	

	@SerializedName("ARR")
	public String cdArr;
	

	@SerializedName("VAR")
	public String cdVar;
	

	@SerializedName("CND")
	public String cnd01;
	
	@SerializedName("PTO")
	public int punto;
	
	
}
