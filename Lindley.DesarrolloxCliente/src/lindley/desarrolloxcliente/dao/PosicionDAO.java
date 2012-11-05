package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;
import java.util.List;


import lindley.desarrolloxcliente.to.EvaluacionTO;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.PosicionCompromisoTO;
import net.msonic.lib.DBHelper;
import android.database.Cursor;
import com.google.inject.Inject;

public class PosicionDAO {
	
	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
	public List<PosicionCompromisoTO> consultarOportunidadesPosicion(EvaluacionTO evaluacionTO, String tipoAgrupacion){
		List<PosicionCompromisoTO> lista = new ArrayList<PosicionCompromisoTO>();
		
		
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
		PosicionCompromisoTO compromisoTO;
		
		while(cursor.moveToNext()){
			compromisoTO = new PosicionCompromisoTO();
			compromisoTO.codigoVariable = cursor.getString(cursor.getColumnIndex("variableRed"));
			compromisoTO.tipoAgrupacion = tipoAgrupacion;
			lista.add(compromisoTO);
		}
		
		
		
		
		cursor.close();
		
		
		return lista;
	}
	
	
	
	
}