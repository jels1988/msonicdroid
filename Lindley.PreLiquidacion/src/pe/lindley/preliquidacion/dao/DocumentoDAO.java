package pe.lindley.preliquidacion.dao;


import java.util.ArrayList;

import pe.lindley.preliquidacion.to.DocumentoResponseTO;
import pe.lindley.preliquidacion.to.DocumentoTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class DocumentoDAO {

	@Inject protected DBHelper dbHelper;
	
	
	public void deleteAll() {
		dbHelper.getDataBase().execSQL("delete from documento");
	}
	
	
	public void insert(DocumentoTO documentoTO){
		ContentValues parametros = new ContentValues();
		parametros.put("cliente",documentoTO.getCliente());
		parametros.put("nombre",documentoTO.getNombre());
		parametros.put("nrodocumento",documentoTO.getNroDocumento());
		parametros.put("importe",documentoTO.getImporte());
		parametros.put("tipodocumento",documentoTO.getTipoDocumento());
		parametros.put("estado",documentoTO.getEstado());
		parametros.put("enviado",documentoTO.getEnviado());
		dbHelper.getDataBase().insertOrThrow("documento", null, parametros);
	}
	
	public void updateState(DocumentoTO documentoTO){
		ContentValues parametros = new ContentValues();
		parametros.put("estado",documentoTO.getEstado());
		
		String[] valores = new String[] { String.valueOf(documentoTO.getDocumentoId()) };

		dbHelper.getDataBase().update("documento", parametros, "documentoId = ?",valores);
	}
	
	public void update(DocumentoTO documentoTO){
		ContentValues parametros = new ContentValues();
		parametros.put("cliente",documentoTO.getCliente());
		parametros.put("nombre",documentoTO.getNombre());
		parametros.put("nrodocumento",documentoTO.getNroDocumento());
		parametros.put("importe",documentoTO.getImporte());
		parametros.put("estado",documentoTO.getEstado());
		parametros.put("enviado",documentoTO.getEnviado());
		
		String[] valores = new String[] { String.valueOf(documentoTO.getDocumentoId()) };

		dbHelper.getDataBase().update("documento", parametros, "documentoId = ?",
				valores);
	}
	

	public String getCodigoCliente(String nroDocumento) {
		
		String[] args = new String[] {nroDocumento};
		Cursor cursor = dbHelper.getDataBase().rawQuery("select cliente from documento where nrodocumento = ?",args);
		
		String codigo=null;
		
		if(cursor.moveToNext()){
			
			codigo = cursor.getString(cursor.getColumnIndex("cliente"));
		}
		
		cursor.close();
		
		return codigo;
		
	}
	
	public ArrayList<DocumentoTO> listarxCodigo(String codigo){
		ArrayList<DocumentoTO> documentos = new ArrayList<DocumentoTO>();
		
		String[] args = new String[] {codigo};
		Cursor cursor = dbHelper.getDataBase().rawQuery("select d.documentoId,d.nombre,d.cliente,d.nrodocumento,d.importe,d.estado,d.enviado,d.tipodocumento,m.codigo,m.descripcion " +
														"from documento d left join motivo m on d.estado = m.codigo and funcion='EST'" +
														"where cliente = ? " +
													    "order by nroDocumento",args);
		
		DocumentoTO documentoTO;
		
		while(cursor.moveToNext()){
			documentoTO = new DocumentoTO();
			documentoTO.setDocumentoId(cursor.getInt(cursor.getColumnIndex("documentoId")));
			documentoTO.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
			documentoTO.setCliente(cursor.getString(cursor.getColumnIndex("cliente")));
			documentoTO.setNroDocumento(cursor.getString(cursor.getColumnIndex("nrodocumento")));
			documentoTO.setImporte(cursor.getDouble(cursor.getColumnIndex("importe")));
			documentoTO.setEstado(cursor.getString(cursor.getColumnIndex("estado")));
			documentoTO.setEnviado(cursor.getInt(cursor.getColumnIndex("enviado")));
			documentoTO.setTipoDocumento(cursor.getString(cursor.getColumnIndex("tipodocumento")));
			documentoTO.setMotivo(cursor.getString(cursor.getColumnIndex("codigo")));
			documentoTO.setEstadoDes(cursor.getString(cursor.getColumnIndex("descripcion")));
			documentos.add(documentoTO);
		}
		
		cursor.close();
		
		return documentos;
	}
	
	public void updateEstado(DocumentoResponseTO documentoTO){
		ContentValues parametros = new ContentValues();
		parametros.put("nrodocumento",documentoTO.getNroDocumento());
		parametros.put("estado",documentoTO.getEstado());
		
		String[] valores = new String[] { String.valueOf(documentoTO.getNroDocumento()) };

		dbHelper.getDataBase().update("documento", parametros, "documentoId = ?",
				valores);
	}
	
public ArrayList<DocumentoTO> listarDocumento(){
		
		ArrayList<DocumentoTO> documentos = new ArrayList<DocumentoTO>();		
		String[] args = new String[0];
		
		Cursor cursor = dbHelper.getDataBase().rawQuery("select d.documentoId,d.nombre,d.cliente,d.nrodocumento,d.importe,d.estado,d.enviado,d.tipodocumento,m.codigo,m.descripcion " +
				"from documento d left join motivo m on d.estado = m.codigo and funcion='EST' " +
			    "order by nroDocumento",args);
		
		DocumentoTO documentoTO;
		
		while(cursor.moveToNext()){
			documentoTO = new DocumentoTO();
			documentoTO.setDocumentoId(cursor.getInt(cursor.getColumnIndex("documentoId")));
			documentoTO.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
			documentoTO.setCliente(cursor.getString(cursor.getColumnIndex("cliente")));
			documentoTO.setNroDocumento(cursor.getString(cursor.getColumnIndex("nrodocumento")));
			documentoTO.setImporte(cursor.getDouble(cursor.getColumnIndex("importe")));
			documentoTO.setEstado(cursor.getString(cursor.getColumnIndex("estado")));
			documentoTO.setEnviado(cursor.getInt(cursor.getColumnIndex("enviado")));
			documentoTO.setTipoDocumento(cursor.getString(cursor.getColumnIndex("tipodocumento")));
			documentoTO.setMotivo(cursor.getString(cursor.getColumnIndex("codigo")));
			documentoTO.setEstadoDes(cursor.getString(cursor.getColumnIndex("descripcion")));
			documentos.add(documentoTO);
		}
		
		cursor.close();
		
		return documentos;
	}
	
}

