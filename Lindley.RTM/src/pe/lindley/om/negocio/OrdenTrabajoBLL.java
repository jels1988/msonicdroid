package pe.lindley.om.negocio;

import java.util.ArrayList;
import java.util.List;

import pe.lindley.om.dao.EventoDAO;
import pe.lindley.om.dao.OrdenTrabajoDAO;
import pe.lindley.om.to.EventoTO;
import pe.lindley.om.to.OrdenTrabajoTO;
import pe.lindley.util.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class OrdenTrabajoBLL {

	public static final String CREADO = "01";
	public static final String CREADO_DES = "CREADO";
    public static final String PROCESO = "02";
    public static final String PROCESO_DES = "EN PROCESO";
    public static final String CERRADO = "03";
    public static final String CERRADO_DES = "CERRADO";
    
    public static final String EVENTO_ASIGNADO = "02";
    public static final String EVENTO_ASIGNADO_DES = "ACTIV ASIGNADA";
    public static final String EVENTO_REPLANIFICADO = "04";
    public static final String EVENTO_RECHAZADO = "03";
    public static final String EVENTO_EJECUTADO = "01";
    public static final String EVENTO_ANULAD0 = "05";
    
    
	private static final String TAG_LOG = OrdenTrabajoBLL.class.getCanonicalName();
	@Inject protected DBHelper dbHelper;
	@Inject protected OrdenTrabajoDAO ordenTrabajoDAO;
	@Inject protected EventoDAO eventoDAO;
	
	
	public String getEstadoOrden(long ordenTrabajoId){
		String estado="";
		try {
			dbHelper.openDataBase();
			estado = ordenTrabajoDAO.getEstadoOrden(ordenTrabajoId);
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "getEstadoOrden", e);
		} finally {
			dbHelper.close();
		}
		return estado;
	}
	
	public ArrayList<OrdenTrabajoTO> listPendientes(){
		ArrayList<OrdenTrabajoTO> ordenes = null;
		try {
			dbHelper.openDataBase();
			ordenes = ordenTrabajoDAO.listPendientes();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}
		
		return ordenes;
	}
	
	public ArrayList<OrdenTrabajoTO> list(String codigoCliente,String estado,String fecha){
		String valores[] = fecha.split("/");
		String fechaFormateada = String.format("%s%s%s", 
									valores[2],
									String.format("%2s", valores[1]).replace(' ', '0'),
									String.format("%2s", valores[0]).replace(' ', '0'));
	
		
		ArrayList<OrdenTrabajoTO> ordenes = null;
		try {
			dbHelper.openDataBase();
			ordenes = ordenTrabajoDAO.list(codigoCliente, estado, fechaFormateada);
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}
		
		return ordenes;
	}
	
	public OrdenTrabajoTO list(long ordenTrabajoId){
		OrdenTrabajoTO ordenTrabajoTO=null;
		try {
			dbHelper.openDataBase();
			ordenTrabajoTO = ordenTrabajoDAO.list(ordenTrabajoId);
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}
		return ordenTrabajoTO;
	}
	
	public void deleteAllPendientesWithEventos(){
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			ordenTrabajoDAO.deleteAllPendientesWithEventos();
		
			dbHelper.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "deleteAllPendientesWithEventos", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	public ArrayList<OrdenTrabajoTO> listAllWithEventos(){
		ArrayList<OrdenTrabajoTO> ordenes = null;
		try {
			dbHelper.openDataBase();
			ordenes = ordenTrabajoDAO.listAllWithEventos();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "listAllWithEventos", e);
		} finally {
			dbHelper.close();
		}
		return ordenes;
	}
	
	public OrdenTrabajoTO listWithEventos(long ordenTrabajoId){
		OrdenTrabajoTO ordenTrabajoTO=null;
		try {
			dbHelper.openDataBase();
			ordenTrabajoTO = ordenTrabajoDAO.listWithEventos(ordenTrabajoId);
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}
		return ordenTrabajoTO;
	}
	
	public void delete(long ordenTrabajoId){
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			eventoDAO.deleteEventos(ordenTrabajoId);
			ordenTrabajoDAO.delete(ordenTrabajoId);
			
			dbHelper.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "delete", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	public void deletePendientesWithEventos(long ordenTrabajoId){
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			ordenTrabajoDAO.delete(ordenTrabajoId);
		
			dbHelper.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "deletePendientesWithEventos", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	public void save(OrdenTrabajoTO ordenTrabajoTO){			
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			ordenTrabajoDAO.save(ordenTrabajoTO);
			
			long nuOrden = ordenTrabajoTO.getNuOrd();
			for (EventoTO eventoTO : ordenTrabajoTO.getEventos()) {
				eventoDAO.save(nuOrden, eventoTO);
			}
			
			dbHelper.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "save", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}

	public void save(List<OrdenTrabajoTO> ordenes){			
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			ordenTrabajoDAO.delete();
			
			for (OrdenTrabajoTO to : ordenes) {
				to.setCdStA(1);
				ordenTrabajoDAO.save(to);
			}
		
			
		
			dbHelper.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "save", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	
	public void saveEventos(List<EventoTO> eventos){			
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			eventoDAO.deleteEventos();
			
			for (EventoTO to : eventos) {
				long ordenTrabajoId = ordenTrabajoDAO.getOrdenTrabajoId(to.getNuOrd());
				eventoDAO.save(ordenTrabajoId, to);
			}
			
			dbHelper.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "save", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	public void update(OrdenTrabajoTO ordenTrabajoTO){			
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			ordenTrabajoDAO.update(ordenTrabajoTO);
			
			long nuOrden = ordenTrabajoTO.getNuOrd();
			
			for (EventoTO eventoTO : ordenTrabajoTO.getEventos()) {
				eventoDAO.save(nuOrden, eventoTO);
			}
			
			dbHelper.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "update", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	public ArrayList<EventoTO> listEventos(long ordenTrabajoId){
		ArrayList<EventoTO> eventos = null;
		try {
			dbHelper.openDataBase();
			eventos = eventoDAO.list(ordenTrabajoId);
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "listEventos", e);
		} finally {
			dbHelper.close();
		}
		
		return eventos;
	}
}
