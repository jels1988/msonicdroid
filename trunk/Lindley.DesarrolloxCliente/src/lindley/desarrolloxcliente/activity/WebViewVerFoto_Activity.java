package lindley.desarrolloxcliente.activity;

import lindley.desarrolloxcliente.R;
import net.msonic.lib.ActivityBase;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thira.examples.actionbar.widget.ActionBar;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

public class WebViewVerFoto_Activity extends ActivityBase {

	private static final String DESARROLLO_X_CLIENTE = "/DesarrolloxCliente/";
	public static final String NOMBRE_FOTO = "nomFoto";
	@InjectView(R.id.wvwFoto) 	WebView   wvwFoto;
	@InjectView(R.id.actionBar) ActionBar mActionBar;
	@InjectExtra(NOMBRE_FOTO) String fotoNombre;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webviewfoto_activity);
		
		 mActionBar.setTitle(R.string.ver_foto_activity_title);
		 mActionBar.setHomeLogo(R.drawable.header_logo);
		
		WebSettings webSettings = wvwFoto.getSettings();
		webSettings.setJavaScriptEnabled(true);
			    
		wvwFoto.setWebViewClient(new WebViewClient(){

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
		

		fotoNombre = fotoNombre.substring(0, (fotoNombre.length()-4));
		System.out.println(fotoNombre);
		wvwFoto.loadUrl("http://java.cdaandino.com/rtm/LanzadorApp/ImageViewService.svc"+ DESARROLLO_X_CLIENTE + fotoNombre);
		//wvwFoto.loadUrl("http://java.cdaandino.com/rtm/LanzadorApp/ImageViewService.svc"+ DESARROLLO_X_CLIENTE + "12_20599_1332711988984");
	}
}
