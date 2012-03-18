package pe.pae.encuesta.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class PreguntaTO {
	
	
	public final static int TIENE_OBSERVACION_SI=1;
	public final static int TIENE_OBSERVACION_NO=0;
	
	public final static int TIENE_FOTO_SI=1;
	public final static int TIENE_FOTO_NO=0;
	
	
	public final static int PREGUNTA_PLANTILLA_NUMERICA=1;
	public final static int PREGUNTA_PLANTILLA_TEXTO=2;
	public final static int PREGUNTA_PLANTILLA_LISTA_MULTIPLE=3;
	public final static int PREGUNTA_PLANTILLA_LISTA_SIMPLE=4;
	
	public PreguntaTO(){
		opciones = new ArrayList<OpcionTO>();
	}
	
	@SerializedName("Id")
	public int preguntaId;
	
	@SerializedName("Des")
	public String pregunta;
	
	@SerializedName("Tip")
	public int tipoPregunta;
	
	
	@SerializedName("Fot")
	public int tieneFoto;
	
	@SerializedName("Obs")
	public int tieneObservacion;
	
	@SerializedName("Com")
	public String comentario;
	
	
	@SerializedName("Opc")
	public ArrayList<OpcionTO> opciones;
	
	
	
	public long respuestaOpcionId;
	public String observacion;
	public String respuesta_1;
	
	
}
