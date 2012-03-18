package pe.pae.encuesta.negocio;

import java.util.List;

import net.msonic.lib.DBHelper;
import pe.pae.encuesta.dao.EncuestaDAO;
import pe.pae.encuesta.to.ProductoTO;

import android.util.Log;

import com.google.inject.Inject;

public class EncuestaBLL {

	

	private static final String TAG_LOG = ClienteBLL.class.getCanonicalName();
	@Inject protected DBHelper dbHelper;
	@Inject protected EncuestaDAO encuestaDAO;
	
	
	public void saveProductos(List<ProductoTO> productos){
		
		

		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			for (ProductoTO productoTO : productos) {
				encuestaDAO.guardar(productoTO);
			}
			
			dbHelper.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG_LOG,"saveProductos", e);
		}finally{
			dbHelper.endTransaction();
			dbHelper.close();
		}
		
	}
}
