package lindley.desarrolloxcliente.to.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AceleradorTO {

			@Expose()
			public long id;
			
			@Expose()
			@SerializedName("AGR")
	        public String tipoAgrupacion;

			@Expose()
	        @SerializedName("VAR")
	        public String codigoVariable;

			@Expose()
	        @SerializedName("FIN")
	        public String fechaInicio;

			@Expose()
	        @SerializedName("FFI")
	        public String fechaFin;

			@Expose()
	        @SerializedName("PTO")
	        public int punto;
	        
}
