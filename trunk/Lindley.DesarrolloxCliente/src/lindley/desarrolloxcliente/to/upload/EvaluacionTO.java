package lindley.desarrolloxcliente.to.upload;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EvaluacionTO {
			
	public EvaluacionTO(){
		oportunidades = new ArrayList<OportunidadTO>();
		posiciones = new ArrayList<PosicionTO>();
		presentaciones = new ArrayList<PresentacionTO>(); 
		skus = new ArrayList<SkuTO>();
		
		comboSS=ConstantesApp.RESPUESTA_NO;
		comboMS=ConstantesApp.RESPUESTA_NO;
	}
	
			@SerializedName("CID")
	      public long id;
	  
		
			
			@SerializedName("CLI")
	        public String codigoCliente;

	        @SerializedName("FCR")
	        public String fechaCreacion;

	        @SerializedName("HCR")
	        public String horaCreacion;

	        @SerializedName("UCR")
	        public String usuarioCreacion;

	        @SerializedName("FCI")
	        public String fechaCierre;

	        @SerializedName("HCI")
	        public String horaCierre;

	        @SerializedName("UCI")
	        public String usuarioCierre;

	        @SerializedName("CSS")
	        public String comboSS;

	        @SerializedName("OSS")
	        public String observacionSS;

	        @SerializedName("CMS")
	        public String comboMS;

	        @SerializedName("OMS")
	        public String observacionMS;

	        @SerializedName("EST")
	        public String estado;

	        @SerializedName("SID")
	        public long serverId;
	        
	        
	        @SerializedName("MID")
	        public String motivoId;
	        
	        @SerializedName("MOD")
	        public String motivo;
	        
	        @SerializedName("ACT")
	        public String activosLindley;
	        
	        @SerializedName("FDE")
	        public String codigoFDE;
	        
	        @SerializedName("OPO")
	        public List<OportunidadTO> oportunidades; 
	        
	        @SerializedName("POS")
	        public List<PosicionTO> posiciones;
	        
	        @SerializedName("PRE")
	        public List<PresentacionTO> presentaciones;
	        
	        @SerializedName("SKU")
	        public List<SkuTO> skus;
	        
	        @Expose()
	        public int tieneCambio;
	        
}
