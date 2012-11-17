package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.SerializedName;

public class AceleradorTO {

			public long id;
			
			@SerializedName("AGR")
	        public String tipoAgrupacion;

	        @SerializedName("VAR")
	        public String codigoVariable;

	        @SerializedName("FIN")
	        public String fechaInicio;

	        @SerializedName("FFI")
	        public String fechaFin;

	        @SerializedName("PTO")
	        public int punto;
	        
}
