package lindley.desarrolloxcliente.negocio;


import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.dao.PosicionDAO;
import lindley.desarrolloxcliente.to.download.TipoActivoTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.PosicionTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class PosicionBLL {

	private static final String TAG_LOG = ClienteBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected PosicionDAO posicionDAO;
	
	public List<TipoActivoTO> consultarTipoActivo(String tipo){
		List<TipoActivoTO> lista = null;
		
		try{
			dbHelper.openDataBase();
			lista = posicionDAO.consultarTipoActivo(tipo);
		}catch(Exception ex){
			Log.e(TAG_LOG, "consultarTipoActivo", ex);
		} finally {
			dbHelper.close();
		}
		
		return lista;
		
	}
	public List<PosicionTO> consultarOportunidadesPosicion(EvaluacionTO evaluacionTO){
		
		List<PosicionTO> lista = null;
		
		try{
			dbHelper.openDataBase();
			lista = posicionDAO.consultarOportunidadesPosicion(evaluacionTO, ConstantesApp.TIPO_AGRUPRACION_POSICION);
		}catch(Exception ex){
			Log.e(TAG_LOG, "consultarOportunidadesPosicion", ex);
		} finally {
			dbHelper.close();
		}
		
		return lista;
	}
}
