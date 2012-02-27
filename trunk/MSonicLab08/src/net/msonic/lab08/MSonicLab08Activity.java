package net.msonic.lab08;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;

public class MSonicLab08Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
      
       
        //Pruebas de clientes
    }
    
    
    
    private static final int TAKE_PHOTO_CODE = 1;
    private static  String file_name="";
    
    public void btn_click(View v){
    	  takePhoto();
    }
    
    private void dispatchTakePictureIntent(int actionCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, actionCode);
    }
    
    private void takePhoto(){
    	file_name = String.format("%d.jpg", System.currentTimeMillis());
    final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(this)) ); 
    intent.putExtra(MediaStore.EXTRA_MEDIA_TITLE, "TITULO");
    startActivityForResult(intent, TAKE_PHOTO_CODE);
    }

    private File getTempFile(Context context){
    
   final File path = new File( Environment.getExternalStorageDirectory(), context.getPackageName() );
   
    if(!path.exists()){
    	path.mkdir();
    }
    
    
    return new File(path, file_name); 
    }

    
    

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == RESULT_OK) {
    	final File file = getTempFile(this);
    	
    		switch(requestCode){
    			case TAKE_PHOTO_CODE:{
    				break;
    			}
    					//
				/*try {
				    Bitmap captureBmp = Media.getBitmap(getContentResolver(), Uri.fromFile(file) );
				    } 
				catch (FileNotFoundException e) {
				     e.printStackTrace();
				     } 
				catch (IOException e) {
				     e.printStackTrace();
				    }*/
    			default:{
    				file.delete();
    			}
    		}
    }
			
    
    
    }
    
}
   