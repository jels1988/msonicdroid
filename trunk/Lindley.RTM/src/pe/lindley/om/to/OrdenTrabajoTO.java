package pe.lindley.om.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;


public class OrdenTrabajoTO {
	
	public OrdenTrabajoTO(){
		eventos = new ArrayList<EventoTO>();
	}
	
	
	
	@SerializedName("CDCLI")
	private String cdCli;
		 
	
	private long nuOrd;
	@SerializedName("NUORD")
	private long nuOrd2;
	
	@SerializedName("TPCON")
	private String tpCon;
	@SerializedName("DSTPC")
	private String dsTpC;
	
	@SerializedName("TPORD")
	private String  tpOrd;
	@SerializedName("DSTPO")
	private String  dsTpO;
    
	public String getTpOrd() {
		return tpOrd;
	}

	public void setTpOrd(String tpOrd) {
		this.tpOrd = tpOrd;
	}

	public String getDsTpO() {
		return dsTpO;
	}

	public void setDsTpO(String dsTpO) {
		this.dsTpO = dsTpO;
	}
	
	@SerializedName("STORD")
	private String stOrd;
	@SerializedName("DSSTO")
	private String dsStO;
	
	@SerializedName("MTORD")
	private String mtOrd;
	@SerializedName("DSMTO")
	private String dsMtO;
	
	@SerializedName("CDPRI")
	private String cdPri;
	@SerializedName("DSPRI")
	private String dsPri;
	
	@SerializedName("ESORD")
	private String esOrd;
	@SerializedName("DSESO")
	private String dsEsO;
	
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
	
	@SerializedName("CDSTA")
	private int cdStA;
	
	@SerializedName("DSOBS")
	private String dsObs;
	
	private ArrayList<EventoTO> eventos;
	
	public String getCdCli() {
		return cdCli;
	}

	public void setCdCli(String cdCli) {
		this.cdCli = cdCli;
	}

	public long getNuOrd() {
		return nuOrd;
	}

	public void setNuOrd(long nuOrd) {
		this.nuOrd = nuOrd;
	}

	public long getNuOrd2() {
		return nuOrd2;
	}

	public void setNuOrd2(long nuOrd2) {
		this.nuOrd2 = nuOrd2;
	}

	public String getTpCon() {
		return tpCon;
	}

	public void setTpCon(String tpCon) {
		this.tpCon = tpCon;
	}

	public String getDsTpC() {
		return dsTpC;
	}

	public void setDsTpC(String dsTpC) {
		this.dsTpC = dsTpC;
	}

	public String getStOrd() {
		return stOrd;
	}

	public void setStOrd(String stOrd) {
		this.stOrd = stOrd;
	}

	public String getDsStO() {
		return dsStO;
	}

	public void setDsStO(String dsStO) {
		this.dsStO = dsStO;
	}

	public String getMtOrd() {
		return mtOrd;
	}

	public void setMtOrd(String mtOrd) {
		this.mtOrd = mtOrd;
	}

	public String getDsMtO() {
		return dsMtO;
	}

	public void setDsMtO(String dsMtO) {
		this.dsMtO = dsMtO;
	}

	public String getCdPri() {
		return cdPri;
	}

	public void setCdPri(String cdPri) {
		this.cdPri = cdPri;
	}

	public String getDsPri() {
		return dsPri;
	}

	public void setDsPri(String dsPri) {
		this.dsPri = dsPri;
	}

	public String getEsOrd() {
		return esOrd;
	}

	public void setEsOrd(String esOrd) {
		this.esOrd = esOrd;
	}

	public String getDsEsO() {
		return dsEsO;
	}

	public void setDsEsO(String dsEsO) {
		this.dsEsO = dsEsO;
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

	public int getCdStA() {
		return cdStA;
	}

	public void setCdStA(int cdStA) {
		this.cdStA = cdStA;
	}

	public String getDsObs() {
		return dsObs;
	}

	public void setDsObs(String dsObs) {
		this.dsObs = dsObs;
	}

	public ArrayList<EventoTO> getEventos() {
		return eventos;
	}

	public void setEventos(ArrayList<EventoTO> eventos) {
		this.eventos = eventos;
	}
	

	
}
