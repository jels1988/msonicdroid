package pe.lindley.util;

//import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

public  class  DBHelper extends SQLiteOpenHelper{
	
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
	   /* File dbFile = new File(DB_PATH+DATABASE_NAME);
	    return dbFile.exists();*/
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
		int EDIT_BD = settings.getInt("EDIT_BD",0);
		
		return (EDIT_BD==1);
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
					
					SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
					Editor editor = settings.edit();
					editor.putInt("EDIT_BD", 1);
					editor.commit();
					
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


	
		
}
