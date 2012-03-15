package lindley.desarrolloxcliente.to;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class PosicionCompromisoTO {

	public PosicionCompromisoTO()
    {
		listCompromisos = new ArrayList<CompromisoPosicionTO>();
    }

    @SerializedName("CVAR")
    private String codigoVariable;

    @SerializedName("DVAR")
    private String descripcionVariable;
            
    @SerializedName("RED")
    private String red;

    @SerializedName("PMX")
    private String ptoMaximo;

    @SerializedName("DIF")
    private String diferencia;

    @SerializedName("CMP")
    private ArrayList<CompromisoPosicionTO> listCompromisos;

    @SerializedName("FINI")
    private String fotoInicial;

    @SerializedName("ACM")
    private String accionCompromiso;

    @SerializedName("FCM")
    private String fechaCompromiso;

    @SerializedName("CNF")
    private String confirmacion;

    @SerializedName("FFIN")
    private String fotoFinal;

    @SerializedName("PTO")
    private String puntosSugeridos;

	public String getCodigoVariable() {
		return codigoVariable;
	}

	public void setCodigoVariable(String codigoVariable) {
		this.codigoVariable = codigoVariable;
	}

	public String getDescripcionVariable() {
		return descripcionVariable;
	}

	public void setDescripcionVariable(String descripcionVariable) {
		this.descripcionVariable = descripcionVariable;
	}

	public String getRed() {
		return red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public String getPtoMaximo() {
		return ptoMaximo;
	}

	public void setPtoMaximo(String ptoMaximo) {
		this.ptoMaximo = ptoMaximo;
	}

	public String getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(String diferencia) {
		this.diferencia = diferencia;
	}

	public ArrayList<CompromisoPosicionTO> getListCompromisos() {
		return listCompromisos;
	}

	public void setListCompromisos(ArrayList<CompromisoPosicionTO> listCompromisos) {
		this.listCompromisos = listCompromisos;
	}

	public String getFotoInicial() {
		return fotoInicial;
	}

	public void setFotoInicial(String fotoInicial) {
		this.fotoInicial = fotoInicial;
	}

	public String getAccionCompromiso() {
		return accionCompromiso;
	}

	public void setAccionCompromiso(String accionCompromiso) {
		this.accionCompromiso = accionCompromiso;
	}

	public String getFechaCompromiso() {
		return fechaCompromiso;
	}

	public void setFechaCompromiso(String fechaCompromiso) {
		this.fechaCompromiso = fechaCompromiso;
	}

	public String getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(String confirmacion) {
		this.confirmacion = confirmacion;
	}

	public String getFotoFinal() {
		return fotoFinal;
	}

	public void setFotoFinal(String fotoFinal) {
		this.fotoFinal = fotoFinal;
	}

	public String getPuntosSugeridos() {
		return puntosSugeridos;
	}

	public void setPuntosSugeridos(String puntosSugeridos) {
		this.puntosSugeridos = puntosSugeridos;
	}
}
