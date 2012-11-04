package lindley.desarrolloxcliente.to.upload;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class EvaluacionTO {
	
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
	        
	        @SerializedName("ACT")
	        public String activosLindley;
	        
	        @SerializedName("FDE")
	        public String codigoFDE;
	        
	        @SerializedName("OPO")
	        public List<OportunidadTO> oportunidades; 
	        
}
