package pe.lindley.prospector.negocio;

import java.util.ArrayList;

import pe.lindley.lanzador.to.UsuarioTO;
import pe.lindley.prospector.dao.ClienteDAO;
import pe.lindley.prospector.to.ClienteTO;
import pe.lindley.prospector.to.DocumentoEnviarTO;
import pe.lindley.prospector.to.DocumentoTO;
import pe.lindley.prospector.to.FileTO;
import pe.lindley.util.DBHelper;
import android.util.Log;

import com.google.inject.Inject;

public class ClienteBLL {

	private static final String TAG_LOG = ClienteBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected ClienteDAO clienteDAO;

	public ArrayList<FileTO> listarDocumentosEnviar(){
		ArrayList<FileTO> archivos = null;
		
		try {
			dbHelper.openDataBase();
			archivos = clienteDAO.listarDocumentosEnviar();
		} catch (Exception e) {
			Log.e(TAG_LOG, "listarDocumentosEnviar", e);
		} finally {
			dbHelper.close();
		}
		
		return archivos;
	}
	
	public void deleteDocumento(long id){
		try {
			dbHelper.openDataBase();
			clienteDAO.deleteDocumento(id);
		} catch (Exception e) {
			Log.e(TAG_LOG, "deleteDocumento", e);
		} finally {
			dbHelper.close();
		}
	}
	
	public long guardarDocumento(int clienteId,DocumentoTO documentoTO){
		long id = documentoTO.getId();
		
		try {
			dbHelper.openDataBase();
			if(id==0){
				id = clienteDAO.insertDocumento(clienteId, documentoTO);
				documentoTO.setId(id);
			}else{
				clienteDAO.updateDocumento(clienteId, documentoTO);
			}
		} catch (Exception e) {
			Log.e(TAG_LOG, "guardarDocumento", e);
		} finally {
			dbHelper.close();
		}
		return id;
	}
	
	public ArrayList<DocumentoTO> listarDocumentos(){
		ArrayList<DocumentoTO> documentos = new ArrayList<DocumentoTO>();
		
		try {
			dbHelper.openDataBase();
			documentos = clienteDAO.listarDocumentos();
		} catch (Exception e) {
			Log.e(TAG_LOG, "listarDocumentosTodos", e);
		} finally {
			dbHelper.close();
		}
		
		return documentos;
		
	}
	
	public ArrayList<DocumentoTO> listarDocumentos(int id){
		ArrayList<DocumentoTO> documentos = new ArrayList<DocumentoTO>();
		
		try {
			dbHelper.openDataBase();
			documentos = clienteDAO.listarDocumentos(id);
		} catch (Exception e) {
			Log.e(TAG_LOG, "listarDocumentos", e);
		} finally {
			dbHelper.close();
		}
		
		return documentos;
		
	}
	
	public ClienteTO listById(int id) {
		ClienteTO clienteTOBD=null;
		try {
			dbHelper.openDataBase();
			clienteTOBD = clienteDAO.listById(id);
		} catch (Exception e) {
			Log.e(TAG_LOG, "listById", e);
		} finally {
			dbHelper.close();
		}
		
		return clienteTOBD;
	}
	public void deleteAll() {
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			clienteDAO.copiarTodosDocumentos();
			clienteDAO.deleteAll();
			dbHelper.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e(TAG_LOG, "deleteAll", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	public boolean delete(int id) {
		boolean rp=false;
		try {
			dbHelper.openDataBase();
			clienteDAO.delete(id);
			rp=true;
		} catch (Exception e) {
			Log.e(TAG_LOG, "delete", e);
		} finally {
			dbHelper.close();
		}
		
		return rp;
	}
	
	public ArrayList<ClienteTO> list(String codigo,String dni,String ruc) {
		ArrayList<ClienteTO> listado = null;
		try {
			dbHelper.openDataBase();
			listado = clienteDAO.list(dni,ruc,codigo);
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}

		return listado;
	}

	
	public ArrayList<ClienteTO> list() {
		ArrayList<ClienteTO> listado = null;
		try {
			dbHelper.openDataBase();
			listado = clienteDAO.list();
			
			for (ClienteTO clienteTO : listado) {
				clienteTO.setDocumentos(clienteDAO.listarDocumentosEnviarxFicha(clienteTO.getClienteId()));
			}
		} catch (Exception e) {
			Log.e(TAG_LOG, "list", e);
		} finally {
			dbHelper.close();
		}

		return listado;
	}

	public void save(ClienteTO clienteTO, UsuarioTO usuarioTO) {
		int clienteId = clienteTO.getClienteId();
		try {
			dbHelper.openDataBase();
			clienteTO.setTieneCambios(ClienteTO.FICHA_TIENE_CAMBIOS);
			if(clienteId==0){
				clienteDAO.save(clienteTO, usuarioTO);
			}else{
				clienteDAO.update(clienteTO, usuarioTO);
			}
		} catch (Exception e) {
			Log.e(TAG_LOG, "Save", e);
		} finally {
			dbHelper.close();
		}
	}
	
	public void saveFichasRechazadas(ArrayList<ClienteTO> fichasRechazadas,UsuarioTO usuarioTO){
		
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			clienteDAO.deleteRechazadas();
			
			for (ClienteTO clienteTO : fichasRechazadas) {
				clienteTO.setAccion(ClienteTO.ACCION_RECHAZO);
				clienteTO.setTieneCambios(ClienteTO.FICHA_SIN_CAMBIOS);
				clienteDAO.save(clienteTO,usuarioTO);
				
 
				 
				for (DocumentoEnviarTO documentoTO : clienteTO.getDocumentos()) {
					DocumentoTO doc = new DocumentoTO();
					doc.setClienteId(clienteTO.getClienteId());
					doc.setDocumentoId(documentoTO.tipoDocumento);
					doc.setNombreArchivo(documentoTO.nombre);
					doc.setEsLocal(DocumentoTO.SERVER);
					clienteDAO.insertDocumento(clienteTO.getClienteId(),doc);
				}
			}
			
			dbHelper.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "Save", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
		
	}
}
