package lindley.desarrolloxcliente.negocio;


import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.dao.PosicionDAO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class PosicionBLL {

	private static final String TAG_LOG = ClienteBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected PosicionDAO posicionDAO;
	
	public List<PosicionCompromisoTO> consultarOportunidadesPosicion(EvaluacionTO evaluacionTO){
		
		List<PosicionCompromisoTO> lista = null;
		
		try{
			dbHelper.openDataBase();
			lista = posicionDAO.consultarOportunidadesPosicion(evaluacionTO, 
															ConstantesApp.TIPO_AGRUPRACION_POSICION);
		}catch(Exception ex){
			Log.e(TAG_LOG, "consultarOportunidadesPoscion", ex);
		} finally {
			dbHelper.close();
		}
		
		return lista;
	}
}
