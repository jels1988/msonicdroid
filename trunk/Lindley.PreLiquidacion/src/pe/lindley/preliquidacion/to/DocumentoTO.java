package pe.lindley.preliquidacion.to;


import com.google.gson.annotations.SerializedName;

public class DocumentoTO {
	
	private int documentoId;
	private int enviado;
	
	public int getEnviado() {
		return enviado;
	}

	public void setEnviado(int enviado) {
		this.enviado = enviado;
	}

	public int getDocumentoId() {
		return documentoId;
	}

	public void setDocumentoId(int documentoId) {
		this.documentoId = documentoId;
	}

	@SerializedName("CLI")
	private String cliente;
	
	@SerializedName("NOM")
	private String nombre;
	
	@SerializedName("NDO")
	private String nroDocumento;
	
	@SerializedName("IMP")
	private double importe;
	
	@SerializedName("EST")
	private String estado;

	@SerializedName("TPO")
	private String tipoDocumento;
	
	private String estadoDes;
	private String motivo;
	
	
	public String getEstadoDes() {
		return estadoDes;
	}

	public void setEstadoDes(String estadoDes) {
		this.estadoDes = estadoDes;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	
	
}
