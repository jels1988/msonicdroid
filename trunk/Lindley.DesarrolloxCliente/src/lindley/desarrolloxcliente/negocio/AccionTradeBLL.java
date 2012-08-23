package lindley.desarrolloxcliente.negocio;

import java.util.List;

import lindley.desarrolloxcliente.dao.AccionTradeDAO;
import lindley.desarrolloxcliente.to.AccionTradeTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class AccionTradeBLL {

	
	private static final String TAG_LOG = ClienteBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected AccionTradeDAO accionTradeDAO;
	
	public List<AccionTradeTO> listarByProducto(String codigo){
		List<AccionTradeTO> acciones=null;
		
		
		try{
			dbHelper.openDataBase();
			acciones = accionTradeDAO.listarByProducto(codigo);
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "listarByProducto", ex);
		} finally {
			dbHelper.close();
		}
		return acciones;
	}
	
}
