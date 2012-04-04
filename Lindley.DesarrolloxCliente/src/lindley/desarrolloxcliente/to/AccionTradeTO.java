package lindley.desarrolloxcliente.to;

import com.google.gson.annotations.SerializedName;

public class AccionTradeTO {

	public AccionTradeTO()
	{
		
	}
	
	public AccionTradeTO(String codigo, String descripcion)
	{
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	@SerializedName("COD")
	private String codigo;

	@SerializedName("DES")
	private String descripcion;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo; 
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
    public String toString() {
        return this.descripcion;
    }
}
