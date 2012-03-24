package lindley.desarrolloxcliente.dao;

import android.content.ContentValues;

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
}
