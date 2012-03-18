package pe.pae.encuesta.negocio;


import java.util.ArrayList;
import java.util.List;

import pe.pae.encuesta.dao.ClienteDAO;
import pe.pae.encuesta.to.ClienteTO;
import pe.pae.encuesta.to.TiendaTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class ClienteBLL {

	private static final String TAG_LOG = ClienteBLL.class.getCanonicalName();
	@Inject protected DBHelper dbHelper;
	@Inject protected ClienteDAO clienteDAO;
	
	public ArrayList<ClienteTO> listarClientes(){
		ArrayList<ClienteTO> clientes=null;
		
		
		try {
			dbHelper.openDataBase();
			clientes = clienteDAO.listarClientes();
		} catch (Exception e) {
			Log.e(TAG_LOG, "listarClientes", e);
		} finally {
			dbHelper.close();
		}
		
		return clientes;
	}
	
	public ArrayList<TiendaTO> listarTiendas(int clienteId){
		ArrayList<TiendaTO> tiendas=null;
		

		try {
			dbHelper.openDataBase();
			tiendas = clienteDAO.listarTiendas(clienteId);
		} catch (Exception e) {
			Log.e(TAG_LOG, "listarTiendas", e);
		} finally {
			dbHelper.close();
		}
		
		return tiendas;
		
	}
	public void saveCliente(List<ClienteTO> clientes){
		
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			clienteDAO.deleteCliente();
			for (ClienteTO clienteTO : clientes) {
				clienteDAO.saveCliente(clienteTO);
			}
			
			dbHelper.setTransactionSuccessful();
			} catch (Exception e) {
				// TODO: handle exception
				Log.e(TAG_LOG,"saveCliente", e);
			}finally{
				dbHelper.endTransaction();
				dbHelper.close();
			}
	}
}
