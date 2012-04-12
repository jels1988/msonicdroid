package lindley.desarrolloxcliente.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class PresentacionCompromisoTO {

	public PresentacionCompromisoTO()
    {
        listaSKU = new ArrayList<SKUPresentacionCompromisoTO>();
    }
    	
    @SerializedName("CVAR")
    public String codigoVariable;

    @SerializedName("LSKU")
    public ArrayList<SKUPresentacionCompromisoTO> listaSKU;

    @SerializedName("FCM")
    public String fechaCompromiso;

    @SerializedName("PTO")
    public String puntosSugeridos;
    
    public String cumplio;

}
