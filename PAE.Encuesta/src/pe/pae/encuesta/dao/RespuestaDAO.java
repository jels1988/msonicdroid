package pe.pae.encuesta.dao;

import java.util.ArrayList;

import pe.pae.encuesta.to.FileTO;
import pe.pae.encuesta.to.RespuestaDataTO;
import pe.pae.encuesta.to.RespuestaPreguntaDataTO;
import pe.pae.encuesta.to.RespuestaPreguntaOpcionDataTO;
import net.msonic.lib.DBHelper;

import android.database.Cursor;

import com.google.inject.Inject;

public class RespuestaDAO {

	@Inject protected DBHelper dbHelper;
	
	
	public void eliminarDocumento(int id){
		
		String[] parametros = new String[]{String.valueOf(id)};
		dbHelper.getDataBase().delete("documento", "documentoId=?", parametros);
		
	}
	public ArrayList<FileTO> listarDocumentos(){
		
		ArrayList<FileTO> documentos = new ArrayList<FileTO>();
		String SQL = "SELECT documentoId,documento FROM documento where documento <> ''";
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL,null);
		
		
		FileTO fileTO;
		
		while(cursor.moveToNext()){
			fileTO = new FileTO();
			
			fileTO.id = cursor.getInt(cursor.getColumnIndex("documentoId"));
			fileTO.nombre = cursor.getString(cursor.getColumnIndex("documento"));
			
			documentos.add(fileTO);
		}
		
		cursor.close();
		
		return documentos;
	}
	
	public void terminarEncuesta(){
		
		dbHelper.getDataBase().execSQL("INSERT INTO documento (documento) SELECT foto FROM respuesta_pregunta where foto <> '' and foto not in (select documento from documento)");
		dbHelper.getDataBase().delete("respuesta", null, null);
		dbHelper.getDataBase().delete("respuesta_pregunta", null, null);
		dbHelper.getDataBase().delete("respuesta_opcion", null, null);	
		dbHelper.getDataBase().delete("producto", null, null);	
		dbHelper.getDataBase().delete("encuesta", null, null);	
		dbHelper.getDataBase().delete("pregunta", null, null);	
		dbHelper.getDataBase().delete("opcion", null, null);	
	}
	
	public ArrayList<RespuestaDataTO> listarRespuesta(){
		
		String SQL = "select respuestaid,tiendaid,productoid,encuestaid,fecharegistro,horaregistro from respuesta";
		
		ArrayList<RespuestaDataTO> respuestas = new ArrayList<RespuestaDataTO>();
		
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL,null);
		RespuestaDataTO respuestaDataTO;
		
		while(cursor.moveToNext()){
			respuestaDataTO = new RespuestaDataTO();
			respuestaDataTO.respuestaId = cursor.getInt(cursor.getColumnIndex("respuestaid"));
			respuestaDataTO.tiendadId = cursor.getInt(cursor.getColumnIndex("tiendaid"));
			respuestaDataTO.productoId = cursor.getInt(cursor.getColumnIndex("productoid"));
			respuestaDataTO.encuestaId = cursor.getInt(cursor.getColumnIndex("encuestaid"));
			respuestaDataTO.fechaRegistro = cursor.getString(cursor.getColumnIndex("fecharegistro"));
			respuestaDataTO.horaRegistro = cursor.getString(cursor.getColumnIndex("horaregistro"));
			respuestaDataTO.usuarioId=1;
			
			respuestas.add(respuestaDataTO);
		}
		
		cursor.close();
		
		
		for (RespuestaDataTO respuestaTO : respuestas) {
			respuestaTO.preguntas = new ArrayList<RespuestaPreguntaDataTO>();
			
			SQL = "select respuestapreguntaid,respuestaid,preguntaid,observacion,respuesta_1,foto from respuesta_pregunta where respuestaid = ?";
			String[] parametros = new String[]{String.valueOf(respuestaTO.respuestaId)};
			
			RespuestaPreguntaDataTO respuestaPreguntaDataTO;
			
			cursor = dbHelper.getDataBase().rawQuery(SQL,parametros);
			
			while(cursor.moveToNext()){
				respuestaPreguntaDataTO = new RespuestaPreguntaDataTO();
				respuestaPreguntaDataTO.respuestaPreguntaDataId = cursor.getInt(cursor.getColumnIndex("respuestapreguntaid"));
				respuestaPreguntaDataTO.respuestaId = cursor.getInt(cursor.getColumnIndex("respuestaid"));
				respuestaPreguntaDataTO.preguntaId = cursor.getInt(cursor.getColumnIndex("preguntaid"));
				respuestaPreguntaDataTO.observacion = cursor.getString(cursor.getColumnIndex("observacion"));
				respuestaPreguntaDataTO.respuesta = cursor.getString(cursor.getColumnIndex("respuesta_1"));
				respuestaPreguntaDataTO.foto = cursor.getString(cursor.getColumnIndex("foto"));
				respuestaTO.preguntas.add(respuestaPreguntaDataTO);
			}
			
			cursor.close();
			for(RespuestaPreguntaDataTO pregunta : respuestaTO.preguntas){
				SQL = "select * from respuesta_opcion where respuestapreguntaid = ?";
				parametros = new String[]{String.valueOf(pregunta.respuestaPreguntaDataId)};
				cursor = dbHelper.getDataBase().rawQuery(SQL,parametros);
				pregunta.opciones = new ArrayList<RespuestaPreguntaOpcionDataTO>();
				RespuestaPreguntaOpcionDataTO opcion;
				
				while(cursor.moveToNext()){
					opcion = new RespuestaPreguntaOpcionDataTO();
					opcion.respuestaPreguntaDataId = cursor.getInt(cursor.getColumnIndex("respuestapreguntaid"));
					opcion.preguntaOpcionId = cursor.getInt(cursor.getColumnIndex("opcionid"));
					pregunta.opciones.add(opcion);
				}
				
				cursor.close();
			}
			
		}
		return respuestas;
		
	}
	
}
