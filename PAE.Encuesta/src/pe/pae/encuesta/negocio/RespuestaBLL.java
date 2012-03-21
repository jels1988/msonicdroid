package pe.pae.encuesta.negocio;

import java.util.ArrayList;

import net.msonic.lib.DBHelper;
import pe.pae.encuesta.dao.RespuestaDAO;
import pe.pae.encuesta.to.RespuestaDataTO;

import android.util.Log;

import com.google.inject.Inject;

public class RespuestaBLL {

	
	
	private static final String TAG_LOG = RespuestaBLL.class.getCanonicalName();
	@Inject protected DBHelper dbHelper;
	@Inject protected RespuestaDAO respuestaDAO;
	
	public  ArrayList<RespuestaDataTO> listarRespuesta(){
		ArrayList<RespuestaDataTO> respuestas = null;
		try {
			dbHelper.openDataBase();
			respuestas = respuestaDAO.listarRespuesta();
		} catch (Exception e) {
			Log.e(TAG_LOG, "listarClientes", e);
		} finally {
			dbHelper.close();
		}
		
		return respuestas;
	}
}