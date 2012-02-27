package pe.lindley.exmedia.activity;

import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import pe.lindley.activity.R;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectView;

public class ViewImageActivity extends ActivityBase {

	public static String URL_ARCHIVO = "url_archivo";
	
	@InjectView(R.id.actionBar) ActionBar mActionBar;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        String url = intent.getStringExtra(URL_ARCHIVO);
        
        setContentView(R.layout.viewimage_activity);
        
		mActionBar.setTitle(R.string.listar_files_multimedia_activity_title);
		mActionBar.setHomeLogo(R.drawable.header_logo);

		
        WebView engine = (WebView) findViewById(R.id.web_engine);
        engine.loadUrl(url);
        
	}
}
