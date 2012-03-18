package pe.pae.encuesta.dao;


import pe.pae.encuesta.to.ProductoTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;

import com.google.inject.Inject;

public class EncuestaDAO {


	@Inject protected DBHelper dbHelper;
	
	public void guardar(ProductoTO producto){
		
		
		ContentValues parametros = new ContentValues();
		parametros.put("productoid", producto.productoId);
		parametros.put("descripcion", producto.descripcion);
		parametros.put("encuestaid", producto.encuestaId);
		
		dbHelper.getDataBase().insertOrThrow("producto", null, parametros);
		
	}
}
