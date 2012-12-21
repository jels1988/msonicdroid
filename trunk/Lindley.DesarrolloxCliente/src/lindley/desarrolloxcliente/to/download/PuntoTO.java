package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PuntoTO {
	
	public long id;
	
	@Expose()
	@SerializedName("TPR")
	public String tpPro;
	
	@Expose()
	@SerializedName("ARR")
	public String cdArr;
	
	@Expose()
	@SerializedName("VAR")
	public String cdVar;
	
	@Expose()
	@SerializedName("CND")
	public String cnd01;
	
	@Expose()
	@SerializedName("PTO")
	public int punto;
	
	
}
