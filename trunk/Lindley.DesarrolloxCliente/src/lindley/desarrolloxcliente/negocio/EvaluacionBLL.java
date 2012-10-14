package lindley.desarrolloxcliente.negocio;

import java.util.ArrayList;
import java.util.List;

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
	
	
	public EvaluacionTO GetById(long id){
		EvaluacionTO evaluacionTO = null;
		
		try{
			dbHelper.openDataBase();
			
			evaluacionTO = evaluacionDAO.GetById(id);
			evaluacionDAO.GetOportunidades(evaluacionTO);
			evaluacionDAO.GetSKUPresentacion(evaluacionTO);
			
			
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "EvaluacionBLL.GetById", ex);
		} finally {
			dbHelper.close();
		}	
		
		return evaluacionTO;
	}
	
	public List<EvaluacionTO> List(String codigoCliente){
		
		List<EvaluacionTO> evaluaciones = new ArrayList<EvaluacionTO>();
		try{
			dbHelper.openDataBase();
			evaluaciones = evaluacionDAO.List(codigoCliente);
		}catch(Exception ex){
			Log.e(TAG_LOG, "EvaluacionBLL.GetById", ex);
		} finally {
			dbHelper.close();
		}	
		
		return evaluaciones;
	}
}
