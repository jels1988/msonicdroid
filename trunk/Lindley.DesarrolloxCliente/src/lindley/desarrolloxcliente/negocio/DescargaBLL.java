package lindley.desarrolloxcliente.negocio;

import java.util.List;

import lindley.desarrolloxcliente.dao.DescargaDAO;
import lindley.desarrolloxcliente.to.download.AccionTradeProductoTO;
import lindley.desarrolloxcliente.to.download.AccionTradeTO;
import lindley.desarrolloxcliente.to.download.ClienteDescargaTO;
import lindley.desarrolloxcliente.to.download.OportunidadTO;
import lindley.desarrolloxcliente.to.download.PosicionTO;
import lindley.desarrolloxcliente.to.download.PresentacionTO;
import lindley.desarrolloxcliente.to.download.ProductoTO;
import lindley.desarrolloxcliente.to.download.PuntoTO;
import lindley.desarrolloxcliente.to.download.SkuTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class DescargaBLL {

	private static final String TAG_LOG = ClienteBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected DescargaDAO descargaDAO;
	public static String MyLock = "DescargaBLL.Lock";
	
	public void saveEvaluacionPosicionCompromiso(List<lindley.desarrolloxcliente.to.upload.PosicionCompromisoTO> lista){
		synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				
				descargaDAO.deleteEvaluacionPosicionCompromiso();
				for (lindley.desarrolloxcliente.to.upload.PosicionCompromisoTO posicion : lista) {
					descargaDAO.insertEvaluacionPosicionCompromiso(posicion);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveEvaluacionPosicionCompromiso", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		}
	}
	
	public void saveEvaluacionSkus(List<lindley.desarrolloxcliente.to.upload.SkuTO> lista){
		synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				
				descargaDAO.deleteEvaluacionSkus();
				for (lindley.desarrolloxcliente.to.upload.SkuTO skuTO : lista) {
					descargaDAO.insertEvaluacionSkus(skuTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveEvaluacionSkus", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		}
	}
	
	public void saveEvaluacionPresentacion(List<lindley.desarrolloxcliente.to.upload.PresentacionTO> lista){
		synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				
				descargaDAO.deleteEvaluacionPresentacion();
				for (lindley.desarrolloxcliente.to.upload.PresentacionTO presentacionTO : lista) {
					descargaDAO.insertEvaluacionPresentacion(presentacionTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveEvaluacionPresentacion", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		}
	}
	
	public void saveEvaluacionPosicion(List<lindley.desarrolloxcliente.to.upload.PosicionTO> lista){
		synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				
				descargaDAO.deleteEvaluacionPosicion();
				for (lindley.desarrolloxcliente.to.upload.PosicionTO posicionTO : lista) {
					descargaDAO.insertEvaluacionPosicion(posicionTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveEvaluacionPosicion", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		}
	}
	
	public void saveEvaluacionOportunidad(List<lindley.desarrolloxcliente.to.upload.OportunidadTO> lista){
		synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				
				descargaDAO.deleteEvaluacionOportunidad();
				for (lindley.desarrolloxcliente.to.upload.OportunidadTO oportunidadTO : lista) {
					descargaDAO.insertEvaluacionOportunidad(oportunidadTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.insertEvaluacionOportunidad", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		}
	}
	
	public void saveEvaluacion(List<EvaluacionTO> lista){
		synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				descargaDAO.deleteEvaluacion();
				for (EvaluacionTO evaluacionTO : lista) {
					descargaDAO.insertEvaluacion(evaluacionTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveEvaluacion", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		}
	}
	
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
	
	
	public void savePosicion(List<PosicionTO> lista){
		synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				descargaDAO.deletePosicion();
				for (PosicionTO posicionTO : lista) {
					descargaDAO.insertPosicion(posicionTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.savePosicion", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		}
	}
	
	public void savePresentacion(List<PresentacionTO> lista){
		synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				descargaDAO.deletePresentacion();
				for (PresentacionTO presentacionTO : lista) {
					descargaDAO.insertPresentacion(presentacionTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.savePresentacion", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		}
	}
	
	public void savePunto(List<PuntoTO> lista){
		synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				descargaDAO.deletePunto();
				for (PuntoTO puntoTO : lista) {
					descargaDAO.insertPunto(puntoTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.savePunto", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		}
	}
	
}
