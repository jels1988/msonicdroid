package pe.pae.encuesta.negocio;


import java.util.List;

import pe.pae.encuesta.dao.ClienteDAO;
import pe.pae.encuesta.to.ClienteTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class ClienteBLL {

	@Inject protected DBHelper dbHelper;
	@Inject protected ClienteDAO clienteDAO;
	
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
		Log.e(ClienteBLL.class.toString(),"saveCliente", e);
	}finally{
		dbHelper.endTransaction();
		dbHelper.close();
	}
	}
}
