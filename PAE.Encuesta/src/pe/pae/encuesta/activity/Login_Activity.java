package pe.pae.encuesta.activity;

import pe.pae.encuesta.R;
import roboguice.inject.InjectView;

import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login_Activity extends ActivityBase {
    /** Called when the activity is first created. */
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mActionBar.setTitle(R.string.login_activity_title);
    }
    
    public void btnIngresar_onclick(View v){
    	Intent i = new Intent("pae.activity.seleccionarTienda");
    	startActivity(i);
    }
}