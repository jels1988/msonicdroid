package lindley.desarrolloxcliente.to.upload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PosicionCompromisoTO {

		@Expose()
		public long id;

		@SerializedName("EID")
	    public long evaluacionId;
		
	    @SerializedName("TPA")
        public String tipoAgrupacion;

	    @SerializedName("CVR")
        public String codigoVariable;
        
	    @SerializedName("ORD")
        public int orden;
	    
	    @SerializedName("OBS")
        public String observacion;
	    
	    @SerializedName("EST")
        public String estado;
	    
	    @SerializedName("FLP")
        public int proceso;
}
