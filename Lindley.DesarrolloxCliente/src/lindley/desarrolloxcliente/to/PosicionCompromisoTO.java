package lindley.desarrolloxcliente.to;

import java.util.ArrayList;

import lindley.desarrolloxcliente.ConstantesApp;

import com.google.gson.annotations.SerializedName;

public class PosicionCompromisoTO {

	public PosicionCompromisoTO()
    {
		listCompromisos = new ArrayList<CompromisoPosicionTO>();
		cumplio = ConstantesApp.RESPUESTA_NO;;
		fotoInicial = "";
		fotoFinal = " ";
		puntosGanados="0";
		puntosSugeridos="0";
		red="0";
    }

	public long id;
	
	@SerializedName("RSPT")
    public String respuesta;
	
    @SerializedName("CVAR")
    public String codigoVariable;
            
    @SerializedName("PTO")
    public String puntosSugeridos;
    
    @SerializedName("PTG")
    public String puntosGanados;
    
    @SerializedName("FINI")
    public String fotoInicial;
    
    @SerializedName("RED")
    public String red;

    @SerializedName("PMX")
    public String ptoMaximo;
    
    @SerializedName("ACM")
    public String observacion;
    
    @SerializedName("FCM")
    public String fechaCompromiso;
    
    
    @SerializedName("CMP")
    public ArrayList<CompromisoPosicionTO> listCompromisos;
    
    @SerializedName("CUM")
    public String cumplio;
    
    @SerializedName("FFIN")
    public String fotoFinal;
    
}
