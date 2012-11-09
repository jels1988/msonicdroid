package lindley.desarrolloxcliente.dao;

import java.util.ArrayList;
import java.util.List;

import lindley.desarrolloxcliente.to.upload.AccionTradeTO;

import net.msonic.lib.DBHelper;

import android.database.Cursor;

import com.google.inject.Inject;

public class AccionTradeDAO {

	@Inject	protected DBHelper dbHelper;
	
	public List<AccionTradeTO> listarByProducto(String codigo){

		String SQL = "select ap.accionId,a.accion from accion_trade a inner join accion_trade_producto ap on a.accionId = ap.accionId " +
					 " where codigoProducto = ?1 and a.estado='A'";
		
		String[] args = new String[] {codigo};
		Cursor cursor = dbHelper.rawQuery(SQL,args);
		
		AccionTradeTO accionTradeTO;
		List<AccionTradeTO> acciones = new ArrayList<AccionTradeTO>();
		
		while(cursor.moveToNext()){
			accionTradeTO = new AccionTradeTO();
			int id = cursor.getInt(cursor.getColumnIndex("accionId"));
			accionTradeTO.codigo=String.valueOf(id);
			accionTradeTO.descripcion=cursor.getString(cursor.getColumnIndex("accion"));
			acciones.add(accionTradeTO);
		}
		
		cursor.close();
		
		return acciones;
	}
}
