package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

import net.msonic.lib.DBHelper;

public class FotoDAO {

	@Inject	protected DBHelper dbHelper;
	
	public void deleteAll() {
		dbHelper.getDataBase().execSQL("delete from tbl_fotos");
	}
	
	public void delete(int idFoto) {
		String[] valores = new String[] { String.valueOf(idFoto) };
		dbHelper.getDataBase().execSQL("delete from tbl_fotos where id = ?",valores);
	}
	
	public void save(final String fileName){
		ContentValues parametros = new ContentValues();
		
		parametros.put("nombre", fileName);
		
		dbHelper.getDataBase().insertOrThrow("tbl_fotos", null, parametros);
	}
	
	public ArrayList<String> listar(){
		ArrayList<String> valores = new ArrayList<String>();
		
		String[] args = new String[] {};
		Cursor cursor = dbHelper.getDataBase().rawQuery("select * from tbl_fotos",args);
		
		while(cursor.moveToNext()){
			String fileName = cursor.getString(cursor.getColumnIndex("nombre"));
			valores.add(fileName);
		}
		cursor.close();
		
		return valores;
	}
}
