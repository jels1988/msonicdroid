package pe.lindley.prospector.negocio;

import java.util.ArrayList;

import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.prospector.dao.ClienteDAO;
import pe.lindley.prospector.to.ClienteTO;
import pe.lindley.util.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class ClienteBLL {

	private static final String TAG_LOG = ClienteBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected ClienteDAO clienteDAO;

	public ClienteTO listById(int id) {
		ClienteTO clienteTOBD=null;
		try {
			dbHelper.openDataBase();
			clienteTOBD = clienteDAO.listById(id);
		} catch (Exception e) {
			Log.e(TAG_LOG, "listById", e);
		} finally {
			dbHelper.close();
		}
		
		return clienteTOBD;
	}
	public void deleteAll() {
		try {
			dbHelper.openDataBase();
			clienteDAO.deleteAll();
		} catch (Exception e) {
			Log.e(TAG_LOG, "deleteAll", e);
		} finally {
			dbHelper.close();
		}
	}
	
	public boolean delete(int id) {
		boolean rp=false;
		try {
			dbHelper.openDataBase();
			clienteDAO.delete(id);
			rp=true;
		} catch (Exception e) {
			Log.e(TAG_LOG, "delete", e);
		} finally {
			dbHelper.close();
		}
		
		return rp;
	}
	
	public ArrayList<ClienteTO> list(String codigo,String dni,String ruc) {
		ArrayList<ClienteTO> listado = null;
		try {
			dbHelper.openDataBase();
			listado = clienteDAO.list(dni,ruc,codigo);
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}

		return listado;
	}

	
	public ArrayList<ClienteTO> list() {
		ArrayList<ClienteTO> listado = null;
		try {
			dbHelper.openDataBase();
			listado = clienteDAO.list();
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}

		return listado;
	}

	public void save(ClienteTO clienteTO, UsuarioTO usuarioTO) {
		int clienteId = clienteTO.getClienteId();
		try {
			dbHelper.openDataBase();
			clienteTO.setTieneCambios(ClienteTO.FICHA_TIENE_CAMBIOS);
			if(clienteId==0){
				clienteDAO.save(clienteTO, usuarioTO);
			}else{
				clienteDAO.update(clienteTO, usuarioTO);
			}
		} catch (Exception e) {
			Log.e(TAG_LOG, "Save", e);
		} finally {
			dbHelper.close();
		}
	}
	
	public void saveFichasRechazadas(ArrayList<ClienteTO> fichasRechazadas,UsuarioTO usuarioTO){
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			clienteDAO.deleteRechazadas();
			
			for (ClienteTO clienteTO : fichasRechazadas) {
				clienteTO.setAccion(ClienteTO.ACCION_RECHAZO);
				clienteTO.setTieneCambios(ClienteTO.FICHA_SIN_CAMBIOS);
				clienteDAO.save(clienteTO,usuarioTO);
			}
			
			dbHelper.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "Save", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
}