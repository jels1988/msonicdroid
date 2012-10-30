package lindley.desarrolloxcliente.negocio;

import java.util.List;

import lindley.desarrolloxcliente.dao.DescargaDAO;
import lindley.desarrolloxcliente.to.download.AccionTradeProductoTO;
import lindley.desarrolloxcliente.to.download.AccionTradeTO;
import lindley.desarrolloxcliente.to.download.ClienteDescargaTO;
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
	public static String MyLock = "DescargaBLL.Lock";
	
	public void saveProducto(List<ProductoTO> lista){
		synchronized(MyLock)	{
			
		
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
	}
	
	public void saveOportunidad(List<OportunidadTO> lista){
		synchronized(MyLock)	{
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
	}
	
	public void saveSku(List<SkuTO> lista){
		synchronized(MyLock)	{
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
	
	public void saveAccionTrade(List<AccionTradeTO> lista){
		synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				descargaDAO.deleteAccionTrade();
				for (AccionTradeTO accionTradeTO : lista) {
					descargaDAO.insertAccionTrade(accionTradeTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveAccionTrade", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		}
	}
	
	
	public void saveAccionTradeProducto(List<AccionTradeProductoTO> lista){
		synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				descargaDAO.deleteAccionTradeProducto();
				for (AccionTradeProductoTO accionTradeProductoTO : lista) {
					descargaDAO.insertAccionTradeProducto(accionTradeProductoTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveAccionTradeProducto", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		}
	}
	
	public void saveCliente(List<ClienteDescargaTO> lista){
		synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				descargaDAO.deleteCliente();
				for (ClienteDescargaTO clienteDescargaTO : lista) {
					descargaDAO.insertCliente(clienteDescargaTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveCliente", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		}
	}
	
}
