package pe.lindley.preliquidacion.negocio;

import java.util.ArrayList;

import net.msonic.lib.DBHelper;
import pe.lindley.preliquidacion.dao.MotivoDAO;
import pe.lindley.preliquidacion.to.MotivoTO;

import android.util.Log;

import com.google.inject.Inject;

public class MotivoBLL {

	@Inject protected DBHelper dbHelper;
	@Inject protected MotivoDAO motivoDAO;
	
	
	public ArrayList<MotivoTO> listarMotivos(){
		ArrayList<MotivoTO> motivos=null;
		
		try{
			dbHelper.openDataBase();
			motivos = motivoDAO.listarMotivos();
			
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(DocumentoBLL.class.toString(),"descargarMotivos", e);
		}finally{
			dbHelper.close();
		}
		
		return motivos;
	}
	
	
	public boolean descargarMotivos(ArrayList<MotivoTO> motivos){
		
		
		
		boolean rp = false;
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			motivoDAO.deleteAll();
			for (MotivoTO motivoTO : motivos) {
				
				motivoDAO.insert(motivoTO);
			}
			dbHelper.setTransactionSuccessful();
			rp=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(DocumentoBLL.class.toString(),"descargarMotivos", e);
		}finally{
			dbHelper.endTransaction();
			dbHelper.close();
		}
		return rp;
	}
	
}
