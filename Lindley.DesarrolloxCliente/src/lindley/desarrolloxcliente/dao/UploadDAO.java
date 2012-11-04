package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.to.PeriodoTO;
import lindley.desarrolloxcliente.to.upload.EvaluacionTO;
import lindley.desarrolloxcliente.to.upload.OportunidadTO;
import net.msonic.lib.DBHelper;

import android.database.Cursor;

import com.google.inject.Inject;

public class UploadDAO {

	@Inject	protected DBHelper dbHelper;
	@Inject protected PeriodoTO periodoTO;
	
	
	public List<EvaluacionTO> listarEvaluaciones(){
		List<EvaluacionTO> evaluaciones = new ArrayList<EvaluacionTO>();
		
		String SQL = "select clienteCodigo,activosLindley,codigoFe,usuario,fecha,hora,usuarioCierre,fechaCierre,horaCierre,estado,serverId,combosSS,combosMS,obsSS,obsMS " +
					 "from evaluacion";
		
		Cursor cursor = dbHelper.rawQuery(SQL,null);
		
		EvaluacionTO evaluacionTO;
		
		while(cursor.moveToNext()){
			evaluacionTO = new EvaluacionTO();
			evaluacionTO.codigoCliente =  cursor.getString(cursor.getColumnIndex("clienteCodigo"));
			evaluacionTO.activosLindley =  cursor.getString(cursor.getColumnIndex("activosLindley"));
			evaluacionTO.codigoFDE =  cursor.getString(cursor.getColumnIndex("codigoFe"));
			evaluacionTO.usuarioCreacion =  cursor.getString(cursor.getColumnIndex("usuario"));
			evaluacionTO.fechaCreacion =  cursor.getString(cursor.getColumnIndex("fecha"));
			evaluacionTO.horaCreacion =  cursor.getString(cursor.getColumnIndex("hora"));
			
			evaluacionTO.usuarioCierre =  cursor.getString(cursor.getColumnIndex("usuarioCierre"));
			evaluacionTO.fechaCierre =  cursor.getString(cursor.getColumnIndex("fechaCierre"));
			evaluacionTO.horaCierre =  cursor.getString(cursor.getColumnIndex("horaCierre"));
			
			evaluacionTO.comboSS =  cursor.getString(cursor.getColumnIndex("combosSS"));
			evaluacionTO.comboMS =  cursor.getString(cursor.getColumnIndex("combosMS"));
			evaluacionTO.observacionSS =  cursor.getString(cursor.getColumnIndex("obsSS"));
			evaluacionTO.observacionMS =  cursor.getString(cursor.getColumnIndex("obsMS"));
			
			evaluacionTO.horaCierre =  cursor.getString(cursor.getColumnIndex("horaCierre"));
			
			evaluacionTO.estado =  cursor.getString(cursor.getColumnIndex("estado"));
			evaluacionTO.serverId =  cursor.getLong(cursor.getColumnIndex("serverId"));
			
			evaluaciones.add(evaluacionTO);
		}
		cursor.close();
		
		return evaluaciones;
		
	}
	
	public void listarOportunidades(long evaluacionId,EvaluacionTO evaluacionTO){
		
		String SQL = "select id,evaluacionId,anio,mes, codigoProducto," +
							"concrecion,concrecionActual,concrecionCumple,sovi,soviActual,soviCumple," +
							"respetaPrecio,respetaPrecioActual,respetaPrecioCumple,numeroSabores,numeroSaboresActual," +
							"numeroSaboresCumple,puntosCocaCola,puntosBonus,fechaProceso,legacy " +
							"from evaluacion_oportunidad " +
							"where evaluacionId = ?1";
		
		String[] args = new String[] {String.valueOf(evaluacionTO.serverId)};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		OportunidadTO oportunidadTO;
		
		while(cursor.moveToNext()){
			oportunidadTO = new OportunidadTO();
			oportunidadTO.anio =  cursor.getInt(cursor.getColumnIndex("anio"));
			oportunidadTO.mes =  cursor.getInt(cursor.getColumnIndex("mes"));
			oportunidadTO.codigoArticulo =  cursor.getString(cursor.getColumnIndex("codigoProducto"));
			
			oportunidadTO.concrecion =  cursor.getString(cursor.getColumnIndex("concrecion"));
			oportunidadTO.concrecionActual =  cursor.getString(cursor.getColumnIndex("concrecionActual"));
			oportunidadTO.concrecionCumple =  cursor.getString(cursor.getColumnIndex("concrecionCumple"));
			
			oportunidadTO.sovi =  cursor.getString(cursor.getColumnIndex("sovi"));
			oportunidadTO.soviActual =  cursor.getString(cursor.getColumnIndex("soviActual"));
			oportunidadTO.soviCumple =  cursor.getString(cursor.getColumnIndex("soviCumple"));
			
			oportunidadTO.respetoPrecio =  cursor.getString(cursor.getColumnIndex("respetaPrecio"));
			oportunidadTO.respetoPrecioActual =  cursor.getString(cursor.getColumnIndex("respetaPrecioActual"));
			oportunidadTO.respetoPrecioCumple =  cursor.getString(cursor.getColumnIndex("respetaPrecioCumple"));
			
			oportunidadTO.numeroSabores =  cursor.getString(cursor.getColumnIndex("numeroSabores"));
			oportunidadTO.numeroSaboresActual =  cursor.getString(cursor.getColumnIndex("numeroSaboresActual"));
			oportunidadTO.numeroSaboresCumple =  cursor.getString(cursor.getColumnIndex("numeroSaboresCumple"));
			
			oportunidadTO.codigoAccionTrade =  cursor.getString(cursor.getColumnIndex("codigoAccion"));
			oportunidadTO.accionTrade =  cursor.getString(cursor.getColumnIndex("accion"));
			
			oportunidadTO.puntosSugeridos =  cursor.getString(cursor.getColumnIndex("puntosSugeridos"));
			oportunidadTO.puntosBonus =  cursor.getString(cursor.getColumnIndex("puntosBonus"));
			oportunidadTO.puntosGanados =  cursor.getString(cursor.getColumnIndex("puntosGanados"));
			
			oportunidadTO.fechaCompromiso =  cursor.getString(cursor.getColumnIndex("fechaProceso"));
			
			oportunidadTO.confirmacion =  cursor.getString(cursor.getColumnIndex("confirmacion"));
			oportunidadTO.origen =  cursor.getString(cursor.getColumnIndex("origen"));
			oportunidadTO.estado =  cursor.getString(cursor.getColumnIndex("estado"));
			oportunidadTO.legacy =  cursor.getString(cursor.getColumnIndex("legacy"));
			
			evaluacionTO.oportunidades.add(oportunidadTO);
		}
		
		cursor.close();
	}
}
