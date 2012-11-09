package lindley.desarrolloxcliente.negocio;

import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.dao.PresentacionDAO;
import lindley.desarrolloxcliente.to.upload.PresentacionTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class PresentacionBLL {

	private static final String TAG_LOG = PresentacionBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected PresentacionDAO presentacionDAO;
	
	public List<PresentacionTO> consultarOportunidadesPresentacion(String codigoCliente){
		List<PresentacionTO> lista = null;
		
		
		try{
			dbHelper.openDataBase();
			lista = presentacionDAO.consultarOportunidadesPresentacion(codigoCliente, ConstantesApp.TIPO_AGRUPRACION_PRESENTACION);
		}catch(Exception ex){
			Log.e(TAG_LOG, "consultarOportunidadesPresentacion", ex);
		} finally {
			dbHelper.close();
		}
		
		return lista;
	}
}
