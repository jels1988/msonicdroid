package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.upload.SkuTO;
import net.msonic.lib.DBHelper;

import android.database.Cursor;

import com.google.inject.Inject;

public class OportunidadDAO {
	
	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
	
	public ArrayList<SkuTO> consultarSKUPresentacion(String cluster){
		ArrayList<SkuTO> skus = new ArrayList<SkuTO>();
		
		String SQL = "select codigo,descripcion,cluster from sku where cluster = ?1";
		
		String[] args = new String[] {cluster};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		lindley.desarrolloxcliente.to.upload.SkuTO skuPresentacionTO;
		
		while(cursor.moveToNext()){
			skuPresentacionTO = new SkuTO();
	
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
	
	public ArrayList<lindley.desarrolloxcliente.to.upload.OportunidadTO> consultarNuevasOportunidades(String codigoCliente){
		
		ArrayList<lindley.desarrolloxcliente.to.upload.OportunidadTO> nuevasOportunidades = new ArrayList<lindley.desarrolloxcliente.to.upload.OportunidadTO>();
		
		String SQL = "select p.productoId,oc.codigoProducto,p.descripcion,oc.legacy," +
					"oc.concrecion,oc.sovi,oc.respetaPrecio,oc.numeroSabores,oc.fechaProceso " +
					 "from oportunidad_cliente oc inner join producto p " +
					 "on oc.codigoProducto = p.codigo " +
					 "where oc.anio = ? and oc.mes = ? and oc.codigoCliente = ?";
		

		String[] args = new String[] {String.valueOf(periodoTO.anio),
									  String.valueOf(periodoTO.mes),
									  codigoCliente};
		
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		lindley.desarrolloxcliente.to.upload.OportunidadTO nuevaOportunidadTO;
		
		while(cursor.moveToNext()){
			nuevaOportunidadTO = new lindley.desarrolloxcliente.to.upload.OportunidadTO();
			nuevaOportunidadTO.productoId = cursor.getLong(cursor.getColumnIndex("productoId"));
			nuevaOportunidadTO.codigoArticulo = cursor.getString(cursor.getColumnIndex("codigoProducto"));
			nuevaOportunidadTO.articulo = cursor.getString(cursor.getColumnIndex("descripcion"));
			nuevaOportunidadTO.legacy = cursor.getString(cursor.getColumnIndex("legacy"));
			nuevaOportunidadTO.concrecion = cursor.getString(cursor.getColumnIndex("concrecion"));
			nuevaOportunidadTO.sovi = cursor.getString(cursor.getColumnIndex("sovi"));
			nuevaOportunidadTO.respetoPrecio = cursor.getString(cursor.getColumnIndex("respetaPrecio"));
			nuevaOportunidadTO.numeroSabores = String.valueOf(cursor.getInt(cursor.getColumnIndex("numeroSabores")));
			nuevaOportunidadTO.puntosBonus = "0"; //String.valueOf(cursor.getInt(cursor.getColumnIndex("puntosBonus")));
			nuevaOportunidadTO.puntosGanados = "0"; //String.valueOf(cursor.getInt(cursor.getColumnIndex("puntosBonus")));
			nuevaOportunidadTO.puntosSugeridos = "0";
			//nuevaOportunidadTO.fechaCompromiso = String.valueOf(cursor.getInt(cursor.getColumnIndex("fechaProceso")));
			nuevasOportunidades.add(nuevaOportunidadTO);
			
		}
		
		cursor.close();
		
		return nuevasOportunidades;

		
	}
}
