package lindley.desarrolloxcliente.activity;

import roboguice.inject.InjectView;

import com.thira.examples.actionbar.widget.ActionBar;

import net.msonic.lib.ActivityBase;
import lindley.desarrolloxcliente.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login_Activity extends ActivityBase {
	
	@InjectView(R.id.actionBar)  	ActionBar 	mActionBar;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        
        mActionBar.setTitle(R.string.login_activity_title);
        mActionBar.setHomeLogo(R.drawable.header_logo);
    }
    
    public void btnLogin_click(View view){
    	Intent intent = new Intent("lindley.desarrolloxcliente.consultarcliente");
    	startActivity(intent);
    }
}