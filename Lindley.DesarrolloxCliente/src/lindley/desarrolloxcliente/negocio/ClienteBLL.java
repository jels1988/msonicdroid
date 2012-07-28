package lindley.desarrolloxcliente.negocio;

import java.util.List;

import lindley.desarrolloxcliente.dao.ClienteDAO;
import lindley.desarrolloxcliente.to.ClienteTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class ClienteBLL {

	private static final String TAG_LOG = ClienteBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected ClienteDAO clienteDAO;
	
	public List<ClienteTO> listarByCodigo(String codigo){
		List<ClienteTO> clienteTO=null;
		
		try{
			dbHelper.openDataBase();
			clienteTO = clienteDAO.listarByCodigo(codigo);
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "listarByCodigo", ex);
		} finally {
			dbHelper.close();
		}
		
		return clienteTO;
		
	}
	
}
