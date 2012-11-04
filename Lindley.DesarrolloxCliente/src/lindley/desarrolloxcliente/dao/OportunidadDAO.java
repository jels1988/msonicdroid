package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import net.msonic.lib.DBHelper;

import android.database.Cursor;

import com.google.inject.Inject;

public class OportunidadDAO {
	
	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
	
	public ArrayList<SKUPresentacionTO> consultarSKUPresentacion(String cluster){
		ArrayList<SKUPresentacionTO> skus = new ArrayList<SKUPresentacionTO>();
		
		String SQL = "select codigo,descripcion,cluster from sku where cluster = ?1";
		
		String[] args = new String[] {cluster};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		SKUPresentacionTO skuPresentacionTO;
		
		while(cursor.moveToNext()){
			skuPresentacionTO = new SKUPresentacionTO();
	
			skuPresentacionTO.codigoSKU  = String.format("%s",cursor.getInt(cursor.getColumnIndex("codigo")));
			skuPresentacionTO.descripcionSKU = cursor.getString(cursor.getColumnIndex("descripcion"));
			skuPresentacionTO.codigoFDE = cursor.getString(cursor.getColumnIndex("cluster"));
			skuPresentacionTO.tipoAgrupacion = ConstantesApp.TIPO_AGRUPRACION_PRESENTACION;
			skuPresentacionTO.codigoVariable = ConstantesApp.VARIABLE_RED_PRECIO_MERCADO;
			skuPresentacionTO.estado=ConstantesApp.OPORTUNIDAD_ABIERTA;
			skuPresentacionTO.marcaActual = ConstantesApp.RESPUESTA_NO;
			skuPresentacionTO.marcaCompromiso = ConstantesApp.RESPUESTA_NO;
			skuPresentacionTO.marcaCumplio = ConstantesApp.RESPUESTA_NO;
			skus.add(skuPresentacionTO);
		}
		
		cursor.close();
		
		return skus;
	}
	
	public ArrayList<OportunidadTO> consultarNuevasOportunidades(String codigoCliente){
		
		ArrayList<OportunidadTO> nuevasOportunidades = new ArrayList<OportunidadTO>();
		
		String SQL = "select p.productoId,oc.codigoProducto,p.descripcion,oc.legacy," +
					"oc.concrecion,oc.sovi,oc.respetaPrecio,oc.numeroSabores,oc.fechaProceso " +
					 "from oportunidad_cliente oc inner join producto p " +
					 "on oc.codigoProducto = p.codigo " +
					 "where oc.anio = ? and oc.mes = ? and oc.codigoCliente = ?";
		

		String[] args = new String[] {String.valueOf(periodoTO.anio),
									  String.valueOf(periodoTO.mes),
									  codigoCliente};
		
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		OportunidadTO nuevaOportunidadTO;
		
		while(cursor.moveToNext()){
			nuevaOportunidadTO = new OportunidadTO();
			nuevaOportunidadTO.productoId = cursor.getLong(cursor.getColumnIndex("productoId"));
			nuevaOportunidadTO.codigoProducto = cursor.getString(cursor.getColumnIndex("codigoProducto"));
			nuevaOportunidadTO.descripcionProducto = cursor.getString(cursor.getColumnIndex("descripcion"));
			nuevaOportunidadTO.codigoLegacy = cursor.getString(cursor.getColumnIndex("legacy"));
			nuevaOportunidadTO.concrecion = cursor.getString(cursor.getColumnIndex("concrecion"));
			nuevaOportunidadTO.sovi = cursor.getString(cursor.getColumnIndex("sovi"));
			nuevaOportunidadTO.cumplePrecio = cursor.getString(cursor.getColumnIndex("respetaPrecio"));
			nuevaOportunidadTO.numeroSabores = String.valueOf(cursor.getInt(cursor.getColumnIndex("numeroSabores")));
			nuevaOportunidadTO.puntosCocaCola = "0"; //String.valueOf(cursor.getInt(cursor.getColumnIndex("puntosCocaCola")));
			nuevaOportunidadTO.puntosBonus = "0"; //String.valueOf(cursor.getInt(cursor.getColumnIndex("puntosBonus")));
			nuevaOportunidadTO.puntosSugeridos = nuevaOportunidadTO.puntosBonus;
			nuevaOportunidadTO.fecha = String.valueOf(cursor.getInt(cursor.getColumnIndex("fechaProceso")));
			nuevasOportunidades.add(nuevaOportunidadTO);
			
		}
		
		cursor.close();
		
		return nuevasOportunidades;

		
	}
}
