package lindley.desarrolloxcliente.negocio;

import java.util.List;

import lindley.desarrolloxcliente.dao.FotoClusterDAO;
import lindley.desarrolloxcliente.to.FotoExitoTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class FotoClusterBLL {

	private static final String TAG_LOG = FotoClusterBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected FotoClusterDAO fotoClusterDAO;
	
	
	public List<FotoExitoTO> listar(String codigoCluster){
		List<FotoExitoTO> lista=null;
		
		try{
			dbHelper.openDataBase();
			lista = fotoClusterDAO.listar(codigoCluster);
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "listar", ex);
		} finally {
			dbHelper.close();
		}
		
		return lista;
		
	}
}
