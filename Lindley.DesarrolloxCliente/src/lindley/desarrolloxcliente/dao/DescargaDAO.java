package lindley.desarrolloxcliente.dao;



import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.ResumenValueTO;
import lindley.desarrolloxcliente.to.download.AccionTradeProductoTO;
import lindley.desarrolloxcliente.to.download.AccionTradeTO;
import lindley.desarrolloxcliente.to.download.AceleradorTO;
import lindley.desarrolloxcliente.to.download.ArticuloTO;
import lindley.desarrolloxcliente.to.download.ClienteDescargaTO;
import lindley.desarrolloxcliente.to.download.OportunidadTO;
import lindley.desarrolloxcliente.to.download.PosicionTO;
import lindley.desarrolloxcliente.to.download.PresentacionTO;
import lindley.desarrolloxcliente.to.download.ProductoTO;
import lindley.desarrolloxcliente.to.download.ProfitTO;
import lindley.desarrolloxcliente.to.download.PuntoTO;
import lindley.desarrolloxcliente.to.download.SkuTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class DescargaDAO {

	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
	
	public void actualizarPuntos(String codigoCliente,long puntos){
		
		String[] args = new String[] {String.valueOf(puntos),codigoCliente};
		dbHelper.execSQL("UPDATE cliente SET puntos = puntos + ?1 WHERE codigo = ?2",args);
	}
	public int factorCierre(String tipoAgrupacion,String codigoVariable,String fechaCierre){
		
		String SQL = "select factor from acelerador where tipoAgrupacion = ?1 and codigoVariable=?2 and ?3 between fechainicio and fechaFin";
		String[] args = new String[] {tipoAgrupacion,codigoVariable,fechaCierre};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		int factor=1;
		
		if(cursor.moveToNext()){
			factor = cursor.getInt(cursor.getColumnIndex("factor"));
		}
		cursor.close();
		
		return factor;
	}
	
	public void deleteEvaluacion(){
		dbHelper.delete("evaluacion", null, null);
	}
	
	public void deleteEvaluacion(long id){
		dbHelper.delete("evaluacion", "id=?",new String[]{String.valueOf(id)});
		dbHelper.delete("evaluacion_oportunidad", "evaluacionId=?",new String[]{String.valueOf(id)});
		dbHelper.delete("evaluacion_posicion", "evaluacionId=?",new String[]{String.valueOf(id)});
		dbHelper.delete("evaluacion_presentacion", "evaluacionId=?",new String[]{String.valueOf(id)});
		dbHelper.delete("evaluacion_sku_presentacion", "evaluacionId=?",new String[]{String.valueOf(id)});
		dbHelper.delete("evaluacion_posicion_compromiso", "evaluacionId=?",new String[]{String.valueOf(id)});
		
	}
	
	
	public void deleteMotivo(){
		dbHelper.delete("motivo", null, null);
	}
	
	public long insertMotivo(ResumenValueTO resumenValueTO){
		
		ContentValues values = new ContentValues();
		values.put("codigo", resumenValueTO.valor);
		values.put("descripcion", resumenValueTO.descripcion);
		
		long id= dbHelper.insertOrThrow("motivo", values);
		resumenValueTO.id = id;
		
		return id;
		
	}
	
	public void deleteArticuloCanje(){
		dbHelper.delete("articulo_canje", null, null);
	}
	
	public long insertArticuloCanje(ArticuloTO articuloTO){
		ContentValues values = new ContentValues();
		
		values.put("codigo", articuloTO.codigoArticulo);
		values.put("descripcion", articuloTO.descripcionArticulo);
		values.put("puntos", articuloTO.puntosCanje);
		
		long id= dbHelper.insertOrThrow("articulo_canje", values);
		articuloTO.id = id;
		
		return id;
		
	}
	
	public void deleteAcelerador(){
		dbHelper.delete("acelerador", null, null);
	}
	
	public long insertAcelerador(AceleradorTO aceleradorTO){
		ContentValues values = new ContentValues();
		
		values.put("tipoAgrupacion", aceleradorTO.tipoAgrupacion);
		values.put("codigoVariable", aceleradorTO.codigoVariable);
		values.put("fechaInicio", aceleradorTO.fechaInicio);
		values.put("fechaFin", aceleradorTO.fechaFin);
		values.put("factor", aceleradorTO.punto);
		
		long id= dbHelper.insertOrThrow("acelerador", values);
		aceleradorTO.id = id;
		
		return id;
		
	}
	
	public void deleteProfit(){
		dbHelper.delete("profit", null, null);
	}
	
	

	
	public long insertProfit(ProfitTO profitTO){
		ContentValues values = new ContentValues();
		
		values.put("anio", profitTO.anio);
		values.put("mes", profitTO.mes);
		values.put("codigoCliente", profitTO.codigoCliente);
		values.put("codigoArticulo", profitTO.codigoArticulo);
		values.put("indicador", profitTO.indicador);
		values.put("cajasFisica", profitTO.cajasFisica);
		values.put("margenActual", profitTO.margenActual);
		values.put("cajasFisicasFaltante", profitTO.cajasFisicasFaltante);
		values.put("margenFaltante", profitTO.margenFaltante);
		
		long id= dbHelper.insertOrThrow("profit", values);
		profitTO.id = id;
		
		return id;
		
	}
	public long insertEvaluacion(EvaluacionTO evaluacionTO){
		ContentValues values = new ContentValues();
		values.put("clienteCodigo", evaluacionTO.codigoCliente);
		values.put("activosLindley", evaluacionTO.activosLindley);
		values.put("codigoFe", evaluacionTO.codigoFDE);
		values.put("usuario", evaluacionTO.usuarioCreacion);
		values.put("fecha", evaluacionTO.fechaCreacion);
		values.put("hora", evaluacionTO.horaCreacion);
		
		values.put("usuarioCierre", evaluacionTO.usuarioCierre);
		values.put("fechaCierre", evaluacionTO.fechaCierre);
		values.put("horaCierre", evaluacionTO.horaCierre);
		
		values.put("combosSS", evaluacionTO.comboSS);
		values.put("combosMS", evaluacionTO.comboMS);
		values.put("obsSS", evaluacionTO.observacionSS);
		values.put("obsMS", evaluacionTO.observacionMS);
		values.put("estado", evaluacionTO.estado);
		values.put("serverId", evaluacionTO.serverId);
		values.put("tieneCambios", evaluacionTO.tieneCambio);
		values.put("motivoId", evaluacionTO.motivoId);
		values.put("motivo", evaluacionTO.motivo);
		values.put("proceso", evaluacionTO.proceso);
		
		long id= dbHelper.insertOrThrow("evaluacion", values);
		
		evaluacionTO.id = id;
		
		return id;
		
	}
	
	public long getEvaluacionId(long serverId){
		
		String SQL = "select id from evaluacion where serverId = ?1";
		
		String[] args = new String[] {String.valueOf(serverId)};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		long id=0;
		
		if(cursor.moveToNext()){
			id = cursor.getLong(cursor.getColumnIndex("id"));
		}
		cursor.close();
		return id;
	}
	
	public void deleteEvaluacionOportunidad(){
		dbHelper.delete("evaluacion_oportunidad", null, null);
	}
	
	public void deleteEvaluacionPosicion(){
		dbHelper.delete("evaluacion_posicion", null, null);
	}
	
	public void deleteEvaluacionPresentacion(){
		dbHelper.delete("evaluacion_presentacion", null, null);
	}
	
	public void deleteEvaluacionSkus(){
		dbHelper.delete("evaluacion_sku_presentacion", null, null);
	}
	
	public void deleteEvaluacionPosicionCompromiso(){
		dbHelper.delete("evaluacion_posicion_compromiso", null, null);
	}
	
	public long insertEvaluacionSkus(lindley.desarrolloxcliente.to.upload.SkuTO sku){
		
		long localID = getEvaluacionId(sku.evaluacionId);
		ContentValues values = new ContentValues();
		values.put("evaluacionId", localID);
		values.put("anio", sku.anio);
		values.put("mes", sku.mes);
		values.put("tipoAgrupacion", sku.tipoAgrupacion);
		values.put("codfde", sku.codigoFDE);
		values.put("codigoVariable", sku.codigoVariable);
		values.put("skuId", sku.codigoSKU);
		values.put("sku", sku.descripcionSKU);
		values.put("marca", sku.marcaActual);
		values.put("marcaCompromiso", sku.marcaCompromiso);
		values.put("confirmacion", sku.marcaCumplio);
		values.put("estado", sku.estado);
		values.put("proceso", sku.proceso);
		long id= dbHelper.insertOrThrow("evaluacion_sku_presentacion", values);
		sku.id = id;
		return id;
	}
	
	public long insertEvaluacionPresentacion(lindley.desarrolloxcliente.to.upload.PresentacionTO presentacionTO){
		
		
		long localID = getEvaluacionId(presentacionTO.evaluacionId);
		ContentValues values = new ContentValues();
		values.put("evaluacionId", localID);
		values.put("anio", presentacionTO.anio);
		values.put("mes", presentacionTO.mes);
		values.put("tipoAgrupacion", presentacionTO.tipoAgrupacion);
		values.put("codfde", presentacionTO.codigoFDE);
		values.put("codigoVariable", presentacionTO.codigoVariable);
		values.put("fechaEncuesta", presentacionTO.fechaEncuesta);
		values.put("puntosSugeridos", presentacionTO.puntosSugeridos);
		values.put("puntosGanados", presentacionTO.puntosGanados);
		values.put("puntosBonus", presentacionTO.puntosBonus);
		values.put("fechaCompromiso", presentacionTO.fechaCompromiso);
		values.put("confirmacion", presentacionTO.confirmacion);
		values.put("origen", presentacionTO.origen);
		values.put("estado", presentacionTO.estado);
		values.put("proceso", presentacionTO.proceso);
		
		long id= dbHelper.insertOrThrow("evaluacion_presentacion", values);
		presentacionTO.id = id;
		return id;
		
	}
	
	public long insertEvaluacionPosicionCompromiso(lindley.desarrolloxcliente.to.upload.PosicionCompromisoTO posicionCompromisoTO){
		ContentValues values = new ContentValues();
		
		long localID = getEvaluacionId(posicionCompromisoTO.evaluacionId);
		
		values.put("evaluacionId", localID);
		values.put("tipoAgrupacion", posicionCompromisoTO.tipoAgrupacion);
		values.put("codigoVariable", posicionCompromisoTO.codigoVariable);
		values.put("orden", posicionCompromisoTO.orden);
		values.put("observacion", posicionCompromisoTO.observacion);
		values.put("estado", posicionCompromisoTO.estado);
		values.put("proceso", posicionCompromisoTO.proceso);
		
		long id= dbHelper.insertOrThrow("evaluacion_posicion_compromiso", values);
		posicionCompromisoTO.id = id;
		return id;
		
	}
	public long insertEvaluacionPosicion(lindley.desarrolloxcliente.to.upload.PosicionTO posicionTO){
		ContentValues values = new ContentValues();
		
		long localID = getEvaluacionId(posicionTO.evaluacionId);
		
		values.put("evaluacionId", localID);
		values.put("anio", posicionTO.anio);
		values.put("mes", posicionTO.mes);
		values.put("codigoVariable", posicionTO.codigoVariable);
		values.put("soviRed", posicionTO.sovir);
		values.put("soviMaximo", posicionTO.sovirMaximo);
		values.put("soviDiferencia", posicionTO.sovirDiferencia);
		
		values.put("puntosSugeridos", posicionTO.puntosSugeridos);
		values.put("puntosGanados", posicionTO.puntosGanados);
		values.put("puntosBonus", posicionTO.puntosBonus);
		
		values.put("fotoInicial", posicionTO.fotoInicial);
		values.put("fotoFinal", posicionTO.fotoFinal);
		
		values.put("activosLindley", posicionTO.activosLindley);
		values.put("observacion", posicionTO.observacion);
		
		values.put("fechaCompromiso", posicionTO.fechaCompromiso);
		values.put("fechaEncuesta", posicionTO.fechaEncuesta);
		values.put("confirmacion", posicionTO.confirmacion);
		values.put("origen", posicionTO.origen);
		values.put("estado", posicionTO.estado);
		values.put("tipoAgrupacion", posicionTO.tipoAgrupacion);
		values.put("proceso", posicionTO.proceso);
		
		long id= dbHelper.insertOrThrow("evaluacion_posicion", values);
		posicionTO.id = id;
		return id;
		
	}
	
	public long insertEvaluacionOportunidad(EvaluacionTO evaluacionTO, lindley.desarrolloxcliente.to.upload.OportunidadTO oportunidadTO){
		ContentValues values = new ContentValues();
		
	
		values.put("evaluacionId", evaluacionTO.id);
		
		
		values.put("anio", oportunidadTO.anio);
		values.put("mes", oportunidadTO.mes);
		
		values.put("productoId", 0);
		values.put("codigoProducto", oportunidadTO.codigoArticulo);
		values.put("producto", oportunidadTO.articulo);
		
		values.put("concrecion", oportunidadTO.concrecion);
		values.put("concrecionActual", oportunidadTO.concrecionActual);
		values.put("concrecionCumple", oportunidadTO.concrecionCumple);
		
		values.put("sovi", oportunidadTO.sovi);
		values.put("soviActual", oportunidadTO.soviActual);
		values.put("soviCumple", oportunidadTO.soviCumple);
		
		values.put("respetaPrecio", oportunidadTO.respetoPrecio);
		values.put("respetaPrecioActual", oportunidadTO.respetoPrecioActual);
		values.put("respetaPrecioCumple", oportunidadTO.respetoPrecioCumple);
		
		values.put("numeroSabores", oportunidadTO.numeroSabores);
		values.put("numeroSaboresActual", oportunidadTO.numeroSaboresActual);
		values.put("numeroSaboresCumple", oportunidadTO.numeroSaboresCumple);
		
		values.put("codigoAccion", oportunidadTO.codigoAccionTrade);
		values.put("accion", oportunidadTO.accionTrade);
		values.put("numeroSaboresCumple", oportunidadTO.numeroSaboresCumple);
		
		values.put("puntosSugeridos", oportunidadTO.puntosSugeridos);
		values.put("puntosBonus", oportunidadTO.puntosBonus);
		values.put("puntosGanados", oportunidadTO.puntosGanados);
		
		values.put("fechaProceso", oportunidadTO.fechaCompromiso);
		
		
		values.put("confirmacion", oportunidadTO.confirmacion);
		values.put("origen", oportunidadTO.origen);
		values.put("estado", oportunidadTO.estado);
		values.put("legacy", oportunidadTO.legacy);
		
		
		long id= dbHelper.insertOrThrow("evaluacion_oportunidad", values);
		oportunidadTO.id = id;
		return id;
	}
	
	public long insertEvaluacionPosicion(EvaluacionTO evaluacionTO, lindley.desarrolloxcliente.to.upload.PosicionTO posicionTO){
		ContentValues values = new ContentValues();
		
		values.put("evaluacionId", evaluacionTO.id);
		values.put("anio", posicionTO.anio);
		values.put("mes", posicionTO.mes);
		values.put("codigoVariable", posicionTO.codigoVariable);
		values.put("soviRed", posicionTO.sovir);
		values.put("soviMaximo", posicionTO.sovirMaximo);
		values.put("soviDiferencia", posicionTO.sovirDiferencia);
		
		values.put("puntosSugeridos", posicionTO.puntosSugeridos);
		values.put("puntosGanados", posicionTO.puntosGanados);
		values.put("puntosBonus", posicionTO.puntosBonus);
		
		values.put("fotoInicial", posicionTO.fotoInicial);
		values.put("fotoFinal", posicionTO.fotoFinal);
		
		values.put("activosLindley", posicionTO.activosLindley);
		values.put("observacion", posicionTO.observacion);
		
		values.put("fechaCompromiso", posicionTO.fechaCompromiso);
		values.put("fechaEncuesta", posicionTO.fechaEncuesta);
		values.put("confirmacion", posicionTO.confirmacion);
		values.put("origen", posicionTO.origen);
		values.put("estado", posicionTO.estado);
		values.put("tipoAgrupacion", posicionTO.tipoAgrupacion);
		
		long id= dbHelper.insertOrThrow("evaluacion_posicion", values);
		posicionTO.id = id;
		return id;
		
	}
	
	public long insertEvaluacionPosicion(EvaluacionTO evaluacionTO, lindley.desarrolloxcliente.to.upload.PosicionCompromisoTO posicionCompromisoTO){
ContentValues values = new ContentValues();
		
		values.put("evaluacionId", evaluacionTO.id);
		values.put("tipoAgrupacion", posicionCompromisoTO.tipoAgrupacion);
		values.put("codigoVariable", posicionCompromisoTO.codigoVariable);
		values.put("orden", posicionCompromisoTO.orden);
		values.put("observacion", posicionCompromisoTO.observacion);
		values.put("estado", posicionCompromisoTO.estado);
		
		long id= dbHelper.insertOrThrow("evaluacion_posicion_compromiso", values);
		posicionCompromisoTO.id = id;
		return id;
		
	}

	public long insertEvaluacionSkus(EvaluacionTO evaluacionTO,lindley.desarrolloxcliente.to.upload.SkuTO sku){
		
		ContentValues values = new ContentValues();
		values.put("evaluacionId", evaluacionTO.id);
		values.put("anio", sku.anio);
		values.put("mes", sku.mes);
		values.put("tipoAgrupacion", sku.tipoAgrupacion);
		values.put("codfde", sku.codigoFDE);
		values.put("codigoVariable", sku.codigoVariable);
		values.put("skuId", sku.codigoSKU);
		values.put("sku", sku.descripcionSKU);
		values.put("marca", sku.marcaActual);
		values.put("marcaCompromiso", sku.marcaCompromiso);
		values.put("confirmacion", sku.marcaCumplio);
		values.put("estado", sku.estado);
		
		long id= dbHelper.insertOrThrow("evaluacion_sku_presentacion", values);
		sku.id = id;
		return id;
	}
	public long insertEvaluacionPresentacion(EvaluacionTO evaluacionTO, lindley.desarrolloxcliente.to.upload.PresentacionTO presentacionTO){
		
		
	
		ContentValues values = new ContentValues();
		values.put("evaluacionId", evaluacionTO.id);
		values.put("anio", presentacionTO.anio);
		values.put("mes", presentacionTO.mes);
		values.put("tipoAgrupacion", presentacionTO.tipoAgrupacion);
		values.put("codfde", presentacionTO.codigoFDE);
		values.put("codigoVariable", presentacionTO.codigoVariable);
		values.put("fechaEncuesta", presentacionTO.fechaEncuesta);
		values.put("puntosSugeridos", presentacionTO.puntosSugeridos);
		values.put("puntosGanados", presentacionTO.puntosGanados);
		values.put("puntosBonus", presentacionTO.puntosBonus);
		values.put("fechaCompromiso", presentacionTO.fechaCompromiso);
		values.put("confirmacion", presentacionTO.confirmacion);
		values.put("origen", presentacionTO.origen);
		values.put("estado", presentacionTO.estado);
		
		
		long id= dbHelper.insertOrThrow("evaluacion_presentacion", values);
		presentacionTO.id = id;
		return id;
		
	}
	
	public long insertEvaluacionOportunidad(lindley.desarrolloxcliente.to.upload.OportunidadTO oportunidadTO){
		ContentValues values = new ContentValues();
		
		
		long localID = getEvaluacionId(oportunidadTO.evaluacionId);
		values.put("evaluacionId", localID);
		
		
		values.put("anio", oportunidadTO.anio);
		values.put("mes", oportunidadTO.mes);
		
		values.put("productoId", 0);
		values.put("codigoProducto", oportunidadTO.codigoArticulo);
		values.put("producto", oportunidadTO.articulo);
		
		values.put("concrecion", oportunidadTO.concrecion);
		values.put("concrecionActual", oportunidadTO.concrecionActual);
		values.put("concrecionCumple", oportunidadTO.concrecionCumple);
		
		values.put("sovi", oportunidadTO.sovi);
		values.put("soviActual", oportunidadTO.soviActual);
		values.put("soviCumple", oportunidadTO.soviCumple);
		
		values.put("respetaPrecio", oportunidadTO.respetoPrecio);
		values.put("respetaPrecioActual", oportunidadTO.respetoPrecioActual);
		values.put("respetaPrecioCumple", oportunidadTO.respetoPrecioCumple);
		
		values.put("numeroSabores", oportunidadTO.numeroSabores);
		values.put("numeroSaboresActual", oportunidadTO.numeroSaboresActual);
		values.put("numeroSaboresCumple", oportunidadTO.numeroSaboresCumple);
		
		values.put("codigoAccion", oportunidadTO.codigoAccionTrade);
		values.put("accion", oportunidadTO.accionTrade);
		values.put("numeroSaboresCumple", oportunidadTO.numeroSaboresCumple);
		
		values.put("puntosSugeridos", oportunidadTO.puntosSugeridos);
		values.put("puntosBonus", oportunidadTO.puntosBonus);
		values.put("puntosGanados", oportunidadTO.puntosGanados);
		
		values.put("fechaProceso", oportunidadTO.fechaCompromiso);
		
		
		values.put("confirmacion", oportunidadTO.confirmacion);
		values.put("origen", oportunidadTO.origen);
		values.put("estado", oportunidadTO.estado);
		values.put("legacy", oportunidadTO.legacy);
		values.put("proceso", oportunidadTO.proceso);
		
		long id= dbHelper.insertOrThrow("evaluacion_oportunidad", values);
		oportunidadTO.id = id;
		return id;
	}

	
	
	public long insertProducto(ProductoTO productoTO){
		
		ContentValues values = new ContentValues();
		values.put("codigo", productoTO.codigo);
		values.put("descripcion", productoTO.descripcion);
		values.put("abreviatura", productoTO.abreviatura);
	
		
		long id= dbHelper.insertOrThrow("producto", values);
		
		productoTO.id = id;
		
		return id;
	}
	
	public void deleteProducto(){
		dbHelper.delete("producto", null, null);
	}
	

	public long insertOportunidad(OportunidadTO oportunidadTO){
		
		ContentValues values = new ContentValues();
		values.put("anio", periodoTO.anio);
		values.put("mes", periodoTO.mes);
		values.put("codigoCliente", oportunidadTO.codigoCliente);
		values.put("codigoProducto", oportunidadTO.codigoProducto);
		values.put("concrecion", oportunidadTO.concrecion);
		values.put("sovi", oportunidadTO.sovi);
		values.put("respetaPrecio", oportunidadTO.respetoPrecios);
		values.put("numeroSabores", oportunidadTO.numeroSabores);
		values.put("legacy", oportunidadTO.legacy);
		values.put("fechaProceso", 0);
		values.put("orden", oportunidadTO.orden);
		
		
		
		long id= dbHelper.insertOrThrow("oportunidad_cliente", values);
		
		oportunidadTO.id = id;
		
		return id;
	}
	
	public void deleteOportunidad(){
		dbHelper.delete("oportunidad_cliente", null, null);
	}

	public long insertSkus(SkuTO skuTO){
		ContentValues values = new ContentValues();
		values.put("codigo", skuTO.codigo);
		values.put("descripcion", skuTO.descripcion);
		values.put("cluster", skuTO.cluster);
		
		long id= dbHelper.insertOrThrow("sku", values);
		
		skuTO.id = id;
		
		return id;
	}
	
	public void deleteSku(){
		dbHelper.delete("sku", null, null);
	}
	
	
	public void insertAccionTrade(AccionTradeTO accionTradeTO){
		ContentValues values = new ContentValues();
		values.put("accionId", accionTradeTO.codigo);
		values.put("accion", accionTradeTO.descripcion);
		values.put("estado", "A");
		
		dbHelper.insertOrThrow("accion_trade", values);
		
	
	}
	
	public void deleteAccionTrade(){
		dbHelper.delete("accion_trade", null, null);
	}
	
	
	public void insertAccionTradeProducto(AccionTradeProductoTO accionTradeProductoTO){
		ContentValues values = new ContentValues();
		values.put("accionId", accionTradeProductoTO.codigoAccion);
		values.put("codigoProducto", accionTradeProductoTO.codigoProducto);
		values.put("estado", "A");
		
		dbHelper.insertOrThrow("accion_trade_producto", values);
		
	
	}
	
	public void deleteAccionTradeProducto(){
		dbHelper.delete("accion_trade_producto", null, null);
	}

	public long insertCliente(ClienteDescargaTO clienteDescargaTO){
		
		ContentValues values = new ContentValues();
		values.put("codigo", clienteDescargaTO.codigo);
		values.put("fecha", clienteDescargaTO.fecha);
		values.put("nombre", clienteDescargaTO.nombre);
		values.put("frecuencia", clienteDescargaTO.frecuencia);
		values.put("proyAlcance", clienteDescargaTO.alcance);
		values.put("proyFalta", clienteDescargaTO.falta);
		values.put("cluster", clienteDescargaTO.cluster);
		values.put("mc", clienteDescargaTO.mc);
		values.put("puntos", clienteDescargaTO.nroPuntos);
		values.put("siguiente", 0);
		values.put("direccion", clienteDescargaTO.direccion);
		values.put("latitud", clienteDescargaTO.latitud);
		values.put("longitud", clienteDescargaTO.longitud);
		values.put("abiertas", 0);
		values.put("tppro", clienteDescargaTO.TPPRO);
		long id = dbHelper.insertOrThrow("cliente", values);
		clienteDescargaTO.id=id;
		return id;
	
	}
	
	public void deleteCliente(){
		dbHelper.delete("cliente", null, null);
	}
	
	public long insertPosicion(PosicionTO posicionTO){
		
		ContentValues values = new ContentValues();
		values.put("anio", periodoTO.anio);
		values.put("mes", periodoTO.mes);
		values.put("codigoCliente", posicionTO.codigoCliente);
		values.put("activo", posicionTO.activo);
		values.put("tipoAgrupacion", posicionTO.tipoAgrupacion);
		values.put("variableRed", posicionTO.variableRed);
		values.put("fechaProceso", 0);
		
		
		long id = dbHelper.insertOrThrow("posicion_cliente", values);
		posicionTO.id=id;
		return id;
	
	}
	
	public void deletePosicion(){
		dbHelper.delete("posicion_cliente", null, null);
	}
	
	
	public long insertPresentacion(PresentacionTO presentacionTO){
		
		ContentValues values = new ContentValues();
		values.put("anio", periodoTO.anio);
		values.put("mes", periodoTO.mes);
		values.put("codigoCliente", presentacionTO.codigoCliente);
		values.put("cdfde", presentacionTO.fde);
		values.put("tipoAgrupacion", presentacionTO.tipoAgrupacion);
		values.put("variableRed", presentacionTO.variableRed);
		values.put("fechaProceso", presentacionTO.fechaRed);
		
		
		long id = dbHelper.insertOrThrow("presentacion_cliente", values);
		presentacionTO.id=id;
		return id;
	
	}

	public void deletePresentacion(){
		dbHelper.delete("presentacion_cliente", null, null);
	}
	
	public long insertPunto(PuntoTO punto){
		
		ContentValues values = new ContentValues();
		
		values.put("tppro", punto.tpPro);
		values.put("cdarr", punto.cdArr);
		values.put("cdvar", punto.cdVar);
		values.put("cnd01", punto.cnd01);
		values.put("puntos", punto.punto);
		
		
		long id = dbHelper.insertOrThrow("punto", values);
		punto.id=id;
		return id;
	
	}

	public void deletePunto(){
		dbHelper.delete("punto", null, null);
	}
	
}
