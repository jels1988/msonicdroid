package pe.lindley.ficha.to;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class SubSeccionTO {
	public final static String TIPO_NUMERICO_SIMPLE="1";
	public final static String TIPO_FLAG="2";
	public final static String TIPO_LISTA_SIMPLE="3";
	public final static String TIPO_LISTA_MULTIPLE="4";
	public final static String TIPO_RANGO_NUMERICOS="5";
	public final static String TIPO_RANGO_CARACTERES="6";
	public final static String TIPO_TEXTO_SIMPLE="7";
	public static final String TIPO_NUMERICO_CARACTER = "8";
	public static final String TIPO_CARACTER_NUMERICO = "9";
	public static final String TIPO_NUMERICO_NUMERICO = "10";
	
	public SubSeccionTO(){
		detallePreguntas = new ArrayList<PreguntaTO>();
	}
	
	@SerializedName("CSUB")
	private String codigoSubSeccion;

	@SerializedName("SUB")
	private String descrSubSeccion;

	@SerializedName("TVAL")
	private String tipoValor;
    
	@SerializedName("PRGS")
	private ArrayList<PreguntaTO> detallePreguntas;

	
	@SerializedName("RTPA1")
	private String respuesta1;
	

	@SerializedName("RTPA2")
	private String respuesta2;
	
	@SerializedName("FLG")
	private boolean flag;
	

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getRespuesta1() {
		return respuesta1;
	}

	public void setRespuesta1(String respuesta1) {
		this.respuesta1 = respuesta1;
	}

	public String getRespuesta2() {
		return respuesta2;
	}

	public void setRespuesta2(String respuesta2) {
		this.respuesta2 = respuesta2;
	}

	public String getCodigoSubSeccion() {
		return codigoSubSeccion;
	}

	public void setCodigoSubSeccion(String codigoSubSeccion) {
		this.codigoSubSeccion = codigoSubSeccion;
	}

	public String getDescrSubSeccion() {
		return descrSubSeccion;
	}

	public void setDescrSubSeccion(String descrSubSeccion) {
		this.descrSubSeccion = descrSubSeccion;
	}

	public String getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	public ArrayList<PreguntaTO> getDetallePreguntas() {
		return detallePreguntas;
	}

	public void setDetallePreguntas(ArrayList<PreguntaTO> detallePreguntas) {
		this.detallePreguntas = detallePreguntas;
	}

}
