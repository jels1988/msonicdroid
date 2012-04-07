package pe.lindley.prospector.to;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class ClienteTO {
	
	public ClienteTO(){
		setDocumentos(new ArrayList<DocumentoEnviarTO>());
	}
	
	public static final int ESTADO_PENDIENTE = 0;
    public static final int ESTADO_CONFIRMADO = 1;
    public static final int ESTADO_RECHAZADAO = 2;
    public static final int ESTADO_REGULARIZADO = 3;
    public static final int ESTADO_FINALIZADO = 4;
    
    public static final int ACCION_ALTA = 1;
    public static final int ACCION_REACTIVACION = 2;
    public static final int ACCION_CORRECCION = 3;
    public static final int ACCION_SUSPENCION = 4;
    public static final int ACCION_RECODIFICACION = 5;
    public static final int ACCION_RECHAZO = 6;

    public static int FICHA_SIN_CAMBIOS = 0;
	public static int FICHA_TIENE_CAMBIOS = 1;
    
	private int clienteId;
	 
	
    public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	@SerializedName("Cod")
    public String codigo;

    @SerializedName("Ruc")
    public String ruc;

    @SerializedName("Dni")
    public String dni;

    @SerializedName("Rzs")
    public String razonSocial;


    @SerializedName("NCe")
    public String nombreComercial;

    @SerializedName("Tel1")
    public String telefono1;

    @SerializedName("Tel2")
    public String telefono2;

    @SerializedName("mail")
    public String email;

    @SerializedName("Url")
    public String url;

    @SerializedName("Pred")
    public String codigoPredecesor;

    @SerializedName("Suc")
    public String codigoSucesor;

    @SerializedName("fax")
    public String fax;

    @SerializedName("sbcnl")
    public String subCanal;

    @SerializedName("dirf")
    public String direccionFiscal;

    @SerializedName("ubifis")
    public String ubigeoFiscal;

    @SerializedName("direent")
    public String direccionEntrega;

    @SerializedName("ubient")
    public String ubigeoEntrega;

    @SerializedName("dist")
    public String distribuidor;

    @SerializedName("dstco")
    public String distritoComercial;

    @SerializedName("dstgeo")
    public String distritoGeografico;

    @SerializedName("zona")
    public String zona;

    @SerializedName("rutaik")
    public String rutaIK;

    @SerializedName("segm")
    public String segmento;

    @SerializedName("ubic")
    public String ubicacion;

    @SerializedName("fecc")
    public String fechaCreacion;

    @SerializedName("horc")
    public String horaCreacion;

    @SerializedName("usrc")
    public String usuarioCrea;


    @SerializedName("acc")
    public int accion;

    @SerializedName("ltd")
    public String latitud;

    @SerializedName("lng")
    public String longitud;


    @SerializedName("codRef")
    public String codigoReferencia;



    @SerializedName("codMot")
    public String codigoMotivo;
    
    public String motivo;
    
    @SerializedName("IdBd")
    public int idBd;


    @SerializedName("obs")
    public String observacion;

    
    @SerializedName("ltdR")
    public String latitudRef;

    @SerializedName("lngR")
    public String longitudRef;

    @SerializedName("diref")
    public String DireccionEntregaRef;

     @SerializedName("rscef")
    public String razonSocialRef;

     @SerializedName("vend")
     public String vendedor;

     @SerializedName("tam")
     public String tamanio;

     @SerializedName("efrio")
     public boolean equiposFrio;


     @SerializedName("tcamb")
     public int tieneCambios;

     @SerializedName("IdRef")
     public int idRef;

     @SerializedName("docs")
     private ArrayList<DocumentoEnviarTO> documentos;
     
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCodigoPredecesor() {
		return codigoPredecesor;
	}

	public void setCodigoPredecesor(String codigoPredecesor) {
		this.codigoPredecesor = codigoPredecesor;
	}

	public String getCodigoSucesor() {
		return codigoSucesor;
	}

	public void setCodigoSucesor(String codigoSucesor) {
		this.codigoSucesor = codigoSucesor;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getSubCanal() {
		return subCanal;
	}

	public void setSubCanal(String subCanal) {
		this.subCanal = subCanal;
	}

	public String getDireccionFiscal() {
		return direccionFiscal;
	}

	public void setDireccionFiscal(String direccionFiscal) {
		this.direccionFiscal = direccionFiscal;
	}

	public String getUbigeoFiscal() {
		return ubigeoFiscal;
	}

	public void setUbigeoFiscal(String ubigeoFiscal) {
		this.ubigeoFiscal = ubigeoFiscal;
	}

	public String getDireccionEntrega() {
		return direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public String getUbigeoEntrega() {
		return ubigeoEntrega;
	}

	public void setUbigeoEntrega(String ubigeoEntrega) {
		this.ubigeoEntrega = ubigeoEntrega;
	}

	public String getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public String getDistritoComercial() {
		return distritoComercial;
	}

	public void setDistritoComercial(String distritoComercial) {
		this.distritoComercial = distritoComercial;
	}

	public String getDistritoGeografico() {
		return distritoGeografico;
	}

	public void setDistritoGeografico(String distritoGeografico) {
		this.distritoGeografico = distritoGeografico;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getRutaIK() {
		return rutaIK;
	}

	public void setRutaIK(String rutaIK) {
		this.rutaIK = rutaIK;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getHoraCreacion() {
		return horaCreacion;
	}

	public void setHoraCreacion(String horaCreacion) {
		this.horaCreacion = horaCreacion;
	}

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public int getAccion() {
		return accion;
	}

	public void setAccion(int accion) {
		this.accion = accion;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getCodigoReferencia() {
		return codigoReferencia;
	}

	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}

	public String getCodigoMotivo() {
		return codigoMotivo;
	}

	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}
	
	public int getIdBd() {
		return idBd;
	}

	public void setIdBd(int idBd) {
		this.idBd = idBd;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getLatitudRef() {
		return latitudRef;
	}

	public void setLatitudRef(String latitudRef) {
		this.latitudRef = latitudRef;
	}

	public String getLongitudRef() {
		return longitudRef;
	}

	public void setLongitudRef(String longitudRef) {
		this.longitudRef = longitudRef;
	}

	public String getDireccionEntregaRef() {
		return DireccionEntregaRef;
	}

	public void setDireccionEntregaRef(String direccionEntregaRef) {
		DireccionEntregaRef = direccionEntregaRef;
	}

	public String getRazonSocialRef() {
		return razonSocialRef;
	}

	public void setRazonSocialRef(String razonSocialRef) {
		this.razonSocialRef = razonSocialRef;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public boolean isEquiposFrio() {
		return equiposFrio;
	}

	public void setEquiposFrio(boolean equiposFrio) {
		this.equiposFrio = equiposFrio;
	}

	public int getTieneCambios() {
		return tieneCambios;
	}

	public void setTieneCambios(int tieneCambios) {
		this.tieneCambios = tieneCambios;
	}

	public int getIdRef() {
		return idRef;
	}

	public void setIdRef(int idRef) {
		this.idRef = idRef;
	}

	public ArrayList<DocumentoEnviarTO> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(ArrayList<DocumentoEnviarTO> documentos) {
		this.documentos = documentos;
	}

	    
	 @SerializedName("tfp")
     private int traficoPersonas = 0;
	 
	 @SerializedName("cni")
	 private int colegioNido  = 0;
	 
	 @SerializedName("esu")
	 private int educacionSuperior  = 0;
	 
	 @SerializedName("cde")
	 private int centroDeportivo  = 0;
	 
	 @SerializedName("ppl")
	 private int parquePlaza  = 0;
	 
	 @SerializedName("epu")
	 private int entidadesPublicas  = 0;
	 
	 @SerializedName("csa")
	 private int centroSalud  = 0;
	 
	 @SerializedName("cco")
	 private int centroComercial  = 0;
	
	 
	 @SerializedName("lav")
     private int localAvenida  = 0;
	 
	 @SerializedName("les")
     private int localEsquina  = 0;


	public int getTraficoPersonas() {
		return traficoPersonas;
	}

	public void setTraficoPersonas(int traficoPersonas) {
		this.traficoPersonas = traficoPersonas;
	}

	public int getColegioNido() {
		return colegioNido;
	}

	public void setColegioNido(int colegioNido) {
		this.colegioNido = colegioNido;
	}

	public int getEducacionSuperior() {
		return educacionSuperior;
	}

	public void setEducacionSuperior(int educacionSuperior) {
		this.educacionSuperior = educacionSuperior;
	}

	public int getCentroDeportivo() {
		return centroDeportivo;
	}

	public void setCentroDeportivo(int centroDeportivo) {
		this.centroDeportivo = centroDeportivo;
	}

	public int getParquePlaza() {
		return parquePlaza;
	}

	public void setParquePlaza(int parquePlaza) {
		this.parquePlaza = parquePlaza;
	}

	public int getEntidadesPublicas() {
		return entidadesPublicas;
	}

	public void setEntidadesPublicas(int entidadesPublicas) {
		this.entidadesPublicas = entidadesPublicas;
	}

	public int getCentroSalud() {
		return centroSalud;
	}

	public void setCentroSalud(int centroSalud) {
		this.centroSalud = centroSalud;
	}

	public int getCentroComercial() {
		return centroComercial;
	}

	public void setCentroComercial(int centroComercial) {
		this.centroComercial = centroComercial;
	}

	public int getLocalAvenida() {
		return localAvenida;
	}

	public void setLocalAvenida(int localAvenida) {
		this.localAvenida = localAvenida;
	}

	public int getLocalEsquina() {
		return localEsquina;
	}

	public void setLocalEsquina(int localEsquina) {
		this.localEsquina = localEsquina;
	}
	
    
}
