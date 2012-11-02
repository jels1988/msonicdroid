package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;
import java.util.List;

import net.msonic.lib.DBHelper;

import android.database.Cursor;

import com.google.inject.Inject;

import lindley.desarrolloxcliente.ConstantesApp;
import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.PresentacionCompromisoTO;



public class PresentacionDAO {

	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
	public List<PresentacionCompromisoTO> consultarOportunidadesPresentacion(String codigoCliente,String tipoAgrupacion){
		List<PresentacionCompromisoTO> lista = new ArrayList<PresentacionCompromisoTO>();
		
		String SQL = "SELECT tipoAgrupacion,cdfde,variableRed,fechaProceso " +
					"FROM presentacion_cliente " +
					"WHERE anio = ?1 and mes= ?2 and codigoCliente= ?3 and tipoAgrupacion = ?4";
		
		String[] args = new String[] {
				  String.valueOf(periodoTO.anio),
				  String.valueOf(periodoTO.mes),
				  codigoCliente,
				  tipoAgrupacion};
		
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		PresentacionCompromisoTO presentacionCompromisoTO;
		
		while(cursor.moveToNext()){
			presentacionCompromisoTO = new PresentacionCompromisoTO();
			presentacionCompromisoTO.tipoAgrupacion = cursor.getString(cursor.getColumnIndex("tipoAgrupacion"));
			presentacionCompromisoTO.codfde = cursor.getString(cursor.getColumnIndex("cdfde"));
			presentacionCompromisoTO.codigoVariable = cursor.getString(cursor.getColumnIndex("variableRed"));
			presentacionCompromisoTO.puntosSugeridos = "0";
			presentacionCompromisoTO.puntosGanados = "0";
			presentacionCompromisoTO.cumplio = ConstantesApp.RESPUESTA_NO;
			lista.add(presentacionCompromisoTO);
		}
		
		cursor.close();
		return lista;
	}
}
