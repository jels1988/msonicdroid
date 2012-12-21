package lindley.desarrolloxcliente.to.upload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccionTradeTO {

	public long id;
	
	@Expose()
	@SerializedName("COD")
	public String codigo;
	
	@Expose()
	@SerializedName("DES")
	public String descripcion;
	
	@Override
    public String toString() {
        return this.descripcion;
    }
}
