package lindley.desarrolloxcliente.dao;

import net.msonic.lib.DBHelper;

import android.database.Cursor;

import com.google.inject.Inject;

import lindley.desarrolloxcliente.to.ClienteTO;

public class ClienteDAO {
	
	@Inject	protected DBHelper dbHelper;
	
	public ClienteTO ConsultaCliente(String codigo){
		
		String SQL = "select id,codigo,fecha,nombre,frecuencia,proyAlcance,proyFalta,cluster,mc,puntos,siguiente,direccion,latitud,longitud " +
					"from sku where cluster = ?1";
		
		String[] args = new String[] {codigo};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		ClienteTO clienteTO=null;
		
		if(cursor.moveToNext()){
			clienteTO = new ClienteTO();
			clienteTO.id = cursor.getLong(cursor.getColumnIndex("id"));
			clienteTO.codigo = cursor.getString(cursor.getColumnIndex("codigo"));
			clienteTO.fecha = cursor.getString(cursor.getColumnIndex("fecha"));
			clienteTO.nombre = cursor.getString(cursor.getColumnIndex("nombre"));
			clienteTO.frecuencia = cursor.getString(cursor.getColumnIndex("frecuencia"));
			clienteTO.alcance = cursor.getDouble(cursor.getColumnIndex("proyAlcance"));
			clienteTO.falta = cursor.getDouble(cursor.getColumnIndex("proyFalta"));
			clienteTO.cluster = cursor.getString(cursor.getColumnIndex("cluster"));
			clienteTO.mc = cursor.getString(cursor.getColumnIndex("mc"));
			clienteTO.nroPuntos = cursor.getInt(cursor.getColumnIndex("puntos"));
			clienteTO.nivelCanje = cursor.getInt(cursor.getColumnIndex("siguiente"));
			clienteTO.direccion = cursor.getString(cursor.getColumnIndex("direccion"));
			clienteTO.latitud = cursor.getDouble(cursor.getColumnIndex("latitud"));
			clienteTO.longitud = cursor.getDouble(cursor.getColumnIndex("longitud"));
			
		}
		
		return clienteTO;
		
	}
	
	
	
}
