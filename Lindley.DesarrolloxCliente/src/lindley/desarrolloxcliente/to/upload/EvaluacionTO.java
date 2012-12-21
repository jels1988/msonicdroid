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
	
	@Expose()
			@SerializedName("CID")
	      public long id;
	  
		
	@Expose()
			@SerializedName("CLI")
	        public String codigoCliente;

	@Expose()
	        @SerializedName("FCR")
	        public String fechaCreacion;

	@Expose()
	        @SerializedName("HCR")
	        public String horaCreacion;

	@Expose()
	        @SerializedName("UCR")
	        public String usuarioCreacion;

	@Expose()
	        @SerializedName("FCI")
	        public String fechaCierre;

	@Expose()
	        @SerializedName("HCI")
	        public String horaCierre;

	@Expose()
	        @SerializedName("UCI")
	        public String usuarioCierre;

	@Expose()
	        @SerializedName("CSS")
	        public String comboSS;

	@Expose()
	        @SerializedName("OSS")
	        public String observacionSS;

	@Expose()
	        @SerializedName("CMS")
	        public String comboMS;

	@Expose()
	        @SerializedName("OMS")
	        public String observacionMS;

	@Expose()
	        @SerializedName("EST")
	        public String estado;

	@Expose()
	        @SerializedName("SID")
	        public long serverId;

	@Expose()
	        @SerializedName("FLP")
	        public int proceso;
	        
	@Expose()
	        @SerializedName("MID")
	        public String motivoId;
	        
	@Expose()
	        @SerializedName("MOD")
	        public String motivo;
	        
	@Expose()
	        @SerializedName("ACT")
	        public String activosLindley;
	        
	@Expose()
	        @SerializedName("FDE")
	        public String codigoFDE;
	        
	@Expose()
	        @SerializedName("OPO")
	        public List<OportunidadTO> oportunidades; 
	        
	@Expose()
	        @SerializedName("POS")
	        public List<PosicionTO> posiciones;
	        
	@Expose()
	        @SerializedName("PRE")
	        public List<PresentacionTO> presentaciones;
	        
	@Expose()
	        @SerializedName("SKU")
	        public List<SkuTO> skus;
	        
	
	        @Expose()
	        public int tieneCambio;
	        
	        @Expose()
	        public String cliente;
	        
}
