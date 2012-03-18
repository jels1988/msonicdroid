package pe.pae.encuesta.dao;


import pe.pae.encuesta.to.EncuestaTO;
import pe.pae.encuesta.to.PreguntaTO;
import pe.pae.encuesta.to.ProductoTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;

import com.google.inject.Inject;

public class EncuestaDAO {


	@Inject protected DBHelper dbHelper;
	
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
		
	}
}
