package pe.lindley.plandesarrollo.dao;

import java.util.ArrayList;

import pe.lindley.plandesarrollo.to.ParametroTO;
import pe.lindley.util.DBHelper;
import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class ParametroDAO {
	
	@Inject	protected DBHelper dbHelper;

	public void deleteAll() {
		dbHelper.getDataBase().execSQL("delete from pd_tablas");
	}
	
	public void save(final ParametroTO parametroTO){
		ContentValues parametros = new ContentValues();
		
		parametros.put("codtabla", parametroTO.getCodigoTabla());
		parametros.put("id", parametroTO.getCodigo());
		parametros.put("descripcion", parametroTO.getDescripcion());
		parametros.put("nomLargo", parametroTO.getNombreLargo());
		parametros.put("nomCorto", parametroTO.getNombreCorto());
			
		dbHelper.getDataBase().insertOrThrow("pd_tablas", null, parametros);
	}
	
	
	public ArrayList<ParametroTO> list(String codigoTabla){
		
		ArrayList<ParametroTO> valores = new ArrayList<ParametroTO>();
		
		String SQL = "SELECT id,descripcion,nomLargo,nomCorto FROM pd_tablas WHERE codtabla = ?";
		String[] parametros = new String[] { codigoTabla };
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		ParametroTO parametroTO;
		
		while (cursor.moveToNext()) {
			parametroTO = new ParametroTO();
			parametroTO.setCodigoTabla(codigoTabla);
			parametroTO.setCodigo(cursor.getString(cursor.getColumnIndex("id")));
			parametroTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
			parametroTO.setNombreCorto(cursor.getString(cursor.getColumnIndex("nomCorto")));
			parametroTO.setNombreLargo(cursor.getString(cursor.getColumnIndex("nomLargo")));
			valores.add(parametroTO);
		}
		
		cursor.close();
		
		
		return valores;
	}
	

	public ArrayList<ParametroTO> list(String codigoTabla,String id){
		
		ArrayList<ParametroTO> valores = new ArrayList<ParametroTO>();
		
		String SQL = "SELECT id,descripcion,nomLargo,nomCorto FROM pd_tablas where codtabla = ? and substr(id,1,1) = ?";
		String[] parametros = new String[] { codigoTabla,id };
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		ParametroTO parametroTO;
		
		while (cursor.moveToNext()) {
			parametroTO = new ParametroTO();
			parametroTO.setCodigoTabla(codigoTabla);
			parametroTO.setCodigo(cursor.getString(cursor.getColumnIndex("id")));
			parametroTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
			parametroTO.setNombreCorto(cursor.getString(cursor.getColumnIndex("nomCorto")));
			parametroTO.setNombreLargo(cursor.getString(cursor.getColumnIndex("nomLargo")));
			valores.add(parametroTO);
		}
		
		cursor.close();
		
		
		return valores;
	}


}
