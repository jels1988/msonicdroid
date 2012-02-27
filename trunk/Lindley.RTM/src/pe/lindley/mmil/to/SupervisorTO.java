package pe.lindley.mmil.to;

import com.google.gson.annotations.SerializedName;

public class SupervisorTO {
	
	@SerializedName("CDS")
	private int codigo;

	@SerializedName("NMS")
	private String nombre;

	@SerializedName("PRG")
	private String primerRegistro;

	@SerializedName("URG")
	private String ultimoRegistro;
	        
	@SerializedName("CUO")
	private String cuota;

	@SerializedName("CCU")
	private String cuotaColor;

	@SerializedName("EGL")
	private String eficGlobal;

	@SerializedName("CEG")
	private String eficGlobalColor;

	@SerializedName("PVS")
	private String planVisita;

	@SerializedName("CPV")
	private String planVisitaColor;

	@SerializedName("EPV")
	private String eficPreventa;

	@SerializedName("CEP")
	private String eficPreventaColor;
	        
	@SerializedName("CCL")
	private String cantidadClientes;

	@SerializedName("VIS")
	private String visitas;

	@SerializedName("CPD")
	private String conPedido;

	@SerializedName("SPD")
	private String sinPedido;

	@SerializedName("IMP")
	private String importe;

	@SerializedName("TME")
	private String tiempoEfec;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerRegistro() {
		return primerRegistro;
	}

	public void setPrimerRegistro(String primerRegistro) {
		this.primerRegistro = primerRegistro;
	}

	public String getUltimoRegistro() {
		return ultimoRegistro;
	}

	public void setUltimoRegistro(String ultimoRegistro) {
		this.ultimoRegistro = ultimoRegistro;
	}

	public String getCuota() {
		return cuota;
	}

	public void setCuota(String cuota) {
		this.cuota = cuota;
	}

	public String getCuotaColor() {
		return cuotaColor;
	}

	public void setCuotaColor(String cuotaColor) {
		this.cuotaColor = cuotaColor;
	}

	public String getEficGlobal() {
		return eficGlobal;
	}

	public void setEficGlobal(String eficGlobal) {
		this.eficGlobal = eficGlobal;
	}

	public String getEficGlobalColor() {
		return eficGlobalColor;
	}

	public void setEficGlobalColor(String eficGlobalColor) {
		this.eficGlobalColor = eficGlobalColor;
	}

	public String getPlanVisita() {
		return planVisita;
	}

	public void setPlanVisita(String planVisita) {
		this.planVisita = planVisita;
	}

	public String getPlanVisitaColor() {
		return planVisitaColor;
	}

	public void setPlanVisitaColor(String planVisitaColor) {
		this.planVisitaColor = planVisitaColor;
	}

	public String getEficPreventa() {
		return eficPreventa;
	}

	public void setEficPreventa(String eficPreventa) {
		this.eficPreventa = eficPreventa;
	}

	public String getEficPreventaColor() {
		return eficPreventaColor;
	}

	public void setEficPreventaColor(String eficPreventaColor) {
		this.eficPreventaColor = eficPreventaColor;
	}

	public String getCantidadClientes() {
		return cantidadClientes;
	}

	public void setCantidadClientes(String cantidadClientes) {
		this.cantidadClientes = cantidadClientes;
	}

	public String getVisitas() {
		return visitas;
	}

	public void setVisitas(String visitas) {
		this.visitas = visitas;
	}

	public String getConPedido() {
		return conPedido;
	}

	public void setConPedido(String conPedido) {
		this.conPedido = conPedido;
	}

	public String getSinPedido() {
		return sinPedido;
	}

	public void setSinPedido(String sinPedido) {
		this.sinPedido = sinPedido;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getTiempoEfec() {
		return tiempoEfec;
	}

	public void setTiempoEfec(String tiempoEfec) {
		this.tiempoEfec = tiempoEfec;
	}
}
