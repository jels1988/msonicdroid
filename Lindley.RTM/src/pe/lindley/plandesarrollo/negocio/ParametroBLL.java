package pe.lindley.plandesarrollo.negocio;

import java.util.ArrayList;
import java.util.List;

import pe.lindley.plandesarrollo.dao.ParametroDAO;
import pe.lindley.plandesarrollo.to.ParametroTO;
import pe.lindley.util.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class ParametroBLL {

	private static final String TAG_LOG = ParametroBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected ParametroDAO parametroDAO;
	public static final String TABLA_TIPO_RESPONSABLE = "01";
	public static final String TABLA_TIPO_ACTIVIDAD = "02";	
	
	
	public void deleteAll(){
		try {
			dbHelper.openDataBase();
			parametroDAO.deleteAll();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "deleteAll", e);
		} finally {
			dbHelper.close();
		}
	}
	public void save(List<ParametroTO> parametros){	
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			for (ParametroTO parametroTO : parametros) {
				parametroDAO.save(parametroTO);
			}
			
			dbHelper.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e(TAG_LOG, "save", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	public ArrayList<ParametroTO> list(String codigoTabla){
		ArrayList<ParametroTO> valores=null;
		try {
			dbHelper.openDataBase();
			valores = parametroDAO.list(codigoTabla);
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}
		
		return valores;
	}

	public ArrayList<ParametroTO> list(String codigoTabla,String id){
		ArrayList<ParametroTO> valores=null;
		try {
			dbHelper.openDataBase();
			valores = parametroDAO.list(codigoTabla,id);
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}
		
		return valores;
	}

}
