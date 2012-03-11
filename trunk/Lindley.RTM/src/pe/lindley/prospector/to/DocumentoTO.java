package pe.lindley.prospector.to;

public class DocumentoTO {

	
	public static final int LOCAL=0;
	public static final int SERVER=1;
	
	private long id;
	private int documentoId;
	private String descripcion;
	private int obligatorio;
	private String nombreArchivo;
	private int esLocal;
	
	public int getDocumentoId() {
		return documentoId;
	}
	public void setDocumentoId(int documentoId) {
		this.documentoId = documentoId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getObligatorio() {
		return obligatorio;
	}
	public void setObligatorio(int obligatorio) {
		this.obligatorio = obligatorio;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getEsLocal() {
		return esLocal;
	}
	public void setEsLocal(int esLocal) {
		this.esLocal = esLocal;
	}
	
}
