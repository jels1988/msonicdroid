package lindley.desarrolloxcliente.dao;

import lindley.desarrolloxcliente.to.EvaluacionTO;
import net.msonic.lib.DBHelper;

import android.content.ContentValues;

import com.google.inject.Inject;

public class EvaluacionDAO {

	@Inject	protected DBHelper dbHelper;
	
	
	public long insertEvaluacion(EvaluacionTO evaluacionTO){
		
		ContentValues values = new ContentValues();
		values.put("clienteCodigo", evaluacionTO.codigoCliente);
		values.put("activosLindley", evaluacionTO.activosLindley);
		values.put("codigoFe", evaluacionTO.codigoFe);
		values.put("usuario", evaluacionTO.codigoCliente);
		
		long id= dbHelper.insertOrThrow("evaluacion", values);
		
		return id;
	}
}