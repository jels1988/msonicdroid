package pe.lindley.om.negocio;

import java.util.ArrayList;
import java.util.List;
import pe.lindley.om.dao.RolDAO;
import pe.lindley.om.to.ClienteTO;
import pe.lindley.om.to.RolTO;
import pe.lindley.util.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class RolBLL {

	private static final String TAG_LOG = ParametroBLL.class.getCanonicalName();
	@Inject protected DBHelper dbHelper;
	@Inject protected RolDAO rolDAO;
	
	
	public ArrayList<RolTO> list(ClienteTO clienteTO,
								 String rol,
								 String tipoOrden){
		
		ArrayList<RolTO> roles = null;
		try {
			dbHelper.openDataBase();
			roles = rolDAO.list(clienteTO, rol, tipoOrden);
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}
		
		return roles;
	}
	public void save(List<RolTO> roles){
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			rolDAO.deleteAll();
			
			for (RolTO rolTO : roles) {
				rolDAO.save(rolTO);
			}
			
			dbHelper.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "save", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
}
