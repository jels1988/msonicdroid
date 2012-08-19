package lindley.desarrolloxcliente.negocio;

import lindley.desarrolloxcliente.dao.EvaluacionDAO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class EvaluacionBLL {

	private static final String TAG_LOG = ClienteBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	
	@Inject protected EvaluacionDAO evaluacionDAO;
	
	public void save(EvaluacionTO evaluacionTO){
		
		try{
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			evaluacionDAO.insertEvaluacion(evaluacionTO);
			
			for (OportunidadTO oportunidadTO : evaluacionTO.oportunidades) {
				evaluacionDAO.insertOportunidad(evaluacionTO, oportunidadTO);
			}
			
			for(SKUPresentacionTO skuPresentacionTO:evaluacionTO.skuPresentacion){
				evaluacionDAO.insertSKUPresentacion(evaluacionTO, skuPresentacionTO);
			}
			
			dbHelper.setTransactionSuccessful();
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "EvaluacionBLL.save", ex);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
}
