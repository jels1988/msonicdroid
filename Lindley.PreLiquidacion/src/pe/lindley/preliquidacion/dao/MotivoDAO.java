package pe.lindley.preliquidacion.dao;

import java.util.ArrayList;

import pe.lindley.preliquidacion.to.MotivoTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class MotivoDAO {

	@Inject protected DBHelper dbHelper;
	
	public ArrayList<MotivoTO> listarMotivos(){

		
		String[] args = new String[] {};
		
		
		Cursor cursor = dbHelper.getDataBase().rawQuery("select codigo,funcion,descripcion from motivo where funcion='MOT' order by descripcion",args);
		
		MotivoTO motivoTO;
		ArrayList<MotivoTO> motivos = new ArrayList<MotivoTO>();
		while(cursor.moveToNext()){
			motivoTO =  new MotivoTO();
			motivoTO.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
			motivoTO.setFuncion(cursor.getString(cursor.getColumnIndex("funcion")));
			motivoTO.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
			motivos.add(motivoTO);
			
		}
		cursor.close();
		
		return motivos;
	}

	public void deleteAll() {
		dbHelper.getDataBase().execSQL("delete from motivo");
	}
	
	public void insert(MotivoTO motivoTO){
		ContentValues parametros = new ContentValues();
		parametros.put("codigo", motivoTO.getCodigo());
		parametros.put("funcion", motivoTO.getFuncion());
		parametros.put("descripcion", motivoTO.getDescripcion());
		
		dbHelper.getDataBase().insertOrThrow("motivo", null, parametros);
	}
	
	

	
}
