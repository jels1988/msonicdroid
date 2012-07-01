package lindley.desarrolloxcliente.negocio;

import java.util.ArrayList;

import lindley.desarrolloxcliente.dao.FotoDAO;
import lindley.desarrolloxcliente.to.FileTO;
import android.util.Log;

import com.google.inject.Inject;

import net.msonic.lib.DBHelper;


public class FotoBLL {

	private static final String TAG_LOG = FotoBLL.class.getSimpleName();
	@Inject protected DBHelper dbHelper;
	@Inject protected FotoDAO fotoDAO;
	
	public void deleteAll(){
		try {
			dbHelper.openDataBase();
			fotoDAO.deleteAll();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "deleteAll", e);
		} finally {
			dbHelper.close();
		}
	}
	
	public void delete(int idFoto){
		try {
			dbHelper.openDataBase();
			fotoDAO.delete(idFoto);
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "deleteAll", e);
		} finally {
			dbHelper.close();
		}
	}
	
	public void save(String fileName){	
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			fotoDAO.save(fileName);
			
			dbHelper.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "save", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	public void save(ArrayList<String> nombres){	
		try {
			dbHelper.openDataBase();
			dbHelper.beginTransaction();
			
			for (String nombreFoto : nombres) {
				fotoDAO.save(nombreFoto);
			}
			
			dbHelper.setTransactionSuccessful();
			
		} catch (Exception e) {
			Log.e(TAG_LOG, "save", e);
		} finally {
			dbHelper.endTransaction();
			dbHelper.close();
		}
	}
	
	public ArrayList<FileTO> Listar(){
		
		ArrayList<FileTO> detalles=null;
		
		try{
			dbHelper.openDataBase();
			detalles = fotoDAO.listar();
		}catch(Exception e){
			Log.e(TAG_LOG, "Listar", e);
		}finally{
			dbHelper.close();
		}
		
		
		
		return detalles;
	}
}
