package pe.pae.encuesta.negocio;

import java.util.ArrayList;
import java.util.List;

import net.msonic.lib.DBHelper;
import pe.pae.encuesta.dao.EncuestaDAO;
import pe.pae.encuesta.to.EncuestaTO;
import pe.pae.encuesta.to.OpcionTO;
import pe.pae.encuesta.to.PreguntaTO;
import pe.pae.encuesta.to.ProductoTO;
import pe.pae.encuesta.to.RespuestaTO;

import android.util.Log;

import com.google.inject.Inject;

public class EncuestaBLL {

	

	private static final String TAG_LOG = ClienteBLL.class.getCanonicalName();
	@Inject protected DBHelper dbHelper;
	@Inject protected EncuestaDAO encuestaDAO;
	
	
	
	
	public void guardarEncuestaRespuesta(RespuestaTO respuesta){
		
		respuesta.fechaRegistro = "010101";
		respuesta.horaRegistro = "0202";
		long respuestaId=respuesta.respuestaId;
		long respuestaPreguntaId=0;
		try {
			dbHelper.openDataBase();
			
			if(respuestaId==0){
				respuestaId = encuestaDAO.insertEncuestaRespuesta(respuesta);
				respuesta.respuestaId=respuestaId;
			}
		
			
			for (PreguntaTO preguntaTO : respuesta.preguntas) {
				
				respuestaPreguntaId = preguntaTO.respuestaOpcionId;
				
				if(respuestaPreguntaId==0){
					respuestaPreguntaId = encuestaDAO.insertEncuestaPreguntaRespuesta(respuestaId, preguntaTO);
					preguntaTO.respuestaOpcionId=respuestaPreguntaId;
				}else{
					 encuestaDAO.updateEncuestaPreguntaRespuesta(preguntaTO);
				}
				
				
				//ELIMINAMOS TODAS LAS OPCIONES
				if(respuestaPreguntaId!=0){
					encuestaDAO.deleteEncuestaPreguntaOpcion(respuestaPreguntaId);
				}
				
				for(OpcionTO opcionTO : preguntaTO.opciones){
					encuestaDAO.guardarEncuestaPreguntaOpcion(respuestaPreguntaId, opcionTO);
				}
				
			}
		} catch (Exception e) {
			Log.e(TAG_LOG, "guardarEncuestaRespuesta", e);
		} finally {
			dbHelper.close();
		}
	}
	
	public void guardarEncuestaRespuesta(RespuestaTO respuesta,PreguntaTO preguntaTO){
		
		respuesta.fechaRegistro = "010101";
		respuesta.horaRegistro = "0202";
		long respuestaId=respuesta.respuestaId;
		long respuestaPreguntaId=0;
		try {
			dbHelper.openDataBase();
			
			if(respuestaId==0){
				respuestaId = encuestaDAO.insertEncuestaRespuesta(respuesta);
				respuesta.respuestaId=respuestaId;
			}
		
		
				
			respuestaPreguntaId = preguntaTO.respuestaOpcionId;
			
			if(respuestaPreguntaId==0){
				respuestaPreguntaId = encuestaDAO.insertEncuestaPreguntaRespuesta(respuestaId, preguntaTO);
			}else{
				 encuestaDAO.updateEncuestaPreguntaRespuesta(preguntaTO);
			}
				
				
				//ELIMINAMOS TODAS LAS OPCIONES
				if(respuestaPreguntaId!=0){
					encuestaDAO.deleteEncuestaPreguntaOpcion(respuestaPreguntaId);
				}
				
				for(OpcionTO opcionTO : preguntaTO.opciones){
					encuestaDAO.guardarEncuestaPreguntaOpcion(respuestaPreguntaId, opcionTO);
				}

		} catch (Exception e) {
			Log.e(TAG_LOG, "guardarEncuestaRespuesta x Pregunta", e);
		} finally {
			dbHelper.close();
		}
	}
	
	
	
	
	public ArrayList<PreguntaTO> listarPreguntas(int encuestaId){
		ArrayList<PreguntaTO> preguntas=null;
		
		try {
			dbHelper.openDataBase();
			preguntas = encuestaDAO.listarPreguntas(encuestaId);
		} catch (Exception e) {
			Log.e(TAG_LOG, "listarPreguntas", e);
		} finally {
			dbHelper.close();
		}
		
		return preguntas;
		
	}
	
	public ArrayList<ProductoTO> buscarProducto(String descripcion){
		ArrayList<ProductoTO> productos=null;
		
		
		try {
			dbHelper.openDataBase();
			productos = encuestaDAO.buscarProducto(descripcion);
		} catch (Exception e) {
			Log.e(TAG_LOG, "buscarProducto", e);
		} finally {
			dbHelper.close();
		}
		
		return productos;
	}
	
	public void saveProductos(List<ProductoTO> productos){
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			for (ProductoTO productoTO : productos) {
				encuestaDAO.guardarProducto(productoTO);
			}
			
			dbHelper.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG_LOG,"saveProductos", e);
		}finally{
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	public void saveEncuestas(List<EncuestaTO> encuestas){
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			for (EncuestaTO encuestaTO : encuestas) {
				encuestaDAO.guardarEncuesta(encuestaTO);
			}
			
			dbHelper.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG_LOG,"saveEncuestas", e);
		}finally{
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
}
