package pe.lindley.mmil.to;

import com.google.gson.annotations.SerializedName;

public class ConfiguracionTO {
	
	@SerializedName("KEY")
	private String key;

	@SerializedName("VAL")
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	

}
