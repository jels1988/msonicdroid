package pe.lindley.om.dao;

import java.util.ArrayList;

import pe.lindley.om.to.ClienteTO;
import pe.lindley.util.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;

public class ClienteDAO {

	@Inject
	protected DBHelper dbHelper;

	public void deleteAll() {
		dbHelper.getDataBase().execSQL("delete from om_cliente");
	}
	
	public void save(final ClienteTO clienteTO){
		
		ContentValues parametros = new ContentValues();
		
		parametros.put("CODIGO", clienteTO.getCodigo());
		parametros.put("RAZONSOCIAL", clienteTO.getRazonSocial());
		parametros.put("DIRECCION", clienteTO.getDireccion());
		parametros.put("RTAVENTA", clienteTO.getRutaVentas());
		parametros.put("RTAMERCA", clienteTO.getRutaMecaderista());
		parametros.put("RTADESA", clienteTO.getRutaDesarrollador());
		parametros.put("RTAMAEST", clienteTO.getRutaTecnicoMan());
		parametros.put("CANALRTM", clienteTO.getCanal());
		parametros.put("SUBCANALRTM", clienteTO.getSubcanal());
		parametros.put("TAMANO", clienteTO.getTamanio());
		parametros.put("POTENCIAL", clienteTO.getPotencial());
		parametros.put("MODOSERVICIO", clienteTO.getModoservicio());

		dbHelper.getDataBase().insertOrThrow("om_cliente", null, parametros);
		
	}
	
	public ClienteTO list(String codigo){
		String SQL = "SELECT * FROM om_cliente WHERE codigo = ?";
		String[] parametros = new String[] { codigo };
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		ClienteTO clienteTO = null;
		
		if (cursor.moveToNext()) {
			clienteTO = cursor2Cliente(cursor);
		}
		
		cursor.close();
		
		return clienteTO;
	}
	
	public ArrayList<ClienteTO> list(String codigo,String razonSocial){
		String SQL = "SELECT * FROM om_cliente WHERE codigo LIKE ? and razonsocial LIKE ?";
		
		if(razonSocial==null) razonSocial="";
		if(codigo==null) codigo="";
		
		String[] parametros = new String[] { 
				"%" +  codigo + "%",
				"%" +  razonSocial + "%"};
		
		Cursor cursor = dbHelper.getDataBase().rawQuery(SQL, parametros);
		
		ArrayList<ClienteTO> listado = new ArrayList<ClienteTO>();
		
		while (cursor.moveToNext()) {
			
			listado.add(cursor2Cliente(cursor));
		}
		
		cursor.close();
		
		return listado;
	}
	
	private ClienteTO cursor2Cliente(Cursor cursor){
		ClienteTO clienteTO = new ClienteTO();
		clienteTO.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
		clienteTO.setRazonSocial(cursor.getString(cursor.getColumnIndex("razonsocial")));
		clienteTO.setCanal(cursor.getString(cursor.getColumnIndex("canalrtm")));
		clienteTO.setSubcanal(cursor.getString(cursor.getColumnIndex("subcanalrtm")));
		clienteTO.setTamanio(cursor.getString(cursor.getColumnIndex("tamano")));
		clienteTO.setPotencial(cursor.getString(cursor.getColumnIndex("potencial")));
		clienteTO.setModoservicio(cursor.getString(cursor.getColumnIndex("modoservicio")));
		clienteTO.setDireccion(cursor.getString(cursor.getColumnIndex("direccion")));
		clienteTO.setRutaVentas(cursor.getString(cursor.getColumnIndex("rtaventa")));
		clienteTO.setRutaDesarrollador(cursor.getString(cursor.getColumnIndex("rtadesa")));
		clienteTO.setRutaMecaderista(cursor.getString(cursor.getColumnIndex("rtamerca")));
		clienteTO.setRutaTecnicoMan(cursor.getString(cursor.getColumnIndex("rtamaest")));
		return clienteTO;
	}
	
}
