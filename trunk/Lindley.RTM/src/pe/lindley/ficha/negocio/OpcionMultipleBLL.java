package pe.lindley.ficha.negocio;

import java.util.ArrayList;
import java.util.List;
import pe.lindley.ficha.dao.OpcionMultipleDAO;
import pe.lindley.ficha.to.OpcionMultipleTO;
import pe.lindley.util.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class OpcionMultipleBLL {
	
	private static final String TAG_LOG = OpcionMultipleBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected OpcionMultipleDAO parametroDAO;
	public static final String TABLA_TIPO_CONTACTO = "01";
	public static final String TABLA_TIPO_GIRO = "02";	
	
	
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
	public void save(List<OpcionMultipleTO> parametros){	
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			for (OpcionMultipleTO parametroTO : parametros) {
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
	
	public ArrayList<OpcionMultipleTO> list(String codigoTabla){
		ArrayList<OpcionMultipleTO> valores=null;
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

	public ArrayList<OpcionMultipleTO> list(String codigoTabla,String id){
		ArrayList<OpcionMultipleTO> valores=null;
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

