package pe.lindley.ventacero.dao;

import java.util.ArrayList;
import pe.lindley.util.DBHelper;
import pe.lindley.ventacero.activity.VentaCeroActivity;
import pe.lindley.ventacero.to.ParametroTO;
import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class ParametroDAO {
	
	@Inject	protected DBHelper dbHelper;

	public void deleteAll() {
		dbHelper.getDataBase().execSQL("delete from vc_tablas");
	}
	
	public void save(final ParametroTO parametroTO){
		ContentValues parametros = new ContentValues();
		
		parametros.put("codtabla", parametroTO.getCodigoTabla());
		parametros.put("id", parametroTO.getCodigo());
		parametros.put("level1", parametroTO.getLevel1());
		parametros.put("level2", parametroTO.getLevel2());
		parametros.put("descripcion", parametroTO.getDescripcion());
			
		dbHelper.getDataBase().insertOrThrow("vc_tablas", null, parametros);
	}
	
	
	public ArrayList<ParametroTO> list(String codigoTabla){
		
		ArrayList<ParametroTO> valores = new ArrayList<ParametroTO>();
		
		String SQL = "SELECT id,descripcion FROM vc_tablas WHERE codtabla = ? order by CAST(id as integer) asc";
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
		
		String SQL = "SELECT id,descripcion FROM vc_tablas where codtabla = ? and substr(id,1,1) = ? order by CAST(id as integer) asc";
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
	

	public ArrayList<ParametroTO> list(String codigoTabla,String id, String subid){
		
		ArrayList<ParametroTO> valores = new ArrayList<ParametroTO>();
		
		String SQL = "";
		String[] parametros = null;
		
		if(codigoTabla.equals(VentaCeroActivity.LISTA_MES))
		{
			SQL = "SELECT id,descripcion FROM vc_tablas where codtabla = ? and level1 = ? order by CAST(id as integer) asc";
			parametros = new String[] { codigoTabla,id };
		}
		
		else if(codigoTabla.equals(VentaCeroActivity.LISTA_SEMANA))
		{
			SQL = "SELECT id,descripcion FROM vc_tablas where codtabla = ? and level1 = ? and level2 = ? order by CAST(id as integer) asc";
			parametros = new String[] { codigoTabla,id,subid };
		}
				
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
