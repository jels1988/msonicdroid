package pe.lindley.provider;

import java.io.IOException;

import pe.lindley.util.DBHelper;

import android.content.Context;
import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class DBHelperProvider implements Provider<DBHelper> {
	
	@Inject
    private Context context; 
	
	
	public DBHelper get() {
		// TODO Auto-generated method stub
		DBHelper dbHelper = new DBHelper(context);
		if(!dbHelper.isDataBaseExist()){
			try {
				dbHelper.createDataBase();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e("DBHelperProvider", "createDataBase", e);
			}
		}
		return dbHelper;
	}

}
