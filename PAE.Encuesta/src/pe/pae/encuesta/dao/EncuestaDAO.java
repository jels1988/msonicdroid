package pe.pae.encuesta.dao;


import java.util.ArrayList;

import pe.pae.encuesta.to.EncuestaTO;
import pe.pae.encuesta.to.OpcionTO;
import pe.pae.encuesta.to.PreguntaTO;
import pe.pae.encuesta.to.ProductoTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class EncuestaDAO {


	@Inject protected DBHelper dbHelper;
	
	
	public ArrayList<ProductoTO> buscarProducto(String descripcion){

		String SQL = "select * from producto where descripcion like ?";
		String[] parametros = new String[] { "%" + descripcion + "%"};

		
		ArrayList<ProductoTO>  productos = new ArrayList<ProductoTO>();
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL,parametros);
		
		ProductoTO productoTO;
		while(cursor.moveToNext()){
			productoTO=new ProductoTO();
			productoTO.productoId = cursor.getInt(cursor.getColumnIndex("productoid"));
			productoTO.descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));	
			productoTO.encuestaId = cursor.getInt(cursor.getColumnIndex("encuestaid"));
			productos.add(productoTO);
		}
		cursor.close();
		
		return productos;
	}
	
	
	public void guardarProducto(ProductoTO producto){
		
		
		ContentValues parametros = new ContentValues();
		parametros.put("productoid", producto.productoId);
		parametros.put("descripcion", producto.descripcion);
		parametros.put("encuestaid", producto.encuestaId);
		
		dbHelper.getDataBase().insertOrThrow("producto", null, parametros);
	}
	
	public void guardarEncuesta(EncuestaTO encuesta){
		ContentValues parametros = new ContentValues();
		parametros.put("encuestaId", encuesta.encuestaId);
		parametros.put("descripcion", encuesta.descripcion);
		
		dbHelper.getDataBase().insertOrThrow("encuesta", null, parametros);
		
		for (PreguntaTO preguntaTO : encuesta.preguntas) {
			guardarPregunta(encuesta.encuestaId,preguntaTO);
		}
		
	}
	
	public void guardarPregunta(int encuestaId,PreguntaTO preguntaTO){
		ContentValues parametros = new ContentValues();
		parametros.put("preguntaid", preguntaTO.preguntaId);
		parametros.put("pregunta", preguntaTO.pregunta);
		parametros.put("tipopregunta", preguntaTO.tipoPregunta);
		parametros.put("tienefoto", preguntaTO.tieneFoto);
		parametros.put("tieneobservacion", preguntaTO.tieneObservacion);
		parametros.put("comentario", preguntaTO.comentario);
		parametros.put("encuestaid", encuestaId);
		
		dbHelper.getDataBase().insertOrThrow("pregunta", null, parametros);
		
		for (OpcionTO opcionTO : preguntaTO.opciones) {
			guardarOpcion(preguntaTO.preguntaId,opcionTO);
		}
		
	}
	
	public void guardarOpcion(int preguntaId,OpcionTO opcionTO){
		ContentValues parametros = new ContentValues();
		parametros.put("opcionid", opcionTO.opcionId);
		parametros.put("descripcion", opcionTO.descripcion);
		parametros.put("seleccionado", (opcionTO.seleccionado?1:0));
		parametros.put("preguntaid", preguntaId);
		
		dbHelper.getDataBase().insertOrThrow("opcion", null, parametros);
	}
}
