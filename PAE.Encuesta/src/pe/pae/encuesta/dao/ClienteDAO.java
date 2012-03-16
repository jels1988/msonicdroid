package pe.pae.encuesta.dao;


import java.util.ArrayList;

import pe.pae.encuesta.to.ClienteTO;
import pe.pae.encuesta.to.TiendaTO;

import net.msonic.lib.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class ClienteDAO {

	
	@Inject protected DBHelper dbHelper;
	
	public ArrayList<TiendaTO> listarTiendas(int clienteId){
		ArrayList<TiendaTO> tiendas = new ArrayList<TiendaTO>();
		
		String SQL = "select * from tienda where clienteid = ?";
		String[] parametros = new String[] { String.valueOf(clienteId) };
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL,parametros);
		
		TiendaTO tiendaTO;
		
		while (cursor.moveToNext()) {
			tiendaTO = new TiendaTO();
			tiendaTO.tiendaId =  cursor.getInt(cursor.getColumnIndex("tiendaid"));
			tiendaTO.nombre  = cursor.getString(cursor.getColumnIndex("descripcion"));
			tiendaTO.direccion  = cursor.getString(cursor.getColumnIndex("direccion"));
			tiendas.add(tiendaTO);
		}
		
		cursor.close();
		
		return tiendas;
		
		
	}
	
	public ArrayList<ClienteTO> listarClientes(){
		
		ArrayList<ClienteTO> clientes = new ArrayList<ClienteTO>();
		
		String SQL = "select * from cliente";
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL,null);
		
		ClienteTO clienteTO;
		
		while (cursor.moveToNext()) {
			clienteTO = new ClienteTO();
			clienteTO.clienteId =  cursor.getInt(cursor.getColumnIndex("clienteid"));
			clienteTO.descripcion  = cursor.getString(cursor.getColumnIndex("descripcion"));
			clientes.add(clienteTO);
		}
		
		 cursor.close();
		 
		 return clientes;
		
	}
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
