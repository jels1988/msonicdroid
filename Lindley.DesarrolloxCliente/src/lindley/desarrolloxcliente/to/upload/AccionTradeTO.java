package lindley.desarrolloxcliente.to.upload;

import com.google.gson.annotations.SerializedName;

public class AccionTradeTO {

	public long id;
	
	@SerializedName("COD")
	public String codigo;
	
	@SerializedName("DES")
	public String descripcion;
	
	@Override
    public String toString() {
        return this.descripcion;
    }
}
