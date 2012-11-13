package lindley.desarrolloxcliente.to.upload;

public class ProcesoInfoTO {
	
	public static final int  ESTADO_INICIO=0;
	public static final int  ESTADO_DESCARGA=1;
	public static final int  ESTADO_DB=2;
	public static final int  ESTADO_ERROR=3;
	public static final int  ESTADO_OK=4;
	
	public int processId;
	public String descripcion;
	public boolean isExito;
	public int estado;
}
