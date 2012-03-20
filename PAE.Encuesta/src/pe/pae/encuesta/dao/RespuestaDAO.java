package pe.pae.encuesta.dao;

import java.util.ArrayList;

import pe.pae.encuesta.to.RespuestaDataTO;
import pe.pae.encuesta.to.RespuestaPreguntaDataTO;
import net.msonic.lib.DBHelper;

import android.database.Cursor;

import com.google.inject.Inject;

public class RespuestaDAO {

	@Inject protected DBHelper dbHelper;
	
	public ArrayList<RespuestaDataTO> listarRespuesta(){
		
		String SQL = "select respuestaid,tiendaid,productoid,encuestaid,fecharegistro,horaregistro from respuesta";
		
		ArrayList<RespuestaDataTO> respuestas = new ArrayList<RespuestaDataTO>();
		
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL,null);
		RespuestaDataTO respuestaDataTO;
		
		while(cursor.moveToNext()){
			respuestaDataTO = new RespuestaDataTO();
			respuestaDataTO.respuestaId = cursor.getInt(cursor.getColumnIndex("respuestaid"));
			respuestaDataTO.tiendadId = cursor.getInt(cursor.getColumnIndex("tiendaid"));
			respuestaDataTO.productoId = cursor.getInt(cursor.getColumnIndex("productoid"));
			respuestaDataTO.encuestaId = cursor.getInt(cursor.getColumnIndex("encuestaid"));
			respuestaDataTO.fechaRegistro = cursor.getString(cursor.getColumnIndex("fecharegistro"));
			respuestaDataTO.horaRegistro = cursor.getString(cursor.getColumnIndex("horaregistro"));
			respuestas.add(respuestaDataTO);
		}
		
		cursor.close();
		
		
		for (RespuestaDataTO respuestaTO : respuestas) {
			SQL = "select respuestaid,tiendaid,productoid,encuestaid,fecharegistro,horaregistro from respuesta";
		}
		return respuestas;
		
	}
	
}
