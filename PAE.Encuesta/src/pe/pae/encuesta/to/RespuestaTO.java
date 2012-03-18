package pe.pae.encuesta.to;

import java.util.ArrayList;

public class RespuestaTO {
	
	
	public long respuestaId;
	public int tiendaId;
	public int productoId;
	public int encuestaId;
	public String fechaRegistro;
	public String horaRegistro;
	
	public ArrayList<PreguntaTO> preguntas;
	 
}
