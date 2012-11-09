package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;
import java.util.List;


import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.PosicionTO;
import net.msonic.lib.DBHelper;
import android.database.Cursor;
import com.google.inject.Inject;

public class PosicionDAO {
	
	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
	public List<PosicionTO> consultarOportunidadesPosicion(EvaluacionTO evaluacionTO, String tipoAgrupacion){
		List<PosicionTO> lista = new ArrayList<PosicionTO>();
		
		
		String SQL = "SELECT variableRed,fechaProceso " +
					"FROM posicion_cliente " +
					"WHERE anio = ?1 and mes= ?2 and codigoCliente= ?3 and tipoAgrupacion = ?4 and activo=?5";
		
		String[] args = new String[] {
				  String.valueOf(periodoTO.anio),
				  String.valueOf(periodoTO.mes),
				  evaluacionTO.codigoCliente,
				  tipoAgrupacion,
				  evaluacionTO.activosLindley};
		
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		PosicionTO posicionTO;
		
		while(cursor.moveToNext()){
			posicionTO = new PosicionTO();
			posicionTO.codigoVariable = cursor.getString(cursor.getColumnIndex("variableRed"));
			posicionTO.tipoAgrupacion = tipoAgrupacion;
			lista.add(posicionTO);
		}
		
		
		
		
		cursor.close();
		
		
		return lista;
	}
	
	
	
	
}
