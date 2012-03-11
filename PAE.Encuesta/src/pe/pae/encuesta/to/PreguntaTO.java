package pe.pae.encuesta.to;

import java.util.ArrayList;

public class PreguntaTO {
	
	public PreguntaTO(){
		opciones = new ArrayList<OpcionTO>();
	}
	public int preguntaId;
	public int tipoPregunta;
	public String pregunta;
	//OPCIONES
	public ArrayList<OpcionTO> opciones;
	
	//ALMACENAR LA RESPUESTA
	public String comentario;
	
		
	
}
