package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.ResumenValueTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.OportunidadTO;
import lindley.desarrolloxcliente.to.upload.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.upload.PosicionTO;
import lindley.desarrolloxcliente.to.upload.PresentacionTO;
import lindley.desarrolloxcliente.to.upload.SkuTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class UploadDAO {

	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
	
	public void updateEvaluacionServerId(long id,long serverId){
		
		
		ContentValues values = new ContentValues();
		
		values.put("serverId", serverId);
		values.put("tieneCambios", ConstantesApp.EVALUACION_NO_TIENE_CAMBIOS);
		dbHelper.update("evaluacion", values, "id=?", new String[]{String.valueOf(id)});
	
	}
	
	
	public void deleteEvaluacion(long id){
		dbHelper.delete("evaluacion", "id=?",new String[]{String.valueOf(id)});
		dbHelper.delete("evaluacion_oportunidad", "evaluacionId=?",new String[]{String.valueOf(id)});
		dbHelper.delete("evaluacion_posicion", "evaluacionId=?",new String[]{String.valueOf(id)});
		dbHelper.delete("evaluacion_presentacion", "evaluacionId=?",new String[]{String.valueOf(id)});
		dbHelper.delete("evaluacion_sku_presentacion", "evaluacionId=?",new String[]{String.valueOf(id)});
	}
	
	public long getCantidadEvaluaciones(){
		
		String SQL = "select count(*) as cantidad from evaluacion where tieneCambios=1";
		long cantidad=0;
		Cursor cursor = dbHelper.rawQuery(SQL,null);
		
		if(cursor.moveToNext()){
			cantidad= cursor.getLong(cursor.getColumnIndex("cantidad"));
		}
		cursor.close();
		return cantidad;
	}
	
	public long getCantidadEvaluacionesAbiertas(String clienteCodigo){
		
		String SQL = "select count(*) as cantidad from evaluacion where clienteCodigo=?1 and estado = 'A'";
		String[] args = new String[] {String.valueOf(clienteCodigo)};
		
		long cantidad=0;
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		if(cursor.moveToNext()){
			cantidad= cursor.getLong(cursor.getColumnIndex("cantidad"));
		}
		cursor.close();
		return cantidad;
	}
	
	public List<ResumenValueTO> resumenEvaluacion(long id){
		List<ResumenValueTO> valores = new ArrayList<ResumenValueTO>();
		
		String SQL = "select '1.-Inventario' as Descripcion,Sum(puntosSugeridos) as puntosSugeridos,Sum(puntosGanados) as puntosGanados from evaluacion_oportunidad  where evaluacionId = ?1 " +
						"union all " +
						"select '2.-Posición' as Descripcion,Sum(puntosSugeridos) as puntosSugeridos,Sum(puntosGanados) as puntosGanados from evaluacion_posicion  where evaluacionId = ?1 " + 
						"union all " +
						"select '3.-Presentación' as Descripcion,Sum(puntosSugeridos) as puntosSugeridos,Sum(puntosGanados) as puntosGanados from evaluacion_presentacion  where evaluacionId = ?1 ";
		
		String[] args = new String[] {String.valueOf(id)};
		
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		ResumenValueTO resumenValueTO;
		long totalSugeridos=0,totalGanados=0;
		
		
		resumenValueTO = new ResumenValueTO();
		resumenValueTO.descripcion="VARIABLE RED";
		resumenValueTO.valor="PTOS SUGERIDOS";
		resumenValueTO.valor2="PTOS GANADOS";
		valores.add(resumenValueTO);
		
		while(cursor.moveToNext()){
			resumenValueTO = new ResumenValueTO();
			resumenValueTO.descripcion =  cursor.getString(cursor.getColumnIndex("Descripcion"));
			resumenValueTO.valor =  cursor.getString(cursor.getColumnIndex("puntosSugeridos"));
			resumenValueTO.valor2 =  cursor.getString(cursor.getColumnIndex("puntosGanados"));
			totalSugeridos=totalSugeridos + cursor.getLong(cursor.getColumnIndex("puntosSugeridos"));
			totalGanados=totalGanados + cursor.getLong(cursor.getColumnIndex("puntosGanados"));
			valores.add(resumenValueTO);
		}
		cursor.close();
		
		resumenValueTO = new ResumenValueTO();
		resumenValueTO.descripcion="TOTAL PUNTAJE";
		resumenValueTO.valor=String.valueOf(totalSugeridos);
		resumenValueTO.valor2=String.valueOf(totalGanados);
		valores.add(resumenValueTO);
		return valores;
	}
	
	public EvaluacionTO listarEvaluacionById(long id){
		
		
		String SQL = "select id,clienteCodigo,activosLindley,codigoFe,usuario,fecha,hora,usuarioCierre,fechaCierre,horaCierre,estado,serverId,combosSS,combosMS,obsSS,obsMS,tieneCambios,motivoId,motivo,tipoActivo " +
					 "from evaluacion where id=?1";
		
		String[] args = new String[] {String.valueOf(id)};
		
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		EvaluacionTO evaluacionTO=null;
		
		while(cursor.moveToNext()){
			evaluacionTO = new EvaluacionTO();
			evaluacionTO.id = cursor.getLong(cursor.getColumnIndex("id"));
			evaluacionTO.codigoCliente =  cursor.getString(cursor.getColumnIndex("clienteCodigo"));
			evaluacionTO.activosLindley =  cursor.getString(cursor.getColumnIndex("activosLindley"));
			evaluacionTO.tipoActivoLindley =  cursor.getString(cursor.getColumnIndex("tipoActivo"));
			evaluacionTO.codigoFDE =  cursor.getString(cursor.getColumnIndex("codigoFe"));
			evaluacionTO.usuarioCreacion =  cursor.getString(cursor.getColumnIndex("usuario"));
			evaluacionTO.fechaCreacion =  cursor.getString(cursor.getColumnIndex("fecha"));
			evaluacionTO.horaCreacion =  cursor.getString(cursor.getColumnIndex("hora"));
			
			evaluacionTO.usuarioCierre =  cursor.getString(cursor.getColumnIndex("usuarioCierre"));
			evaluacionTO.fechaCierre =  cursor.getString(cursor.getColumnIndex("fechaCierre"));
			evaluacionTO.horaCierre =  cursor.getString(cursor.getColumnIndex("horaCierre"));
			
			evaluacionTO.comboSS =  cursor.getString(cursor.getColumnIndex("combosSS"));
			evaluacionTO.comboMS =  cursor.getString(cursor.getColumnIndex("combosMS"));
			evaluacionTO.observacionSS =  cursor.getString(cursor.getColumnIndex("obsSS"));
			evaluacionTO.observacionMS =  cursor.getString(cursor.getColumnIndex("obsMS"));
			
			evaluacionTO.horaCierre =  cursor.getString(cursor.getColumnIndex("horaCierre"));
			
			evaluacionTO.estado =  cursor.getString(cursor.getColumnIndex("estado"));
			evaluacionTO.serverId =  cursor.getLong(cursor.getColumnIndex("serverId"));
			evaluacionTO.tieneCambio=cursor.getInt(cursor.getColumnIndex("tieneCambios"));
			evaluacionTO.motivoId =  cursor.getString(cursor.getColumnIndex("motivoId"));
			evaluacionTO.motivo =  cursor.getString(cursor.getColumnIndex("motivo"));
			
		}
		cursor.close();
		
		
		listarOportunidades(evaluacionTO);
		listarPosicion(evaluacionTO);
		listarPosicionCompromiso(evaluacionTO);
		listarPresentacion(evaluacionTO);
		listarSku(evaluacionTO);
		
		return evaluacionTO;
		
	}
	
	
	public List<EvaluacionTO> listarEvaluaciones(){
		
		List<EvaluacionTO> evaluaciones = new ArrayList<EvaluacionTO>();
		
		String SQL = "select c.nombre,e.id,e.clienteCodigo,e.activosLindley,e.codigoFe,e.usuario,e.fecha,e.hora,e.usuarioCierre,e.fechaCierre,e.horaCierre,e.estado,e.serverId," +
					"e.combosSS,e.combosMS,e.obsSS,e.obsMS,e.motivoId,e.motivo,e.tieneCambios,e.tipoActivo " +
					 "from evaluacion e inner join cliente c on e.clienteCodigo = c.codigo where tieneCambios=?1";
		
		String[] args = new String[] {String.valueOf(ConstantesApp.EVALUACION_TIENE_CAMBIOS)};
		
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		EvaluacionTO evaluacionTO;
		
		while(cursor.moveToNext()){
			evaluacionTO = new EvaluacionTO();
			evaluacionTO.id = cursor.getLong(cursor.getColumnIndex("id"));
			evaluacionTO.codigoCliente =  cursor.getString(cursor.getColumnIndex("clienteCodigo"));
			evaluacionTO.activosLindley =  cursor.getString(cursor.getColumnIndex("activosLindley"));
			evaluacionTO.codigoFDE =  cursor.getString(cursor.getColumnIndex("codigoFe"));
			evaluacionTO.usuarioCreacion =  cursor.getString(cursor.getColumnIndex("usuario"));
			evaluacionTO.fechaCreacion =  cursor.getString(cursor.getColumnIndex("fecha"));
			evaluacionTO.horaCreacion =  cursor.getString(cursor.getColumnIndex("hora"));
			
			evaluacionTO.usuarioCierre =  cursor.getString(cursor.getColumnIndex("usuarioCierre"));
			evaluacionTO.fechaCierre =  cursor.getString(cursor.getColumnIndex("fechaCierre"));
			evaluacionTO.horaCierre =  cursor.getString(cursor.getColumnIndex("horaCierre"));
			
			evaluacionTO.comboSS =  cursor.getString(cursor.getColumnIndex("combosSS"));
			evaluacionTO.comboMS =  cursor.getString(cursor.getColumnIndex("combosMS"));
			evaluacionTO.observacionSS =  cursor.getString(cursor.getColumnIndex("obsSS"));
			evaluacionTO.observacionMS =  cursor.getString(cursor.getColumnIndex("obsMS"));
			
			evaluacionTO.horaCierre =  cursor.getString(cursor.getColumnIndex("horaCierre"));
			evaluacionTO.motivo =  cursor.getString(cursor.getColumnIndex("motivo"));
			evaluacionTO.motivoId =  cursor.getString(cursor.getColumnIndex("motivoId"));
			evaluacionTO.estado =  cursor.getString(cursor.getColumnIndex("estado"));
			evaluacionTO.serverId =  cursor.getLong(cursor.getColumnIndex("serverId"));
			evaluacionTO.cliente = cursor.getString(cursor.getColumnIndex("nombre"));
			evaluacionTO.tipoActivoLindley = cursor.getString(cursor.getColumnIndex("tipoActivo"));
			evaluacionTO.tieneCambio = cursor.getInt(cursor.getColumnIndex("tieneCambios"));
			
			evaluaciones.add(evaluacionTO);
		}
		cursor.close();
		
		return evaluaciones;
	}
	
	public List<EvaluacionTO> listarEvaluaciones(int limit){
		List<EvaluacionTO> evaluaciones = new ArrayList<EvaluacionTO>();
		
		String SQL = "select c.nombre,e.id,e.clienteCodigo,e.activosLindley,e.codigoFe,e.usuario,e.fecha,e.hora,e.usuarioCierre,e.fechaCierre,e.horaCierre,e.estado,e.serverId," +
					"e.combosSS,e.combosMS,e.obsSS,e.obsMS,e.motivoId,e.motivo,e.proceso,e.tipoActivo " +
					 "from evaluacion e inner join cliente c on e.clienteCodigo = c.codigo where tieneCambios=?1 limit " + String.valueOf(limit);
		
		String[] args = new String[] {String.valueOf(ConstantesApp.EVALUACION_TIENE_CAMBIOS)};
		
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		EvaluacionTO evaluacionTO;
		
		while(cursor.moveToNext()){
			evaluacionTO = new EvaluacionTO();
			evaluacionTO.id = cursor.getLong(cursor.getColumnIndex("id"));
			evaluacionTO.codigoCliente =  cursor.getString(cursor.getColumnIndex("clienteCodigo"));
			evaluacionTO.activosLindley =  cursor.getString(cursor.getColumnIndex("activosLindley"));
			evaluacionTO.tipoActivoLindley = cursor.getString(cursor.getColumnIndex("tipoActivo"));
			evaluacionTO.codigoFDE =  cursor.getString(cursor.getColumnIndex("codigoFe"));
			evaluacionTO.usuarioCreacion =  cursor.getString(cursor.getColumnIndex("usuario"));
			evaluacionTO.fechaCreacion =  cursor.getString(cursor.getColumnIndex("fecha"));
			evaluacionTO.horaCreacion =  cursor.getString(cursor.getColumnIndex("hora"));
			
			evaluacionTO.usuarioCierre =  cursor.getString(cursor.getColumnIndex("usuarioCierre"));
			evaluacionTO.fechaCierre =  cursor.getString(cursor.getColumnIndex("fechaCierre"));
			evaluacionTO.horaCierre =  cursor.getString(cursor.getColumnIndex("horaCierre"));
			
			evaluacionTO.comboSS =  cursor.getString(cursor.getColumnIndex("combosSS"));
			evaluacionTO.comboMS =  cursor.getString(cursor.getColumnIndex("combosMS"));
			evaluacionTO.observacionSS =  cursor.getString(cursor.getColumnIndex("obsSS"));
			evaluacionTO.observacionMS =  cursor.getString(cursor.getColumnIndex("obsMS"));
			
			evaluacionTO.horaCierre =  cursor.getString(cursor.getColumnIndex("horaCierre"));
			evaluacionTO.motivo =  cursor.getString(cursor.getColumnIndex("motivo"));
			evaluacionTO.motivoId =  cursor.getString(cursor.getColumnIndex("motivoId"));
			evaluacionTO.estado =  cursor.getString(cursor.getColumnIndex("estado"));
			evaluacionTO.serverId =  cursor.getLong(cursor.getColumnIndex("serverId"));
			evaluacionTO.cliente = cursor.getString(cursor.getColumnIndex("nombre"));
			evaluacionTO.proceso = cursor.getInt(cursor.getColumnIndex("proceso"));
			
			evaluaciones.add(evaluacionTO);
		}
		cursor.close();
		
		for (EvaluacionTO evaluacionTempTO : evaluaciones) {
			listarOportunidades(evaluacionTempTO);
		}
		
		for (EvaluacionTO evaluacionTempTO : evaluaciones) {
			listarPosicion(evaluacionTempTO);
		}
		
		for (EvaluacionTO evaluacionTempTO : evaluaciones) {
			listarPosicionCompromiso(evaluacionTempTO);
		}
		
		for (EvaluacionTO evaluacionTempTO : evaluaciones) {
			listarPresentacion(evaluacionTempTO);
		}
		
		for (EvaluacionTO evaluacionTempTO : evaluaciones) {
			listarSku(evaluacionTempTO);
		}
		return evaluaciones;
		
	}
	
	public void listarOportunidades(EvaluacionTO evaluacionTO){
		
		String SQL = "select id,evaluacionId,anio,mes, codigoProducto,producto," +
							"concrecion,concrecionActual,concrecionCumple,sovi,soviActual,soviCumple," +
							"respetaPrecio,respetaPrecioActual,respetaPrecioCumple,numeroSabores,numeroSaboresActual,codigoAccion,accion, " +
							"numeroSaboresCumple,puntosSugeridos,puntosBonus,puntosGanados,fechaProceso,legacy,confirmacion,origen,estado,proceso " +
							"from evaluacion_oportunidad " +
							"where evaluacionId = ?1";
		
		String[] args = new String[] {String.valueOf(evaluacionTO.id)};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		OportunidadTO oportunidadTO;
		
		while(cursor.moveToNext()){
			oportunidadTO = new OportunidadTO();
			oportunidadTO.anio =  cursor.getInt(cursor.getColumnIndex("anio"));
			oportunidadTO.mes =  cursor.getInt(cursor.getColumnIndex("mes"));
			oportunidadTO.codigoArticulo =  cursor.getString(cursor.getColumnIndex("codigoProducto"));
			oportunidadTO.articulo =  cursor.getString(cursor.getColumnIndex("producto"));
			
			oportunidadTO.concrecion =  cursor.getString(cursor.getColumnIndex("concrecion"));
			oportunidadTO.concrecionActual =  cursor.getString(cursor.getColumnIndex("concrecionActual"));
			oportunidadTO.concrecionCumple =  cursor.getString(cursor.getColumnIndex("concrecionCumple"));
			
			oportunidadTO.sovi =  cursor.getString(cursor.getColumnIndex("sovi"));
			oportunidadTO.soviActual =  cursor.getString(cursor.getColumnIndex("soviActual"));
			oportunidadTO.soviCumple =  cursor.getString(cursor.getColumnIndex("soviCumple"));
			
			oportunidadTO.respetoPrecio =  cursor.getString(cursor.getColumnIndex("respetaPrecio"));
			oportunidadTO.respetoPrecioActual =  cursor.getString(cursor.getColumnIndex("respetaPrecioActual"));
			oportunidadTO.respetoPrecioCumple =  cursor.getString(cursor.getColumnIndex("respetaPrecioCumple"));
			
			oportunidadTO.numeroSabores =  cursor.getString(cursor.getColumnIndex("numeroSabores"));
			oportunidadTO.numeroSaboresActual =  cursor.getString(cursor.getColumnIndex("numeroSaboresActual"));
			oportunidadTO.numeroSaboresCumple =  cursor.getString(cursor.getColumnIndex("numeroSaboresCumple"));
			
			oportunidadTO.codigoAccionTrade =  cursor.getString(cursor.getColumnIndex("codigoAccion"));
			oportunidadTO.accionTrade =  cursor.getString(cursor.getColumnIndex("accion"));
			
			oportunidadTO.puntosSugeridos =  cursor.getString(cursor.getColumnIndex("puntosSugeridos"));
			oportunidadTO.puntosBonus =  cursor.getString(cursor.getColumnIndex("puntosBonus"));
			oportunidadTO.puntosGanados =  cursor.getString(cursor.getColumnIndex("puntosGanados"));
			
			oportunidadTO.fechaCompromiso =  cursor.getString(cursor.getColumnIndex("fechaProceso"));
			
			oportunidadTO.confirmacion =  cursor.getString(cursor.getColumnIndex("confirmacion"));
			oportunidadTO.origen =  cursor.getString(cursor.getColumnIndex("origen"));
			oportunidadTO.estado =  cursor.getString(cursor.getColumnIndex("estado"));
			oportunidadTO.legacy =  cursor.getString(cursor.getColumnIndex("legacy"));
			oportunidadTO.proceso = cursor.getInt(cursor.getColumnIndex("proceso"));
			
			evaluacionTO.oportunidades.add(oportunidadTO);
		}
		
		cursor.close();
	}
	
	public void listarPosicionCompromiso(EvaluacionTO evaluacionTO){
		String SQL = "select id,evaluacionId,tipoAgrupacion,codigoVariable,orden,observacion,estado,proceso " +
					" from evaluacion_posicion_compromiso " +
					"where evaluacionId = ?1";
		
		String[] args = new String[] {String.valueOf(evaluacionTO.id)};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		PosicionCompromisoTO posicionCompromisoTO;
		while(cursor.moveToNext()){
			posicionCompromisoTO = new PosicionCompromisoTO();
			
			if(evaluacionTO.posiciones.get(0)!=null){
				posicionCompromisoTO.id = cursor.getLong(cursor.getColumnIndex("id"));
				posicionCompromisoTO.tipoAgrupacion =  cursor.getString(cursor.getColumnIndex("tipoAgrupacion"));
				posicionCompromisoTO.codigoVariable =  cursor.getString(cursor.getColumnIndex("codigoVariable"));
				posicionCompromisoTO.orden = cursor.getInt(cursor.getColumnIndex("orden"));
				posicionCompromisoTO.observacion =  cursor.getString(cursor.getColumnIndex("observacion"));
				posicionCompromisoTO.estado =  cursor.getString(cursor.getColumnIndex("estado"));
				posicionCompromisoTO.proceso = cursor.getInt(cursor.getColumnIndex("proceso"));
				evaluacionTO.posiciones.get(0).compromisos.add(posicionCompromisoTO);
			}
		}
		cursor.close();
	}
	public void listarPosicion(EvaluacionTO evaluacionTO){
		String SQL = "select id,evaluacionId,anio,mes,codigoVariable,soviRed,soviMaximo,soviDiferencia," +
					"puntosSugeridos,puntosGanados,puntosBonus,fotoInicial,fotoFinal,activosLindley," +
					"observacion,fechaCompromiso,confirmacion,origen,estado,tipoAgrupacion,fechaEncuesta,proceso " +
					"from evaluacion_posicion " +
					"where evaluacionId = ?1";

		String[] args = new String[] {String.valueOf(evaluacionTO.id)};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		PosicionTO posicionTO;
		while(cursor.moveToNext()){
			posicionTO = new PosicionTO();
			posicionTO.anio =  cursor.getInt(cursor.getColumnIndex("anio"));
			posicionTO.mes =  cursor.getInt(cursor.getColumnIndex("mes"));
			posicionTO.codigoVariable =  cursor.getString(cursor.getColumnIndex("codigoVariable"));
			posicionTO.sovir =  cursor.getString(cursor.getColumnIndex("soviRed"));
			posicionTO.sovirMaximo =  cursor.getString(cursor.getColumnIndex("soviMaximo"));
			posicionTO.sovirDiferencia =  cursor.getString(cursor.getColumnIndex("soviDiferencia"));
			posicionTO.puntosSugeridos =  cursor.getString(cursor.getColumnIndex("puntosSugeridos"));
			posicionTO.puntosGanados =  cursor.getString(cursor.getColumnIndex("puntosGanados"));
			posicionTO.puntosBonus =  cursor.getString(cursor.getColumnIndex("puntosBonus"));
			posicionTO.fotoInicial =  cursor.getString(cursor.getColumnIndex("fotoInicial"));
			posicionTO.fotoFinal =  cursor.getString(cursor.getColumnIndex("fotoFinal"));
			posicionTO.activosLindley =  cursor.getString(cursor.getColumnIndex("activosLindley"));
			posicionTO.observacion =  cursor.getString(cursor.getColumnIndex("observacion"));
			posicionTO.fechaCompromiso =  cursor.getString(cursor.getColumnIndex("fechaCompromiso"));
			posicionTO.tipoAgrupacion =  cursor.getString(cursor.getColumnIndex("tipoAgrupacion"));
			posicionTO.fechaEncuesta=cursor.getString(cursor.getColumnIndex("fechaEncuesta"));
			posicionTO.confirmacion =  cursor.getString(cursor.getColumnIndex("confirmacion"));
			posicionTO.origen =  cursor.getString(cursor.getColumnIndex("origen"));
			posicionTO.estado =  cursor.getString(cursor.getColumnIndex("estado"));
			posicionTO.proceso = cursor.getInt(cursor.getColumnIndex("proceso"));
			evaluacionTO.posiciones.add(posicionTO);
		}
		cursor.close();
	}
	
	public void listarPresentacion(EvaluacionTO evaluacionTO){
		String SQL = "select id,evaluacionId,anio,mes,tipoAgrupacion,codfde,codigoVariable,fechaEncuesta," +
				"puntosSugeridos,puntosGanados,puntosBonus,fechaCompromiso,confirmacion," +
				"origen,estado,proceso,tipoMarcado " +
				"from evaluacion_presentacion " +
				"where evaluacionId = ?1";
		
		String[] args = new String[] {String.valueOf(evaluacionTO.id)};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		PresentacionTO presentacionTO;
		while(cursor.moveToNext()){
			presentacionTO = new PresentacionTO();
			presentacionTO.anio =  cursor.getInt(cursor.getColumnIndex("anio"));
			presentacionTO.mes =  cursor.getInt(cursor.getColumnIndex("mes"));
			presentacionTO.tipoAgrupacion =  cursor.getString(cursor.getColumnIndex("tipoAgrupacion"));
			presentacionTO.codigoFDE =  cursor.getString(cursor.getColumnIndex("codfde"));
			presentacionTO.codigoVariable =  cursor.getString(cursor.getColumnIndex("codigoVariable"));
			presentacionTO.fechaEncuesta =  cursor.getString(cursor.getColumnIndex("fechaEncuesta"));
			presentacionTO.puntosSugeridos =  cursor.getString(cursor.getColumnIndex("puntosSugeridos"));
			presentacionTO.puntosGanados =  cursor.getString(cursor.getColumnIndex("puntosGanados"));
			presentacionTO.puntosBonus =  cursor.getString(cursor.getColumnIndex("puntosBonus"));
			presentacionTO.fechaCompromiso =  cursor.getString(cursor.getColumnIndex("fechaCompromiso"));
			presentacionTO.confirmacion =  cursor.getString(cursor.getColumnIndex("confirmacion"));
			presentacionTO.origen =  cursor.getString(cursor.getColumnIndex("origen"));
			presentacionTO.estado =  cursor.getString(cursor.getColumnIndex("estado"));
			presentacionTO.proceso = cursor.getInt(cursor.getColumnIndex("proceso"));
			presentacionTO.tipoMarcado = cursor.getString(cursor.getColumnIndex("tipoMarcado"));
			evaluacionTO.presentaciones.add(presentacionTO);
		}
		cursor.close();
	}
	
	public void listarSku(EvaluacionTO evaluacionTO){
		String SQL = "select id,evaluacionId,anio,mes,tipoAgrupacion,codfde,codigoVariable,skuId," +
				"sku,marca,marcaCompromiso,confirmacion,estado,proceso " +
				"from evaluacion_sku_presentacion " +
				"where evaluacionId = ?1";

		String[] args = new String[] {String.valueOf(evaluacionTO.id)};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		SkuTO skuTO;
		while(cursor.moveToNext()){
			skuTO = new SkuTO();
			skuTO.anio =  cursor.getInt(cursor.getColumnIndex("anio"));
			skuTO.mes =  cursor.getInt(cursor.getColumnIndex("mes"));
			skuTO.tipoAgrupacion =  cursor.getString(cursor.getColumnIndex("tipoAgrupacion"));
			skuTO.codigoFDE =  cursor.getString(cursor.getColumnIndex("codfde"));
			skuTO.codigoVariable =  cursor.getString(cursor.getColumnIndex("codigoVariable"));
			skuTO.codigoSKU =  cursor.getString(cursor.getColumnIndex("skuId"));
			skuTO.descripcionSKU =  cursor.getString(cursor.getColumnIndex("sku"));
			skuTO.marcaActual =  cursor.getString(cursor.getColumnIndex("marca"));
			skuTO.marcaCompromiso =  cursor.getString(cursor.getColumnIndex("marcaCompromiso"));
			skuTO.marcaCumplio =  cursor.getString(cursor.getColumnIndex("confirmacion"));
			skuTO.estado =  cursor.getString(cursor.getColumnIndex("estado"));
			skuTO.proceso = cursor.getInt(cursor.getColumnIndex("proceso"));
			evaluacionTO.skus.add(skuTO);
		}
		
		
		cursor.close();
	}

}
