package pe.lindley.mmil.activity;

import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import pe.lindley.activity.R;
import pe.lindley.util.ActivityBase;
import roboguice.inject.InjectView;

public class MMILPaisActivity extends ActivityBase {
	
	@InjectView(R.id.wvwPaises) WebView webView;
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mmilpais_activity);
		
		 mActionBar.setTitle(R.string.mmil_regiones);
		 mActionBar.setHomeLogo(R.drawable.header_logo);
		
		 

			    WebSettings webSettings = webView.getSettings();
			    webSettings.setJavaScriptEnabled(true);
			    
			    
			   
			    webView.setWebViewClient(new WebViewClient(){

					@Override
					public void onPageFinished(WebView view, String url) {
						// TODO Auto-generated method stub
						super.onPageFinished(view, url);
						ocultarEspera();
					}

					@Override
					public void onPageStarted(WebView view, String url,
							Bitmap favicon) {
						// TODO Auto-generated method stub
						super.onPageStarted(view, url, favicon);
						mostrarEspera();
					}
			    	
					
					@Override
					public void onReceivedError(WebView view, int errorCode,
							String description, String failingUrl) {
						// TODO Auto-generated method stub
						super.onReceivedError(view, errorCode, description, failingUrl);
						ocultarEspera();
					}

					@Override
					public boolean shouldOverrideUrlLoading(WebView view,
							String url) {
						
				        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
				        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				        startActivity(intent);
				        return true;

					}
					
					
			    })  ;  
			    
		webView.loadUrl("http://java.cdaandino.com/rtm/MMILAndroid/Android/PoligonaPaisAndroid.aspx");
	}

}

