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
}
