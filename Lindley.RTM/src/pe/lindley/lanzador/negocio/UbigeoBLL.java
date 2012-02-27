package pe.lindley.lanzador.negocio;

import java.util.ArrayList;

import com.google.inject.Inject;

import pe.lindley.lanzador.dao.UbigeoDAO;
import pe.lindley.lanzador.to.UbigeoTO;
import pe.lindley.util.DBHelper;
import android.util.Log;

public class UbigeoBLL {
	
	@Inject protected DBHelper dbHelper;
	@Inject protected UbigeoDAO ubigeoDAO;
	
	
	public ArrayList<UbigeoTO> listarDepartamentos(){
			ArrayList<UbigeoTO> listado = null;
		
		try{
			dbHelper.openDataBase();
			listado = ubigeoDAO.listarDepartamentos();
		}catch (Exception e) {
			Log.e(TablaBLL.class.toString(),"listarDepartamentos", e);
		}finally{
			dbHelper.close();
		}
		
		return listado;
	}
	
	public ArrayList<UbigeoTO> listarProvincia(String departamento){
		ArrayList<UbigeoTO> listado = null;
	
	try{
		dbHelper.openDataBase();
		listado = ubigeoDAO.listarProvincias(departamento);
	}catch (Exception e) {
		Log.e(TablaBLL.class.toString(),"listarProvincia", e);
	}finally{
		dbHelper.close();
	}
	
	return listado;
	}
	
	public ArrayList<UbigeoTO> listarDistrito(String provincia){
		ArrayList<UbigeoTO> listado = null;
	
	try{
		dbHelper.openDataBase();
		listado = ubigeoDAO.listarDistritos(provincia);
	}catch (Exception e) {
		Log.e(TablaBLL.class.toString(),"listarDistrito", e);
	}finally{
		dbHelper.close();
	}
	
	return listado;
	}
}
