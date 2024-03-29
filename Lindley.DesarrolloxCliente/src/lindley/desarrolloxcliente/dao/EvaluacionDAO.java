package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import lindley.desarrolloxcliente.to.download.ArticuloTO;
import lindley.desarrolloxcliente.to.download.ProfitTO;
import lindley.desarrolloxcliente.to.download.ResumenTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.List;

import com.google.inject.Inject;

public class EvaluacionDAO {

	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
	
	
	public List<ArticuloTO> consultarArticulos(int puntos){
		//
		List<ArticuloTO> lista = new ArrayList<ArticuloTO>();
		
		String SQL = "SELECT codigo,descripcion,puntos FROM articulo_canje where puntos<?1 order by puntos DESC";
		String[] args = new String[] {String.valueOf(puntos)};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		ArticuloTO articuloTO;
		
		while(cursor.moveToNext()){
			articuloTO = new ArticuloTO();
			articuloTO.codigoArticulo=cursor.getString(cursor.getColumnIndex("codigo"));
			articuloTO.descripcionArticulo=cursor.getString(cursor.getColumnIndex("descripcion"));
			articuloTO.puntosCanje=cursor.getString(cursor.getColumnIndex("puntos"));
			lista.add(articuloTO);
		}
		
		cursor.close();
		
		return lista;
		
	}
	
	public List<ResumenTO> consultarMotivos(){
		//
		List<ResumenTO> lista = new ArrayList<ResumenTO>();
		String SQL = "SELECT codigo,descripcion FROM motivo";
		Cursor cursor = dbHelper.rawQuery(SQL,null);
		
		ResumenTO resumen;
		
		while(cursor.moveToNext()){
			resumen = new ResumenTO();
			resumen.valor=cursor.getString(cursor.getColumnIndex("codigo"));
			resumen.descripcion=cursor.getString(cursor.getColumnIndex("descripcion"));
			lista.add(resumen);
		}
		
		cursor.close();
		
		return lista;
		
	}
	public List<ProfitTO> consultarProfit(String anio,String mes,String codigoCliente,String codigoArticulo){
		
		List<ProfitTO> lista = new ArrayList<ProfitTO>();
		
		String SQL = "SELECT indicador,cajasFisica,margenActual,cajasFisicasFaltante,margenFaltante " +
					"FROM profit where anio = ?1 and mes = ?2 and codigocliente=?3 and codigoArticulo=?4";
		
		String[] args = new String[] {anio,mes,codigoCliente,codigoArticulo};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		ProfitTO profitTO;
		
		while(cursor.moveToNext()){
			profitTO=new ProfitTO();
			profitTO.indicador=cursor.getString(cursor.getColumnIndex("indicador"));
			profitTO.cajasFisica=cursor.getDouble(cursor.getColumnIndex("cajasFisica"));
			profitTO.margenActual=cursor.getDouble(cursor.getColumnIndex("margenActual"));
			profitTO.cajasFisicasFaltante=cursor.getDouble(cursor.getColumnIndex("cajasFisicasFaltante"));
			profitTO.margenFaltante=cursor.getDouble(cursor.getColumnIndex("margenFaltante"));
			
			lista.add(profitTO);
		}
		
		cursor.close();
		
		return lista;
	}
	
	
	public long insertEvaluacion(EvaluacionTO evaluacionTO){
		
		ContentValues values = new ContentValues();
		values.put("clienteCodigo", evaluacionTO.codigoCliente);
		values.put("activosLindley", evaluacionTO.activosLindley);
		values.put("codigoFe", evaluacionTO.codigoFe);
		values.put("usuario", evaluacionTO.usuarioCrea);
		values.put("usuarioCierre", evaluacionTO.usuarioCierre);
		values.put("fecha", evaluacionTO.fecha);
		values.put("hora", evaluacionTO.hora);
		
		values.put("fechaCierre", "0");
		values.put("horaCierre", "0");
		values.put("estado", evaluacionTO.estado);
		values.put("serverId", evaluacionTO.serverId);
		values.put("combosSS", evaluacionTO.combosSS);
		values.put("obsSS", evaluacionTO.observacionSS);
		values.put("combosMS", evaluacionTO.combosMS);
		values.put("obsMS", evaluacionTO.observacionMS);
		values.put("tieneCambios", ConstantesApp.EVALUACION_TIENE_CAMBIOS);
		values.put("tipoActivo", evaluacionTO.tipoActivoLindley);
		
		long id= dbHelper.insertOrThrow("evaluacion", values);
		
		evaluacionTO.id = id;
		return id;
	}
	
	
	public List<EvaluacionTO> List(String codigoCliente){
		
		List<EvaluacionTO> evaluaciones = new ArrayList<EvaluacionTO>();
		
		String SQL = "select * from evaluacion where clienteCodigo=?1 order by fecha desc,hora";
		String[] args = new String[] {String.valueOf(codigoCliente)};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		while(cursor.moveToNext()){
			EvaluacionTO evaluacionTO=new EvaluacionTO();
			
			evaluacionTO.id=cursor.getLong(cursor.getColumnIndex("id"));
			evaluacionTO.serverId=cursor.getLong(cursor.getColumnIndex("serverId"));
			evaluacionTO.codigoCliente = cursor.getString(cursor.getColumnIndex("clienteCodigo"));
			evaluacionTO.activosLindley = cursor.getString(cursor.getColumnIndex("activosLindley"));
			evaluacionTO.tipoActivoLindley = cursor.getString(cursor.getColumnIndex("tipoActivo"));
			evaluacionTO.codigoFe = cursor.getString(cursor.getColumnIndex("codigoFe"));
			evaluacionTO.usuarioCrea = cursor.getString(cursor.getColumnIndex("usuario"));
			evaluacionTO.fecha = cursor.getString(cursor.getColumnIndex("fecha"));
			evaluacionTO.hora = cursor.getString(cursor.getColumnIndex("hora"));
			evaluacionTO.fechaCierre = cursor.getString(cursor.getColumnIndex("fechaCierre"));
			evaluacionTO.horaCierre = cursor.getString(cursor.getColumnIndex("horaCierre"));
			evaluacionTO.estado = cursor.getString(cursor.getColumnIndex("estado"));
			
			evaluaciones.add(evaluacionTO);
		}
		
		cursor.close();
		
		return evaluaciones;
	}
	
