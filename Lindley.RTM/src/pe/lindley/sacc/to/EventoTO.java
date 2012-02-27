package pe.lindley.sacc.to;

import com.google.gson.annotations.SerializedName;

public class EventoTO {

	@SerializedName("IDE")
	private int idEvento;

	@SerializedName("TIP")
	private String tipoEvento;

	@SerializedName("FRG")
	private String fechaRegistro;

	@SerializedName("HRG")
	private String horaRegistro;

	@SerializedName("URS")
	private String usuarioResponsable;

	@SerializedName("MOT")
	private String motivo;

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(String horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

	public String getUsuarioResponsable() {
		return usuarioResponsable;
	}

	public void setUsuarioResponsable(String usuarioResponsable) {
		this.usuarioResponsable = usuarioResponsable;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}
