package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;
import java.util.List;

import net.msonic.lib.DBHelper;

import android.database.Cursor;

import com.google.inject.Inject;

import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.upload.PresentacionTO;



public class PresentacionDAO {

	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
	public List<PresentacionTO> consultarOportunidadesPresentacion(String codigoCliente,String tipoAgrupacion){
		List<PresentacionTO> lista = new ArrayList<PresentacionTO>();
		
		String SQL = "SELECT tipoAgrupacion,cdfde,variableRed,fechaProceso " +
					"FROM presentacion_cliente " +
					"WHERE anio = ?1 and mes= ?2 and codigoCliente= ?3 and tipoAgrupacion = ?4";
		
		String[] args = new String[] {
				  String.valueOf(periodoTO.anio),
				  String.valueOf(periodoTO.mes),
				  codigoCliente,
				  tipoAgrupacion};
		
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		PresentacionTO presentacionTO;
		
		while(cursor.moveToNext()){
			presentacionTO = new PresentacionTO();
			presentacionTO.tipoAgrupacion = cursor.getString(cursor.getColumnIndex("tipoAgrupacion"));
			presentacionTO.codigoFDE = cursor.getString(cursor.getColumnIndex("cdfde"));
			presentacionTO.codigoVariable = cursor.getString(cursor.getColumnIndex("variableRed"));
		
			lista.add(presentacionTO);
		}
		
		cursor.close();
		return lista;
	}
}
