package lindley.desarrolloxcliente.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import lindley.desarrolloxcliente.R;

import com.actionbarsherlock.view.Window;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import net.msonic.lib.Decompress;
import net.msonic.lib.Decompress.UnCompressFileListener;
import net.msonic.lib.sherlock.ListActivityBase;

public class DescargarFotos_Activity extends ListActivityBase {

	
	private static final int DESCARGAR_ARCHIVOS=0;
	private static final int UNZIP_ARCHIVOS=1;
	
	
	List<String> lista =null;
	EfficientAdapter adap = null;
	
	@InjectView(R.id.progressBar1) ProgressBar progressBar;
	@InjectView(R.id.txtStatus) TextView txtStatus;
	@InjectResource(R.string.urlFotosDescarga) String urlDescarga;
	@InjectResource(R.string.descargafotos_descargando_activity) String descargafotos_status_activity;
	@InjectResource(R.string.descargafotos_descromprimiendo_activity) String descomprimiendofotos_status_activity;
	
	Handler handler;
	Handler handler2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.inicializarRecursos();
		this.mostrarWaitMessage=false;
		this.validarConexionInternet=true;
		
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.descargarfotos_activity);
		 handler = new Handler();
		 handler2 = new Handler();
		 lista = new ArrayList<String>();
		 
		 adap = new EfficientAdapter(this,lista);
		 setListAdapter(adap);
		 
		 processAsync(DESCARGAR_ARCHIVOS);
		
	}
	
	
	
	public void updateProgress(int downSize, int totsize) {
		
	    double percentage = ((downSize * 100) / totsize) ;
	    int status = (int) Math.floor(percentage);
	    progressBar.setProgress(status);
	    txtStatus.setText(String.format(descargafotos_status_activity, status + " %"));
	    
	    
	}





	@Override
	protected void process(int accion) throws Exception {
		// TODO Auto-generated method stub
		if(DESCARGAR_ARCHIVOS==accion){
			descargarArchivoZip();
		}else if(UNZIP_ARCHIVOS==accion){
			unzipArchivo();
		}
		super.process(accion);
	}








	@Override
	protected void processOk(int accion) {
		// TODO Auto-generated method stub
		if(DESCARGAR_ARCHIVOS==accion){
			processAsync(UNZIP_ARCHIVOS);
		}else if(UNZIP_ARCHIVOS==accion){
			setSupportProgressBarIndeterminateVisibility(false);
		}
		super.processOk(accion);
	}








	@Override
	protected void processError(int accion) {
		// TODO Auto-generated method stub
		super.processError(accion);
	}








	private void unzipArchivo(){

		File SDCardRoot = new File(Environment.getExternalStorageDirectory(),this.getPackageName());
		File file = new File(SDCardRoot,"fotos.zip");
		
		Decompress d = new Decompress(file.getAbsoluteFile().toString(), SDCardRoot.getPath() + "/"); 
		d.unCompressFileListener = new UnCompressFileListener() {
			@Override
			public void onUnCompressFile(final String fileName) {
				// TODO Auto-generated method stub
				handler2.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						//txtStatus.setText(fileName);	
						lista.add(String.format(descomprimiendofotos_status_activity, fileName));
						adap.notifyDataSetChanged();
					}
				});
			}
		}; 
		d.unzip(); 
	}

	 int downloadedSize=0;
	 int totalSize = 0 ;
   private void descargarArchivoZip(){
	   try {
			//set the download URL, a url that points to a file on the internet
			//this is the file to be downloaded
			URL url = new URL(urlDescarga);

			//create the new connection
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			//set up some things on the connection
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);

			//and connect!
			urlConnection.connect();

			//set the path where we want to save the file
			//in this case, going to save it on the root directory of the
			//sd card.
			File SDCardRoot = new File(Environment.getExternalStorageDirectory(),this.getPackageName());
			//create a new file, specifying the path, and the filename
			//which we want to save the file as.
			File file = new File(SDCardRoot,"fotos.zip");

			//this will be used to write the downloaded data into the file we created
			FileOutputStream fileOutput = new FileOutputStream(file);

			//this will be used in reading the data from the internet
			InputStream inputStream = urlConnection.getInputStream();

			//this is the total size of the file
			 totalSize = urlConnection.getContentLength();
			//variable to store total downloaded bytes
			  downloadedSize=0;
					

			//create a buffer...
			byte[] buffer = new byte[1024];
			int bufferLength = 0; //used to store a temporary size of the buffer

			//now, read through the input buffer and write the contents to the file
			while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
				//add the data in the buffer to the file in the file output stream (the file on the sd card
			
				fileOutput.write(buffer, 0, bufferLength);
				//add up the size so we know how much is downloaded
				downloadedSize += bufferLength;
				handler.post(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
					

					
					            updateProgress(downloadedSize, totalSize);
					
					}
				});
			}
			//close the output stream when done
			fileOutput.close();

		//catch some possible errors...
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
   }
	
   
   public static class EfficientAdapter extends ArrayAdapter<String>{
		 private final List<String> detalle;
		 private final DescargarFotos_Activity context;
		 
		 public EfficientAdapter(DescargarFotos_Activity context,List<String> detalle){
				super(context, R.layout.descargarfotos_content, detalle);
				this.detalle = detalle;
				this.context = context;
			}
		 
		 
		 
		 public int getCount() {
				// TODO Auto-generated method stub
				if (detalle == null) {
					return 0;
				} else {
					return detalle.size();
				}
			}
			
			public String getItem(int position) {
				// TODO Auto-generated method stub
				return this.detalle.get(position);
			}
			
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			
			public View getView(final int position, View convertView, ViewGroup parent) {
				View view = null;	
				
				if (convertView == null) {
					
					LayoutInflater inflator = context.getLayoutInflater();
					view = inflator.inflate(R.layout.descargarfotos_content, null);
					
						final ViewHolder holder = new ViewHolder();
					
					holder.txtFile = (TextView) view.findViewById(R.id.txtFile);
					
					view.setTag(holder);
					
					
			    	holder.txtFile.setTag(this.detalle.get(position));
				}else{
					view = convertView;
					((ViewHolder) view.getTag()).txtFile.setTag(this.detalle.get(position));
				}
				
				ViewHolder holder = (ViewHolder) view.getTag();
				String file = getItem(position);
				holder.txtFile.setText(file);
				return view;
			}
   }
	
   
   static class ViewHolder {
		TextView txtFile;
	}
	
	
	
	
	
}
