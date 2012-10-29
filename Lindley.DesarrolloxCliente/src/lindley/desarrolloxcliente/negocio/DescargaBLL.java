package lindley.desarrolloxcliente.negocio;

import java.util.List;

import lindley.desarrolloxcliente.dao.DescargaDAO;
import lindley.desarrolloxcliente.to.download.OportunidadTO;
import lindley.desarrolloxcliente.to.download.ProductoTO;
import lindley.desarrolloxcliente.to.download.SkuTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class DescargaBLL {

	private static final String TAG_LOG = ClienteBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected DescargaDAO descargaDAO;
	
	public void saveProducto(List<ProductoTO> lista){
		
		try{
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			descargaDAO.deleteProducto();
			for (ProductoTO productoTO : lista) {
				descargaDAO.insertProducto(productoTO);
			}
			dbHelper.setTransactionSuccessful();
		}catch(Exception ex){
			Log.e(TAG_LOG, "DescargaBLL.saveProducto", ex);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	public void saveOportunidad(List<OportunidadTO> lista){
		
		try{
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			descargaDAO.deleteOportunidad();
			for (OportunidadTO oportunidadTO : lista) {
				descargaDAO.insertOportunidad(oportunidadTO);
			}
			dbHelper.setTransactionSuccessful();
		}catch(Exception ex){
			Log.e(TAG_LOG, "DescargaBLL.saveOportunidad", ex);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	public void saveSku(List<SkuTO> lista){
		
		try{
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			descargaDAO.deleteSku();
			for (SkuTO skuTO : lista) {
				descargaDAO.insertSkus(skuTO);
			}
			dbHelper.setTransactionSuccessful();
		}catch(Exception ex){
			Log.e(TAG_LOG, "DescargaBLL.saveSku", ex);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
}
