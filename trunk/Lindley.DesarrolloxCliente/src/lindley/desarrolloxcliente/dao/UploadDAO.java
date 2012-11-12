package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.OportunidadTO;
import lindley.desarrolloxcliente.to.upload.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.upload.PosicionTO;
import lindley.desarrolloxcliente.to.upload.PresentacionTO;
import lindley.desarrolloxcliente.to.upload.SkuTO;
import net.msonic.lib.DBHelper;

import android.database.Cursor;

import com.google.inject.Inject;

public class UploadDAO {

	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
	
	
	
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
		
		String SQL = "select count(*) as cantidad from evaluacion where clienteCodigo=?1";
		String[] args = new String[] {String.valueOf(clienteCodigo)};
		
		long cantidad=0;
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		if(cursor.moveToNext()){
			cantidad= cursor.getLong(cursor.getColumnIndex("cantidad"));
		}
		cursor.close();
		return cantidad;
	}

	public EvaluacionTO listarEvaluacionById(long id){
		
		
		String SQL = "select id,clienteCodigo,activosLindley,codigoFe,usuario,fecha,hora,usuarioCierre,fechaCierre,horaCierre,estado,serverId,combosSS,combosMS,obsSS,obsMS " +
					 "from evaluacion where id=?1";
		
		String[] args = new String[] {String.valueOf(id)};
		
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		EvaluacionTO evaluacionTO=null;
		
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
			
			evaluacionTO.estado =  cursor.getString(cursor.getColumnIndex("estado"));
			evaluacionTO.serverId =  cursor.getLong(cursor.getColumnIndex("serverId"));
			
			
		}
		cursor.close();
		
		
		listarOportunidades(evaluacionTO);
		listarPosicion(evaluacionTO);
		listarPosicionCompromiso(evaluacionTO);
		listarPresentacion(evaluacionTO);
		listarSku(evaluacionTO);
		
		return evaluacionTO;
		
	}
	
	public List<EvaluacionTO> listarEvaluaciones(int limit){
		List<EvaluacionTO> evaluaciones = new ArrayList<EvaluacionTO>();
		
		String SQL = "select id,clienteCodigo,activosLindley,codigoFe,usuario,fecha,hora,usuarioCierre,fechaCierre,horaCierre,estado,serverId,combosSS,combosMS,obsSS,obsMS " +
					 "from evaluacion where tieneCambios=?1 limit " + String.valueOf(limit);
		
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
			
			evaluacionTO.estado =  cursor.getString(cursor.getColumnIndex("estado"));
			evaluacionTO.serverId =  cursor.getLong(cursor.getColumnIndex("serverId"));
			
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
							"numeroSaboresCumple,puntosSugeridos,puntosBonus,puntosGanados,fechaProceso,legacy,confirmacion,origen,estado " +
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
			
			evaluacionTO.oportunidades.add(oportunidadTO);
		}
		
		cursor.close();
	}
	
	public void listarPosicionCompromiso(EvaluacionTO evaluacionTO){
		String SQL = "select id,evaluacionId,tipoAgrupacion,codigoVariable,orden,observacion,estado " +
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
				evaluacionTO.posiciones.get(0).compromisos.add(posicionCompromisoTO);
			}
		}
		cursor.close();
	}
	public void listarPosicion(EvaluacionTO evaluacionTO){
		String SQL = "select id,evaluacionId,anio,mes,codigoVariable,soviRed,soviMaximo,soviDiferencia," +
					"puntosSugeridos,puntosGanados,puntosBonus,fotoInicial,fotoFinal,activosLindley," +
					"observacion,fechaCompromiso,confirmacion,origen,estado,tipoAgrupacion,fechaEncuesta " +
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
			evaluacionTO.posiciones.add(posicionTO);
		}
		cursor.close();
	}
	
	public void listarPresentacion(EvaluacionTO evaluacionTO){
		String SQL = "select id,evaluacionId,anio,mes,tipoAgrupacion,codfde,codigoVariable,fechaEncuesta," +
				"puntosSugeridos,puntosGanados,puntosBonus,fechaCompromiso,confirmacion," +
				"origen,estado " +
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
			evaluacionTO.presentaciones.add(presentacionTO);
		}
		cursor.close();
	}
	
	public void listarSku(EvaluacionTO evaluacionTO){
		String SQL = "select id,evaluacionId,anio,mes,tipoAgrupacion,codfde,codigoVariable,skuId," +
				"sku,marca,marcaCompromiso,confirmacion,estado " +
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
			evaluacionTO.skus.add(skuTO);
		}
		
		
		cursor.close();
	}

}
