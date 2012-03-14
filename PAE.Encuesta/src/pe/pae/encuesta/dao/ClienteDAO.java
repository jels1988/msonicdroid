package pe.pae.encuesta.dao;


import pe.pae.encuesta.to.ClienteTO;
import pe.pae.encuesta.to.TiendaTO;

import net.msonic.lib.DBHelper;

import android.content.ContentValues;

import com.google.inject.Inject;

public class ClienteDAO {

	
	@Inject protected DBHelper dbHelper;
	
	
	public void deleteCliente(){
		dbHelper.getDataBase().execSQL("delete from cliente");
		dbHelper.getDataBase().execSQL("delete from tienda");
	}
	
	public void saveCliente(ClienteTO cliente){
		
		ContentValues parametros = new ContentValues();
		parametros.put("clienteid", cliente.clienteId);
		parametros.put("descripcion", cliente.descripcion);
		dbHelper.getDataBase().insertOrThrow("cliente", null, parametros);
		
		
		for (TiendaTO tiendaTO : cliente.tiendas) {
			ContentValues parametros2 = new ContentValues();
			parametros2.put("tiendaid", tiendaTO.tiendaId);
			parametros2.put("descripcion", tiendaTO.nombre);
			parametros2.put("direccion", tiendaTO.direccion);
			parametros2.put("clienteid", cliente.clienteId);
			dbHelper.getDataBase().insertOrThrow("tienda", null, parametros2);
		}
		
	}
}
