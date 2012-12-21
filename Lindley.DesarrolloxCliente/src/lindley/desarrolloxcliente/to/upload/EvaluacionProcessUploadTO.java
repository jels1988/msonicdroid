package lindley.desarrolloxcliente.to.upload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EvaluacionProcessUploadTO {
	@Expose()
	@SerializedName("SID")
		        public long serverId;

	@Expose()
	@SerializedName("CID")
		        public long clientId;
}
