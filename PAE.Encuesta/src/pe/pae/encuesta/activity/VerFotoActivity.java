package pe.pae.encuesta.activity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import pe.pae.encuesta.R;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;

public class VerFotoActivity extends ActivityBase {

	
	
	public static final String PREGUNTA="PREGUNTA";
	public static final String FILE_NAME="FILE_NAME";
	
	
	@InjectExtra(PREGUNTA) String pregunta;
	@InjectView(R.id.actionBar)ActionBar mActionBar;
	@InjectExtra(FILE_NAME) String fileName;
	
	@InjectView(R.id.imgFoto) ImageView imgVer;
	private Bitmap bitmap ;
	private Bitmap bitmapTemp1 ;
	private Bitmap bitmapTemp2 ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		inicializarRecursos();
		
		setContentView(R.layout.ver_foto_activity);
		
		mActionBar.setTitle(R.string.ver_foto_title);
		mActionBar.setSubTitle(pregunta);
		
		processAsync();
		
		
	}

	
	
	 @Override
	protected void process() {
		 
		// TODO Auto-generated method stub
		 File path = new File( Environment.getExternalStorageDirectory(), this.getPackageName() );
		
		FileInputStream is = null;
		BufferedInputStream bis = null;
		    
		    try {
		        is = new FileInputStream(new File(path, fileName));
		        bis = new BufferedInputStream(is);
		        
		        
		        BitmapFactory.Options bfo = new BitmapFactory.Options();
		        bfo.inScaled = true;
		        bfo.inDensity = DisplayMetrics.DENSITY_MEDIUM;
		        Rect padding = new Rect();
		        bfo.inTargetDensity = imgVer.getResources().getDisplayMetrics().densityDpi;
		        
		        bitmapTemp1 = BitmapFactory.decodeStream(bis,padding,bfo);
		        
		        /*
		        int density = bitmapTemp1.getDensity();
		        bitmapTemp1.getScaledHeight(DisplayMetrics.)
		        int originalWidth = (int)(bitmapTemp1.getScaledWidth(density) / density);
		        int originalHeight = (int)(bitmapTemp1.getScaledHeight(density)  / density);
		        */
		        
		      
		        
		        int width = bitmapTemp1.getWidth();
		        int height = bitmapTemp1.getHeight();
		        int newWidth = 320;//imgVer.getWidth();
		        int newHeight = 411;//imgVer.getHeight();

		        Log.d("width", String.valueOf(width));
		        Log.d("height", String.valueOf(height));
		        Log.d("newWidth", String.valueOf(newWidth));
		        Log.d("newHeight", String.valueOf(newHeight));
		        
		        
		        // calculate the scale - in this case = 0.4f
		        float scaleWidth = ((float) newWidth) / width;
		        float scaleHeight = ((float) newHeight) / height;
		        
		        //createa matrix for the manipulation
		        Matrix matrix = new Matrix();
		        // resize the bit map
		        matrix.postScale(scaleWidth, scaleHeight);
		        // rotate the Bitmap
		        matrix.postRotate(90);
		        
		        
		     // recreate the new Bitmap
		        bitmapTemp2 = Bitmap.createBitmap(bitmapTemp1, 0, 0,
		                          width, height, matrix, true);
		        bitmap = Bitmap.createScaledBitmap(bitmapTemp2, newWidth, newHeight, true);
               
                
		        //bitmap = Bitmap.createScaledBitmap(bitmapTemp,imgVer.getWidth(), imgVer.getHeight(),  true);
		        //imgVer.setImageBitmap(bitmap);    
		        } 
		    catch (Exception e) {
		        //Try to recover
		    	Log.e("VerFotoActivity", "Error Cargando Foto", e);
		    }
		    finally {
		        try {
		            if (bis != null) {
		                bis.close();
		            }
		            if (is != null) {
		                is.close();
		            }    
		        } catch (Exception e) {
		        	Log.e("VerFotoActivity", "Error Cargando Foto", e);
		        }
		    }  
		    
		super.process();
	}


	 @Override
		protected void processOk() {
			imgVer.setImageBitmap(bitmap);
			super.processOk();
		}

}