	public EvaluacionTO getById(long id){
		
		EvaluacionTO evaluacionTO=null;
		
		String SQL = "select * from evaluacion where id = ?1";
		String[] args = new String[] {String.valueOf(id)};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		if(cursor.moveToNext()){
			evaluacionTO = new EvaluacionTO();
			evaluacionTO.id=id;
			evaluacionTO.codigoCliente = cursor.getString(cursor.getColumnIndex("clienteCodigo"));
			evaluacionTO.activosLindley = cursor.getString(cursor.getColumnIndex("activosLindley"));
			evaluacionTO.tipoActivoLindley = cursor.getString(cursor.getColumnIndex("tipoActivo"));
			evaluacionTO.codigoFe = cursor.getString(cursor.getColumnIndex("codigoFe"));
			evaluacionTO.usuarioCrea = cursor.getString(cursor.getColumnIndex("usuario"));
			evaluacionTO.fecha = cursor.getString(cursor.getColumnIndex("fecha"));
			evaluacionTO.hora = cursor.getString(cursor.getColumnIndex("hora"));
		}
		cursor.close();
		
		return evaluacionTO;
		
	}
	
	
	
	
	public long insertOportunidad(EvaluacionTO evaluacionTO,OportunidadTO oportunidadTO){
		ContentValues values = new ContentValues();
		
		values.put("evaluacionId", evaluacionTO.id);
		values.put("anio", periodoTO.anio);
		values.put("mes", periodoTO.mes);
		
		values.put("productoId", oportunidadTO.productoId);
		values.put("codigoProducto", oportunidadTO.codigoProducto);
		values.put("producto", oportunidadTO.descripcionProducto);
		
		values.put("concrecion", oportunidadTO.concrecion);
		values.put("concrecionActual", oportunidadTO.concrecionActual);
		values.put("concrecionCumple", oportunidadTO.concrecionCumple);
		
		values.put("sovi", oportunidadTO.sovi);
		values.put("soviActual", oportunidadTO.soviActual);
		values.put("soviCumple", oportunidadTO.soviCumple);
		
		values.put("respetaPrecio", oportunidadTO.cumplePrecio);
		values.put("respetaPrecioActual", oportunidadTO.cumplePrecioActual);
		values.put("respetaPrecioCumple", oportunidadTO.cumplePrecioCumple);
		
		values.put("numeroSabores", oportunidadTO.numeroSabores);
		values.put("numeroSaboresActual", oportunidadTO.numeroSaboresActual);
		values.put("numeroSaboresCumple", oportunidadTO.numeroSaboresCumple);
		
		values.put("puntosSugeridos", oportunidadTO.puntosSugeridos);
		values.put("puntosBonus", oportunidadTO.puntosBonus);
		values.put("puntosGanados", oportunidadTO.puntosGanados);
		
		values.put("fechaProceso", oportunidadTO.fechaOportunidad);
		values.put("legacy", oportunidadTO.codigoLegacy);
		values.put("codigoAccion", oportunidadTO.codigoAccionTrade);
		values.put("accion", oportunidadTO.accioneTrade);
		values.put("origen", oportunidadTO.origen);
		values.put("estado", oportunidadTO.estado);
		values.put("confirmacion", oportunidadTO.confirmacion);
		
		long id= dbHelper.insertOrThrow("evaluacion_oportunidad", values);
		oportunidadTO.oportunidadId = id;
		
		return id;
		
	}
	
