package pe.pae.encuesta.dao;


import java.util.ArrayList;

import pe.pae.encuesta.to.EncuestaTO;
import pe.pae.encuesta.to.OpcionTO;
import pe.pae.encuesta.to.PreguntaTO;
import pe.pae.encuesta.to.ProductoTO;
import pe.pae.encuesta.to.RespuestaTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class EncuestaDAO {


	@Inject protected DBHelper dbHelper;
	
	
	public long insertEncuestaRespuesta(RespuestaTO respuesta){
		
		ContentValues parametros = new ContentValues();
		parametros.put("tiendaid", respuesta.tiendaId);
		parametros.put("productoid", respuesta.productoId);
		parametros.put("encuestaid", respuesta.encuestaId);
		parametros.put("fecharegistro", respuesta.fechaRegistro);
		parametros.put("horaregistro", respuesta.horaRegistro);
		
		long id  = dbHelper.getDataBase().insertOrThrow("respuesta", null, parametros);
		return id;
	}
	
	
	public long insertEncuestaPreguntaRespuesta(long respuestaId,PreguntaTO preguntaTO){
		ContentValues parametros = new ContentValues();
		parametros.put("respuestaid", respuestaId);
		parametros.put("preguntaid", preguntaTO.preguntaId);
		parametros.put("observacion", preguntaTO.observacion);
		parametros.put("respuesta_1", preguntaTO.respuesta_1);
		
		long id  = dbHelper.getDataBase().insertOrThrow("respuesta_pregunta", null, parametros);
		return id;
	}
	
	public long updateEncuestaPreguntaRespuesta(PreguntaTO preguntaTO){
		ContentValues parametros = new ContentValues();
		parametros.put("preguntaid", preguntaTO.preguntaId);
		parametros.put("observacion", preguntaTO.observacion);
		parametros.put("respuesta_1", preguntaTO.respuesta_1);
		
		String[] valores = new String[] { String.valueOf(preguntaTO.respuestaOpcionId) };
		dbHelper.getDataBase().update("respuesta_pregunta", parametros, "respuestaOpcionId=?", valores);
		return preguntaTO.respuestaOpcionId;
	}
	
	
	public void deleteEncuestaPreguntaOpcion(long respuestapreguntaid){
		String[] valores = new String[] { String.valueOf(respuestapreguntaid) };
		dbHelper.getDataBase().delete("respuesta_opcion", "respuestapreguntaid=?", valores);
	}
	
	public void guardarEncuestaPreguntaOpcion(long respuestapreguntaid,OpcionTO opcionTO){
		ContentValues parametros = new ContentValues();
		parametros.put("respuestapreguntaid", respuestapreguntaid);
		parametros.put("opcionid", opcionTO.opcionId);
		dbHelper.getDataBase().insertOrThrow("respuesta_opcion", null, parametros);
	}
	
	
	public ArrayList<OpcionTO> listarOpciones(int preguntaId){
		String SQL = "select * from opcion where preguntaid = ?";
		String[] parametros = new String[] { String.valueOf(preguntaId)};
		
		ArrayList<OpcionTO> opciones = new ArrayList<OpcionTO>();
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL,parametros);
		
		OpcionTO opcionTO;
		while(cursor.moveToNext()){
			opcionTO = new OpcionTO();
			opcionTO.opcionId = cursor.getInt(cursor.getColumnIndex("opcionid"));
			opcionTO.descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));
			opciones.add(opcionTO);
		}
		
		cursor.close();
		
		return opciones;
	}
	
	public ArrayList<PreguntaTO> listarPreguntas(int encuestaId){
		String SQL = "select * from pregunta where encuestaid = ?";
		String[] parametros = new String[] { String.valueOf(encuestaId)};
		
		ArrayList<PreguntaTO> preguntas = new ArrayList<PreguntaTO>();
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL,parametros);
		
		PreguntaTO preguntaTO;
		
		while(cursor.moveToNext()){
			preguntaTO = new PreguntaTO();
			preguntaTO.preguntaId = cursor.getInt(cursor.getColumnIndex("preguntaid"));
			preguntaTO.pregunta = cursor.getString(cursor.getColumnIndex("pregunta"));
			preguntaTO.tipoPregunta = cursor.getInt(cursor.getColumnIndex("tipopregunta"));
			preguntaTO.tieneFoto = cursor.getInt(cursor.getColumnIndex("tienefoto"));
			preguntaTO.tieneObservacion = cursor.getInt(cursor.getColumnIndex("tieneobservacion"));
			preguntaTO.comentario = cursor.getString(cursor.getColumnIndex("comentario"));
			preguntaTO.opciones = listarOpciones(preguntaTO.preguntaId);
			preguntas.add(preguntaTO);
			
		}
		
		cursor.close();
		
		return preguntas;
	}
	
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
