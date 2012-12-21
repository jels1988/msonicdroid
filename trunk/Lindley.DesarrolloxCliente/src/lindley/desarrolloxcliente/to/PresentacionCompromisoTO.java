package lindley.desarrolloxcliente.to;

import java.util.ArrayList;

import lindley.desarrolloxcliente.ConstantesApp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PresentacionCompromisoTO {

	public PresentacionCompromisoTO()
    {
        listaSKU = new ArrayList<SKUPresentacionCompromisoTO>();
        puntosSugeridos = "0";
        puntosBonus = "0";
        puntosGanados="0";
        
        confirmacion =  ConstantesApp.RESPUESTA_NO;
		origen =  ConstantesApp.OPORTUNIDAD_SISTEMA;
		estado =  ConstantesApp.OPORTUNIDAD_ABIERTA;
    }
    
	public long id;
	
	@Expose()
    @SerializedName("LSKU")
    //public ArrayList<SKUPresentacionTO> listaSKU;
    public ArrayList<SKUPresentacionCompromisoTO> listaSKU;
    
    
    
    
    public String fechaCompromiso;

    
    public String puntosSugeridos;
    
    
    public String puntosGanados;
    
    public String puntosBonus;
    
    
    public String cumplio;
    
    public String tipoAgrupacion;
    
    
    public String codigoVariable;

    public String codfde;
    
    public String fechaEncuesta;
    
    public int isOk;
    
    public String confirmacion;
   	public String origen;
   	public String estado;
    
}
