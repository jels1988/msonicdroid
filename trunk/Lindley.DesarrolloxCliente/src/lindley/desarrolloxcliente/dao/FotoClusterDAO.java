package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.to.FotoExitoTO;

import net.msonic.lib.DBHelper;

import android.database.Cursor;

import com.google.inject.Inject;

public class FotoClusterDAO {

	@Inject	protected DBHelper dbHelper;
	
	public List<FotoExitoTO> listar(String codigoCluster){
		
		List<FotoExitoTO> lista = new ArrayList<FotoExitoTO>();
		FotoExitoTO fotoExitoTO;
		
		String SQL = "select archivo,titulo from fotos_cluster where cluster=?1";
	
		String[] args = new String[] {codigoCluster};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
	
		while(cursor.moveToNext()){
			fotoExitoTO = new FotoExitoTO();
			fotoExitoTO.nombre = cursor.getString(cursor.getColumnIndex("archivo"));
			fotoExitoTO.titulo = cursor.getString(cursor.getColumnIndex("titulo"));
			lista.add(fotoExitoTO);
		}
		
		cursor.close();
		
		return lista;
	}
}
