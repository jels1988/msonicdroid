package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.List;

import com.google.inject.Inject;

public class EvaluacionDAO {

	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
	public long insertEvaluacion(EvaluacionTO evaluacionTO){
		
		ContentValues values = new ContentValues();
		values.put("clienteCodigo", evaluacionTO.codigoCliente);
		values.put("activosLindley", evaluacionTO.activosLindley);
		values.put("codigoFe", evaluacionTO.codigoFe);
		values.put("usuario", evaluacionTO.codigoUsuario);
		values.put("fecha", evaluacionTO.fecha);
		values.put("hora", evaluacionTO.hora);
		
		values.put("fechaCierre", evaluacionTO.fecha);
		values.put("horaCierre", evaluacionTO.hora);
		values.put("estado", evaluacionTO.estado);
		
		long id= dbHelper.insertOrThrow("evaluacion", values);
		
		evaluacionTO.evaluacionId = id;
		return id;
	}
	
	
	public List<EvaluacionTO> List(String codigoCliente){
		
		List<EvaluacionTO> evaluaciones = new ArrayList<EvaluacionTO>();
		
		String SQL = "select * from evaluacion where clienteCodigo=?1";
		String[] args = new String[] {String.valueOf(codigoCliente)};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		while(cursor.moveToNext()){
			EvaluacionTO evaluacionTO=new EvaluacionTO();
			
			evaluacionTO.evaluacionId=cursor.getLong(cursor.getColumnIndex("id"));
			evaluacionTO.serverId=cursor.getLong(cursor.getColumnIndex("serverId"));
			evaluacionTO.codigoCliente = cursor.getString(cursor.getColumnIndex("clienteCodigo"));
			evaluacionTO.activosLindley = cursor.getString(cursor.getColumnIndex("activosLindley"));
			evaluacionTO.codigoFe = cursor.getString(cursor.getColumnIndex("codigoFe"));
			evaluacionTO.codigoUsuario = cursor.getString(cursor.getColumnIndex("usuario"));
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
	
	public EvaluacionTO GetById(long id){
		
		EvaluacionTO evaluacionTO=null;
		
		String SQL = "select * from evaluacion where id = ?1";
		String[] args = new String[] {String.valueOf(id)};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		if(cursor.moveToNext()){
			evaluacionTO = new EvaluacionTO();
			evaluacionTO.evaluacionId=id;
			evaluacionTO.codigoCliente = cursor.getString(cursor.getColumnIndex("clienteCodigo"));
			evaluacionTO.activosLindley = cursor.getString(cursor.getColumnIndex("activosLindley"));
			evaluacionTO.codigoFe = cursor.getString(cursor.getColumnIndex("codigoFe"));
			evaluacionTO.codigoUsuario = cursor.getString(cursor.getColumnIndex("usuario"));
			evaluacionTO.fecha = cursor.getString(cursor.getColumnIndex("fecha"));
			evaluacionTO.hora = cursor.getString(cursor.getColumnIndex("hora"));
		}
		cursor.close();
		
		return evaluacionTO;
		
	}
	
	public void GetOportunidades(EvaluacionTO evaluacionTO){
		if(evaluacionTO!=null){
			evaluacionTO.oportunidades = new ArrayList<OportunidadTO>();
			
			String SQL = "select * from evaluacion_oportunidad where evaluacionId = ?1";
			String[] args = new String[] {String.valueOf(evaluacionTO.evaluacionId)};
			Cursor cursor = dbHelper.rawQuery(SQL,args);
			
			while(cursor.moveToNext()){
				OportunidadTO oportunidadTO = new OportunidadTO();
				oportunidadTO.productoId=cursor.getLong(cursor.getColumnIndex("productoId"));
				oportunidadTO.codigoProducto = cursor.getString(cursor.getColumnIndex("codigoProducto"));
				oportunidadTO.descripcionProducto = cursor.getString(cursor.getColumnIndex("producto"));
				
				oportunidadTO.concrecion = cursor.getString(cursor.getColumnIndex("concrecion"));
				oportunidadTO.concrecionActual = cursor.getString(cursor.getColumnIndex("concrecionActual"));
				oportunidadTO.concrecionCumple=cursor.getInt(cursor.getColumnIndex("concrecionCumple"));
				
				oportunidadTO.sovi = cursor.getString(cursor.getColumnIndex("sovi"));
				oportunidadTO.soviActual = cursor.getString(cursor.getColumnIndex("soviActual"));
				oportunidadTO.soviCumple=cursor.getInt(cursor.getColumnIndex("soviCumple"));
				
				
				oportunidadTO.cumplePrecio = cursor.getString(cursor.getColumnIndex("respetaPrecio"));
				oportunidadTO.cumplePrecioActual = cursor.getString(cursor.getColumnIndex("respetaPrecioActual"));
				oportunidadTO.cumplePrecioCumple=cursor.getInt(cursor.getColumnIndex("respetaPrecioCumple"));
				
				oportunidadTO.numeroSabores = cursor.getString(cursor.getColumnIndex("numeroSabores"));
				oportunidadTO.numeroSaboresActual = cursor.getString(cursor.getColumnIndex("numeroSaboresActual"));
				oportunidadTO.numeroSaboresCumple=cursor.getInt(cursor.getColumnIndex("numeroSaboresCumple"));
				
				oportunidadTO.puntosCocaCola = cursor.getString(cursor.getColumnIndex("puntosCocaCola"));
				oportunidadTO.puntosBonus = cursor.getString(cursor.getColumnIndex("puntosBonus"));
				oportunidadTO.fecha = cursor.getString(cursor.getColumnIndex("fechaProceso"));
				
				evaluacionTO.oportunidades.add(oportunidadTO);
			}
			
			cursor.close();
		}
	}
	
	public void GetSKUPresentacion(EvaluacionTO evaluacionTO){
		if(evaluacionTO!=null){
			evaluacionTO.skuPresentacion = new ArrayList<SKUPresentacionTO>();
			
			String SQL = "select * from evaluacion_sku_presentacion where evaluacionId = ?1";
			String[] args = new String[] {String.valueOf(evaluacionTO.evaluacionId)};
			Cursor cursor = dbHelper.rawQuery(SQL,args);
			
			while(cursor.moveToNext()){
				SKUPresentacionTO skuPresentacionTO = new SKUPresentacionTO();
				skuPresentacionTO.codigoSKU = cursor.getString(cursor.getColumnIndex("skuId"));
				skuPresentacionTO.descripcionSKU = cursor.getString(cursor.getColumnIndex("sku"));
				skuPresentacionTO.valorActual = cursor.getString(cursor.getColumnIndex("valorActual"));
				skuPresentacionTO.compromiso = cursor.getString(cursor.getColumnIndex("compromiso"));
				evaluacionTO.skuPresentacion.add(skuPresentacionTO);
			}
			cursor.close();
		}
	}
	
	public long insertOportunidad(EvaluacionTO evaluacionTO,OportunidadTO oportunidadTO){
		ContentValues values = new ContentValues();
		
		values.put("evaluacionId", evaluacionTO.evaluacionId);
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
		
		values.put("puntosCocaCola", oportunidadTO.puntosCocaCola);
		values.put("puntosBonus", oportunidadTO.puntosBonus);
		values.put("fechaProceso", oportunidadTO.fecha);
		
		long id= dbHelper.insertOrThrow("evaluacion_oportunidad", values);
		oportunidadTO.oportunidadId = id;
		
		return id;
		
	}
	
	public long insertSKUPresentacion(EvaluacionTO evaluacionTO,SKUPresentacionTO skuPresentacionTO){
		ContentValues values = new ContentValues();
		
		values.put("evaluacionId", evaluacionTO.evaluacionId);
		values.put("skuId", skuPresentacionTO.codigoSKU);
		values.put("sku", skuPresentacionTO.descripcionSKU);
		values.put("valorActual", skuPresentacionTO.valorActual);
		values.put("compromiso", skuPresentacionTO.compromiso);
		
		
		long id= dbHelper.insertOrThrow("evaluacion_sku_presentacion", values);
		
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
