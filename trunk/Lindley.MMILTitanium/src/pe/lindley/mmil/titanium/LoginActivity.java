package pe.lindley.mmil.titanium;

import net.msonic.lib.ActivityBase;
import roboguice.inject.InjectView;

import com.thira.examples.actionbar.widget.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends ActivityBase {
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarRecursos();
        
        setContentView(R.layout.login_activity);
        
        mActionBar.setHomeLogo(R.drawable.header_logo);
        mActionBar.setTitle(R.string.login_activity_title);
    }
    
    public void btnLogin_click(View v){
    	Intent intent = new Intent(this, TableroActivity.class);
    	startActivity(intent);
    }
}