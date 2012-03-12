package pe.lindley.prospector.to;

import com.google.gson.annotations.SerializedName;

public class ClienteIdTO {

	@SerializedName("cid")
	private int clienteId;
	
	@SerializedName("bdid")
	private long idBd;
	
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	public long getIdBd() {
		return idBd;
	}
	public void setIdBd(long idBd) {
		this.idBd = idBd;
	}
}
