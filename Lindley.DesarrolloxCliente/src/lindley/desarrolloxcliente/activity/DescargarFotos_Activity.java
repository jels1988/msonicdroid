package lindley.desarrolloxcliente.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.os.Environment;
import net.msonic.lib.Decompress;
import net.msonic.lib.sherlock.ActivityBase;

public class DescargarFotos_Activity extends ActivityBase {

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.inicializarRecursos();
		this.mostrarWaitMessage=false;
		
		processAsync();
	}
	
	
	
	
	
	@Override
	protected void processOk() {
		// TODO Auto-generated method stub
		
		File SDCardRoot = new File(Environment.getExternalStorageDirectory(),this.getPackageName());
		File file = new File(SDCardRoot,"somefile.zip");
		
		
		 
		Decompress d = new Decompress(file.getAbsoluteFile().toString(), SDCardRoot.getAbsolutePath().toString()); 
		d.unzip(); 
		
		super.processOk();
	}





	@Override
	protected void process() throws Exception {
		// TODO Auto-generated method stub
		try {
			//set the download URL, a url that points to a file on the internet
			//this is the file to be downloaded
			URL url = new URL("http://java.cdaandino.com/rtm/DesarrolloxCliente2/data.zip");

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
			File file = new File(SDCardRoot,"somefile.zip");

			//this will be used to write the downloaded data into the file we created
			FileOutputStream fileOutput = new FileOutputStream(file);

			//this will be used in reading the data from the internet
			InputStream inputStream = urlConnection.getInputStream();

			//this is the total size of the file
			int totalSize = urlConnection.getContentLength();
			//variable to store total downloaded bytes
			int downloadedSize = 0;

			//create a buffer...
			byte[] buffer = new byte[1024];
			int bufferLength = 0; //used to store a temporary size of the buffer

			//now, read through the input buffer and write the contents to the file
			while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
				//add the data in the buffer to the file in the file output stream (the file on the sd card
				fileOutput.write(buffer, 0, bufferLength);
				//add up the size so we know how much is downloaded
				downloadedSize += bufferLength;
				//this is where you would do something to report the prgress, like this maybe
				//updateProgress(downloadedSize, totalSize);

			}
			//close the output stream when done
			fileOutput.close();

		//catch some possible errors...
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// see http://androidsnippets.com/download-an-http-file-to-sdcard-with-progress-notification
		
		
		
		super.process();
	}

	
	
	
	
	
	
	
	
}
