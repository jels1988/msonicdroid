package pe.lindley.preliquidacion.negocio;


import java.util.ArrayList;
import java.util.List;

import pe.lindley.preliquidacion.dao.DocumentoDAO;
import pe.lindley.preliquidacion.to.DocumentoResponseTO;
import pe.lindley.preliquidacion.to.DocumentoTO;
import net.msonic.lib.DBHelper;

import android.util.Log;

import com.google.inject.Inject;

public class DocumentoBLL {

	public static final String ESTADO_RECHAZO="1";
	public static final String ESTADO_ENTREGA_TOTAL="2";
	public static final String ESTADO_PENDIENTE="0";
	
	@Inject protected DBHelper dbHelper;
	@Inject protected DocumentoDAO documentoDAO;
	

	public void entregarDocumento(List<DocumentoTO> documentos){
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			for (DocumentoTO documentoTO : documentos) {
				documentoTO.setEstado(ESTADO_ENTREGA_TOTAL);
				documentoDAO.update(documentoTO);
			}
			dbHelper.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(DocumentoBLL.class.toString(),"entregarDocumento", e);
		}finally{
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	public void entregarDocumento(DocumentoTO documentoTO){
		try {
			dbHelper.openDataBase();
			documentoTO.setEstado(ESTADO_ENTREGA_TOTAL);
			documentoDAO.update(documentoTO);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(DocumentoBLL.class.toString(),"entregarDocumento", e);
		}finally{
			dbHelper.close();
		}
	}
	
	public void rechazarDocumento(DocumentoTO documentoTO){
		try {
			dbHelper.openDataBase();
			documentoTO.setEstado(ESTADO_RECHAZO);
			documentoDAO.update(documentoTO);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(DocumentoBLL.class.toString(),"rechazarDocumento", e);
		}finally{
			dbHelper.close();
		}
	}
	
	public void activarDocumento(DocumentoTO documentoTO){
		try {
			dbHelper.openDataBase();
			documentoTO.setEstado(ESTADO_PENDIENTE);
			documentoDAO.update(documentoTO);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(DocumentoBLL.class.toString(),"activarDocumento", e);
		}finally{
			dbHelper.close();
		}
	}
	
	public boolean descargarDocumentos(List<DocumentoTO> documentos){
		boolean rp = false;
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			documentoDAO.deleteAll();
			for (DocumentoTO documentoTO : documentos) {
				documentoDAO.insert(documentoTO);
			}
			dbHelper.setTransactionSuccessful();
			rp=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(DocumentoBLL.class.toString(),"descargarDocumentos", e);
		}finally{
			dbHelper.endTransaction();
			dbHelper.close();
		}
		return rp;
	}
	
	public ArrayList<DocumentoTO> consultarDocumentoxCodigo(String codigo){
		
		ArrayList<DocumentoTO> documentos=null;
		try{
			dbHelper.openDataBase();
			documentos = documentoDAO.listarxCodigo(codigo);
		}catch (Exception e) {
			Log.e(DocumentoBLL.class.toString(),"consultarDocumento", e);
		}finally{
			dbHelper.close();
		}
		
		return documentos;
	}
	
	public String consultarCodigoCliente(String nroDocumento){
		String codigo=null; 
		try{
			dbHelper.openDataBase();
			codigo = documentoDAO.getCodigoCliente(nroDocumento);
					
		}catch (Exception e) {
			Log.e(DocumentoBLL.class.toString(),"consultarCodigoCliente", e);
		}finally{
			dbHelper.close();
		}
		
		return codigo;
	}
	
	public void cerrarDocumento(List<DocumentoResponseTO> documentos){
		try {
			dbHelper.openDataBase();
			for (DocumentoResponseTO documentoResponseTO : documentos) {
				documentoDAO.updateEstado(documentoResponseTO);
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(DocumentoBLL.class.toString(),"cerrarDocumento", e);
		}finally{
			dbHelper.close();
		}
	}
	
	public ArrayList<DocumentoTO> consultarDocumento(){
		ArrayList<DocumentoTO> documentos=null;
		try{
			dbHelper.openDataBase();
			documentos = documentoDAO.listarDocumento();
		}catch (Exception e) {
			Log.e(DocumentoBLL.class.toString(),"consultarDocumento", e);
		}finally{
			dbHelper.close();
		}
		
		return documentos;
	}
}