	public long insertSKUPresentacion(EvaluacionTO evaluacionTO,SKUPresentacionTO skuPresentacionTO){
		ContentValues values = new ContentValues();
		
		values.put("evaluacionId", evaluacionTO.id);
	
		
		values.put("anio", periodoTO.anio);
		values.put("mes", periodoTO.mes);
		values.put("skuId", skuPresentacionTO.codigoSKU);
		values.put("sku", skuPresentacionTO.descripcionSKU);
		values.put("tipoAgrupacion", skuPresentacionTO.tipoAgrupacion);
		values.put("codigoVariable", skuPresentacionTO.codigoVariable);
		values.put("codfde", skuPresentacionTO.codigoFDE);
		values.put("marca", skuPresentacionTO.marcaActual);
		values.put("marcaCompromiso", skuPresentacionTO.marcaCompromiso);
		values.put("confirmacion", skuPresentacionTO.marcaCumplio);
		values.put("estado", skuPresentacionTO.estado);

		
		long id= dbHelper.insertOrThrow("evaluacion_sku_presentacion", values);
		
		return id;
		
	}
	
	public long insertOportunidadPosicion(EvaluacionTO evaluacionTO,PosicionCompromisoTO posicionCompromisoTO){
		
		ContentValues values = new ContentValues();
		values.put("evaluacionId", evaluacionTO.id);
		values.put("anio", periodoTO.anio);
		values.put("mes", periodoTO.mes);
		values.put("codigoVariable", posicionCompromisoTO.codigoVariable);
		values.put("puntosSugeridos", posicionCompromisoTO.puntosSugeridos);
		values.put("puntosGanados", posicionCompromisoTO.puntosGanados);
		values.put("puntosBonus", posicionCompromisoTO.puntosBonus);
		values.put("soviRed", posicionCompromisoTO.soviRed);
		values.put("soviMaximo", posicionCompromisoTO.soviMaximo);
		values.put("soviDiferencia", posicionCompromisoTO.soviDiferencia);
		values.put("tipoAgrupacion", posicionCompromisoTO.tipoAgrupacion);
		values.put("fechaEncuesta", evaluacionTO.fecha);
		values.put("activosLindley", evaluacionTO.activosLindley);
		values.put("fotoInicial", posicionCompromisoTO.fotoInicial);
		values.put("fotoFinal", posicionCompromisoTO.fotoFinal);
		values.put("observacion", posicionCompromisoTO.observacion);
		values.put("fechaCompromiso", evaluacionTO.fecha);
		values.put("confirmacion", posicionCompromisoTO.cumplio);
		values.put("origen", posicionCompromisoTO.origen);
		values.put("estado", posicionCompromisoTO.estado);
		values.put("confirmacion", posicionCompromisoTO.confirmacion);
		
		long id= dbHelper.insertOrThrow("evaluacion_posicion", values);
		
		posicionCompromisoTO.id = id;
		
		return id;
	}
	
	public long insertOportunidadPresentacion(EvaluacionTO evaluacionTO,PresentacionCompromisoTO presentacionCompromisoTO){
		
		ContentValues values = new ContentValues();
		values.put("evaluacionId", evaluacionTO.id);
		values.put("anio", periodoTO.anio);
		values.put("mes", periodoTO.mes);
		values.put("tipoAgrupacion", presentacionCompromisoTO.tipoAgrupacion);
		values.put("codfde", presentacionCompromisoTO.codfde);
		values.put("codigoVariable", presentacionCompromisoTO.codigoVariable);
		values.put("fechaEncuesta", evaluacionTO.fecha);
		values.put("puntosSugeridos", presentacionCompromisoTO.puntosSugeridos);
		values.put("puntosGanados", presentacionCompromisoTO.puntosGanados);
		values.put("puntosBonus", presentacionCompromisoTO.puntosBonus);
		values.put("fechaCompromiso", presentacionCompromisoTO.fechaCompromiso);
		values.put("confirmacion", presentacionCompromisoTO.cumplio);
		values.put("origen", presentacionCompromisoTO.origen);
		values.put("estado", presentacionCompromisoTO.estado);
		long id= dbHelper.insertOrThrow("evaluacion_presentacion", values);
		
		presentacionCompromisoTO.id = id;
		
		return id;
	}

	public List<Integer> listarPuntosInventario(){
		List<Integer> puntos = new ArrayList<Integer>();
		String SQL = "select puntos from punto where cdarr = ?1 and tppro = ?2";
		String[] args = new String[] {ConstantesApp.TIPO_AGRUPRACION_INVENTARIO,ConstantesApp.VARIABLE_RED_PRECIO_MERCADO};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		while(cursor.moveToNext()){
			puntos.add(cursor.getInt(cursor.getColumnIndex("puntos")));
		}
		
		cursor.close();
		return puntos;
	}
	
	public List<Integer> listarPuntos(String tppro,String tipoAgrupacion,String variableRed,String activosLindley){
		List<Integer> puntos = new ArrayList<Integer>();
		String SQL;
		String[] args;
		if(activosLindley!=null){
			SQL = "select puntos from punto where tppro = ?1 and cdarr = ?2 and cdvar=?3 and cnd01=?4";
			args = new String[] {tppro,tipoAgrupacion,variableRed,activosLindley};
		}else{
			SQL = "select puntos from punto where tppro = ?1 and cdarr = ?2 and cdvar=?3";
			args = new String[] {tppro,tipoAgrupacion,variableRed};
		}
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		while(cursor.moveToNext()){
			puntos.add(cursor.getInt(cursor.getColumnIndex("puntos")));
		}
		
		cursor.close();
		return puntos;
	}
}
