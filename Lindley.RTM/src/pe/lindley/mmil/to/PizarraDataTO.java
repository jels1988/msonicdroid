package pe.lindley.mmil.to;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class PizarraDataTO {
	
	public PizarraDataTO(){
		detalleSupervisor = new ArrayList<PizarraDetalleTO>();
		detalleTeleventas = new ArrayList<PizarraDetalleTO>();
	}

	@SerializedName("COD")
	private String codigoDeposito;
	
	@SerializedName("CDA")
	private String cda;
	
	@SerializedName("NOMSP")
	private String nombreSupervisor;
	
	@SerializedName("NOMTV")
	private String nombreTeleventa;
	
	@SerializedName("CANSP")
	private int cantidadSupervisor;
	
	@SerializedName("CANTV")
	private int cantidadTeleventa;

	@SerializedName("DETSP")
	private ArrayList<PizarraDetalleTO> detalleSupervisor;
	
	@SerializedName("DETTV")
	private ArrayList<PizarraDetalleTO> detalleTeleventas;

	public String getCodigoDeposito() {
		return codigoDeposito;
	}

	public void setCodigoDeposito(String codigoDeposito) {
		this.codigoDeposito = codigoDeposito;
	}

	public String getCda() {
		return cda;
	}

	public void setCda(String cda) {
		this.cda = cda;
	}

	public String getNombreSupervisor() {
		return nombreSupervisor;
	}

	public void setNombreSupervisor(String nombreSupervisor) {
		this.nombreSupervisor = nombreSupervisor;
	}

	public String getNombreTeleventa() {
		return nombreTeleventa;
	}

	public void setNombreTeleventa(String nombreTeleventa) {
		this.nombreTeleventa = nombreTeleventa;
	}

	public int getCantidadSupervisor() {
		return cantidadSupervisor;
	}

	public void setCantidadSupervisor(int cantidadSupervisor) {
		this.cantidadSupervisor = cantidadSupervisor;
	}

	public int getCantidadTeleventa() {
		return cantidadTeleventa;
	}

	public void setCantidadTeleventa(int cantidadTeleventa) {
		this.cantidadTeleventa = cantidadTeleventa;
	}

	public ArrayList<PizarraDetalleTO> getDetalleSupervisor() {
		return detalleSupervisor;
	}

	public void setDetalleSupervisor(ArrayList<PizarraDetalleTO> detalleSupervisor) {
		this.detalleSupervisor = detalleSupervisor;
	}

	public ArrayList<PizarraDetalleTO> getDetalleTeleventas() {
		return detalleTeleventas;
	}

	public void setDetalleTeleventas(ArrayList<PizarraDetalleTO> detalleTeleventas) {
		this.detalleTeleventas = detalleTeleventas;
	}
	
}
