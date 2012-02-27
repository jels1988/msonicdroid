package pe.lindley.om.to;

import com.google.gson.annotations.SerializedName;

public class EventoTO {

	@SerializedName("NUORD")
	private long nuOrd;
	
	
	private int nuEve;
	
	@SerializedName("NUEVE")
	private String nuEve2;
	
	@SerializedName("TPEVE")
	private String tpEve;
	@SerializedName("DSTPE")
	private String dsTpE;
	
	@SerializedName("TPMOT")
	private String tpMot;
	@SerializedName("DSTPM")
	private String dsTpM;
	
	@SerializedName("TPRCR")
	private String tpRCr;
	@SerializedName("USRCR")
	private String usRCr;
	@SerializedName("DSUSC")
	private String dsUsC;
	
	@SerializedName("FECRE")
	private String feCre;
	@SerializedName("HRCRE")
	private String hrCre;
	
	@SerializedName("TPREA")
	private String tpReA;
	@SerializedName("USREA")
	private String usReA;
	@SerializedName("DSUSA")
	private String dsUsA;
	
	@SerializedName("TPREU")
	private String tpReU;
	
	@SerializedName("USREU")
	private String usReU;
	
	@SerializedName("DSUSU")
	private String dsUsU;
	
	@SerializedName("FEMOD")
	private String feMod;
	@SerializedName("HRMOD")
	private String hrMod;
	@SerializedName("DSOBS")
	private String dsObs;
	
	public long getNuOrd() {
		return nuOrd;
	}
	public void setNuOrd(long nuOrd) {
		this.nuOrd = nuOrd;
	}
	public long getNuEve() {
		return nuEve;
	}
	public void setNuEve(int nuEve) {
		this.nuEve = nuEve;
	}
	public String getNuEve2() {
		return nuEve2;
	}
	public void setNuEve2(String nuEve2) {
		this.nuEve2 = nuEve2;
	}
	public String getTpEve() {
		return tpEve;
	}
	public void setTpEve(String tpEve) {
		this.tpEve = tpEve;
	}
	public String getDsTpE() {
		return dsTpE;
	}
	public void setDsTpE(String dsTpE) {
		this.dsTpE = dsTpE;
	}
	public String getTpMot() {
		return tpMot;
	}
	public void setTpMot(String tpMot) {
		this.tpMot = tpMot;
	}
	public String getDsTpM() {
		return dsTpM;
	}
	public void setDsTpM(String dsTpM) {
		this.dsTpM = dsTpM;
	}
	public String getTpRCr() {
		return tpRCr;
	}
	public void setTpRCr(String tpRCr) {
		this.tpRCr = tpRCr;
	}
	public String getUsRCr() {
		return usRCr;
	}
	public void setUsRCr(String usRCr) {
		this.usRCr = usRCr;
	}
	public String getDsUsC() {
		return dsUsC;
	}
	public void setDsUsC(String dsUsC) {
		this.dsUsC = dsUsC;
	}
	public String getFeCre() {
		return feCre;
	}
	public void setFeCre(String feCre) {
		this.feCre = feCre;
	}
	public String getHrCre() {
		return hrCre;
	}
	public void setHrCre(String hrCre) {
		this.hrCre = hrCre;
	}
	public String getTpReA() {
		return tpReA;
	}
	public void setTpReA(String tpReA) {
		this.tpReA = tpReA;
	}
	public String getUsReA() {
		return usReA;
	}
	public void setUsReA(String usReA) {
		this.usReA = usReA;
	}
	public String getDsUsA() {
		return dsUsA;
	}
	public void setDsUsA(String dsUsA) {
		this.dsUsA = dsUsA;
	}
	public String getTpReU() {
		return tpReU;
	}
	public void setTpReU(String tpReU) {
		this.tpReU = tpReU;
	}
	public String getUsReU() {
		return usReU;
	}
	public void setUsReU(String usReU) {
		this.usReU = usReU;
	}
	public String getDsUsU() {
		return dsUsU;
	}
	public void setDsUsU(String dsUsU) {
		this.dsUsU = dsUsU;
	}
	public String getFeMod() {
		return feMod;
	}
	public void setFeMod(String feMod) {
		this.feMod = feMod;
	}
	public String getHrMod() {
		return hrMod;
	}
	public void setHrMod(String hrMod) {
		this.hrMod = hrMod;
	}
	public String getDsObs() {
		return dsObs;
	}
	public void setDsObs(String dsObs) {
		this.dsObs = dsObs;
	}

}
