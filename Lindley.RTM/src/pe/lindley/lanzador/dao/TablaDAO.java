package pe.lindley.lanzador.dao;

import java.util.ArrayList;

import pe.lindley.lanzador.to.TablaTO;
import pe.lindley.util.DBHelper;
import android.database.Cursor;
import com.google.inject.Inject;

public class TablaDAO {
	@Inject protected DBHelper dbHelper;
	
	public ArrayList<TablaTO> listar(String codigo){
		ArrayList<TablaTO> valores = new ArrayList<TablaTO>();
		
		String[] args = new String[] {codigo};
		Cursor cursor = dbHelper.getDataBase().rawQuery("select * from tablas where codigoTabla = ? order by descripcion",args);
		TablaTO tablaTO;
		while(cursor.moveToNext()){
			tablaTO = new TablaTO();
			tablaTO.setCodigo(cursor.getString(cursor.getColumnIndex("codigoCampo")));
			tablaTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
			valores.add(tablaTO);
		}
		cursor.close();
		
		return valores;
	}
}
