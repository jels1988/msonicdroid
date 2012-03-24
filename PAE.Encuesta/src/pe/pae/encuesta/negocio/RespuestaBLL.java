package pe.pae.encuesta.negocio;

import java.util.ArrayList;

import net.msonic.lib.DBHelper;
import pe.pae.encuesta.dao.RespuestaDAO;
import pe.pae.encuesta.to.FileTO;
import pe.pae.encuesta.to.RespuestaDataTO;

import android.util.Log;

import com.google.inject.Inject;

public class RespuestaBLL {

	
	
	private static final String TAG_LOG = RespuestaBLL.class.getCanonicalName();
	@Inject protected DBHelper dbHelper;
	@Inject protected RespuestaDAO respuestaDAO;
	
	
	public void eliminarDocumento(int id){
		try {
			dbHelper.openDataBase();
			respuestaDAO.eliminarDocumento(id);
		} catch (Exception e) {
			Log.e(TAG_LOG, "eliminarDocumento", e);
		} finally {
			dbHelper.close();
		}
	}
	
	public ArrayList<FileTO> listarDocumentos(){
		
		ArrayList<FileTO> documentos=null;
		
		try {
			dbHelper.openDataBase();
			documentos = respuestaDAO.listarDocumentos();
		} catch (Exception e) {
			Log.e(TAG_LOG, "listarDocumentos", e);
		} finally {
			dbHelper.close();
		}
		
		return documentos;
	}
	
	public void terminarEncuesta(){
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			respuestaDAO.terminarEncuesta();
			dbHelper.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e(TAG_LOG, "iniciarEncuesta", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	public  ArrayList<RespuestaDataTO> listarRespuesta(){
		ArrayList<RespuestaDataTO> respuestas = null;
		try {
			dbHelper.openDataBase();
			respuestas = respuestaDAO.listarRespuesta();
		} catch (Exception e) {
			Log.e(TAG_LOG, "listarRespuesta", e);
		} finally {
			dbHelper.close();
		}
		
		return respuestas;
	}
}
