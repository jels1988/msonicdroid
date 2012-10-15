package lindley.desarrolloxcliente.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class PresentacionCompromisoTO {

	public PresentacionCompromisoTO()
    {
        listaSKU = new ArrayList<SKUPresentacionCompromisoTO>();
    }
    	
  
    @SerializedName("LSKU")
    //public ArrayList<SKUPresentacionTO> listaSKU;
    public ArrayList<SKUPresentacionCompromisoTO> listaSKU;
    
    
    
    @SerializedName("FCM")
    public String fechaCompromiso;

    @SerializedName("PTO")
    public String puntosSugeridos;
    
    @SerializedName("PTOG")
    public String puntosGanados;
    
    @SerializedName("CUM")
    public String cumplio;
    
    public String tipoAgrupacion;
    
    @SerializedName("CVAR")
    public String codigoVariable;

    public String codfde;
    
    public String fechaEncuesta;
    
    //@SerializedName("CUM")
    public int isOk;
    
}
