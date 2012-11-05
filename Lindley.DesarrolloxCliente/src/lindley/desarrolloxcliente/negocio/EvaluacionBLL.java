package lindley.desarrolloxcliente.negocio;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.dao.EvaluacionDAO;
import lindley.desarrolloxcliente.to.ClienteTO;
import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.OportunidadTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;
import lindley.desarrolloxcliente.to.SKUPresentacionTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class EvaluacionBLL {

	private static final String TAG_LOG = EvaluacionBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	
	@Inject protected EvaluacionDAO evaluacionDAO;
	
	public void save(EvaluacionTO evaluacionTO){
		
		try{
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			evaluacionDAO.insertEvaluacion(evaluacionTO);
			
			for (OportunidadTO oportunidadTO : evaluacionTO.oportunidades) {
				evaluacionDAO.insertOportunidad(evaluacionTO, oportunidadTO);
			}
			
			for(PosicionCompromisoTO posicionCompromisoTO:evaluacionTO.posiciones){
				evaluacionDAO.insertOportunidadPosicion(evaluacionTO, posicionCompromisoTO);
			}
			
			for(PresentacionCompromisoTO presentacionCompromisoTO:evaluacionTO.presentaciones){
				evaluacionDAO.insertOportunidadPresentacion(evaluacionTO, presentacionCompromisoTO);
			}
			
			for(SKUPresentacionTO skuPresentacionTO:evaluacionTO.skuPresentacion){
				evaluacionDAO.insertSKUPresentacion(evaluacionTO, skuPresentacionTO);
			}
			dbHelper.setTransactionSuccessful();
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "EvaluacionBLL.save", ex);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	
	public EvaluacionTO GetById(long id){
		EvaluacionTO evaluacionTO = null;
		
		try{
			dbHelper.openDataBase();
			
			evaluacionTO = evaluacionDAO.GetById(id);
			evaluacionDAO.GetOportunidades(evaluacionTO);
			evaluacionDAO.GetSKUPresentacion(evaluacionTO);
			
			
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "EvaluacionBLL.GetById", ex);
		} finally {
			dbHelper.close();
		}	
		
		return evaluacionTO;
	}
	
	public List<EvaluacionTO> List(String codigoCliente){
		
		List<EvaluacionTO> evaluaciones = new ArrayList<EvaluacionTO>();
		try{
			dbHelper.openDataBase();
			evaluaciones = evaluacionDAO.List(codigoCliente);
		}catch(Exception ex){
			Log.e(TAG_LOG, "EvaluacionBLL.GetById", ex);
		} finally {
			dbHelper.close();
		}	
		
		return evaluaciones;
	}
	
	public void asignarPuntos(EvaluacionTO evaluacionTO,ClienteTO clienteTO){
		List<Integer> puntosInventario=null;
		List<Integer> puntosPosicion=null;
		List<Integer> puntosPresentacion=null;
		
		try{
			dbHelper.openDataBase();
			puntosInventario = evaluacionDAO.listarPuntosInventario();
			
			String variableRED = (evaluacionTO.activosLindley.equals(ConstantesApp.RESPUESTA_SI)?ConstantesApp.VARIABLE_RED_ESTANDAR_EXHIBICION:ConstantesApp.VARIABLE_RED_ESTANDAR_ANAQUEL);
			
			puntosPosicion = evaluacionDAO.listarPuntos(clienteTO.tppro,
													ConstantesApp.TIPO_AGRUPRACION_POSICION,
													variableRED,
													evaluacionTO.activosLindley);	
			
			puntosPresentacion = evaluacionDAO.listarPuntos(clienteTO.tppro,
															ConstantesApp.TIPO_AGRUPRACION_PRESENTACION,
															ConstantesApp.VARIABLE_RED_PRECIO_MERCADO,
					 										null);
			
		}catch(Exception ex){
			Log.e(TAG_LOG, "EvaluacionBLL.asignarPuntos", ex);
		} finally {
			dbHelper.close();
		}	
		
		if((puntosInventario!=null) && (puntosInventario.size()>=evaluacionTO.oportunidades.size())){
			int i=0;
			for (OportunidadTO oportunidadTO : evaluacionTO.oportunidades) {
				oportunidadTO.puntosSugeridos = puntosInventario.get(i).toString();
				oportunidadTO.puntosCocaCola = puntosInventario.get(i).toString();
				i++;
			}
		}
		
		if((puntosPosicion!=null) && (puntosPosicion.size()>=evaluacionTO.presentaciones.size())){
			int i=0;
			for (PosicionCompromisoTO compromisoTO  : evaluacionTO.posiciones) {
				compromisoTO.puntosSugeridos = puntosPosicion.get(i).toString();
				compromisoTO.puntosGanados = puntosPosicion.get(i).toString();
				i++;
			}
		}
		
		if((puntosPresentacion!=null) && (puntosPresentacion.size()>=evaluacionTO.posiciones.size())){
			
			int i=0;
			for (PresentacionCompromisoTO presentacionTO : evaluacionTO.presentaciones) {
				presentacionTO.puntosSugeridos = puntosPresentacion.get(i).toString();
				presentacionTO.puntosGanados = puntosPresentacion.get(i).toString();
				i++;
			}
		}
		
	}
	
	
}