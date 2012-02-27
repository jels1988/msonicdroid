package pe.lindley.exmedia.activity;



import com.thira.examples.actionbar.widget.ActionBar;

import pe.lindley.activity.R;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class ViewVideoActivity extends ActivityBase implements MediaPlayer.OnPreparedListener  {
	
	public static String URL_ARCHIVO = "url_archivo";
	public static String NAME_ARCHIVO = "name_archivo";
	@InjectView(R.id.actionBar) ActionBar mActionBar;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.viewvideo_activity);
        
		mActionBar.setTitle(R.string.listar_files_multimedia_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);

		
        VideoView videoView = (VideoView)this.findViewById(R.id.videoView1);
        MediaController mc = new MediaController(this);
        
        videoView.setMediaController(mc);
        
        
        Intent intent = getIntent();
        String urlText = intent.getStringExtra(URL_ARCHIVO);
        //String fileName = intent.getStringExtra(NAME_ARCHIVO);
        /*
        try
        {

            URL url = new URL(urlText);
            InputStream inputStream = url.openStream();
            long bytesRead = 0; 

                File root = android.os.Environment.getExternalStorageDirectory();               
                File dir = new File (root.getAbsolutePath() + "/usbStorage");
                if(dir.exists()== false) 
                {
                    dir.mkdirs();
                }

            File outputSource = new File(dir, fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(outputSource);
            //Log.d(LOG_TAG, "FileOutputStream: " + outputSource);
            int c;
            int howManyBytes = 12000; 
        //You decide the amount of bytes to read because the live stream while keep on going forver
            while ((c = inputStream.read()) != 10000) 
            {

                fileOutputStream.write(c);
                bytesRead++;
                if (bytesRead > howManyBytes) break;
            }
            fileOutputStream.close();
            */
            videoView.setVideoURI(Uri.parse(urlText));
            //videoView.setVideoPath(outputSource.getAbsolutePath());
            videoView.requestFocus();
            videoView.start();
       
    }
	

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		 mp.start();
		
	}
}
