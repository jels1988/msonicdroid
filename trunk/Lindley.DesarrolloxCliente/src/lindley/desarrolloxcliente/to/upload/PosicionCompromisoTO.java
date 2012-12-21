package lindley.desarrolloxcliente.to.upload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PosicionCompromisoTO {

		@Expose()
		public long id;

		@Expose()
		@SerializedName("EID")
	    public long evaluacionId;
		
		@Expose()
	    @SerializedName("TPA")
        public String tipoAgrupacion;

		@Expose()
	    @SerializedName("CVR")
        public String codigoVariable;
        
		@Expose()
	    @SerializedName("ORD")
        public int orden;
	    
		@Expose()
	    @SerializedName("OBS")
        public String observacion;
	    
		@Expose()
	    @SerializedName("EST")
        public String estado;
	    
		@Expose()
	    @SerializedName("FLP")
        public int proceso;
}
