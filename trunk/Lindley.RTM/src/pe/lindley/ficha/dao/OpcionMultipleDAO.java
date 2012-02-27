package pe.lindley.ficha.dao;
import java.util.ArrayList;
import pe.lindley.ficha.to.OpcionMultipleTO;
import pe.lindley.util.DBHelper;
import android.content.ContentValues;
import android.database.Cursor;
import com.google.inject.Inject;

public class OpcionMultipleDAO {
	
	@Inject	protected DBHelper dbHelper;

	public void deleteAll() {
		dbHelper.getDataBase().execSQL("delete from fic_tablas");
	}
	
	

	public void save(final OpcionMultipleTO opcionMultipleTO){
		ContentValues parametros = new ContentValues();
		
		parametros.put("codtabla", opcionMultipleTO.getCodigoTabla());
		parametros.put("id", opcionMultipleTO.getCodigo());
		parametros.put("descripcion", opcionMultipleTO.getDescripcion());
		
		dbHelper.getDataBase().insertOrThrow("fic_tablas", null, parametros);
	}
	
	
	public ArrayList<OpcionMultipleTO> list(String codigoTabla){
		
		ArrayList<OpcionMultipleTO> valores = new ArrayList<OpcionMultipleTO>();
		
		String SQL = "SELECT id,descripcion FROM fic_tablas WHERE codtabla = ?";
		String[] parametros = new String[] { codigoTabla };
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		OpcionMultipleTO parametroTO;
		
		while (cursor.moveToNext()) {
			parametroTO = new OpcionMultipleTO();
			parametroTO.setCodigoTabla(codigoTabla);
			parametroTO.setCodigo(cursor.getString(cursor.getColumnIndex("id")));
			parametroTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
			valores.add(parametroTO);
		}
		
		cursor.close();
		
		
		return valores;
	}
	

	public ArrayList<OpcionMultipleTO> list(String codigoTabla,String id){
		
		ArrayList<OpcionMultipleTO> valores = new ArrayList<OpcionMultipleTO>();
		
		String SQL = "SELECT id,descripcion FROM fic_tablas where codtabla = ? and substr(id,1,1) = ?";
		String[] parametros = new String[] { codigoTabla,id };
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		OpcionMultipleTO parametroTO;
		
		while (cursor.moveToNext()) {
			parametroTO = new OpcionMultipleTO();
			parametroTO.setCodigoTabla(codigoTabla);
			parametroTO.setCodigo(cursor.getString(cursor.getColumnIndex("id")));
			parametroTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
			valores.add(parametroTO);
		}
		
		cursor.close();
		
		
		return valores;
	}

	
}
