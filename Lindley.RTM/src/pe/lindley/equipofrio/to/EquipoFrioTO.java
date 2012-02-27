package pe.lindley.equipofrio.to;

import com.google.gson.annotations.SerializedName;

public class EquipoFrioTO {

	
	 @SerializedName("EQUNUM")
      private String numero;

      public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getCenso() {
		return censo;
	}

	public void setCenso(String censo) {
		this.censo = censo;
	}

	public String getCodigoModelo() {
		return codigoModelo;
	}

	public void setCodigoModelo(String codigoModelo) {
		this.codigoModelo = codigoModelo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@SerializedName("SERNUM")
      private String serie ;

      @SerializedName("NROCENSO")
      private String censo ;

      @SerializedName("MOD")
      private String codigoModelo ;

      @SerializedName("MODDCR")
      private String modelo ;

      @SerializedName("PURAMT")
      private double monto ;

      @SerializedName("EQUBRD")
      private String codigoMarca ;

      @SerializedName("MAR")
      private String marca ;


      @SerializedName("EQUSTA")
      private String estado ;

      @SerializedName("TIP")
      private String tipo ;
      
}
