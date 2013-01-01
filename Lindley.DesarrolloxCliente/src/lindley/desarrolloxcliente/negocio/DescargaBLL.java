package lindley.desarrolloxcliente.negocio;

import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.dao.DescargaDAO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.ResumenValueTO;
import lindley.desarrolloxcliente.to.UsuarioTO;
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
import lindley.desarrolloxcliente.to.upload.PosicionCompromisoTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class DescargaBLL {

	private static final String TAG_LOG = ClienteBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected DescargaDAO descargaDAO;
	public static String MyLock = "DescargaBLL.Lock";
	
	
	public void saveMotivo(List<ResumenValueTO> lista){
		//synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				
				descargaDAO.deleteMotivo();
				
				for (ResumenValueTO resumenValueTO : lista) {
					descargaDAO.insertMotivo(resumenValueTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveMotivo", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
			//}
	}
	
	
	public void saveArticulo(List<lindley.desarrolloxcliente.to.download.ArticuloTO> lista){
		//synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				
				descargaDAO.deleteArticuloCanje();
				
				for (lindley.desarrolloxcliente.to.download.ArticuloTO articuloTO : lista) {
					descargaDAO.insertArticuloCanje(articuloTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveArticulo", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
			//}
	}
	
	public void saveAcelerador(List<lindley.desarrolloxcliente.to.download.AceleradorTO> lista){
		//synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				
				descargaDAO.deleteAcelerador();
				
				for (lindley.desarrolloxcliente.to.download.AceleradorTO aceleradorTO : lista) {
					descargaDAO.insertAcelerador(aceleradorTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveAcelerador", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
			//}
	}
	
	
	public void saveProfitCliente(String codigoCliente, List<lindley.desarrolloxcliente.to.download.ProfitTO> lista){
		//synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				
				descargaDAO.deleteProfitCliente(codigoCliente);
				for (lindley.desarrolloxcliente.to.download.ProfitTO profit : lista) {
					descargaDAO.insertProfit(profit);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveProfitCliente", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
			//}
	}
	
	public void saveProfit(List<lindley.desarrolloxcliente.to.download.ProfitTO> lista){
		//synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				
				descargaDAO.deleteProfit();
				for (lindley.desarrolloxcliente.to.download.ProfitTO profit : lista) {
					descargaDAO.insertProfit(profit);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveProfit", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
			//}
	}
	
	public void saveEvaluacionPosicionCompromiso(List<lindley.desarrolloxcliente.to.upload.PosicionCompromisoTO> lista){
		//synchronized(MyLock)	{
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
			//}
	}
	
	public void saveEvaluacionSkus(List<lindley.desarrolloxcliente.to.upload.SkuTO> lista){
		//synchronized(MyLock)	{
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
			//}
	}
	
	public void saveEvaluacionPresentacion(List<lindley.desarrolloxcliente.to.upload.PresentacionTO> lista){
		//synchronized(MyLock)	{
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
			//}
	}
	
	public void saveEvaluacionPosicion(List<lindley.desarrolloxcliente.to.upload.PosicionTO> lista){
		//synchronized(MyLock)	{
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
			//}
	}
	
	public void saveEvaluacionOportunidad(List<lindley.desarrolloxcliente.to.upload.OportunidadTO> lista){
		//synchronized(MyLock)	{
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
			//}
	}
	
	public void cerrarEnCero(EvaluacionTO evaluacionTO,UsuarioTO usuarioTO){
		try{
			
			evaluacionTO.fechaCierre=ConstantesApp.getFechaSistemaAS400();
			evaluacionTO.horaCierre=ConstantesApp.getHoraSistemaAS400();
			evaluacionTO.usuarioCierre=usuarioTO.codigoSap;
			evaluacionTO.tieneCambio=ConstantesApp.EVALUACION_TIENE_CAMBIOS;
			evaluacionTO.estado = ConstantesApp.OPORTUNIDAD_CERRADA;
			
			for (lindley.desarrolloxcliente.to.upload.OportunidadTO oportunidadTO : evaluacionTO.oportunidades) {
				oportunidadTO.concrecionCumple=ConstantesApp.RESPUESTA_NO;
				oportunidadTO.soviCumple=ConstantesApp.RESPUESTA_NO;
				oportunidadTO.respetoPrecioCumple =ConstantesApp.RESPUESTA_NO;
				oportunidadTO.respetoPrecioCumple =ConstantesApp.RESPUESTA_NO;
				oportunidadTO.puntosGanados="0";
			}
			
			for (lindley.desarrolloxcliente.to.upload.PosicionTO posicionTO : evaluacionTO.posiciones) {
				posicionTO.confirmacion=ConstantesApp.RESPUESTA_NO;
				posicionTO.puntosGanados="0";
			}
			
			for (lindley.desarrolloxcliente.to.upload.PresentacionTO presentacionTO : evaluacionTO.presentaciones) {
				presentacionTO.confirmacion=ConstantesApp.RESPUESTA_NO;
				presentacionTO.puntosGanados="0";
			}
			
			
			
			
			saveEvaluacion(evaluacionTO);
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "UploadBLL.cerrarEnCero", ex);
		}
	}
	
	public void cerrarEvaluacion(EvaluacionTO evaluacionTO,UsuarioTO usuarioTO,ClienteTO clienteTO){
		try{
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			evaluacionTO.tieneCambio=ConstantesApp.EVALUACION_TIENE_CAMBIOS;
			evaluacionTO.estado = ConstantesApp.OPORTUNIDAD_CERRADA;
			evaluacionTO.fechaCierre = ConstantesApp.getFechaSistemaAS400();
			evaluacionTO.horaCierre = ConstantesApp.getHoraSistemaAS400();
			evaluacionTO.usuarioCierre = usuarioTO.codigoSap;
			
			long puntosGanados =0;
			long puntosSugeridos=0;
			if(evaluacionTO.id!=0){
				descargaDAO.deleteEvaluacion(evaluacionTO.id);
			}
			
			descargaDAO.insertEvaluacion(evaluacionTO);
			
			int factorOportunidad = descargaDAO.factorCierre(ConstantesApp.TIPO_AGRUPRACION_INVENTARIO, ConstantesApp.VARIABLE_RED_SKU_PRIORITARIOS, evaluacionTO.fechaCierre);
			
			for (lindley.desarrolloxcliente.to.upload.OportunidadTO oportunidadTO : evaluacionTO.oportunidades) {
				if(ConstantesApp.isSI(oportunidadTO.concrecionCumple) && 
				   ConstantesApp.isSI(oportunidadTO.soviCumple) && 
				   ConstantesApp.isSI(oportunidadTO.respetoPrecioCumple) &&
				   ConstantesApp.isSI(oportunidadTO.numeroSaboresCumple)){
					
					puntosSugeridos=Integer.parseInt(oportunidadTO.puntosSugeridos) * factorOportunidad;
					puntosGanados=+puntosSugeridos;
					oportunidadTO.puntosGanados = String.valueOf(puntosSugeridos);
				}
				descargaDAO.insertEvaluacionOportunidad(evaluacionTO,oportunidadTO);
			}
			
			for (lindley.desarrolloxcliente.to.upload.PosicionTO posicionTO : evaluacionTO.posiciones) {
				
				int factorPosicion= descargaDAO.factorCierre(ConstantesApp.TIPO_AGRUPRACION_POSICION,posicionTO.codigoVariable, evaluacionTO.fechaCierre);
				
				
				if(ConstantesApp.isSI(posicionTO.confirmacion)){
					puntosSugeridos = Integer.parseInt(posicionTO.puntosSugeridos) * factorPosicion;
					puntosGanados=+puntosSugeridos;
					posicionTO.puntosGanados = String.valueOf(puntosSugeridos);
				}
				
				descargaDAO.insertEvaluacionPosicion(evaluacionTO,posicionTO);
				for(PosicionCompromisoTO posicionCompromisoTO:posicionTO.compromisos){
					descargaDAO.insertEvaluacionPosicion(evaluacionTO, posicionCompromisoTO);
				}
			}
			
			
			for (lindley.desarrolloxcliente.to.upload.PresentacionTO presentacionTO : evaluacionTO.presentaciones) {
				
				int factorPresentacion= descargaDAO.factorCierre(ConstantesApp.TIPO_AGRUPRACION_PRESENTACION,presentacionTO.codigoVariable, evaluacionTO.fechaCierre);
				if(ConstantesApp.isSI(presentacionTO.confirmacion)){
					puntosSugeridos = Integer.parseInt(presentacionTO.puntosSugeridos) * factorPresentacion;
					puntosGanados=+puntosSugeridos;
					presentacionTO.puntosGanados = String.valueOf(puntosSugeridos);
				}
				
				descargaDAO.insertEvaluacionPresentacion(evaluacionTO,presentacionTO);
			}
			
			for (lindley.desarrolloxcliente.to.upload.SkuTO skuTO : evaluacionTO.skus) {
				descargaDAO.insertEvaluacionSkus(evaluacionTO,skuTO);
			}
			
			descargaDAO.actualizarPuntos(clienteTO.codigo,clienteTO.nroPuntos + puntosGanados);
			
			
			dbHelper.setTransactionSuccessful();
		}catch(Exception ex){
			Log.e(TAG_LOG, "DescargaBLL.cerrarEvaluacion", ex);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	public void saveEvaluacion(EvaluacionTO evaluacionTO){
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				evaluacionTO.tieneCambio=ConstantesApp.EVALUACION_TIENE_CAMBIOS;
				
				if(evaluacionTO.id!=0){
					descargaDAO.deleteEvaluacion(evaluacionTO.id);
				}
				
				descargaDAO.insertEvaluacion(evaluacionTO);
				
				for (lindley.desarrolloxcliente.to.upload.OportunidadTO oportunidadTO : evaluacionTO.oportunidades) {
					descargaDAO.insertEvaluacionOportunidad(evaluacionTO,oportunidadTO);
				}
				
				for (lindley.desarrolloxcliente.to.upload.PosicionTO posicionTO : evaluacionTO.posiciones) {
					descargaDAO.insertEvaluacionPosicion(evaluacionTO,posicionTO);
					for(PosicionCompromisoTO posicionCompromisoTO:posicionTO.compromisos){
						descargaDAO.insertEvaluacionPosicion(evaluacionTO, posicionCompromisoTO);
					}
				}
				
				
				for (lindley.desarrolloxcliente.to.upload.PresentacionTO presentacionTO : evaluacionTO.presentaciones) {
					descargaDAO.insertEvaluacionPresentacion(evaluacionTO,presentacionTO);
				}
				
				for (lindley.desarrolloxcliente.to.upload.SkuTO skuTO : evaluacionTO.skus) {
					descargaDAO.insertEvaluacionSkus(evaluacionTO,skuTO);
				}
				
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveEvaluacion", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
		
	}
	
	public void saveEvaluacion(List<EvaluacionTO> lista){
		//synchronized(MyLock)	{
			try{
				dbHelper.openDataBase();
				dbHelper.beginTransaction();
				descargaDAO.deleteEvaluacion();
				for (EvaluacionTO evaluacionTO : lista) {
					evaluacionTO.tieneCambio=ConstantesApp.EVALUACION_NO_TIENE_CAMBIOS;
					descargaDAO.insertEvaluacion(evaluacionTO);
				}
				dbHelper.setTransactionSuccessful();
			}catch(Exception ex){
				Log.e(TAG_LOG, "DescargaBLL.saveEvaluacion", ex);
			} finally {
				dbHelper.endTransaction();
				dbHelper.close();
			}
			//}
	}
	
	public void saveProducto(List<ProductoTO> lista){
		//synchronized(MyLock)	{
			
		
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
			//}
	}
	
	public void saveOportunidad(List<OportunidadTO> lista){
		//synchronized(MyLock)	{
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
			//}
	}
	
	public void saveSku(List<SkuTO> lista){
		//synchronized(MyLock)	{
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
			//}
	}
	
	public void saveAccionTrade(List<AccionTradeTO> lista){
		//synchronized(MyLock)	{
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
			//}
	}
	
	
	public void saveAccionTradeProducto(List<AccionTradeProductoTO> lista){
		//synchronized(MyLock)	{
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
			//}
	}
	
	public void saveCliente(List<ClienteDescargaTO> lista){
		//synchronized(MyLock)	{
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
			//}
	}
	
	
	public void savePosicion(List<PosicionTO> lista){
		//synchronized(MyLock)	{
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
			//}
	}
	
	public void savePresentacion(List<PresentacionTO> lista){
		//synchronized(MyLock)	{
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
		//}
	}
	
	public void savePunto(List<PuntoTO> lista){
		//synchronized(MyLock)	{
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
		//}
	}
	
}
