package pe.lindley.om.dao;


import java.util.ArrayList;

import pe.lindley.om.to.ClienteTO;
import pe.lindley.om.to.RolTO;
import pe.lindley.util.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class RolDAO {

	@Inject
	protected DBHelper dbHelper;

	public void deleteAll() {
		dbHelper.getDataBase().execSQL("delete from om_asignacion");
	}
	
	
	public ArrayList<RolTO> list(ClienteTO clienteTO,
								 String rol,
								 String tipoOrden){

		ArrayList<RolTO> roles = new ArrayList<RolTO>();
		String SQL = "SELECT asignacionid, tipoorde,ruta,rol,figura,nombre,orden,codsap,codsup FROM om_asignacion WHERE tipoorde = ? AND rol = ? " +
					 "AND (nullif(ruta,'') = ? OR nullif(ruta,'') = ? OR nullif(ruta,'') = ? OR nullif(ruta,'') = ?) " +
					 "ORDER BY orden";
		
		String[] parametros = new String[] { tipoOrden,rol, 
											clienteTO.getRutaVentas(),
											clienteTO.getRutaMecaderista(),
											clienteTO.getRutaDesarrollador(),
											clienteTO.getRutaTecnicoMan()};
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		RolTO rolTO = null;
		while (cursor.moveToNext()){
			rolTO = new RolTO();
			rolTO.setAsignacionId(cursor.getInt(cursor.getColumnIndex("asignacionid")));
			rolTO.setTipoOrden(cursor.getString(cursor.getColumnIndex("tipoorde")));
			rolTO.setCodigoRuta(cursor.getString(cursor.getColumnIndex("ruta")));
			rolTO.setTipoRol(cursor.getString(cursor.getColumnIndex("rol")));
			rolTO.setTipoSub(cursor.getString(cursor.getColumnIndex("figura")));
			rolTO.setNombres(cursor.getString(cursor.getColumnIndex("nombre")));
			rolTO.setOrden(cursor.getInt(cursor.getColumnIndex("orden")));
			rolTO.setCodigoSap(cursor.getString(cursor.getColumnIndex("codsap")));
			rolTO.setCodigoSub(cursor.getString(cursor.getColumnIndex("codsup")));
			roles.add(rolTO);
		}

		cursor.close();
		
		return roles;
	}
	
	public void save(final RolTO rolTO){
		
		ContentValues parametros = new ContentValues();
		parametros.put("TIPOORDE", rolTO.getTipoOrden());
		parametros.put("RUTA", rolTO.getCodigoRuta());
		parametros.put("ROL", rolTO.getTipoRol());
		parametros.put("FIGURA", rolTO.getTipoSub());
		parametros.put("NOMBRE", rolTO.getNombres());
		parametros.put("ORDEN", rolTO.getOrden());
		parametros.put("CODSAP", rolTO.getCodigoSap());
		parametros.put("CODSUP", rolTO.getCodigoSub());
		
		dbHelper.getDataBase().insertOrThrow("om_asignacion", null, parametros);
	}
}
