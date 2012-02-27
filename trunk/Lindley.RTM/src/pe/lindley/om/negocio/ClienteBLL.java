package pe.lindley.om.negocio;

import java.util.ArrayList;
import java.util.List;

import pe.lindley.om.dao.ClienteDAO;
import pe.lindley.om.to.ClienteTO;
import pe.lindley.util.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class ClienteBLL {

	private static final String TAG_LOG = ClienteBLL.class.getCanonicalName();
	@Inject protected DBHelper dbHelper;
	@Inject protected ClienteDAO clienteDAO;

	public ClienteTO list(String codigo){
		ClienteTO clienteTO = null;
		try {
			dbHelper.openDataBase();
			clienteTO = clienteDAO.list(codigo);
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}
		
		return clienteTO;
	}
	
	public ArrayList<ClienteTO> list(String codigo,String razonSocial){
		ArrayList<ClienteTO> listado = null;
		try {
			dbHelper.openDataBase();
			listado = clienteDAO.list(codigo, razonSocial);
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}
		
		return listado;
	}
	
	public void save(List<ClienteTO> clientes){	
		if(clientes==null) return;
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			clienteDAO.deleteAll();
			
			for (ClienteTO clienteTO : clientes) {
				clienteDAO.save(clienteTO);
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
