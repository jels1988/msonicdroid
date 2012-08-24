package net.msonic.lib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public  class  DBHelper extends SQLiteOpenHelper{
	
	private static final String TAG = DBHelper.class.getSimpleName();
	private static final String DATABASE_NAME = "prospector.db";
	private static final int DATABASE_VERSION = 1;
	private String DB_PATH =null;
	private Context mContext;
	
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
		DB_PATH =  "/data/data/"+ mContext.getPackageName()+"/databases/";

	}
	
	public boolean isDataBaseExist() {
	    File dbFile = new File(DB_PATH+DATABASE_NAME);
	    return dbFile.exists();
	}
	
	private void copyDataBase() throws IOException {

	    // Open your local db as the input stream

	    InputStream myInput = mContext.getAssets().open("databases/"+DATABASE_NAME);


	    // Path to the just created empty db

	    String outFileName = DB_PATH + DATABASE_NAME;

	    OutputStream myOutput = new FileOutputStream(outFileName);


	    // transfer bytes from the inputfile to the outputfile

	    byte[] buffer = new byte[1024];

	    int length;

	    while ((length = myInput.read(buffer)) > 0) {

	        myOutput.write(buffer, 0, length);

	    }


	    // Close the streams

	    myOutput.flush();

	    myOutput.close();

	    myInput.close();

	}

	public void createDataBase() throws IOException{
			boolean isExisteDb=isDataBaseExist();
			if(!isExisteDb){
				this.getReadableDatabase();
				try {
					copyDataBase();
				} catch (Exception e) {
					throw new Error("Error copying database");
				}
			}
	}
	
	private SQLiteDatabase dataBase;
	
	public SQLiteDatabase getDataBase() {
		return dataBase;
	}



	public void openDataBase() throws SQLException{
    	//Open the database
        String myPath = DB_PATH+DATABASE_NAME;
    	dataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
 
    }
	
	public void beginTransaction(){
		dataBase.beginTransaction();
	}
	
	public void setTransactionSuccessful(){
		dataBase.setTransactionSuccessful();
	}
	
	public void endTransaction(){
		dataBase.endTransaction();
	}
	
	
    @Override
	public synchronized void close() {
 
    	    if(dataBase != null)
    		    dataBase.close();
 
    	    super.close();
 
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	
	public Cursor rawQuery(String SQL,String[] selectionArgs){
		
		Log.d(TAG, String.format("SQL: %s",SQL));
		
		if(selectionArgs!=null){
			int index=0;
			for (String parametro : selectionArgs) {
				Log.d(TAG, String.format("PARA[%s]: %s",index++,parametro));
			}
		}
		
		Cursor cursor = getDataBase().rawQuery(SQL,selectionArgs);
		
		Log.d(TAG, String.format("ROWCOUNT: %s",cursor.getCount()));
		
		return cursor;
	}
	
	
	private void logContentValues(ContentValues values){
		Log.d(TAG, "PARAMETROS");
		if(values!=null){
			Set<Entry<String, Object>> listaValues=values.valueSet();
			Iterator<Map.Entry<String, Object>> itr = listaValues.iterator();
			
			
			
			while (itr.hasNext()) {
				
				Map.Entry<String, Object> me = itr.next(); 
		        String key = me.getKey().toString();
		        Object value =  me.getValue();

		        Log.d(TAG, String.format("[%s]=: '%s'",key, (String)(value == null?null:value.toString())));
		        
			}
		}else{
			Log.d(TAG, "SIN PARAMETROS");
		}
	}
	public long insertOrThrow(String table,ContentValues values){
		Log.d(TAG, String.format("TABLE: %s",table));
		logContentValues(values);
		
		long id = this.getDataBase().insertOrThrow(table, null, values);
		Log.d(TAG, String.format("ID: %s",id));
		return id;
	}
		
}
