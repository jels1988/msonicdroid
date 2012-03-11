package pe.lindley.prospector.dao;

import java.util.ArrayList;
import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.prospector.to.ClienteTO;
import pe.lindley.prospector.to.DocumentoTO;
import pe.lindley.util.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class ClienteDAO {

	@Inject
	protected DBHelper dbHelper;


	public long updateDocumento(int clienteId,DocumentoTO documentoTO){
		
		ContentValues parametros = new ContentValues();
		parametros.put("nombreArchivo", documentoTO.getNombreArchivo());
		parametros.put("islocal", documentoTO.getEsLocal());
		
		String[] valores = new String[] { String.valueOf(documentoTO.getId()) };

		dbHelper.getDataBase().update("cliente_documento", parametros, "clienteDocumentoId = ?",valores);
		
		return documentoTO.getId();
	}
	
	public long insertDocumento(int clienteId,DocumentoTO documentoTO){
		
		ContentValues parametros = new ContentValues();
		parametros.put("clienteId", clienteId);
		parametros.put("documentoId", documentoTO.getDocumentoId());
		parametros.put("nombreArchivo", documentoTO.getNombreArchivo());
		parametros.put("islocal", documentoTO.getEsLocal());
		
		long id =dbHelper.getDataBase().insertOrThrow("cliente_documento", null, parametros);
		return id;
	}
	
	
	public ArrayList<DocumentoTO> listarDocumentos(){
		String SQL = "SELECT CD.clienteId,ifnull(CD.clienteDocumentoId,0) as id,TD.documentoId,TD.descripcion,TD.obligatorio,ifnull(CD.nombrearchivo,'') as nombrearchivo " +
				"FROM cliente_tipo_documento TD inner join cliente_documento CD on TD.DocumentoId = CD.DocumentoId";
	
	
	Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, null);
	
	ArrayList<DocumentoTO> documentos = new ArrayList<DocumentoTO>();
	
	DocumentoTO documentoTO;

	while (cursor.moveToNext()) {
		documentoTO = new DocumentoTO();
		documentoTO.setId(cursor.getInt(cursor.getColumnIndex("id")));
		documentoTO.setClienteId(cursor.getInt(cursor.getColumnIndex("clienteId")));
		documentoTO.setDocumentoId(cursor.getInt(cursor.getColumnIndex("documentoId")));
		documentoTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
		documentoTO.setObligatorio(cursor.getInt(cursor.getColumnIndex("obligatorio")));
		documentoTO.setNombreArchivo(cursor.getString(cursor.getColumnIndex("nombrearchivo")));
		documentos.add(documentoTO);
	}
	
	cursor.close();
	
	return documentos;
	}
	public ArrayList<DocumentoTO> listarDocumentos(int id){
		
		String SQL = "SELECT ifnull(CD.clienteDocumentoId,0) as id,TD.documentoId,TD.descripcion,TD.obligatorio,ifnull(CD.nombrearchivo,'') as nombrearchivo " +
					"FROM cliente_tipo_documento TD left join cliente_documento CD on TD.DocumentoId = CD.DocumentoId " +
					"and CD.ClienteId = ?";
		
		
		String[] parametros = new String[] { String.valueOf(id) };
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		ArrayList<DocumentoTO> documentos = new ArrayList<DocumentoTO>();
		
		DocumentoTO documentoTO;

		while (cursor.moveToNext()) {
			documentoTO = new DocumentoTO();
			documentoTO.setId(cursor.getInt(cursor.getColumnIndex("id")));
			documentoTO.setDocumentoId(cursor.getInt(cursor.getColumnIndex("documentoId")));
			documentoTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
			documentoTO.setObligatorio(cursor.getInt(cursor.getColumnIndex("obligatorio")));
			documentoTO.setNombreArchivo(cursor.getString(cursor.getColumnIndex("nombrearchivo")));
			documentos.add(documentoTO);
		}
		
		cursor.close();
		
		return documentos;
	}
	

	public void deleteRechazadas() {
		String[] args = new String[] { String.valueOf(ClienteTO.ACCION_RECHAZO)};
		
		dbHelper.getDataBase().execSQL("delete from cliente where accion = ?", args);
	}

	public void deleteAll() {
		dbHelper.getDataBase().execSQL("delete from cliente");
	}

	public void delete(int id) {
		String[] args = new String[] { String.valueOf(id) };
		dbHelper.getDataBase().execSQL(
				"delete from cliente where clienteid = ?", args);
	}

	public ClienteTO listById(int id) {

		String SQL = "SELECT clienteId,accion,nullif(codigo,0) as codigo,nullif(Ruc,'') as Ruc,nullif(Dni,'') as Dni,nullif(RazonSocial,'') as  RazonSocial,nullif(NombreComercial,'') as NombreComercial,"
				+ "nullif(DireccionFiscal,'') as DireccionFiscal,nullif(CodUbigeoFiscal,'') as CodUbigeoFiscal,nullif(DireccionEntrega,'') as DireccionEntrega,"
				+ "nullif(CodUbigeoEntrega,'') as CodUbigeoEntrega,nullif(SubCanal,'') as SubCanal,nullif(Fax,'') as Fax,nullif(Distribuidor,'') as Distribuidor,"
				+ "nullif(DistritoComercial,'') as DistritoComercial,nullif(DistritoGeografico,'') as DistritoGeografico,nullif(Zona,'') as Zona,nullif(RutaIK,'') as RutaIK,"
				+ "nullif(Segmento,'') as Segmento,nullif(Ubicacion,'') as Ubicacion,nullif(Email,'') as Email,nullif(Url,'') as Url,nullif(Telefono1,'') as Telefono1,nullif(Telefono2,'') as Telefono2,"
				+ "nullif(CodSucesor,'') as CodSucesor,nullif(CodPredecesor,'') as CodPredecesor,nullif(Usuario,'') as Usuario,nullif(Fecha,'') as Fecha,nullif(Hora,'') as Hora,nullif(Longitud,0) as Longitud,"
				+ "nullif(Latitud,0) as Latitud,nullif(CodReferencia,'') as CodReferencia,nullif(Vendedor,'') as Vendedor,nullif(TamanioCliente,'') as TamanioCliente,nullif(IdReg,0) as IdReg,"
				+ "nullif(TamanioCliente,'') as TamanioCliente,nullif(IdReg,0) as IdReg,nullif(C.codMotivo,'') as codMotivo,nullif(M.Descripcion,'') as motivo,nullif(observacion,'') as observacion,"
				+ "nullif(LongitudRef,0) as LongitudRef,nullif(LatitudRef,0) as LatitudRef,nullif(RazonSocialRef,'') as RazonSocialRef,nullif(DireccionRef,'') as DireccionRef"
				+ " FROM Cliente C LEFT JOIN Tablas M ON M.CodigoTabla = '009' AND M.CodigoCampo = C.codMotivo"
				+ " Where ClienteId=?";

		String[] parametros = new String[] { String.valueOf(id) };
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		ClienteTO clienteTO = null;

		if (cursor.moveToNext()) {
			clienteTO = new ClienteTO();
			clienteTO.setClienteId(cursor.getInt(cursor.getColumnIndex("clienteId")));
			clienteTO.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
			clienteTO.setRuc(cursor.getString(cursor.getColumnIndex("Ruc")));
			clienteTO.setDni(cursor.getString(cursor.getColumnIndex("Dni")));
			clienteTO.setRazonSocial(cursor.getString(cursor.getColumnIndex("RazonSocial")));
			clienteTO.setNombreComercial(cursor.getString(cursor.getColumnIndex("NombreComercial")));
			clienteTO.setTelefono1(cursor.getString(cursor.getColumnIndex("Telefono1")));
			clienteTO.setTelefono2(cursor.getString(cursor.getColumnIndex("Telefono2")));
			clienteTO.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
			clienteTO.setUrl(cursor.getString(cursor.getColumnIndex("Url")));
			clienteTO.setCodigoPredecesor(cursor.getString(cursor.getColumnIndex("CodPredecesor")));
			clienteTO.setCodigoSucesor(cursor.getString(cursor.getColumnIndex("CodSucesor")));
			clienteTO.setFax(cursor.getString(cursor.getColumnIndex("Fax")));
			clienteTO.setSubCanal(cursor.getString(cursor.getColumnIndex("SubCanal")));
			clienteTO.setDireccionFiscal(cursor.getString(cursor.getColumnIndex("DireccionFiscal")));
			clienteTO.setUbigeoFiscal(cursor.getString(cursor.getColumnIndex("CodUbigeoFiscal")));
			clienteTO.setDireccionEntrega(cursor.getString(cursor.getColumnIndex("DireccionEntrega")));
			clienteTO.setUbigeoEntrega(cursor.getString(cursor.getColumnIndex("CodUbigeoEntrega")));
			clienteTO.setDistribuidor(cursor.getString(cursor.getColumnIndex("Distribuidor")));
			clienteTO.setDistritoComercial(cursor.getString(cursor.getColumnIndex("DistritoComercial")));
			clienteTO.setDistritoGeografico(cursor.getString(cursor.getColumnIndex("DistritoGeografico")));
			clienteTO.setZona(cursor.getString(cursor.getColumnIndex("Zona")));
			clienteTO.setRutaIK(cursor.getString(cursor.getColumnIndex("RutaIK")));
			clienteTO.setSegmento(cursor.getString(cursor.getColumnIndex("Segmento")));
			clienteTO.setUbicacion(cursor.getString(cursor.getColumnIndex("Ubicacion")));
			clienteTO.setFechaCreacion(cursor.getString(cursor.getColumnIndex("Fecha")));
			clienteTO.setHoraCreacion(cursor.getString(cursor.getColumnIndex("Hora")));
			clienteTO.setUsuarioCrea(cursor.getString(cursor.getColumnIndex("Usuario")));
			clienteTO.setLatitud(cursor.getString(cursor.getColumnIndex("Latitud")));
			clienteTO.setLongitud(cursor.getString(cursor.getColumnIndex("Longitud")));
			clienteTO.setLatitudRef(cursor.getString(cursor.getColumnIndex("LatitudRef")));
			clienteTO.setLongitudRef(cursor.getString(cursor.getColumnIndex("LongitudRef")));
			clienteTO.setRazonSocialRef(cursor.getString(cursor.getColumnIndex("RazonSocialRef")));
			clienteTO.setDireccionEntregaRef(cursor.getString(cursor.getColumnIndex("DireccionRef")));
			clienteTO.setCodigoReferencia(cursor.getString(cursor.getColumnIndex("CodReferencia")));
			clienteTO.setAccion(cursor.getInt(cursor.getColumnIndex("accion")));
			clienteTO.setVendedor(cursor.getString(cursor.getColumnIndex("Vendedor")));
			clienteTO.setTamanio(cursor.getString(cursor.getColumnIndex("TamanioCliente")));
			clienteTO.setIdBd(cursor.getInt(cursor.getColumnIndex("IdReg")));
			clienteTO.setCodigoMotivo(cursor.getString(cursor.getColumnIndex("codMotivo")));
			clienteTO.setMotivo(cursor.getString(cursor.getColumnIndex("motivo")));
			clienteTO.setObservacion(cursor.getString(cursor.getColumnIndex("observacion")));

		}

		cursor.close();

		return clienteTO;

	}

	public ArrayList<ClienteTO> list(String dni, String ruc, String codigo) {
		ArrayList<ClienteTO> listado = new ArrayList<ClienteTO>();

		String SQL = "SELECT clienteId,accion,nullif(codigo,0) as codigo,nullif(Ruc,'') as Ruc,nullif(Dni,'') as Dni,nullif(RazonSocial,'') as  RazonSocial,nullif(NombreComercial,'') as NombreComercial,"
				+ "nullif(DireccionFiscal,'') as DireccionFiscal,nullif(CodUbigeoFiscal,'') as CodUbigeoFiscal,nullif(DireccionEntrega,'') as DireccionEntrega,"
				+ "nullif(CodUbigeoEntrega,'') as CodUbigeoEntrega,nullif(SubCanal,'') as SubCanal,nullif(Fax,'') as Fax,nullif(Distribuidor,'') as Distribuidor,"
				+ "nullif(DistritoComercial,'') as DistritoComercial,nullif(DistritoGeografico,'') as DistritoGeografico,nullif(Zona,'') as Zona,nullif(RutaIK,'') as RutaIK,"
				+ "nullif(Segmento,'') as Segmento,nullif(Ubicacion,'') as Ubicacion,nullif(Email,'') as Email,nullif(Url,'') as Url,nullif(Telefono1,'') as Telefono1,nullif(Telefono2,'') as Telefono2,"
				+ "nullif(CodSucesor,'') as CodSucesor,nullif(CodPredecesor,'') as CodPredecesor,nullif(Usuario,'') as Usuario,nullif(Fecha,'') as Fecha,nullif(Hora,'') as Hora,nullif(Longitud,0) as Longitud,nullif(Latitud,0) as Latitud,nullif(CodReferencia,'') as CodReferencia,nullif(Vendedor,'') as Vendedor,"
				+ "nullif(TamanioCliente,'') as TamanioCliente,nullif(IdReg,0) as IdReg,nullif(C.codMotivo,'') as codMotivo,nullif(M.Descripcion,'') as motivo,nullif(observacion,'') as observacion,"
				+ "nullif(LongitudRef,0) as LongitudRef,nullif(LatitudRef,0) as LatitudRef,nullif(RazonSocialRef,'') as RazonSocialRef,nullif(DireccionRef,'') as DireccionRef"
				+ " FROM Cliente C LEFT JOIN Tablas M ON M.CodigoTabla = '009' AND M.CodigoCampo = C.codMotivo"
				+ " Where Ruc like ? or Dni like ? or Codigo like ?";
		//M.Descripcion,
		if(dni==null) dni="";
		if(ruc==null) ruc="";
		if(codigo==null) codigo="";
		
		String[] parametros = new String[] { 
									"%" +  dni + "%",
									"%" +  ruc + "%", 
									"%" +  codigo + "%" };

		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);

		ClienteTO clienteTO;

		while (cursor.moveToNext()) {
			clienteTO = new ClienteTO();
			clienteTO.setClienteId(cursor.getInt(cursor.getColumnIndex("clienteId")));
			clienteTO.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
			clienteTO.setRuc(cursor.getString(cursor.getColumnIndex("Ruc")));
			clienteTO.setDni(cursor.getString(cursor.getColumnIndex("Dni")));
			clienteTO.setRazonSocial(cursor.getString(cursor.getColumnIndex("RazonSocial")));
			clienteTO.setNombreComercial(cursor.getString(cursor.getColumnIndex("NombreComercial")));
			clienteTO.setTelefono1(cursor.getString(cursor.getColumnIndex("Telefono1")));
			clienteTO.setTelefono2(cursor.getString(cursor.getColumnIndex("Telefono2")));
			clienteTO.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
			clienteTO.setUrl(cursor.getString(cursor.getColumnIndex("Url")));
			clienteTO.setCodigoPredecesor(cursor.getString(cursor.getColumnIndex("CodPredecesor")));
			clienteTO.setCodigoSucesor(cursor.getString(cursor.getColumnIndex("CodSucesor")));
			clienteTO.setFax(cursor.getString(cursor.getColumnIndex("Fax")));
			clienteTO.setSubCanal(cursor.getString(cursor.getColumnIndex("SubCanal")));
			clienteTO.setDireccionFiscal(cursor.getString(cursor.getColumnIndex("DireccionFiscal")));
			clienteTO.setUbigeoFiscal(cursor.getString(cursor.getColumnIndex("CodUbigeoFiscal")));
			clienteTO.setDireccionEntrega(cursor.getString(cursor.getColumnIndex("DireccionEntrega")));
			clienteTO.setUbigeoEntrega(cursor.getString(cursor.getColumnIndex("CodUbigeoEntrega")));
			clienteTO.setDistribuidor(cursor.getString(cursor.getColumnIndex("Distribuidor")));
			clienteTO.setDistritoComercial(cursor.getString(cursor.getColumnIndex("DistritoComercial")));
			clienteTO.setDistritoGeografico(cursor.getString(cursor.getColumnIndex("DistritoGeografico")));
			clienteTO.setZona(cursor.getString(cursor.getColumnIndex("Zona")));
			clienteTO.setRutaIK(cursor.getString(cursor.getColumnIndex("RutaIK")));
			clienteTO.setSegmento(cursor.getString(cursor.getColumnIndex("Segmento")));
			clienteTO.setUbicacion(cursor.getString(cursor.getColumnIndex("Ubicacion")));
			clienteTO.setFechaCreacion(cursor.getString(cursor.getColumnIndex("Fecha")));
			clienteTO.setHoraCreacion(cursor.getString(cursor.getColumnIndex("Hora")));
			clienteTO.setUsuarioCrea(cursor.getString(cursor.getColumnIndex("Usuario")));
			clienteTO.setLatitud(cursor.getString(cursor.getColumnIndex("Latitud")));
			clienteTO.setLongitud(cursor.getString(cursor.getColumnIndex("Longitud")));
			clienteTO.setCodigoReferencia(cursor.getString(cursor.getColumnIndex("CodReferencia")));
			clienteTO.setRazonSocialRef(cursor.getString(cursor.getColumnIndex("RazonSocialRef")));
			clienteTO.setAccion(cursor.getInt(cursor.getColumnIndex("accion")));
			clienteTO.setVendedor(cursor.getString(cursor.getColumnIndex("Vendedor")));
			clienteTO.setTamanio(cursor.getString(cursor.getColumnIndex("TamanioCliente")));

			clienteTO.setCodigoMotivo(cursor.getString(cursor.getColumnIndex("codMotivo")));
			clienteTO.setMotivo(cursor.getString(cursor.getColumnIndex("motivo")));
			clienteTO.setObservacion(cursor.getString(cursor.getColumnIndex("observacion")));
			
			listado.add(clienteTO);
		}

		cursor.close();

		return listado;
	}

	public ArrayList<ClienteTO> list() {

		ArrayList<ClienteTO> listado = new ArrayList<ClienteTO>();

		String SQL = "SELECT accion,nullif(codigo,0) as codigo,nullif(Ruc,'') as Ruc,nullif(Dni,'') as Dni,nullif(RazonSocial,'') as  RazonSocial,nullif(NombreComercial,'') as NombreComercial,"
				+ "nullif(DireccionFiscal,'') as DireccionFiscal,nullif(CodUbigeoFiscal,'') as CodUbigeoFiscal,nullif(DireccionEntrega,'') as DireccionEntrega,"
				+ "nullif(CodUbigeoEntrega,'') as CodUbigeoEntrega,nullif(SubCanal,'') as SubCanal,nullif(Fax,'') as Fax,nullif(Distribuidor,'') as Distribuidor,"
				+ "nullif(DistritoComercial,'') as DistritoComercial,nullif(DistritoGeografico,'') as DistritoGeografico,nullif(Zona,'') as Zona,nullif(RutaIK,'') as RutaIK,"
				+ "nullif(Segmento,'') as Segmento,nullif(Ubicacion,'') as Ubicacion,nullif(Email,'') as Email,nullif(Url,'') as Url,nullif(Telefono1,'') as Telefono1,nullif(Telefono2,'') as Telefono2,"
				+ "nullif(CodSucesor,'') as CodSucesor,nullif(CodPredecesor,'') as CodPredecesor,nullif(Usuario,'') as Usuario,nullif(Fecha,'') as Fecha,nullif(Hora,'') as Hora,nullif(Longitud,0) as Longitud,nullif(Latitud,0) as Latitud,nullif(CodReferencia,'') as CodReferencia,nullif(Vendedor,'') as Vendedor,nullif(TamanioCliente,'') as TamanioCliente,nullif(IdReg,0) as IdReg"
				+ " FROM Cliente Where TieneCambios=?";

		String[] parametros = new String[] { String.valueOf(ClienteTO.FICHA_TIENE_CAMBIOS) };
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);

		ClienteTO clienteTO;

		while (cursor.moveToNext()) {
			clienteTO = new ClienteTO();
			clienteTO.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
			clienteTO.setRuc(cursor.getString(cursor.getColumnIndex("Ruc")));
			clienteTO.setDni(cursor.getString(cursor.getColumnIndex("Dni")));
			clienteTO.setRazonSocial(cursor.getString(cursor.getColumnIndex("RazonSocial")));
			clienteTO.setNombreComercial(cursor.getString(cursor.getColumnIndex("NombreComercial")));
			clienteTO.setTelefono1(cursor.getString(cursor.getColumnIndex("Telefono1")));
			clienteTO.setTelefono2(cursor.getString(cursor.getColumnIndex("Telefono2")));
			clienteTO.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
			clienteTO.setUrl(cursor.getString(cursor.getColumnIndex("Url")));
			clienteTO.setCodigoPredecesor(cursor.getString(cursor.getColumnIndex("CodPredecesor")));
			clienteTO.setCodigoSucesor(cursor.getString(cursor.getColumnIndex("CodSucesor")));
			clienteTO.setFax(cursor.getString(cursor.getColumnIndex("Fax")));
			clienteTO.setSubCanal(cursor.getString(cursor.getColumnIndex("SubCanal")));
			clienteTO.setDireccionFiscal(cursor.getString(cursor.getColumnIndex("DireccionFiscal")));
			clienteTO.setUbigeoFiscal(cursor.getString(cursor.getColumnIndex("CodUbigeoFiscal")));
			clienteTO.setDireccionEntrega(cursor.getString(cursor.getColumnIndex("DireccionEntrega")));
			clienteTO.setUbigeoEntrega(cursor.getString(cursor.getColumnIndex("CodUbigeoEntrega")));
			clienteTO.setDistribuidor(cursor.getString(cursor.getColumnIndex("Distribuidor")));
			clienteTO.setDistritoComercial(cursor.getString(cursor.getColumnIndex("DistritoComercial")));
			clienteTO.setDistritoGeografico(cursor.getString(cursor.getColumnIndex("DistritoGeografico")));
			clienteTO.setZona(cursor.getString(cursor.getColumnIndex("Zona")));
			clienteTO.setRutaIK(cursor.getString(cursor.getColumnIndex("RutaIK")));
			clienteTO.setSegmento(cursor.getString(cursor.getColumnIndex("Segmento")));
			clienteTO.setUbicacion(cursor.getString(cursor.getColumnIndex("Ubicacion")));
			clienteTO.setFechaCreacion(cursor.getString(cursor.getColumnIndex("Fecha")));
			clienteTO.setHoraCreacion(cursor.getString(cursor.getColumnIndex("Hora")));
			clienteTO.setUsuarioCrea(cursor.getString(cursor.getColumnIndex("Usuario")));
			clienteTO.setLatitud(cursor.getString(cursor.getColumnIndex("Latitud")));
			clienteTO.setLongitud(cursor.getString(cursor.getColumnIndex("Longitud")));
			clienteTO.setCodigoReferencia(cursor.getString(cursor.getColumnIndex("CodReferencia")));
			clienteTO.setAccion(cursor.getInt(cursor.getColumnIndex("accion")));
			clienteTO.setVendedor(cursor.getString(cursor.getColumnIndex("Vendedor")));
			clienteTO.setTamanio(cursor.getString(cursor.getColumnIndex("TamanioCliente")));
			clienteTO.setIdBd(cursor.getInt(cursor.getColumnIndex("IdReg")));

			listado.add(clienteTO);
		}

		cursor.close();

		return listado;
	}

	public void save(final ClienteTO clienteTO, final UsuarioTO usuarioTO) {

		ContentValues parametros = new ContentValues();

		if (clienteTO.getRuc() == null)
			clienteTO.setRuc("");
		parametros.put("Ruc", clienteTO.getRuc());

		if (clienteTO.getDni() == null)
			clienteTO.setDni("");
		parametros.put("Dni", clienteTO.getDni());

		if (clienteTO.getRazonSocial() == null)
			clienteTO.setRazonSocial("");
		parametros.put("RazonSocial", clienteTO.getRazonSocial());

		if (clienteTO.getNombreComercial() == null)
			clienteTO.setNombreComercial("");
		parametros.put("NombreComercial", clienteTO.getNombreComercial());

		if (clienteTO.getDireccionFiscal() == null)
			clienteTO.setDireccionFiscal("");
		parametros.put("DireccionFiscal", clienteTO.getDireccionFiscal());

		if (clienteTO.getUbigeoFiscal() == null)
			clienteTO.setUbigeoFiscal("");
		parametros.put("CodUbigeoFiscal", clienteTO.getUbigeoFiscal());

		if (clienteTO.getDireccionEntrega() == null)
			clienteTO.setDireccionEntrega("");
		parametros.put("DireccionEntrega", clienteTO.getDireccionEntrega());

		if (clienteTO.getUbigeoEntrega() == null)
			clienteTO.setUbigeoEntrega("");
		parametros.put("CodUbigeoEntrega", clienteTO.getUbigeoEntrega());

		if (clienteTO.getSubCanal() == null)
			clienteTO.setSubCanal("");
		parametros.put("SubCanal", clienteTO.getSubCanal());

		if (clienteTO.getTelefono1() == null)
			clienteTO.setTelefono1("");
		parametros.put("Telefono1", clienteTO.getTelefono1());

		if (clienteTO.getTelefono2() == null)
			clienteTO.setTelefono2("");
		parametros.put("Telefono2", clienteTO.getTelefono2());

		if (clienteTO.getFax() == null)
			clienteTO.setFax("");
		parametros.put("Fax", clienteTO.getFax());

		if (clienteTO.getDistribuidor() == null)
			clienteTO.setDistribuidor("");
		parametros.put("Distribuidor", clienteTO.getDistribuidor());

		if (clienteTO.getDistritoComercial() == null)
			clienteTO.setDistritoComercial("");
		parametros.put("DistritoComercial", clienteTO.getDistritoComercial());

		if (clienteTO.getDistritoGeografico() == null)
			clienteTO.setDistritoGeografico("");
		parametros.put("DistritoGeografico", clienteTO.getDistritoGeografico());

		if (clienteTO.getZona() == null)
			clienteTO.setZona("");
		parametros.put("Zona", clienteTO.getZona());

		if (clienteTO.getRutaIK() == null)
			clienteTO.setRutaIK("");
		parametros.put("RutaIK", clienteTO.getRutaIK());

		if (clienteTO.getSegmento() == null)
			clienteTO.setSegmento("");
		parametros.put("Segmento", clienteTO.getSegmento());

		if (clienteTO.getUbicacion() == null)
			clienteTO.setUbicacion("");
		parametros.put("Ubicacion", clienteTO.getUbicacion());

		if (clienteTO.getEmail() == null)
			clienteTO.setEmail("");
		parametros.put("Email", clienteTO.getEmail());

		if (clienteTO.getUrl() == null)
			clienteTO.setUrl("");
		parametros.put("Url", clienteTO.getUrl());

		if (clienteTO.getCodigoSucesor() == null)
			clienteTO.setCodigoSucesor("");
		parametros.put("CodSucesor", clienteTO.getCodigoSucesor());
		
		if (clienteTO.getCodigoPredecesor() == null)
			clienteTO.setCodigoPredecesor("");
		parametros.put("CodPredecesor", clienteTO.getCodigoPredecesor());

		parametros.put("Usuario", usuarioTO.getCodigoSap());

		CharSequence fecha = android.text.format.DateFormat.format("yyyyMMdd",
				new java.util.Date());
		CharSequence hora = android.text.format.DateFormat.format("kkmm",
				new java.util.Date());

		parametros.put("Fecha", fecha.toString());
		parametros.put("Hora", hora.toString());

		if (clienteTO.getCodigo() == null)
			clienteTO.setCodigo("");
		parametros.put("Codigo", clienteTO.codigo);

		parametros.put("Accion", clienteTO.accion);
		parametros.put("CodMotivo", clienteTO.getCodigoMotivo());
		parametros.put("Observacion", clienteTO.getObservacion());

		if (clienteTO.getCodigoReferencia() == null)
			clienteTO.setCodigoReferencia("");
		parametros.put("CodReferencia", clienteTO.getCodigoReferencia());

		parametros.put("Latitud", clienteTO.getLatitud());
		parametros.put("Longitud", clienteTO.getLongitud());

		parametros.put("LatitudRef", clienteTO.getLatitudRef());
		parametros.put("LongitudRef", clienteTO.getLongitudRef());
		parametros.put("RazonSocialRef", clienteTO.getRazonSocialRef());
		parametros.put("DireccionRef", clienteTO.getDireccionEntregaRef());

		if (clienteTO.getVendedor() == null)
			clienteTO.setVendedor("");
		parametros.put("Vendedor", clienteTO.getVendedor());

		parametros.put("IdReg", clienteTO.idBd);

		if (clienteTO.getTamanio() == null)
			clienteTO.setTamanio("");
		parametros.put("TamanioCliente", clienteTO.getTamanio());

		parametros.put("TieneCambios", clienteTO.getTieneCambios());

		long id = dbHelper.getDataBase().insertOrThrow("Cliente", null, parametros);
		
		clienteTO.setClienteId((int)id);

	}

	public void update(final ClienteTO clienteTO, final UsuarioTO usuarioTO) {

		ContentValues parametros = new ContentValues();

		if (clienteTO.getRuc() == null)
			clienteTO.setRuc("");
		parametros.put("Ruc", clienteTO.getRuc());

		if (clienteTO.getDni() == null)
			clienteTO.setDni("");
		parametros.put("Dni", clienteTO.getDni());

		if (clienteTO.getRazonSocial() == null)
			clienteTO.setRazonSocial("");
		parametros.put("RazonSocial", clienteTO.getRazonSocial());

		if (clienteTO.getNombreComercial() == null)
			clienteTO.setNombreComercial("");
		parametros.put("NombreComercial", clienteTO.getNombreComercial());

		if (clienteTO.getDireccionFiscal() == null)
			clienteTO.setDireccionFiscal("");
		parametros.put("DireccionFiscal", clienteTO.getDireccionFiscal());

		if (clienteTO.getUbigeoFiscal() == null)
			clienteTO.setUbigeoFiscal("");
		parametros.put("CodUbigeoFiscal", clienteTO.getUbigeoFiscal());

		if (clienteTO.getDireccionEntrega() == null)
			clienteTO.setDireccionEntrega("");
		parametros.put("DireccionEntrega", clienteTO.getDireccionEntrega());

		if (clienteTO.getUbigeoEntrega() == null)
			clienteTO.setUbigeoEntrega("");
		parametros.put("CodUbigeoEntrega", clienteTO.getUbigeoEntrega());

		if (clienteTO.getSubCanal() == null)
			clienteTO.setSubCanal("");
		parametros.put("SubCanal", clienteTO.getSubCanal());

		if (clienteTO.getTelefono1() == null)
			clienteTO.setTelefono1("");
		parametros.put("Telefono1", clienteTO.getTelefono1());

		if (clienteTO.getTelefono2() == null)
			clienteTO.setTelefono2("");
		parametros.put("Telefono2", clienteTO.getTelefono2());

		if (clienteTO.getFax() == null)
			clienteTO.setFax("");
		parametros.put("Fax", clienteTO.getFax());

		if (clienteTO.getDistribuidor() == null)
			clienteTO.setDistribuidor("");
		parametros.put("Distribuidor", clienteTO.getDistribuidor());

		if (clienteTO.getDistritoComercial() == null)
			clienteTO.setDistritoComercial("");
		parametros.put("DistritoComercial", clienteTO.getDistritoComercial());

		if (clienteTO.getDistritoGeografico() == null)
			clienteTO.setDistritoGeografico("");
		parametros.put("DistritoGeografico", clienteTO.getDistritoGeografico());

		if (clienteTO.getZona() == null)
			clienteTO.setZona("");
		parametros.put("Zona", clienteTO.getZona());

		if (clienteTO.getRutaIK() == null)
			clienteTO.setRutaIK("");
		parametros.put("RutaIK", clienteTO.getRutaIK());

		if (clienteTO.getSegmento() == null)
			clienteTO.setSegmento("");
		parametros.put("Segmento", clienteTO.getSegmento());

		if (clienteTO.getUbicacion() == null)
			clienteTO.setUbicacion("");
		parametros.put("Ubicacion", clienteTO.getUbicacion());

		if (clienteTO.getEmail() == null)
			clienteTO.setEmail("");
		parametros.put("Email", clienteTO.getEmail());

		if (clienteTO.getUrl() == null)
			clienteTO.setUrl("");
		parametros.put("Url", clienteTO.getUrl());

		parametros.put("CodSucesor", clienteTO.getCodigoSucesor());
		parametros.put("CodPredecesor",clienteTO.getCodigoPredecesor());

		parametros.put("Usuario", usuarioTO.getCodigoSap());

		CharSequence fecha = android.text.format.DateFormat.format("yyyyMMdd",
				new java.util.Date());
		CharSequence hora = android.text.format.DateFormat.format("kkmm",
				new java.util.Date());

		parametros.put("Fecha", fecha.toString());
		parametros.put("Hora", hora.toString());

		if (clienteTO.getCodigo() == null)
			clienteTO.setCodigo("");
		parametros.put("Codigo", clienteTO.codigo);

		parametros.put("Accion", clienteTO.accion);
		parametros.put("CodMotivo", clienteTO.codigoMotivo);
		parametros.put("Observacion", clienteTO.observacion);

		if (clienteTO.getCodigoReferencia() == null)
			clienteTO.setCodigoReferencia("");
		parametros.put("CodReferencia", clienteTO.getCodigoReferencia());

		parametros.put("Latitud", clienteTO.getLatitud());
		parametros.put("Longitud", clienteTO.getLongitud());

		parametros.put("LatitudRef", clienteTO.getLatitudRef());
		parametros.put("LongitudRef", clienteTO.getLongitudRef());
		parametros.put("RazonSocialRef", clienteTO.getRazonSocialRef());
		parametros.put("DireccionRef", clienteTO.getDireccionEntregaRef());

		if (clienteTO.getVendedor() == null)
			clienteTO.setVendedor("");
		parametros.put("Vendedor", clienteTO.getVendedor());

		parametros.put("IdReg",clienteTO.getIdBd());

		if (clienteTO.getTamanio() == null)
			clienteTO.setTamanio("");
		parametros.put("TamanioCliente", clienteTO.getTamanio());

		parametros.put("TieneCambios", clienteTO.tieneCambios);

		String[] valores = new String[] { String.valueOf(clienteTO.getClienteId()) };

		dbHelper.getDataBase().update("Cliente", parametros, "ClienteId = ?",
				valores);
	}
}
