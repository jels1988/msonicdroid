package pe.lindley.lanzador.negocio;

import java.util.ArrayList;

import pe.lindley.lanzador.dao.TablaDAO;
import pe.lindley.lanzador.to.TablaTO;
import pe.lindley.util.DBHelper;

import com.google.inject.Inject;

public class TablaBLL {
	@Inject protected DBHelper dbHelper;
	@Inject protected TablaDAO tablaDAO;
	
	public ArrayList<TablaTO> Listar(String codigo){
		ArrayList<TablaTO> detalles;
		dbHelper.openDataBase();
		detalles = tablaDAO.listar(codigo);
		dbHelper.close();
		return detalles;
	}
}
