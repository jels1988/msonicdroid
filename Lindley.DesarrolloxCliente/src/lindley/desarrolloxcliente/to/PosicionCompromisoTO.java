package lindley.desarrolloxcliente.to;

import java.util.ArrayList;

import lindley.desarrolloxcliente.ConstantesApp;

public class PosicionCompromisoTO {

	public PosicionCompromisoTO()
    {
		listCompromisos = new ArrayList<CompromisoPosicionTO>();
		cumplio = ConstantesApp.RESPUESTA_NO;;
		fotoInicial = "";
		fotoFinal = " ";
		puntosGanados="0";
		puntosSugeridos="0";
		puntosBonus="0";
		soviMaximo="0";
		soviDiferencia="0";
		ptoMaximo="0";
		soviRed="0";
		
		activosLindley = ConstantesApp.RESPUESTA_NO;
		confirmacion =  ConstantesApp.RESPUESTA_NO;
		origen =  ConstantesApp.OPORTUNIDAD_SISTEMA;
		estado =  ConstantesApp.OPORTUNIDAD_ABIERTA;
	
		
    }

	public long id;
	
	
    public String activosLindley;
	
    
    public String codigoVariable;
    
    public String puntosSugeridos;
    public String puntosGanados;
    public String puntosBonus;
    
    public String soviRed;
    public String soviMaximo;
    public String soviDiferencia;
    
    public String fotoInicial;
    public String fotoFinal;
 
    
    public String ptoMaximo;
    
    
    public String observacion;
    
    
    public String fechaCompromiso;
    
    
    
    public ArrayList<CompromisoPosicionTO> listCompromisos;
    
    
    public String cumplio;
    
    
   
    
    public String tipoAgrupacion;
    public String confirmacion;
	public String origen;
	public String estado;
    
}
