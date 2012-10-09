package lindley.desarrolloxcliente.dao;

import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;

import com.google.inject.Inject;

public class EvaluacionDAO {

	@Inject	protected DBHelper dbHelper;
	
	
	public long insertEvaluacion(EvaluacionTO evaluacionTO){
		
		ContentValues values = new ContentValues();
		values.put("clienteCodigo", evaluacionTO.codigoCliente);
		values.put("activosLindley", evaluacionTO.activosLindley);
		values.put("codigoFe", evaluacionTO.codigoFe);
		values.put("usuario", evaluacionTO.codigoUsuario);
		
		long id= dbHelper.insertOrThrow("evaluacion", values);
		
		evaluacionTO.evaluacionId = id;
		return id;
	}
	
	public long insertOportunidad(EvaluacionTO evaluacionTO,OportunidadTO oportunidadTO){
		ContentValues values = new ContentValues();
		
		values.put("evaluacionId", evaluacionTO.evaluacionId);
		values.put("productoId", oportunidadTO.productoId);
		values.put("codigoProducto", oportunidadTO.codigoProducto);
		values.put("producto", oportunidadTO.descripcionProducto);
		values.put("concrecion", oportunidadTO.concrecion);
		values.put("sovi", oportunidadTO.sovi);
		values.put("respetaPrecio", oportunidadTO.cumplePrecio);
		values.put("numeroSabores", oportunidadTO.numeroSabores);
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
		
		
		long id= dbHelper.insertOrThrow("evaluacion_sku_presentacion", values);
		
		return id;
		
	}
	
}
