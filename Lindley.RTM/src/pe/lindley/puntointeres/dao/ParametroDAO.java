package pe.lindley.puntointeres.dao;

import java.util.ArrayList;
import pe.lindley.puntointeres.to.ParametroTO;
import pe.lindley.util.DBHelper;
import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class ParametroDAO {

	@Inject	protected DBHelper dbHelper;

	public void deleteAll() {
		dbHelper.getDataBase().execSQL("delete from pint_tablas");
	}
	
	public void save(final ParametroTO parametroTO){
		ContentValues parametros = new ContentValues();
		
		parametros.put("codtabla", parametroTO.getCodigoTabla());
		parametros.put("id", parametroTO.getCodigo());
		parametros.put("codrelacion", parametroTO.getCodigoRelacion());
		parametros.put("descripcion", parametroTO.getDescripcion());
			
		dbHelper.getDataBase().insertOrThrow("pint_tablas", null, parametros);
	}
	
	public ArrayList<ParametroTO> list(String codigoTabla){
		
		ArrayList<ParametroTO> valores = new ArrayList<ParametroTO>();
		
		String SQL = "SELECT id,descripcion FROM pint_tablas WHERE codtabla = ? order by CAST(id as integer) asc";
		String[] parametros = new String[] { codigoTabla };
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		ParametroTO parametroTO;
		
		while (cursor.moveToNext()) {
			parametroTO = new ParametroTO();
			parametroTO.setCodigoTabla(codigoTabla);
			parametroTO.setCodigo(cursor.getString(cursor.getColumnIndex("id")));
			parametroTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
			valores.add(parametroTO);
		}
		
		cursor.close();
		
		
		return valores;
	}
	
	public ArrayList<ParametroTO> list(String codigoTabla,String id){
		
		ArrayList<ParametroTO> valores = new ArrayList<ParametroTO>();
		
		String SQL = "SELECT id,descripcion FROM pint_tablas where codtabla = ? and codrelacion = ? order by CAST(id as integer) asc";
		String[] parametros = new String[] { codigoTabla,id };
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		ParametroTO parametroTO;
		
		while (cursor.moveToNext()) {
			parametroTO = new ParametroTO();
			parametroTO.setCodigoTabla(codigoTabla);
			parametroTO.setCodigo(cursor.getString(cursor.getColumnIndex("id")));
			parametroTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
			valores.add(parametroTO);
		}
		
		cursor.close();
		
		
		return valores;
	}
	
}
