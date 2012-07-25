package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.to.NuevaOportunidadTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import net.msonic.lib.DBHelper;

import android.database.Cursor;

import com.google.inject.Inject;

public class OportunidadDAO {
	
	@Inject	protected DBHelper dbHelper;
	
	
	
	public ArrayList<SKUPresentacionTO> consultarSKUPresentacion(String cluster){
		ArrayList<SKUPresentacionTO> skus = new ArrayList<SKUPresentacionTO>();
		
		String SQL = "select codigo,descripcion from sku where cluster = ?";
		
		String[] args = new String[] {cluster};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		SKUPresentacionTO skuPresentacionTO;
		
		while(cursor.moveToNext()){
			skuPresentacionTO = new SKUPresentacionTO();
		
			skuPresentacionTO.setCodigoSKU(String.format("%s",cursor.getInt(cursor.getColumnIndex("codigo"))));
			skuPresentacionTO.setDescripcionSKU(cursor.getString(cursor.getColumnIndex("descripcion")));
			skuPresentacionTO.valorActual = ConstantesApp.SKU_RESPUESTA_NO;
			
			skus.add(skuPresentacionTO);
		}
		
		cursor.close();
		
		return skus;
	}
	
	public ArrayList<NuevaOportunidadTO> consultarNuevasOportunidades(String codigoCliente){
		
		ArrayList<NuevaOportunidadTO> nuevasOportunidades = new ArrayList<NuevaOportunidadTO>();
		
		String SQL = "select pc.codigoProducto,p.descripcion,p.legacy from producto_cliente pc inner join producto p " +
					 "on pc.codigoProducto = p.codigo " +
					 "where pc.codigoCliente = ?";
		

		String[] args = new String[] {codigoCliente};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		NuevaOportunidadTO nuevaOportunidadTO;
		
		while(cursor.moveToNext()){
			nuevaOportunidadTO = new NuevaOportunidadTO();
			nuevaOportunidadTO.codigoProducto = cursor.getString(cursor.getColumnIndex("codigoProducto"));
			nuevaOportunidadTO.descripcionProducto = cursor.getString(cursor.getColumnIndex("descripcion"));
			nuevaOportunidadTO.codigoLegacy = cursor.getString(cursor.getColumnIndex("legacy"));
			nuevasOportunidades.add(nuevaOportunidadTO);
			
		}
		
		cursor.close();
		
		return nuevasOportunidades;

		
	}
}
