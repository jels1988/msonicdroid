package lindley.desarrolloxcliente.negocio;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.dao.UploadDAO;
import lindley.desarrolloxcliente.to.ResumenValueTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionProcessUploadTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class UploadBLL {

	private static final String TAG_LOG = UploadBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	
	@Inject protected UploadDAO uploadDAO;
	
	
	
	
	
	public void updateEvaluacionServerId(List<EvaluacionProcessUploadTO> ids){
		try{
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			for (EvaluacionProcessUploadTO evaluacionProcessUploadTO : ids) {
				uploadDAO.updateEvaluacionServerId(evaluacionProcessUploadTO.clientId, evaluacionProcessUploadTO.serverId);
			}
			
			dbHelper.setTransactionSuccessful();
		}catch(Exception ex){
			Log.e(TAG_LOG, "UploadBLL.deleteEvaluacion", ex);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	public void deleteEvaluacion(long id){
		try{
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			uploadDAO.deleteEvaluacion(id);
			dbHelper.setTransactionSuccessful();
		}catch(Exception ex){
			Log.e(TAG_LOG, "UploadBLL.deleteEvaluacion", ex);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	
	public EvaluacionTO listarEvaluacionById(long id){
		
		EvaluacionTO evaluacion=null;
		
		try{
			dbHelper.openDataBase();
			
			evaluacion = uploadDAO.listarEvaluacionById(id);
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "UploadBLL.listarEvaluacionById", ex);
		} finally {
			dbHelper.close();
		}
		
		return evaluacion;
	}

	public List<EvaluacionTO> listarEvaluaciones(){
		
		List<EvaluacionTO> evaluaciones=null;
		
		try{
			dbHelper.openDataBase();
			
			evaluaciones = uploadDAO.listarEvaluaciones();
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "UploadBLL.listarEvaluaciones", ex);
		} finally {
			dbHelper.close();
		}
		
		return evaluaciones;
	}

	public List<EvaluacionTO> listarEvaluaciones(int limit){
		
		List<EvaluacionTO> evaluaciones=null;
		
		try{
			dbHelper.openDataBase();
			
			evaluaciones = uploadDAO.listarEvaluaciones(limit);
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "UploadBLL.listarEvaluaciones", ex);
		} finally {
			dbHelper.close();
		}
		
		return evaluaciones;
	}
	
	public long getCantidadEvaluaciones(){
		long cantidadEvaluaciones=0;
		
		try{
			dbHelper.openDataBase();
			
			cantidadEvaluaciones = uploadDAO.getCantidadEvaluaciones();
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "UploadBLL.getCantidadEvaluaciones", ex);
		} finally {
			dbHelper.close();
		}
		
		return cantidadEvaluaciones;
		
	}
	
	public long getCantidadEvaluacionesAbiertas(String codigoCliente){
		long cantidadEvaluaciones=0;
		
		try{
			dbHelper.openDataBase();
			cantidadEvaluaciones = uploadDAO.getCantidadEvaluacionesAbiertas(codigoCliente);
		}catch(Exception ex){
			Log.e(TAG_LOG, "UploadBLL.getCantidadEvaluacionesAbiertas", ex);
		} finally {
			dbHelper.close();
		}
		
		return cantidadEvaluaciones;
	}
	public List<ResumenValueTO> resumenEvaluacion(long id){
		List<ResumenValueTO> lista = new ArrayList<ResumenValueTO>();
		try{
			dbHelper.openDataBase();
			lista = uploadDAO.resumenEvaluacion(id);
		}catch(Exception ex){
			Log.e(TAG_LOG, "UploadBLL.resumenEvaluacion", ex);
		} finally {
			dbHelper.close();
		}
		return lista;
	}
	
}
