package pe.lindley.mmil.titanium.to;

import com.google.gson.annotations.SerializedName;

public class PedidoTO {

	@SerializedName("NUM")
	public String pedido;
	
	@SerializedName("CLI")
	public String cliente;
	

	@SerializedName("HOR")
	public String hora;
	
	@SerializedName("EST")
	public String estado;
}
