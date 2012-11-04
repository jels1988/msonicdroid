package lindley.desarrolloxcliente.negocio;

import java.util.List;

import lindley.desarrolloxcliente.dao.UploadDAO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class UploadBLL {

	private static final String TAG_LOG = UploadBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	
	@Inject protected UploadDAO uploadDAO;
	
	
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
}
