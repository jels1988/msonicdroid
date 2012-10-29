package lindley.desarrolloxcliente.dao;


import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.download.AccionTradeProductoTO;
import lindley.desarrolloxcliente.to.download.AccionTradeTO;
import lindley.desarrolloxcliente.to.download.OportunidadTO;
import lindley.desarrolloxcliente.to.download.ProductoTO;
import lindley.desarrolloxcliente.to.download.SkuTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;

import com.google.inject.Inject;

public class DescargaDAO {

	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
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
		values.put("fechaProceso", 0);
		
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

}
