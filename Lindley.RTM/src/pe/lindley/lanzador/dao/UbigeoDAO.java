package pe.lindley.lanzador.dao;

import java.util.ArrayList;

import com.google.inject.Inject;

import pe.lindley.lanzador.to.UbigeoTO;
import pe.lindley.util.DBHelper;

import android.database.Cursor;

public class UbigeoDAO {

	@Inject protected DBHelper dbHelper;
	
	public ArrayList<UbigeoTO> listarDepartamentos(){
		ArrayList<UbigeoTO> departamentos = new ArrayList<UbigeoTO>();
		Cursor cursor = dbHelper.getDataBase().rawQuery("SELECT codigo, descripcion FROM ubigeo where substr(codigo,3,4) = '0000'  order by descripcion",null);
		
		UbigeoTO ubigeoTO;
		
		while(cursor.moveToNext()){
			ubigeoTO = new UbigeoTO();
			ubigeoTO.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
			ubigeoTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
			departamentos.add(ubigeoTO);
		}
		
		cursor.close();
		return departamentos;
	}
	
	public ArrayList<UbigeoTO> listarProvincias(String codigoDepartamento){
		String[] parametros = new String[]{codigoDepartamento};
		ArrayList<UbigeoTO> provincias = new ArrayList<UbigeoTO>();
		Cursor cursor = dbHelper.getDataBase().rawQuery("SELECT codigo,descripcion FROM ubigeo where substr(codigo,1,2) = substr(?,1,2) and substr(codigo,3,2) <> '00'  and substr(codigo,5,2) = '00'    order by descripcion",parametros);
		
		UbigeoTO ubigeoTO;
		
		while(cursor.moveToNext()){
			ubigeoTO = new UbigeoTO();
			ubigeoTO.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
			ubigeoTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
			provincias.add(ubigeoTO);
		}
		
		cursor.close();
		
		return provincias;
	}
	
	public ArrayList<UbigeoTO> listarDistritos(String codigoProvincia){
		String[] parametros = new String[]{codigoProvincia};
		ArrayList<UbigeoTO> distritos = new ArrayList<UbigeoTO>();
		
		Cursor cursor = dbHelper.getDataBase().rawQuery("SELECT codigo,descripcion FROM ubigeo where substr(codigo,1,4) = substr(?,1,4) and substr(codigo,5,2) <> '00' order by descripcion",parametros);
		
		UbigeoTO ubigeoTO;
		
		while(cursor.moveToNext()){
			ubigeoTO = new UbigeoTO();
			ubigeoTO.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
			ubigeoTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
			distritos.add(ubigeoTO);
		}
		
		cursor.close();
		
		return distritos;
	}
}

