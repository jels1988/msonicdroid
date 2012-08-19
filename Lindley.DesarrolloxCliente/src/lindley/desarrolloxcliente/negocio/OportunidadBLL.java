package lindley.desarrolloxcliente.negocio;

import java.util.ArrayList;

import lindley.desarrolloxcliente.dao.OportunidadDAO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class OportunidadBLL {

	
	private static final String TAG_LOG = OportunidadBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected OportunidadDAO oportunidadDAO;
	@Inject protected PeriodoTO periodoTO;
	
	
	public ArrayList<SKUPresentacionTO> consultarSKUPresentacion(String cluster){
		ArrayList<SKUPresentacionTO> listaSku=null;
		
		try{
			dbHelper.openDataBase();
			listaSku = oportunidadDAO.consultarSKUPresentacion(cluster);
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "consultarSKUPresentacion", ex);
		} finally {
			dbHelper.close();
		}
		
		return listaSku;
		
	}
	
	public ArrayList<OportunidadTO> consultarNuevasOportunidades(String codigoCliente){
		ArrayList<OportunidadTO> nuevasOportunidades=null;
		try{
			dbHelper.openDataBase();
			nuevasOportunidades = oportunidadDAO.consultarNuevasOportunidades(codigoCliente);
		}catch(Exception ex){
			Log.e(TAG_LOG, "consultarNuevasOportunidades", ex);
		} finally {
			dbHelper.close();
		}
		return nuevasOportunidades;
	}
}
